package hs.kr.equus.banner.domain.banner.domain

import org.springframework.data.annotation.Id
import java.util.UUID
import org.springframework.data.redis.core.RedisHash

@RedisHash
class BannerLink (
    @Id
    val id : UUID? = null,

    val link : String
    
)