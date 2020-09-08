package ua.xet.ConferenceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.xet.ConferenceApp.config.EncryptionConfig;
import ua.xet.ConferenceApp.config.LocalPasswordEncoder;
import ua.xet.ConferenceApp.controller.RegistrationValidation;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.RoleType;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocalPasswordEncoder localPasswordEncoder;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    public void init(){
        userRepository.findByUsername("user").ifPresent(user -> {
            user.setPassword(localPasswordEncoder.encode("123"));
            userRepository.save(user);
        });

        userRepository.findByUsername("admin").ifPresent(user -> {
            user.setPassword(localPasswordEncoder.encode("123"));
            userRepository.save(user);
        });


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserDTO(userRepository.findByUsername(username).orElseThrow(() ->
               new  UsernameNotFoundException("user"+username+"was not found")));
    }

    public void addNewUser(User user) throws Exception{
        saveUser(buildUser(user));
    }

    private void saveUser(User user) throws Exception{
        try{
            userRepository.save(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private User buildUser(User user){
        return user.builder()
                .username(user.getUsername())
                .password(localPasswordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .role(RoleType.ROLE_USER)
                .build();
    }
}
