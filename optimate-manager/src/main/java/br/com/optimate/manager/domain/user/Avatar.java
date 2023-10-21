package br.com.optimate.manager.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String avatar35;

    @Lob
    private String avatar70;

    @Lob
    private String avatar220;

    @JsonIgnore
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Avatar() {
    }

    public Avatar(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar35() {
        return avatar35;
    }

    public void setAvatar35(String avatar35) {
        this.avatar35 = avatar35;
    }

    public String getAvatar70() {
        return avatar70;
    }

    public void setAvatar70(String avatar70) {
        this.avatar70 = avatar70;
    }

    public String getAvatar220() {
        return avatar220;
    }

    public void setAvatar220(String avatar220) {
        this.avatar220 = avatar220;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avatar avatar = (Avatar) o;

        return Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
