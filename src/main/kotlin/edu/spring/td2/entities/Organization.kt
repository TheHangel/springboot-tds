package edu.spring.td2.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue

@Entity
open class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long ?= null
    @Column(nullable = false, length = 60)
    open lateinit var name: String
    @Column(length = 45)
    open var domain: String? = null
    @Column(length = 45)
    open var aliases: String? = null

}