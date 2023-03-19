package zalex14.course3_jdbc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Города сотрудников
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employees;

    public City(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Город " + cityId + ": " + cityName + " сотрудники: " + employees;
    }
}