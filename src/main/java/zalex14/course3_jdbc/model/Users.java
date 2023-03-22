package zalex14.course3_jdbc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Персональные данные пользователей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grant_id", nullable = false, referencedColumnName = "grant_id")
    private Grants grants;

    @Override
    public String toString() {
        return " User ID: " + userId + " " + name;
    }

    public enum StringFormat {
        F1, F2
    }

    public String toString(StringFormat format) {
        return switch (format) {
            case F1 -> " User ID: " + userId + " " + name + " (Логин: " + login + " Пароль: " + password + ")";
            case F2 -> " User ID: " + userId + " " + name + " (Логин: " + login + " Пароль: " + password + ") Права: "
                    + grants.getGrant();
        };
    }
}