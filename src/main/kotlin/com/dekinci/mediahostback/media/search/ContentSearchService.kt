package com.dekinci.mediahostback.media.search

import com.dekinci.mediahostback.media.content.Content
import javax.transaction.Transactional

interface ContentSearchService {
    @Transactional
    fun searchUsers(text: String): List<Content>
}