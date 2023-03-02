package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
open class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column(length = 45)
    lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "idDeveloper")
    open lateinit var developer : Developer

    @ManyToMany(mappedBy = "stories")
    open val tags = mutableSetOf<Tag>()

}
