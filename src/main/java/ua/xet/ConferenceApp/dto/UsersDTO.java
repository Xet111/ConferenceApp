package ua.xet.ConferenceApp.dto;

import lombok.*;
import ua.xet.ConferenceApp.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<User> users;
}
