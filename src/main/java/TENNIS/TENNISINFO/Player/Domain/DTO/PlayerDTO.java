package TENNIS.TENNISINFO.Player.Domain.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {
    private String name;
    private String surName;
    private String birth;
    private String country;
    private String debutYear;
    private String weight;
    private String height;
    private String foreHand;
    private String backHand;
    private String coach;

}
