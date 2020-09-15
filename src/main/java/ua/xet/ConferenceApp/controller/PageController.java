package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.repository.UserRepository;
import ua.xet.ConferenceApp.service.ConferenceService;
import ua.xet.ConferenceApp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    UserService userService;
    @Autowired
    ConferenceService conferenceService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/index"})
    public String mainPage(Model model){
        return "index";
    }
    @RequestMapping(value = {"/schedule"})
    public String schedulePage(Model model){
        model.addAttribute("conferences", findByActive());
        model.addAttribute("users", getCreator(findByActive()));
        return "schedule";
    }

    @RequestMapping(value = {"/", "/home"})
    public String homePage(){
        return "home";
    }

    @RequestMapping("/users")
    public String usersList(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    @PostMapping("/setRole/{id}")
    public String setUserRole(Model model, @ModelAttribute User user, @PathVariable Long id){
        model.addAttribute("user", user);
        setRole(user, id);
        return "redirect:/users";
    }
    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        deleteUserById(id);
        return "redirect:/users";
    }


    private List<Conference> findByActive(){
        List<Conference> conferences = new ArrayList<>();
        conferences.addAll(conferenceService.findByConfirmed());
        return conferences;
    }

    private List<User> getCreator(List<Conference> conferences){
        return conferenceService.getCreator(conferences);
    }

    private void setRole(User user, Long id){
        userService.setRole(user, id);
    }
    private void deleteUserById(Long id){
        userService.deleteUser(id);
    }

}
