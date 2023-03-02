package edu.spring.stories.repositories

import edu.spring.stories.entities.Developer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeveloperRepository : CrudRepository<Developer, Int> {

    fun findByStoryNameDeveloperWithThisStory(story: String): List<Developer>

    fun findDeveloperByFistAndLastname(firstname: String, lastname: String): List<Developer>

}