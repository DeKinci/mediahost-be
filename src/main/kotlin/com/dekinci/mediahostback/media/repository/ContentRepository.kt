package com.dekinci.mediahostback.media.repository

import com.dekinci.mediahostback.media.content.Content
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface ContentRepository : PagingAndSortingRepository<Content, Int> {
    fun findByNameContains(name: String): List<Content>
}