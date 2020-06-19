package com.dekinci.mediahostback.media.search

import com.dekinci.mediahostback.media.content.Content
import org.hibernate.search.jpa.FullTextQuery
import org.hibernate.search.jpa.Search
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
class FtsContentSearchService(@PersistenceContext val entityManager: EntityManager) : ContentSearchService {
    @Transactional
    override fun searchUsers(text: String): List<Content> {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)

        val queryBuilder =
                fullTextEntityManager.searchFactory
                        .buildQueryBuilder()
                        .forEntity(Content::class.java)
                        .get()

        val query = queryBuilder
                .keyword()
//                .wildcard()
                .onFields("name")
                .matching(text)
                .createQuery()

        val jpaQuery: FullTextQuery = fullTextEntityManager.createFullTextQuery(query, Content::class.java)

        return jpaQuery.resultList.map { result -> result as Content }.toList()
    }
}
