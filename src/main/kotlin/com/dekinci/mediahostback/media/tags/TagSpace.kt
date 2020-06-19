package com.dekinci.mediahostback.media.tags

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.TermVector
import java.time.Instant
import javax.persistence.*

@Entity
@Indexed
data class TagSpace(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,

        @Column(columnDefinition = "TEXT")
        @Field(termVector = TermVector.YES)
        val name: String,

        val creationDate: Instant
)