package zalex14.course3_jdbc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer grantId;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "user_grant",
            joinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"),     //
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")  //
    )
    private Set<User> users;

    public Faculty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " Роль ID: " + grantId ;
    }
}