package edu.spring.dogs.controllers

import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import io.github.jeemv.springboot.vuejs.VueJS
import io.github.jeemv.springboot.vuejs.utilities.Http
import io.github.jeemv.springboot.vuejs.utilities.JsArray
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
        vue.addData("master", "pas de nom")
        vue.addMethod("addMaster",
            "Http.post('/master/add',this.master,Http.responseArrayToArray('this.masters','masters'))"
        )
        vue.addMethod("remove",
            Http.delete("'/masters/'+master.id",
                JsArray.remove("this.masters","master")+
                        JsArray.addAll("this.dogs","master.dogs")+
                        "console.log(`Maître \${master.firstname} supprimé!`);",
                "console.log('Erreur sur suppression de master!');"
            ),
            "master")
        vue.addDataRaw("message","{title:'',content:''}")
        vue.addMethod("showMessage",
            "this.message={error: error,title: title, content: content, display: true};"+
                    "setTimeout(function(){this.message.display=false;}.bind(this),5000);",
            "title","content","error")
        vue.addMethod("successMessage",
            "this.showMessage(title,content,false);",
            "title","content")
        vue.addMethod("errorsMessage",
            "this.showMessage(title,content,true);",
            "title","content")
        vue.onMounted(Http.get("/masters",
                Http.responseArrayToArray("this.masters","masters")
            )
        )
        return "/spa/index"
    }
}