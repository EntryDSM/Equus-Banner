package com.example.equusbanner.domain.banner.domain

import com.example.equusbanner.domain.banner.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_banner")
class Banner (
    id : UUID? = null,

    @Column(name = "banner", nullable = false)
    val banner : String,
) : BaseUUIDEntity(id)