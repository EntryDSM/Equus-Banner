package hs.kr.equus.banner.domain.banner.presentation

import hs.kr.equus.banner.domain.banner.service.CreateBannerLinkService
import hs.kr.equus.banner.domain.banner.service.UpdateBannerLinkService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/banner")
@RestController
class BannerLinkController(
    private val createBannerLinkService: CreateBannerLinkService,
    private val updateBannerLinkService: UpdateBannerLinkService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBannerLink(@RequestPart(name = "photo") file : MultipartFile) =
        createBannerLinkService.execute(file)

    @PatchMapping("/{banner-id}")
    fun updateBannerLink(@RequestPart(name = "photo") file: MultipartFile, @PathVariable("banner-id") bannerId: Long) =
        updateBannerLinkService.execute(file, bannerId)
}