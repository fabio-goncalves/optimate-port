package br.com.optimate.manager.domain.user;


import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.domain.type.StatusType;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@UserDefinition
@Data
@Table(name = "optimate_user")
public class User implements AbstractEntity, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "receive_emails", nullable = false)
    private boolean receiveEmails = false;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private PersonalInformation personalInformation;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private StatusType status;
    @Roles
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    private List<String> roles;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Avatar avatar;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Company> companyList;

    public User() {
    }

    public User(PersonalInformation personalInformation, boolean receiveEmails, StatusType status, List<String> roles, Avatar avatar, List<Company> companyList) {
        this.personalInformation = personalInformation;
        this.receiveEmails = receiveEmails;
        this.status = status;
        this.roles = roles;
        this.avatar = avatar;
        this.companyList = companyList;
    }

    @Serial
    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
    }

    @Serial
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
