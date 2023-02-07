package edu.spring.td2.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null
    @Column(length = 30)
    open var firstName : String? = null
    @Column(length = 30)
    open var lastName : String? = null

    @Column(length = 255, nullable = false, unique = true)
    open lateinit var email : String

    open var password : String ?= null

    open var suspended: Boolean = false

    @ManyToOne
    @JoinColumn(name = "idOrganization", nullable = false)
    open lateinit var organization: Organization

    @ManyToMany
    @JoinTable(name = "user_groups")
    open val groups: MutableSet<Group> ?= HashSet()

}