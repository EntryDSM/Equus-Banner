package hs.kr.equus.banner.domain.banner.domain.repository

import hs.kr.equus.banner.domain.banner.domain.Banner
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BannerRepository : JpaRepository<Banner, UUID> {
}