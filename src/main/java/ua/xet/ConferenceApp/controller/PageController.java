package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.xet.ConferenceApp.repository.UserRepository;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/","/index"})
    public String mainPage(Model model){
        return "index";
    }
}
