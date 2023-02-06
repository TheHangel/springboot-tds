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
    var id: Long ?= null
    @Column(nullable = false)
    lateinit var name: String
    var domain: String? = null
    var aliases: String? = null

}