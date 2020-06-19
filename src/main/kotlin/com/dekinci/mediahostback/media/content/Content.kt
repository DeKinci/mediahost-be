package com.dekinci.mediahostback.media.content

import com.dekinci.mediahostback.media.ContentType
import com.dekinci.mediahostback.media.tags.Tag
import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.lucene.analysis.core.LowerCaseFilterFactory
import org.apache.lucene.analysis.core.LowerCaseTokenizerFactory
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory
import org.hibernate.search.annotations.*
import org.hibernate.search.annotations.Parameter
import java.time.Instant
import javax.persistence.*

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
        tokenizer = TokenizerDef(factory = LowerCaseTokenizerFactory::class),
        filters = [
            TokenFilterDef(factory = LowerCaseFilterFactory::class),
            TokenFilterDef(factory = SnowballPorterFilterFactory::class, params = [Parameter(name = "language", value = "English")]),
            TokenFilterDef(factory = SnowballPorterFilterFactory::class, params = [Parameter(name = "language", value = "Russian")])
        ]
)
data class Content(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,

        @ManyToOne
        val contentType: ContentType,

        @Analyzer(definition = "customanalyzer")
        @Field(termVector = TermVector.YES)
        @Column(columnDefinition = "TEXT")
        val name: String,

        val fileUrl: String,

        val uploadDate: Instant = Instant.now(),

        @ManyToMany
        @JsonIgnore
        val likes: List<Like> = emptyList(),

        val ratingValue: RatingValue = RatingValue.NONE,

        @ManyToMany(fetch = FetchType.EAGER)
        @IndexedEmbedded
        val tagList: List<Tag> = emptyList()
)