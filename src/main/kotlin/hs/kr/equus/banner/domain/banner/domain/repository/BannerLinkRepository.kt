package hs.kr.equus.banner.domain.banner.domain.repository

import hs.kr.equus.banner.domain.banner.domain.BannerLink
import org.springframework.data.repository.CrudRepository

interface BannerLinkRepository : CrudRepository<BannerLink, Long>
