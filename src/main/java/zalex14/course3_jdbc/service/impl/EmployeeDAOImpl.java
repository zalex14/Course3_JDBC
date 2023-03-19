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
 * CRUD для Employee
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
            try {
                Transaction transaction = session.beginTransaction();
                session.save(employee);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение конкретного объекта Employee по id
     */
    @Override
    public Employee readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    /**
     * Получение списка всех объектов Employee из базы
     */
    @Override
    public List<Employee> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Employee", Employee.class).list();
        }
    }

    /**
     * Изменение конкретного объекта Employee в базе по id
     */
    @Override
    public void update(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.update(employee);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Удаление конкретного объекта Employee из базы по id
     */
    @Override
    public void delete(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.delete(employee);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}