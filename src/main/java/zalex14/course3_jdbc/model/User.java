package zalex14.course3_jdbc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

/**
 * Персональные данные пользователей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "user_grant",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),    //
            inverseJoinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"))  //
    private Set<Faculty> faculties;

    public User(String name, String login, String password, Set<Faculty> faculties) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        return " User ID: " + userId + " " + name + " (Логин: " + login + " Пароль: " + password + ") ";
    }

    public enum StringFormat {
        NAME, ALL
    }

    public String toString(StringFormat format) {
        return switch (format) {
            case NAME -> " User ID: " + userId + " " + name;
            case ALL -> " User ID: " + userId + " " + name + " (Логин: " + login + " Пароль: " + password + ") Права: "
                    + faculties;
        };
    }
}