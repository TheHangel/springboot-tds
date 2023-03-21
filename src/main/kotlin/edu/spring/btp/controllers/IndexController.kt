package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import org.springframework.beans.factory.annotation.Autowired
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
    @ResponseBody
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
        val err = complaintRepository.save(complaint)
        return err.toString()
    }

    @RequestMapping("complaints/{domain}/new")
    fun newComplaint(@PathVariable domain:String, model:Model):String{
        model.addAttribute("domain", domainRepository.findByName(domain))
        return "forms/complaint"
    }

}