package ua.xet.ConferenceApp.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.xet.ConferenceApp.dto.ConferenceDTO;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.ConferenceRepository;
import ua.xet.ConferenceApp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository confRepo;
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
                .build();
    }


}
