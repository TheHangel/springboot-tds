package edu.spring.stories.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
open class Developer(firstname: String, lastname: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 45)
    open var firstname: String? = null

    @Column(length = 45)
    open var lastname: String? = null

    @OneToMany(mappedBy = "developer")
    open val stories= mutableSetOf<Story>()


}