package zalex14.course3_jdbc.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.City;
import zalex14.course3_jdbc.service.CityDAO;

import java.util.List;

/**
 * Обработка городов сотрудников
 * CRUD для City
 */
public class CityDAOImpl implements CityDAO {

    /**
     * Создание(добавление) сущности City
     */
    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.save(city);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение конкретного объекта City по id
     */
    @Override
    public City readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    /**
     * Получение списка всех городов из базы
     */
    @Override
    public List<City> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From City", City.class).list();
        }
    }

    /**
     * Изменение конкретного объекта City в базе по id
     */
    @Override
    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.update(city);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Удаление конкретного объекта City из базы по id
     */
    @Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.delete(city);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
