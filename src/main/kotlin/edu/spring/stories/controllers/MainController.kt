package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class MainController {

    @Autowired
    lateinit var developerRepository: DeveloperRepository

    @Autowired
    lateinit var storyRepository: StoryRepository

    //Affiche la page principale
    @RequestMapping(path = ["","index"])
    fun indexAction(model: ModelMap):String{
        val devs = developerRepository.findAll()
        model.addAttribute("developers", devs)
        model.addAttribute("nb_devs", devs.count())
        return "index"
    }

    @PostMapping("/developer/add")
    fun submitNewAction(
        @ModelAttribute dev:Developer
    ):RedirectView{
        developerRepository.save(dev)
        return RedirectView("/")
    }

    @GetMapping("/developer/{id}/delete")
    fun deleteAction(
        @PathVariable id:Int
    ):RedirectView{
        developerRepository.deleteById(id)
        return RedirectView("/")
    }

    @PostMapping("/developer/{id}/story")
    fun addStoryAction(
        @PathVariable id:Int,
        @RequestParam storyname:String
    ):RedirectView{
        val dev = developerRepository.findById(id).get()
        val story = Story(storyname)
        story.name = storyname
        story.developer = dev
        dev.addStory(story)
        developerRepository.save(dev)
        return RedirectView("/")
    }

    @GetMapping("/story/{id}/giveup")
    fun giveUpAction(
        @PathVariable id:Int
    ):RedirectView{
        storyRepository.deleteById(id)
        return RedirectView("/")
    }

}