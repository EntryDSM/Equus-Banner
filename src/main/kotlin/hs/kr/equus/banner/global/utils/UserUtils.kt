package hs.kr.equus.banner.global.utils

import hs.kr.equus.banner.infrastructure.fegin.client.UserFeignClient
import hs.kr.equus.banner.infrastructure.fegin.client.model.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserUtils(
    private val userFeignClient: UserFeignClient
) {
    fun getCurrentUserId(): UUID = UUID.fromString(SecurityContextHolder.getContext().authentication.name)
    fun getCurrentUser(): User = userFeignClient.getUserByUUID(getCurrentUserId())
}
