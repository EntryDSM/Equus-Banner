package hs.kr.equus.banner.domain.banner.presentation.request

data class UploadBannerRequest(
    val url: String,
    val fileName: String
)