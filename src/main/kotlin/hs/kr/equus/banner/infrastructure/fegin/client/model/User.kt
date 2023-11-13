package hs.kr.equus.banner.infrastructure.fegin.client.model

import hs.kr.equus.banner.global.security.jwt.UserRole
import java.util.*

data class User(
    val id: UUID,
    val phoneNumber: String,
    val name: String,
    val isParent: Boolean,
    val receiptCode: UUID?,
    val role: UserRole
)
