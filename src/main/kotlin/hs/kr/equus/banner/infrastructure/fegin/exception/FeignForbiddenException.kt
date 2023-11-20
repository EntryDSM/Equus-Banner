package hs.kr.equus.banner.infrastructure.fegin.exception

import hs.kr.equus.banner.global.error.exception.EquusException
import hs.kr.equus.banner.global.error.exception.ErrorCode

object FeignForbiddenException : EquusException(
    ErrorCode.FEIGN_FORBIDDEN
)
