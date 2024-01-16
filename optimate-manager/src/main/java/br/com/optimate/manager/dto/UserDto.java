package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.domain.type.StatusType;
import br.com.optimate.manager.domain.user.Avatar;
import br.com.optimate.manager.domain.user.PersonalInformation;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private PersonalInformation personalInformation;
    private StatusType status;
    private boolean receiveEmails = false;
    private String username;
    private String password;
    private List<String> roles;
    private Avatar avatar;
    private List<Company> companyList;

}
