package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
open class Developer(firstname: String, lastname: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int = 0

    @Column(length = 45)
    open var firstname: String? = null

    @Column(length = 45)
    open var lastname: String? = null

    @OneToMany(mappedBy = "developer", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open val stories= mutableSetOf<Story>()

    fun addStory(story: Story) {
        stories.add(story)
        story.developer = this
    }

    fun giveUpStory(story: Story) {
        stories.remove(story)
    }

    @PreRemove
    fun preRemove() {
        stories.removeAll(stories)
    }

}