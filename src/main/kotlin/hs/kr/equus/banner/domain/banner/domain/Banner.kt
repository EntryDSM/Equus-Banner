package hs.kr.equus.banner.domain.banner.domain

import hs.kr.equus.banner.domain.banner.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_banner")
class Banner (
    id : UUID? = null,

    @Column(name = "banner", nullable = false)
    val link : String
    
) : BaseUUIDEntity(id)