package ua.xet.ConferenceApp.dto;

import lombok.*;
import ua.xet.ConferenceApp.entity.Conference;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConferencesDTO {
    private List<Conference> conferences;
}
