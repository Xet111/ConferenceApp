package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.repository.UserRepository;

import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ConferenceRepository conferenceRepository;

    @RequestMapping(value = {"/","/index"})
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
        model.addAttribute("conferences",conferenceRepository.findByActiveTrue().stream().collect(Collectors.toList()));
        return "schedule";
    }

    @RequestMapping(value = {"/conference"})
    public String conferencePage(Model model){
        return "conference";
    }


}
