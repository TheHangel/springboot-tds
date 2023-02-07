package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
open class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long ?= null
    @Column(nullable = false, length = 60, unique = true)
    open lateinit var name: String
    @Column(length = 45)
    open var domain: String? = null
    @Column(length = 45)
    open var aliases: String? = null

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER, cascade = [CascadeType.ALL]) //mappedBy nom du membre de la classe User
    open val users: MutableSet<User> ?= HashSet()

    @OneToMany(mappedBy = "organization") //nom du membre de la classe Group
    open val groups: MutableSet<Group> ?= HashSet()

    fun addUser(user: User) {
        if (users?.add(user) == true) {
            user.organization = this
        }
    }

}