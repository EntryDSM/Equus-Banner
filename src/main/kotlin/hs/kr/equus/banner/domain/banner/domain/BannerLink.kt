package hs.kr.equus.banner.domain.banner.domain

import hs.kr.equus.banner.domain.banner.BaseUUIDEntity
import java.util.UUID
import org.springframework.data.redis.core.RedisHash

@RedisHash
class BannerLink (
    id : UUID? = null,

    val link : String
    
) : BaseUUIDEntity(id)