package ua.xet.ConferenceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.xet.ConferenceApp.entity.Conference;
import ua.xet.ConferenceApp.repository.ConferenceRepository;

import java.util.Optional;




@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository confRepo;
    public Optional<Conference>findByConfirmed(boolean confirmed){
        return confRepo.findByConfConfirmed(confirmed);
    }

}
