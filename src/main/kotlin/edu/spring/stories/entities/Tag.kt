package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
open class Tag(color: String, label: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column
    open lateinit var color: String

    @Column
    open lateinit var label: String

    @ManyToMany
    @JoinTable(name = "story_tag",
        joinColumns = [JoinColumn(name = "idTag")],
        inverseJoinColumns = [JoinColumn(name = "idStory")]
    )
    open var stories = mutableSetOf<Story>()

}
