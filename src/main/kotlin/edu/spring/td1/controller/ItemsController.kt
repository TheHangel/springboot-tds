package edu.spring.td1.controller

import edu.spring.td1.model.Item
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@SessionAttributes("items")
class ItemsController {
    @get:ModelAttribute("items")
    val items: Set<Any>
        get() {
            var elements=HashSet<Item>()
            elements.add(Item())
            return elements
        }

    @PostMapping("items/addNew")
    fun addNew(@RequestParam nom: String?, @RequestParam evaluation: Int?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        val newItem = Item()
        if (nom != null) {
            newItem.nom = nom
        }
        if (evaluation != null) {
            newItem.evaluation = evaluation
        }
        items.add(newItem)
        return RedirectView("/items/")
    }

    @PostMapping("items/addTest")
    fun addNew(@RequestParam nom: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        val e = Item()
        e.nom = "item"
        e.evaluation = 20
        items.add(e)
        return RedirectView("/items/")
    }

    @GetMapping("items/inc/{nom}")
    fun incrementer(@RequestParam nom: String?, @PathVariable("nom") nomItem: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        for (e in items) {
            if (e != null) {
                if (e.nom == nomItem ) {
                    e.evaluation = ( e.evaluation + 1)
                }
            }
        }
        return RedirectView("/items/")
    }

    @GetMapping("items/dec/{nom}")
    fun decrementer(@RequestParam nom: String?, @PathVariable("nom") nomItem: String?, @SessionAttribute("items") items: HashSet<Item?>): RedirectView {
        for (e in items) {
            if (e != null) {
                if (e.nom == nomItem ) {
                    e.evaluation = ( e.evaluation - 1)
                }
            }
        }
        return RedirectView("/items/")
    }
}