package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.service.ConferenceService;

@Controller
@RequestMapping("/conf")
public class ConferenceController {

    @Autowired
    ConferenceService conferenceService;

    @RequestMapping("/form")
    public String conferenceForm(Model model){
        Conference conference = new Conference();
        model.addAttribute("conference", conference);
        return "confform";
    }
    @PostMapping("/add")
    public String confAdd(Model model, @ModelAttribute Conference conference) throws Exception {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDTO user = (UserDTO)authentication.getPrincipal();
        model.addAttribute("conference", conference);
        addConference(conference, user.getUser());
        return "confform";
    }

    private void addConference(Conference conference, User user) throws Exception {
        conferenceService.addNewConference(conference, user);
    }

}
