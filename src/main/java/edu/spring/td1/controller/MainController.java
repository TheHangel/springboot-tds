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

    @PostMapping("items/addNew")
    public RedirectView addNew(@RequestParam String nom) {
        Element e = new Element();
        e.setNom("item");
        e.setEvaluation(20);
        this.getItems().add(e);
        return new RedirectView("/items/");
    }

    @PostMapping("items/delete/{nom}")
    public RedirectView removeItem(@RequestParam String nom, @PathVariable("nom") String nomItem) {

        //this.getItems().remove(this.getItems().get(""));
        return new RedirectView("/items/");
    }

}
