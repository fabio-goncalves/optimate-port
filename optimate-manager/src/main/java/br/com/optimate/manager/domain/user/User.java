package br.com.optimate.manager.domain.user;


import br.com.optimate.manager.domain.company.Company;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@UserDefinition
@Table(name = "optimate_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Column(unique = true)
    @Size(min = 6, max = 30)
    @Username
    private String username;
    @Password
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private boolean activated = false;
    @NotNull
    @Column(name = "receive_emails", nullable = false)
    private boolean receiveEmails = false;

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

    public User(Long id, String firstName, String lastName, String username, String password, String email, boolean activated, boolean receiveEmails, List<String> roles, Avatar avatar, List<Company> companyList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = BcryptUtil.bcryptHash(password);
        this.email = email;
        this.activated = activated;
        this.receiveEmails = receiveEmails;
        this.roles = roles;
        this.avatar = avatar;
        this.companyList = companyList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isReceiveEmails() {
        return receiveEmails;
    }

    public void setReceiveEmails(boolean receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
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
