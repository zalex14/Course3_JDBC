package zalex14.course3_jdbc.model;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_grant",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),    //
            inverseJoinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"))  //
//    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Faculty> faculties;

    public User(String name, String login, String password, Set<Faculty> faculties) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        return " User ID " + userId + ": " + name + " (Логин: " + login + " Пароль: " + password + ") ";
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