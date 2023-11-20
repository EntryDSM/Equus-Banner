package hs.kr.equus.banner.infrastructure.fegin.exception

import hs.kr.equus.banner.global.error.exception.EquusException
import hs.kr.equus.banner.global.error.exception.ErrorCode

object FeignServerError : EquusException(
    ErrorCode.FEIGN_SERVER_ERROR
)
