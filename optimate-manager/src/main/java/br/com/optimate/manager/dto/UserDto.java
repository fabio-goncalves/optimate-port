package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.domain.type.StatusType;
import br.com.optimate.manager.domain.user.Avatar;
import br.com.optimate.manager.domain.user.PersonalInformation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.runtime.annotations.IgnoreProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private PersonalInformation personalInformation;
    private StatusType status;
    @NotNull
    private boolean receiveEmails = false;
    private List<String> roles;
    private Avatar avatar;
    private List<Company> companyList;

}
