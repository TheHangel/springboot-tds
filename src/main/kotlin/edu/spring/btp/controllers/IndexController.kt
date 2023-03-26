package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.User
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.ProviderRepository
import edu.spring.btp.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
@SessionAttributes("user")
class IndexController {

    @get:ModelAttribute("user")
    var user: User = User(id = 0, username = "GUEST", role = "GUEST")

    @Autowired
    lateinit var domainRepository: DomainRepository

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var providerRepository: ProviderRepository

    @RequestMapping("", "index")
    fun index(model:Model):String{
        model.addAttribute("domain", domainRepository.findByParentNull())
        //model.addAttribute("user", user)
        return "index"
    }

    @RequestMapping("domain/{name}")
    fun domain(@PathVariable name:String, model:Model):String{
        model.addAttribute("domain", domainRepository.findByName(name))
        return "index"
    }

    @GetMapping("complaints/{domain}")
    fun complaints(@PathVariable domain:String, model:Model):String{
        if(user.id!=0 && user.role=="USER") {
            model.addAttribute("complaints", complaintRepository.findByDomainName(domain))
            return "complaints"
        }
        return "redirect:/login"
    }

    @PostMapping("complaints/{domain}/new")
    fun submitNewComplaint(
        @PathVariable domain:String,
        @RequestParam("title") title:String,
        @RequestParam("description") description:String,
        @RequestParam("provider") provider:Int
    ): String {
        if(user.id!=0 && user.role=="USER") {
            var provider = providerRepository.findById(provider)
            var complaint = Complaint()
            complaint.provider = provider.get()
            complaint.user = this.user
            complaint.title = title
            complaint.description = description
            complaint.domain = domainRepository.findByName(domain)
            complaintRepository.save(complaint)
            return "redirect:/complaints/$domain"
        }
        return "redirect:/login"
    }

    @RequestMapping("complaints/{domain}/new")
    fun newComplaint(@PathVariable domain:String, model:Model):String{
        if(user.id!=0 && user.role=="USER") {
            model.addAttribute("domain", domainRepository.findByName(domain))
            model.addAttribute("providers", providerRepository.findAll())
            model.addAttribute("userId", this.user.id)
            return "forms/complaint"
        }
        return "redirect:/login"
    }

    @RequestMapping("login")
    fun login():String{
        return "forms/login"
    }

    @PostMapping("login")
    fun loginConnect(@RequestParam("UsernameOrEmail") UsernameOrEmail:String,
                     @RequestParam("password") password:String,
                     @SessionAttribute("user") user:User
    ):RedirectView{
        try {
            if (isEmailValid(UsernameOrEmail)) {
                this.user = userRepository.findByEmailAndPassword(UsernameOrEmail, password)
                return RedirectView("")
            }
            else {
                this.user = userRepository.findByUsernameAndPassword(UsernameOrEmail, password)
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

    @PostMapping("signup")
    fun signupConnect(
        @RequestParam("username") username:String,
        @RequestParam("email") email:String,
        @RequestParam("password") password:String,
        @RequestParam("passwordConfirmation") passwordConfirmation:String
    ):RedirectView{
        if (password == passwordConfirmation && isEmailValid(email)){
            var user = User()
            user.username = username
            user.email = email
            user.password = password
            user.role = "USER"
            userRepository.save(user)
            return RedirectView("login")
        }
        return RedirectView("signup")
    }

    @RequestMapping("logout")
    fun logout(@SessionAttribute("user") user:User):RedirectView{
        this.user = User(id = 0, username = "GUEST", role = "GUEST")
        return RedirectView("")
    }

    @RequestMapping("test")
    @ResponseBody
    fun test():String{
        return this.user.toString()
    }

    companion object {
        fun isEmailValid(email: String): Boolean {
            val emailRegex = Regex("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$")
            return emailRegex.matches(email)
        }
    }

}