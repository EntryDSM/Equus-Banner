package hs.kr.equus.banner.domain.banner.domain.repository

import hs.kr.equus.banner.domain.banner.domain.BannerLink
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface BannerLinkRepository : CrudRepository<BannerLink, UUID> {
}