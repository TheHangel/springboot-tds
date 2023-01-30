package edu.spring.td1.controller

import edu.spring.td1.model.Item
import edu.spring.td1.service.UIMessage
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.util.*

@Controller
@SessionAttributes("items")
class ItemsController {
    @get:ModelAttribute("items")
    val items: Set<Any>
        get() {
            var elements= HashSet<Item>()
            elements.add(Item().apply { nom = "objet par défaut"; evaluation = 0 })
            return elements
        }

    private fun addMessage(resp:Boolean, attrs: RedirectAttributes, title:String, success:String, error:String){
        if(resp) {
            attrs.addFlashAttribute("msg",
                UIMessage.message(title, success))
        } else {
            attrs.addFlashAttribute("msg",
                UIMessage.message(title, error,"error","warning circle"))

        }
    }

    @PostMapping("/addNew")
    fun addNewAction(
        @ModelAttribute("nom") nom:String,
        @SessionAttribute("items") items:HashSet<Item>,
        attrs:RedirectAttributes):RedirectView{
        val item = Item()
        item.nom = nom
        item.evaluation = 0
        addMessage(
            items.add(item),
            attrs,
            "Ajout",
            "$nom a été ajouté à la liste.",
            "$nom est déjà dans la liste."
        )
        return RedirectView("/")
    }

    @GetMapping("/new")
    fun newAction():String{
        return "newItemForm"
    }

    @RequestMapping("/")
    fun indexAction(@RequestAttribute("msg") message: UIMessage.Message?): String {
        return "index"
    }

    @PostMapping("/addTest")
    fun addNew(@RequestParam nom: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        val e = Item()
        e.nom = "item"
        e.evaluation = 20
        items.add(e)
        return RedirectView("/")
    }

    @GetMapping("/inc/{nom}")
    fun incrementer(@RequestParam nom: String?, @PathVariable("nom") nomItem: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        for (e in items) {
            if (e != null) {
                if (e.nom == nomItem ) {
                    e.evaluation?.plus(1)
                }
            }
        }
        return RedirectView("/")
    }

    @GetMapping("/dec/{nom}")
    fun decrementer(@RequestParam nom: String?, @PathVariable("nom") nomItem: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        for (e in items) {
            if (e != null) {
                if (e.nom == nomItem ) {
                    e.evaluation?.minus(1)
                }
            }
        }
        return RedirectView("/")
    }
}