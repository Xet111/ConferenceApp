package ua.xet.ConferenceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stat")
public class StatisticsController {
    @RequestMapping("/confrequests")
    public String conferenceRequests(Model model){
        return "confrequests";
    }
    @RequestMapping(value = {"/statistic"})
    public String statisticsPage(){
        return "statistics";
    }
}
