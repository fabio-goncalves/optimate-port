package br.com.optimate.manager.domain.user;

import br.com.optimate.manager.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "personal_information")
@Data
public class PersonalInformation implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private boolean activated = false;
    @JsonIgnore
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public PersonalInformation(String firstName, String lastName, String email, boolean activated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
    }

    public PersonalInformation() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
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

        PersonalInformation personalInformation = (PersonalInformation) o;

        return Objects.equals(id, personalInformation.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
