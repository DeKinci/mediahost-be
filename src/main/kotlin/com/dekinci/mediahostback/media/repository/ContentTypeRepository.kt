package com.dekinci.mediahostback.media.repository

import com.dekinci.mediahostback.media.ContentType
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ContentTypeRepository : PagingAndSortingRepository<ContentType, Int> {
    fun findByType(type: String): Optional<ContentType>
}