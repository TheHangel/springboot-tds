package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class IndexController {

    @Autowired
    lateinit var domainRepository: DomainRepository

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @RequestMapping("", "index")
    fun index(model:Model):String{
        model.addAttribute("domain", domainRepository.findByParentNull())
        return "index"
    }

    @RequestMapping("domain/{name}")
    fun domain(@PathVariable name:String, model:Model):String{
        model.addAttribute("domain", domainRepository.findByName(name))
        return "index"
    }

    @GetMapping("complaints/{domain}")
    fun complaints(@PathVariable domain:String, model:Model):String{
        model.addAttribute("complaints", complaintRepository.findByDomainName(domain))
        return "complaints"
    }

    @PostMapping("complaints/{domain}/new")
    fun submitNewAction(
        @PathVariable domain:String,
        @RequestParam("title") title:String,
        @RequestParam("description") description:String,
    ): String {
        println(title)
        var complaint = Complaint()
        complaint.title = title
        complaint.description = description
        complaint.domain = domainRepository.findByName(domain)
        complaintRepository.save(complaint)
        return "redirect:/complaints/$domain"
    }

    @RequestMapping("complaints/{domain}/new")
    fun newComplaint(@PathVariable domain:String, model:Model):String{
        model.addAttribute("domain", domainRepository.findByName(domain))
        return "forms/complaint"
    }

    @RequestMapping("login")
    fun login():String{
        return "forms/login"
    }

    @PostMapping("login", )
    fun loginConnect(@RequestParam("UsernameOrEmail") UsernameOrEmail:String,
                     @RequestParam("password") password:String
    ):RedirectView{
        try {
            if (isEmailValid(UsernameOrEmail)) {
                var user = userRepository.findByEmailAndPassword(UsernameOrEmail, password)
                return RedirectView("")
            }
            else {
                var user = userRepository.findByUsernameAndPassword(UsernameOrEmail, password)
                return RedirectView("")
            }
        }
        catch (e: EmptyResultDataAccessException){
            return RedirectView("login")
        }
        return RedirectView("")
    }

    @RequestMapping("signup")
    fun signup():String{
        return "forms/signup"
    }

    companion object {
        fun isEmailValid(email: String): Boolean {
            val emailRegex = Regex("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$")
            return emailRegex.matches(email)
        }
    }

}