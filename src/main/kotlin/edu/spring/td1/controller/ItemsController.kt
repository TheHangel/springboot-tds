package edu.spring.td1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.SessionAttributes


@Controller
@SessionAttributes("items")
class ItemsController {

    @GetMapping("/")
    @ResponseBody
    fun helloAction(): String? {
        return "Hello world"
    }

}