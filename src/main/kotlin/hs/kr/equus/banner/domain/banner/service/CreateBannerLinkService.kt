package hs.kr.equus.banner.domain.banner.service

import hs.kr.equus.banner.domain.banner.domain.BannerLink
import hs.kr.equus.banner.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.banner.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateBannerLinkService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {
    @Transactional
    fun execute(file: MultipartFile): String {
        val bannerLink = s3Utils.upload(file)
        bannerLinkRepository.save(BannerLink(fileName = bannerLink))
        return bannerLink
    }
}
