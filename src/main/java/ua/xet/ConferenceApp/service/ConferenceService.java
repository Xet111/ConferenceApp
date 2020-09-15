package ua.xet.ConferenceApp.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.xet.ConferenceApp.controller.ConferenceDuration;
import ua.xet.ConferenceApp.dto.ConferenceDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository confRepo;

    @PostConstruct
    @Scheduled(cron = "0 * * * * *")
    public void deleteOutdated(){
        for(Conference conf : confRepo.findAll()){
            if((LocalDateTime.now()
                    .minusMinutes(ConferenceDuration.DURATION_MINUTES)
                    .compareTo(conf.getDateActive()) == 1)){
                confRepo.delete(conf);
            }
        }

    }

    public List<Conference> findByConfirmed(){
        return confRepo.findByActiveTrue().stream().collect(Collectors.toList());
    }

    public void activateConferenceById(Long id, Conference conference){
        conference = confRepo.getOne(id);
        conference.setActive(true);
        confRepo.save(conference);
    }
    public void deleteConferenceById(Long id){
//        Conference conference = confRepo.getOne(id.longValue());
//        User user = conference.getUserId();
//        user.getConferences().remove(conference);
        confRepo.deleteById(id);
    }

    public List<User> getCreator(List<Conference> conferences){
        List<User> users = new ArrayList<>();
        for(Conference conf : conferences){
            users.add(conf.getUserId());
        }
        return users;
    }
    public void addNewConference(Conference conference, User user) throws Exception{
        addConference(buildConference(conference, user));
    }
    public Conference getActiveById(Long id)  {
        try{
        if(!confRepo.findById(id).get().isActive()) {
            throw new Exception("Conference must be Active");
        }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return confRepo.findById(id).get();
    }

    public Conference getById(Long id){
        return confRepo.findById(id).get();
    }
    private void addConference(Conference conference)throws Exception{
        try{
            confRepo.save(conference);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private Conference buildConference(Conference conference, User user){
        return conference.builder()
                .name(conference.getName())
                .userId(user)
                .active(false)
                .dateCreated(new Date())
                .dateActive(conference.getDateActive())
                .theme(conference.getTheme())
                .body(conference.getBody())
                .build();
    }


}
