package com.dekinci.mediahostback.controllers

import com.dekinci.mediahostback.media.content.Content
import com.dekinci.mediahostback.media.search.ContentSearchService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/v1/search")
class SearchController(val contentSearchService: ContentSearchService) {
    @GetMapping("{query}")
    fun fullTextSearch(@RequestParam(value = "page", defaultValue = "0") page: Int,
                       @RequestParam(value = "size", defaultValue = "20") size: Int,
                       @PathVariable query: String): Page<Content> {
        return PageImpl(contentSearchService.searchUsers(query).drop(page * size).take(size))
    }
}
