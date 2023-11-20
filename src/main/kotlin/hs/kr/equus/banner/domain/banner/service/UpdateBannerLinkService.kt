package hs.kr.equus.banner.domain.banner.service

import hs.kr.equus.banner.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.banner.domain.banner.exception.BannerNotFoundException
import hs.kr.equus.banner.global.utils.S3Utils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class UpdateBannerLinkService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {
    @Transactional
    fun execute(file: MultipartFile, id: Long): String {
        val bannerLink = bannerLinkRepository.findById(id).orElseThrow {BannerNotFoundException}
        bannerLink.update(file.name)
        return s3Utils.upload(file)
    }
}
