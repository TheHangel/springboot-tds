package edu.spring.stories.repositories

import edu.spring.stories.entities.Story
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StoryRepository: CrudRepository<Story, Int> {

    fun findByNameAndDeveloperId(name: String, idDeveloper: Int?): Story

    fun findByDeveloperIsNull(): List<Story>

}