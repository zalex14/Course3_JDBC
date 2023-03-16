package zalex14.course3_jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Персональные данные сотрудников
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private City city;

    @Override
    public String toString() {
        return "ID: " + employeeId +
                " Имя: " + firstName + " " + lastName +
                " Пол: " + gender +
                " Возраст: " + age +
                " Город: " + getCity().getCityName();
    }
}