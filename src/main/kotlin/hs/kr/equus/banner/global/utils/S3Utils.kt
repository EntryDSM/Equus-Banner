package hs.kr.equus.banner.global.utils

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.*
import hs.kr.equus.banner.global.exception.BadFileExtensionException
import hs.kr.equus.banner.global.exception.EmptyFileException
import hs.kr.equus.banner.global.exception.ImageNotFoundException
import org.imgscalr.Scalr
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
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
    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucketName: String,
    private val amazonS3: AmazonS3
) {
    companion object {
        private const val EXP_TIME = 1000 * 60 * 2
    }

    fun upload(file: MultipartFile, path: String): String {
        val ext = verificationFile(file)

        val randomName = UUID.randomUUID().toString()
        val filename = "$randomName.$ext"
        val outputImage = makeThumbnail(file)
        val os = ByteArrayOutputStream()

        try {
            ImageIO.write(outputImage, "png", os)
        } catch (e: IOException) {
            throw ImageNotFoundException
        }

        val inputStream : InputStream = ByteArrayInputStream(os.toByteArray())

        val metadata = ObjectMetadata().apply {
            contentType = MediaType.IMAGE_PNG_VALUE
            contentLength = os.size().toLong()
            contentDisposition = "inline"
        }

        amazonS3.putObject(
            PutObjectRequest(bucketName, "$path$filename", inputStream, metadata)
            .withCannedAcl(CannedAccessControlList.AuthenticatedRead)
        )

        return filename
    }

    fun generateObjectUrl(fileName: String): String {
        val expiration = Date().apply {
            time = time + EXP_TIME.toLong()
        }
        val url: URL = amazonS3.generatePresignedUrl(
            GeneratePresignedUrlRequest(bucketName, fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration)
        )
        return url.toString()
    }


    fun delete(objectName: String, path: String) {
        amazonS3.deleteObject(bucketName, "$path$objectName")
    }

    private fun makeThumbnail(file: MultipartFile): BufferedImage {
        val srcImg: BufferedImage
        try {
            srcImg = ImageIO.read(file.inputStream)
        } catch (e: IOException) {
            throw EmptyFileException
        }

        val dw = 300
        val dh = 400

        val ow = srcImg.width
        val oh = srcImg.height

        var nw = ow
        var nh = (ow * dh) / dw

        if (nh > oh) {
            nw = (oh * dw) / dh
            nh = oh
        }

        val cropImg = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh)

        return Scalr.resize(cropImg, dw, dh)
    }

    private fun verificationFile(file: MultipartFile): String {
        if (file.isEmpty || file.originalFilename == null) throw EmptyFileException
        val originalFilename = file.originalFilename!!
        val ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).lowercase(Locale.getDefault())

        if (!(ext == "jpg" || ext == "jpeg" || ext == "png" || ext == "heic")) throw BadFileExtensionException
        return ext
    }
}
