package zalex14.course3_jdbc.entity;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.util.Set;

/**
 * Права и роли пользователей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SelectBeforeUpdate
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long facultyId;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "users_faculty",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

    public Faculty(Access access) {
        this.name = access.name();
    }

    @Override
    public String toString() {
        return " Роль " + name + " " + facultyId + " " + users;
    }
}