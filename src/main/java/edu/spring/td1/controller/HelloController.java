package edu.spring.td1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @GetMapping("/")
    @ResponseBody
    public String helloAction() {
        return "Hello world";
    }

    @GetMapping(path={"msg/{c}", "msg/{c}/"})
    @ResponseBody
    public String messageAction(@PathVariable("c") String content){
        return content;
    }

    @GetMapping(path={"msg/view/{c}", "msg/view/{c}/"})
    public String messageViewAction(ModelMap data, @PathVariable("c") String content){
        int value=220;
        data.addAttribute("value", value);
        return "helloView";
    }

    @GetMapping("msg/view/2/{c}")
    public ModelAndView messageAction2(@PathVariable("c") String content){
        ModelAndView mv = new ModelAndView("helloView");
        mv.addObject("value", 220);
        return mv;
    }

}