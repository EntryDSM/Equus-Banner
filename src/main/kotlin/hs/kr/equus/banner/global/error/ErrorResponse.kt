package hs.kr.equus.banner.global.error

data class ErrorResponse(
    val status: Int,
    val message: String?
)