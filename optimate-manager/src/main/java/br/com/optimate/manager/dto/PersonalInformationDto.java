package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.user.User;
import lombok.Data;

@Data
public class PersonalInformationDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = false;
    private User user;
}
