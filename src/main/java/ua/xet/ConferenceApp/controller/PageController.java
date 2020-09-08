package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.repository.UserRepository;
import ua.xet.ConferenceApp.service.ConferenceService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    ConferenceService conferenceService;

    @RequestMapping(value = {"/index"})
    public String mainPage(Model model){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDTO user = (UserDTO)authentication.getPrincipal();

        model.addAttribute("username",user.getUsername());
        model.addAttribute("role",user.getAuthorities());
        return "index";
    }
    @RequestMapping(value = {"/schedule"})
    public String schedulePage(Model model){
        model.addAttribute("conferences", findByActive());
        model.addAttribute("users", getCreator(findByActive()));
        return "schedule";
    }

    @RequestMapping(value = {"/conference"})
    public String conferencePage(Model model){
        return "conference";
    }

    @RequestMapping(value = {"/", "/home"})
    public String homePage(){
        return "home";
    }


    private List<Conference> findByActive(){
        List<Conference> conferences = new ArrayList<>();
        conferences.addAll(conferenceService.findByConfirmed());
        return conferences;
    }

    private List<User> getCreator(List<Conference> conferences){
        return conferenceService.getCreator(conferences);
    }

}
