package br.com.optimate.manager.domain.user;


import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.domain.type.StatusType;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

@Entity
@UserDefinition
@Data
@Table(name = "optimate_user")
public class User implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private PersonalInformation personalInformation;
    @Column(name = "receive_emails")
    private boolean receiveEmails;
    @Column(unique = true)
    @NotNull
    @Size(min = 6, max = 30)
    @Username
    private String username;
    @Password
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;
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

    private User(UserBuilder userBuilder) {
        this.personalInformation = userBuilder.personalInformation;
        this.receiveEmails = userBuilder.receiveEmails;
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.status = userBuilder.status;
        this.roles = userBuilder.roles;
        this.avatar = userBuilder.avatar;
        this.companyList = userBuilder.companyList;
    }

    public User() {

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

    public static class UserBuilder {
        private PersonalInformation personalInformation;
        private boolean receiveEmails = false;
        private final String username;
        private final String password;
        private final StatusType status;
        private final List<String> roles;
        private Avatar avatar;
        private List<Company> companyList;

        public UserBuilder(String username, String password, StatusType status, List<String> roles) {
            this.username = username;
            this.password = password;
            this.status = status;
            this.roles = roles;
        }

        public UserBuilder personalInformation(PersonalInformation personalInformation) {
            this.personalInformation = personalInformation;
            return this;
        }

        public UserBuilder receiveEmails(boolean receiveEmails) {
            this.receiveEmails = receiveEmails;
            return this;
        }

        public UserBuilder avatar(Avatar avatar) {
            this.avatar = avatar;
            return this;
        }

        public UserBuilder companyList(List<Company> companyList) {
            this.companyList = companyList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
