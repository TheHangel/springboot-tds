package edu.spring.td2.controller

import edu.spring.td2.entities.Organization
import edu.spring.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/organization/")
class OrganizationController {

    @Autowired
    lateinit var organizationRepository: OrganizationRepository
    @GetMapping("add/{name}")
    @ResponseBody
    fun createOrganization(@PathVariable name : String): String {
        val orga = Organization()
        orga.name = name
        organizationRepository.save(orga)
        return "Organisation $name ajoutée"
    }

}