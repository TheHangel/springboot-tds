package edu.spring.td1.controller;

import java.util.ArrayList;
import java.util.List;
import edu.spring.models.Element;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@SessionAttributes("items")
public class MainController {

    @ModelAttribute("items")
    public List<Element> getItems(){
        return new ArrayList<>();
    }

    @GetMapping("items/new")
    public RedirectView newItem(@RequestParam String nom, @SessionAttribute("items") List<Element> items) {
        //TODO
        return new RedirectView("/items/");
    }

    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam String nom, @SessionAttribute("items") List<Element> items) {
        Element e = new Element();
        e.setNom("item");
        e.setEvaluation(20);
        items.add(e);
        return new RedirectView("/items/");
    }

    @GetMapping("items/inc/{nom}")
    public RedirectView incrementer(@RequestParam String nom, @PathVariable("nom") String nomItem, @SessionAttribute("items") List<Element> items) {
        for(Element e : items){
            if(e.getNom().equals(nomItem)){
                e.setEvaluation(e.getEvaluation()+1);
            }
        }
        return new RedirectView("/items/");
    }

    @GetMapping("items/dec/{nom}")
    public RedirectView decrementer(@RequestParam String nom, @PathVariable("nom") String nomItem, @SessionAttribute("items") List<Element> items) {
        for(Element e : items){
            if(e.getNom().equals(nomItem)){
                e.setEvaluation(e.getEvaluation()-1);
            }
        }
        return new RedirectView("/items/");
    }

    @PostMapping("items/delete/{nom}")
    public RedirectView removeItem(@RequestParam String nom, @PathVariable("nom") String nomItem) {

        for(Element e : this.getItems()){
            if(e.getNom().equals(nomItem)){
                this.getItems().remove(e);
            }
        }

        return new RedirectView("/items/");
    }

}
