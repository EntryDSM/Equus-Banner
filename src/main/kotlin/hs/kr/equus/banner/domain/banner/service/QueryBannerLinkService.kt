package hs.kr.equus.banner.domain.banner.service

import hs.kr.equus.banner.domain.banner.domain.BannerLink
import hs.kr.equus.banner.domain.banner.domain.repository.BannerLinkRepository
import hs.kr.equus.banner.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryBannerLinkService(
    private val bannerLinkRepository: BannerLinkRepository,
    private val s3Utils: S3Utils
) {

}