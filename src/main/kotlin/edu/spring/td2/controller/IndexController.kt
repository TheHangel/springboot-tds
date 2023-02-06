package edu.spring.td2.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController {

    @RequestMapping(path=["", "index"]) //deux routes possibles, soit / , soit index
    fun indexAction(): String {
        return "index"
    }

}