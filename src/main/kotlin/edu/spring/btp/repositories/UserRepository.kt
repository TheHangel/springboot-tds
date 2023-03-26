package edu.spring.btp.repositories

import edu.spring.btp.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Int> {
    @Query(nativeQuery = true,value="SELECT * FROM \"user\" ORDER BY rand() LIMIT 1")
    fun getRandomUser(): User

    fun findByEmailOrUsername(email:String, username:String):User

    fun findByEmailAndPassword(email:String, password:String):User

    fun findByUsernameAndPassword(username:String, password:String):User

    fun findByEmail(email:String):User

    fun findByUsername(username:String):User
}