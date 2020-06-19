package com.dekinci.mediahostback.media.content

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_like")
data class Like(
        @Id
        val id: Int? = null,
        val name: String,
        val value: Int,
        val ts: Instant = Instant.now()
)