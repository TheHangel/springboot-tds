package edu.spring.td2.controllers

import edu.spring.td2.controllers.services.OrgaService
import edu.spring.td2.entities.Organization
import edu.spring.td2.entities.User
import edu.spring.td2.exceptions.ElementNotFoundException
import edu.spring.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/orgas/")
class OrgaController {

    @Autowired
    lateinit var orgaRepository:OrganizationRepository

    @Autowired
    lateinit var orgaService: OrgaService

    @RequestMapping(path = ["","index"])
    fun indexAction(model:ModelMap):String{
        val orgas=orgaRepository.findAll()
        model["orgas"]=orgas
        return "/orgas/index"
    }

    @GetMapping("new")
    fun newAction(model:ModelMap):String{
        model["orga"]=Organization("")
        return "/orgas/form"
    }

    @PostMapping("new")
    fun newPostAction(@ModelAttribute orga:Organization, @ModelAttribute("users") users:String):RedirectView{
        orgaService.addUsersFromString(orga, users)
        orgaRepository.save(orga)
        return RedirectView("/orgas/")
    }

    @GetMapping("display/{id}")
    fun displayAction(@PathVariable id:Int, model: ModelMap):String{
        val option = orgaRepository.findById(id)
        if ( option.isPresent ) {
            model["orga"]=option.get()
            return "/orgas/display"
        }
        throw ElementNotFoundException("L'organisation d'id $id n'existe pas")
    }

    @ExceptionHandler//(value = [ElementNotFoundException::class])
    fun handleElementNotFoundException(e:ElementNotFoundException):ModelAndView{
        val mv = ModelAndView("/main/error")
        mv.addObject("message", e.message)
        return mv
    }

    @GetMapping("add/{name}")
    @ResponseBody
    fun testAddAction(@PathVariable name:String):String{
        val orga=Organization()
        orga.name=name
        val user=User()
        user.firstname="Bob"
        user.lastname="Leponge"
        user.email="boob@mail.com"
        orga.addUser(user)
        orgaRepository.save(orga)
        return "Organisation $name ajoutée avec 1 user : ${user.email}"
    }

}