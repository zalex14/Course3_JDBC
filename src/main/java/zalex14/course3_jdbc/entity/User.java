package zalex14.course3_jdbc.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.util.Set;

/**
 * Персональные данные пользователей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SelectBeforeUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "users_faculty",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<Faculty> faculty;

    public User(String name, String login, String password, Set<Faculty> faculty) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return " User ID " + userId + ": " + name + " (Логин: " + login + " Пароль: " + password + ") ";
    }

    public String toString(Integer format) {
        return switch (format) {
            case 0 -> " User ID: " + userId;
            case 1 -> " User ID: " + userId + " " + name;
            default -> " User ID: " + userId + " " + name + " " + faculty;
        };
    }
}