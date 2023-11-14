package hs.kr.equus.banner.global.exception

import hs.kr.equus.banner.global.error.exception.EquusException
import hs.kr.equus.banner.global.error.exception.ErrorCode

object FileIsEmptyException : EquusException(
    ErrorCode.FILE_IS_EMPTY
)