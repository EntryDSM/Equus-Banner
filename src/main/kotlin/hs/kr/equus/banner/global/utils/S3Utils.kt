package hs.kr.equus.banner.global.utils

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.*
import com.amazonaws.util.IOUtils
import hs.kr.equus.banner.global.exception.BadFileExtensionException
import hs.kr.equus.banner.global.exception.FileIsEmptyException
import hs.kr.equus.banner.global.exception.ImageNotFoundException
import org.imgscalr.Scalr
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.http.MediaType;
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.*
import javax.imageio.ImageIO


@Component
class S3Utils(
    private val amazonS3Client: AmazonS3Client,

    @Value("\${aws.s3.bucket}")
    private val bucketName : String,

    @Value("\${{aws.s3.base-image-url}")
    private val baseImageUrl : String
) {
    companion object{
        private val EXP_TIME : Int = 1000 * 60 * 2;
    }

    fun upload(file: MultipartFile?, path: String): String {
        val ext = verificationFile(file)
        val randomName = UUID.randomUUID().toString()
        val filename = "$randomName.$ext"
        val outputImage: BufferedImage
        val os = ByteArrayOutputStream()
        outputImage = makeThumbnail(file!!)
        val inStream: InputStream = ByteArrayOutputStream().run { // apply를 사용하려면 변수 타입을 변경해야함
            try {
                ImageIO.write(outputImage, "png", this)
            } catch (e: IOException) {
                throw ImageNotFoundException
            }

            ByteArrayInputStream(toByteArray())
        }
        val metadata = ObjectMetadata()
            .apply {
                contentType = MediaType.IMAGE_PNG_VALUE
                contentLength = os.size().toLong()
                contentDisposition = "inline"
            }
        amazonS3Client.putObject(
            PutObjectRequest(bucketName, path + filename, inStream, metadata)
                .withCannedAcl(CannedAccessControlList.AuthenticatedRead)
        )
        return filename
    }


    fun generateObjectUrl(fileName : String): String {
        val expiration = Date().apply {
            time = time + EXP_TIME.toLong()
        }
        val url: URL = amazonS3Client.generatePresignedUrl(
            GeneratePresignedUrlRequest(baseImageUrl, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration)
        )

        return url.toString()
    }

    fun getObject(fileName: String, path: String): ByteArray {
        try {
            val s3Object: S3Object = amazonS3Client.getObject(bucketName, "$path$fileName")
            return IOUtils.toByteArray(s3Object.objectContent)
        } catch (e: RuntimeException) {
            throw ImageNotFoundException
        }
    }

    fun delete(objectName : String, path : String) {
        amazonS3Client.deleteObject(bucketName, objectName + path)
    }

   private fun makeThumbnail(file: MultipartFile): BufferedImage {
        val srcImg : BufferedImage

        srcImg = try {
            ImageIO.read(file.inputStream)
        } catch (e: IOException) {
            throw FileIsEmptyException
        }

        val dw = 300
        val dh = 400

        val ow = srcImg.width
        val oh = srcImg.height

        var nw = ow
        var nh = ow * dh / dw

        if (nh > oh) {
            nw = oh * dw / dh
            nh = oh
        }

        val cropImg: BufferedImage = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh)

        return Scalr.resize(cropImg, dw, dh)
    }

    private fun makePdfThumbnail(file : MultipartFile): BufferedImage {
        val srcImg : BufferedImage

        try {
            srcImg = ImageIO.read(file.inputStream)
        } catch (e : IOException) {
            throw FileIsEmptyException
        }
        return srcImg
    }


    private fun verificationFile(file : MultipartFile?): String {
        if(file == null || file.isEmpty || file.originalFilename == null) throw FileIsEmptyException

        val originalFilename = file.originalFilename!!
        val ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1)
        val lowerExt = ext.lowercase(Locale.getDefault())

        if (!(lowerExt == "jpg" || lowerExt == "jpeg" || lowerExt == "png" || lowerExt == "heic")) throw BadFileExtensionException

        return ext
    }
}