package edu.spring.stories.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController {

    //Affiche la page principale
    @RequestMapping(path = ["","index"])
    fun indexAction():String{
        return "index"
    }

}