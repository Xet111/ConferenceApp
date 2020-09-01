package ua.xet.ConferenceApp.dto;

import lombok.*;
import ua.xet.ConferenceApp.entity.Conference;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConferenceDTO {
    private Conference conference;
}
