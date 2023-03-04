package edu.spring.stories.entities

import jakarta.persistence.*

@Entity
open class Story(name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 45)
    open lateinit var name: String

    @ManyToOne
    @JoinColumn(name = "idDeveloper")
    open var developer : Developer? = null

    @ManyToMany(mappedBy = "stories")
    open val tags = mutableSetOf<Tag>()

}
