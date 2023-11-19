package hs.kr.equus.banner.domain.banner.service

import hs.kr.equus.banner.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.banner.domain.banner.presentation.dto.response.QueryBannerResponse
import hs.kr.equus.banner.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryBannerService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {
    @Transactional(readOnly = true)
    fun execute(): List<QueryBannerResponse> {
        val banner = bannerLinkRepository.findAll()
        return banner.map {
            bannerLink -> QueryBannerResponse(s3Utils.generateObjectUrl(bannerLink.fileName))
            }
        }
}