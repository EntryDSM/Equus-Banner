package hs.kr.equus.banner.global.error.exception

import java.lang.RuntimeException

abstract class EquusException(
    val errorCode: ErrorCode
) : RuntimeException()
