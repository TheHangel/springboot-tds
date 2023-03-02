package edu.spring.stories.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController {

    //Affiche la page principale
    @RequestMapping("", "/", "index")
    fun index(): String {
        return "index"
    }

}