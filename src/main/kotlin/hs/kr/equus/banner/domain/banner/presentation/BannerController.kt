package hs.kr.equus.banner.domain.banner.presentation

import hs.kr.equus.banner.domain.banner.service.CreateBannerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/banner")
@RestController
class BannerController(
    private val createBannerService: CreateBannerService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createBanner(@RequestPart(name = "photo") file : MultipartFile) =
        createBannerService.execute(file)

}