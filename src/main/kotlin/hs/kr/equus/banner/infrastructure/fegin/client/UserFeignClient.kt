package hs.kr.equus.banner.infrastructure.fegin.client

import hs.kr.equus.banner.infrastructure.fegin.client.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "UserClient", url = "\${url.user}")
interface UserFeignClient {
    @GetMapping("/user")
    fun getUserByUUID(@RequestParam("userId") userId: UUID): User
}
