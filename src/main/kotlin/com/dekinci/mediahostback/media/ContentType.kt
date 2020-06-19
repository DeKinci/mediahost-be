package com.dekinci.mediahostback.media

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ContentType(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Int? = null,
        val type: String
)
