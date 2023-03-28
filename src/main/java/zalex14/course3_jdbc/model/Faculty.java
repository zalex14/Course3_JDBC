package zalex14.course3_jdbc.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Права пользователей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long grantId;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_grant",
            joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"),     //
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")  //
    )
//    @LazyCollection(LazyCollectionOption.EXTRA)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

    public Faculty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " Роль ID" + grantId + ":  " + users + "\n";
    }

    public enum Faculties {
        ROOT, ADMIN, BACKUP, USER, GUEST
    }
}