package ua.xet.ConferenceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.xet.ConferenceApp.dto.ConferenceDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.service.ConferenceService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/stat")
public class StatisticsController {
    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    ConferenceService conferenceService;
    @RequestMapping("/confrequests")
    public String conferenceRequests(Model model){
        model.addAttribute("conferences", conferenceRepository.findAll());
        return "confrequest";
    }
    @RequestMapping(value = {"/statistic"})
    public String statisticsPage(){
        return "statistics";
    }

    @PostMapping("/confrequests/{id}/activate")
    public String activateConference(@PathVariable Long id, @ModelAttribute("conference") Conference conference, Model model){
        model.addAttribute("conference", conference);
        conferenceService.activateConferenceById(id, conference);
        return "activated";
    }
    @PostMapping("/confrequests/{id}/delete")
    public String deleteConference(@PathVariable Long id, Model model){
        conferenceService.deleteConferenceById(id);
        return "activated";
    }
}
