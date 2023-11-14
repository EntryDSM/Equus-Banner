package hs.kr.equus.banner.domain.banner.service

import hs.kr.equus.banner.domain.banner.domain.Banner
import hs.kr.equus.banner.domain.banner.domain.repository.BannerRepository
import hs.kr.equus.banner.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateBannerService (
    private val bannerRepository: BannerRepository,
    private val s3Utils: S3Utils
) {
    @Transactional
    fun execute(file : MultipartFile): String {
        bannerRepository.save(Banner(link = file.name))
        return s3Utils.generateObjectUrl(file.name)
    }
}
