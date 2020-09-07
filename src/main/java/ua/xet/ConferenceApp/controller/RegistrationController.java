package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.UserRepository;
import ua.xet.ConferenceApp.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @RequestMapping("/page")
    public String registrationPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/add")
    public String userRegistration(Model model, @ModelAttribute User user){
        model.addAttribute("user", user);
        addNewUser(user);
        return "activated";
    }



    private void addNewUser(User user){
        userService.addNewUser(user);
    }

}
