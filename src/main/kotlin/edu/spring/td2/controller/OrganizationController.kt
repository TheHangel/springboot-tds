package edu.spring.td2.controller

import edu.spring.td2.entities.Organization
import edu.spring.td2.entities.User
import edu.spring.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/organization/", "/organization")
class OrganizationController {

    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    @RequestMapping(path=["","index"])
    fun indexAction(model:ModelMap): String {
        val organizations = organizationRepository.findAll()
        model.addAttribute("organizations", organizations)
        return "organization"
    }

    @GetMapping("add/{name}")
    @ResponseBody
    fun createOrganization(@PathVariable name : String): String {
        val orga = Organization()
        orga.name = name
        var user= User()
        user.firstName="Flash"
        user.lastName="McQueen"
        user.email="flash.mcqueen@rusteze.com"
        orga.addUser(user)
        organizationRepository.save(orga)
        return "Organisation $name ajoutée"
    }

}