package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.RoleType;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.service.ConferenceService;

import java.time.LocalDateTime;

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
        UserDTO user = getUserDTO();
        model.addAttribute("conference", conference);
        addConference(conference, user.getUser());
        return "index";
    }
    @RequestMapping("/conference/{id}")
    public String conferencePage(@PathVariable Long id, Model model){
        UserDTO user = getUserDTO();
        if(user.getUser().getRole().equals(RoleType.ROLE_ADMIN)){
         model.addAttribute("conference", findById(id)) ;
        }
        else {
            if(LocalDateTime.now().compareTo(findById(id).getDateActive()) >= ConferenceConstants.COMPARE_EQUALS){
            if(LocalDateTime.now().minusMinutes(ConferenceConstants.DURATION_MINUTES).compareTo(findById(id).getDateActive()) <= ConferenceConstants.COMPARE_EQUALS) {
                model.addAttribute("conference", findActiveById(id));
                setConferenceVisitors(id, user);
            }
            else
                deleteById(id);
        }
        }
        return "conference";
    }

    private void addConference(Conference conference, User user) throws Exception {
        conferenceService.addNewConference(conference, user);
    }
    private Conference findActiveById(Long id){
        return conferenceService.getActiveById(id);
    }
    private Conference findById(Long id){
        return conferenceService.getById(id);
    }
    private void deleteById(Long id){
        conferenceService.deleteConferenceById(id);
    }
    private void setConferenceVisitors(Long id, UserDTO user){
        conferenceService.setConferenceVisitors(id, user);
    }
    private UserDTO getUserDTO(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDTO user = (UserDTO)authentication.getPrincipal();
        return user;
    }

}
