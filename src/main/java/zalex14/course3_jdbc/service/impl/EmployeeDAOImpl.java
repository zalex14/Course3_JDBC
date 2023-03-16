package zalex14.course3_jdbc.service.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.Employee;
import zalex14.course3_jdbc.service.EmployeeDAO;

import java.util.List;

/**
 * Обработка персональных данных сотрудников
 */
@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {
    /**
     * Создание(добавление) сущности Employee в таблицу
     */
    @Override
    public void create(Employee employee) {
// Формируем запрос к базе с помощью PreparedStatement
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    /**
     * Получение конкретного объекта Employee по id
     */
    @Override
    public Employee readById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }

    /**
     * Получение списка всех объектов Employee из базы
     */
    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = (List<Employee>) HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return employeeList;
    }

    /**
     * Изменение конкретного объекта Employee в базе по id
     */
    @Override
    public void updateById(int id, Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    /**
     * Удаление конкретного объекта Employee из базы по id
     */
    @Override
    public void deleteById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}