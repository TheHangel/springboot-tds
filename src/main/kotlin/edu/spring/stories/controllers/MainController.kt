package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.repositories.DeveloperRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var developerRepository: DeveloperRepository

    //Affiche la page principale
    @RequestMapping(path = ["","index"])
    fun indexAction(model: ModelMap):String{
        model.addAttribute("developers", developerRepository.findAll())
        return "index"
    }

    /*@PostMapping("/developer/add")
    fun addDeveloper(@RequestParam firstname: String, @RequestParam lastname: String): RedirectView {
        val developer = Developer(firstname, lastname)
        developerRepository.save(developer)
        return RedirectView("/")
    }*/

    @PostMapping("/developer/add")
    fun submitNewAction(
        @ModelAttribute dev:Developer
    ):RedirectView{
        developerRepository.save(dev)
        return RedirectView("/")
    }

    /*@PostMapping("/developer/add")
    fun addDeveloper(@ModelAttribute developer: Developer): RedirectView {
        developerRepository.save(developer)
        return RedirectView("/")
    }*/

}