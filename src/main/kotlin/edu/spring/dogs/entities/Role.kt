package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int=0

    @Column(length = 65, nullable = false)
    lateinit var name:String

    @ManyToMany(mappedBy = "roles")
    val users:MutableList<User> = mutableListOf()

}