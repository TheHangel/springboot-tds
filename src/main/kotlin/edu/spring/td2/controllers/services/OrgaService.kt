package edu.spring.td2.controllers.services

import edu.spring.td2.entities.Organization
import edu.spring.td2.entities.User
import org.springframework.stereotype.Service

@Service
class OrgaService {

    fun addUsersFromString(orga: Organization, users:String){
        if(users.isNotEmpty()){
            users.split("\n").forEach{
                val user= User()
                val (firstname,lastname)=it.trim().split(" ", limit = 2)
                user.firstname=firstname
                user.lastname=lastname
                user.email="$firstname.$lastname@${orga.domain}".lowercase()
                orga.addUser(user)
            }
        }
    }

}