package edu.spring.btp.repositories

import edu.spring.btp.entities.Complaint
import org.springframework.data.jpa.repository.JpaRepository

interface ComplaintRepository:JpaRepository<Complaint, Int> {

    fun findByDomainName(name:String):List<Complaint>

}