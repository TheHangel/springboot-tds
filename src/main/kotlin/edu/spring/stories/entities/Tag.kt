package edu.spring.stories.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
open class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column
    lateinit var color: String

    @Column
    lateinit var label: String

    @ManyToMany(mappedBy = "tags")
    open val stories = mutableSetOf<Story>()

}
