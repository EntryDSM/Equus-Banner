package hs.kr.equus.banner.domain.banner.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "banner")
class BannerLink(
    @Id
    var id: Long? = null,

    @Indexed
    var fileName: String

) {
    fun update(fileName: String): String {
        this.fileName = fileName
        return fileName
    }
}
