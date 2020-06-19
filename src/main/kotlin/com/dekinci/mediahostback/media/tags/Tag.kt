package com.dekinci.mediahostback.media.tags

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import org.hibernate.search.annotations.TermVector
import javax.persistence.*

@Entity
@Indexed
data class Tag(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,

        @Column(columnDefinition = "TEXT")
        @Field(termVector = TermVector.YES)
        val name: String,

        @ManyToOne
        val tagSpace: TagSpace,

        val isHidden: Boolean
);