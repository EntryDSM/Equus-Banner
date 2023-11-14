package hs.kr.equus.banner.infrastructure.fegin

import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import hs.kr.equus.banner.infrastructure.fegin.exception.FeignBadRequestException
import hs.kr.equus.banner.infrastructure.fegin.exception.FeignForbiddenException
import hs.kr.equus.banner.infrastructure.fegin.exception.FeignServerError
import hs.kr.equus.banner.infrastructure.fegin.exception.FeignUnAuthorizedException

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        if (response!!.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException
                401 -> throw FeignUnAuthorizedException
                403 -> throw FeignForbiddenException
                else -> throw FeignServerError
            }
        }
        return FeignException.errorStatus(methodKey, response)
    }
}
