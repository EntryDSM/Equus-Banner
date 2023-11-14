package hs.kr.equus.banner.global.exception

import hs.kr.equus.banner.global.error.exception.EquusException
import hs.kr.equus.banner.global.error.exception.ErrorCode

object ImageNotFoundException : EquusException(
    ErrorCode.IMAGE_NOT_FOUND
)