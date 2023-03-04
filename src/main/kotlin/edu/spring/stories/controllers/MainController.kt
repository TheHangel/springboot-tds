package edu.spring.stories.controllers

import edu.spring.stories.entities.Developer
import edu.spring.stories.entities.Story
import edu.spring.stories.repositories.DeveloperRepository
import edu.spring.stories.repositories.StoryRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
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
        val stories = storyRepository.findByDeveloperIsNull()
        model.addAttribute("developers", devs)
        model.addAttribute("nb_devs", devs.count())
        model.addAttribute("stories", stories)
        model.addAttribute("nb_stories", stories.count())
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
    fun giveUpAction(@PathVariable id: Int): RedirectView {
        val story = storyRepository.findById(id).get()
        val developer = story.developer
        developer?.giveUpStory(story)
        story.developer = null
        storyRepository.save(story)
        return RedirectView("/")
    }

    @PostMapping("/story/{id}/remove")
    fun removeStoryAction(@PathVariable id: Int): RedirectView {
        val story = storyRepository.findById(id).orElseThrow { EntityNotFoundException() }
        val developer = story.developer
        developer?.giveUpStory(story)
        storyRepository.deleteById(id)
        return RedirectView("/")
    }

    @PostMapping("/story/{id}/affect")
    fun affectStoryAction(
        @PathVariable id:Int,
        @RequestParam developer_id:Int,
        @RequestParam story_id:Int
    ): RedirectView{
        val story = storyRepository.findById(story_id).get()
        val developer = developerRepository.findById(developer_id).get()
        developer.addStory(story)
        developerRepository.save(developer)
        return RedirectView("/")
    }


}