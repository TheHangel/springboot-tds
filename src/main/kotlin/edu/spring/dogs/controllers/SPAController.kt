package edu.spring.dogs.controllers

import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import io.github.jeemv.springboot.vuejs.VueJS
import io.github.jeemv.springboot.vuejs.utilities.Http
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/spa")
class SPAController {

    @Autowired
    lateinit var masterRepository: MasterRepository

    @Autowired
    lateinit var dogRepository: DogRepository

    @Autowired
    lateinit var vue: VueJS

    @ModelAttribute("vue")
    fun vue(): VueJS = vue

    @GetMapping(path = ["/","","/index"])
    fun index(): String {
        vue.addData("message", "Hello Vue.js!")
        vue.addDataRaw("masters", "[]")
        vue.addData("dogs", dogRepository.findByMasterIsNull())
        vue.onMounted(Http.get("/masters",
                Http.responseArrayToArray("this.masters","masters")
            )
        )
        return "/spa/index"
    }
}