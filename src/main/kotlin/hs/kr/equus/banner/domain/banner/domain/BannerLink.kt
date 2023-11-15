package hs.kr.equus.banner.domain.banner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "banner")
class BannerLink (
    @Id
    var id : Long? = null,

    val link : String
    
)