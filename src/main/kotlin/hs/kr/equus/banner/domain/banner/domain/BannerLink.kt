package hs.kr.equus.banner.domain.banner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "banner")
class BannerLink (
    @Id
    var id : Long? = null,

    @Indexed
    var link : String
    
) {
    fun update(link: String) {
        this.link = link
    }
}