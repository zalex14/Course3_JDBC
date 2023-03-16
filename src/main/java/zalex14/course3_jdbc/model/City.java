package zalex14.course3_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Города сотрудников
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int cityId;
    private String cityName;
}