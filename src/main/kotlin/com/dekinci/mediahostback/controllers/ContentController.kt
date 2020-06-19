package com.dekinci.mediahostback.controllers

import com.dekinci.mediahostback.media.ContentType
import com.dekinci.mediahostback.media.content.Content
import com.dekinci.mediahostback.media.repository.ContentRepository
import com.dekinci.mediahostback.media.repository.ContentTypeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/api/v1/content")
class ContentController(
        private val repository: ContentRepository,
        private val typeRepository: ContentTypeRepository) {

    @PostMapping
    fun addContent(@RequestBody content: Content): Content {
        val typeString = content.contentType.type
        val type = typeRepository.findByType(typeString).orElseGet { typeRepository.save(ContentType(null, typeString)) }

        return repository.save(Content(
                null,
                type,
                content.name,
                content.fileUrl
        ))
    }

    @GetMapping
    fun findPaginated(@RequestParam(value = "page", defaultValue = "0") page: Int,
                      @RequestParam(value = "size", defaultValue = "20") size: Int
    ): Page<Content> {
        val request = PageRequest.of(page, size)
        return repository.findAll(request)
    }

    @GetMapping("/{id}")
    fun getContent(@PathVariable id: Int): Content {
        return repository.findById(id).get()
    }

    @PutMapping
    fun updateContent(@RequestBody content: Content): Content {
        if (content.id == null)
            throw IllegalStateException()

        return repository.save(content)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): Content {
        val content = repository.findById(id).orElseThrow { IllegalStateException() }

        repository.deleteById(id)

        return content
    }
}