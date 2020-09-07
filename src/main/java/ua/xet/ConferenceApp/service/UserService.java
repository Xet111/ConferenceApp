package ua.xet.ConferenceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.xet.ConferenceApp.config.EncryptionConfig;
import ua.xet.ConferenceApp.dto.UserDTO;
import ua.xet.ConferenceApp.entity.RoleType;
import ua.xet.ConferenceApp.entity.User;
import ua.xet.ConferenceApp.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionConfig encryptionConfig;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    public void init(){
        userRepository.findByUsername("user").ifPresent(user -> {
            user.setPassword(encryptionConfig.getPasswordEncoder().encode("123"));
            userRepository.save(user);
        });

        userRepository.findByUsername("admin").ifPresent(user -> {
            user.setPassword(encryptionConfig.getPasswordEncoder().encode("123"));
            userRepository.save(user);
        });


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserDTO(userRepository.findByUsername(username).orElseThrow(() ->
               new  UsernameNotFoundException("user"+username+"was not found")));
    }

    public void addNewUser(User user){
        user.setPassword(encryptionConfig.getPasswordEncoder().encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRole(RoleType.ROLE_USER);
        userRepository.save(user);
    }
}
