package hs.kr.equus.banner.domain.banner.exception

import hs.kr.equus.banner.global.error.exception.EquusException
import hs.kr.equus.banner.global.error.exception.ErrorCode

object BannerNotFoundException : EquusException(
    ErrorCode.BANNER_NOT_FOUND
)
