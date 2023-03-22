package zalex14.course3_jdbc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Права пользователей
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grants")
public class Grants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grant_id")
    private Integer grantId;
    @Column(name = "grant")
    private String grant;
    @OneToMany(mappedBy = "grants", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Users> users;

    @Override
    public String toString() {
        return " Права: " + grant + " сотрудники: " + users;
    }
}