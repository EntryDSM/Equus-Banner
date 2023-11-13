package com.example.equusbanner.domain.banner.domain.repository

import com.example.equusbanner.domain.banner.domain.Banner
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BannerRepository : JpaRepository<Banner, UUID> {
}