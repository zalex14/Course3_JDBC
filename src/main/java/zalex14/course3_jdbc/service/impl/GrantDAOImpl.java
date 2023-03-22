package zalex14.course3_jdbc.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.Grants;
import zalex14.course3_jdbc.service.GrantDAO;

import java.util.List;

/**
 * Обработка прав пользователей
 */
public class GrantDAOImpl implements GrantDAO {

    /**
     * Создание(добавление)
     */
    @Override
    public void create(Grants grants) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.persist(grants);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение по id
     */
    @Override
    public Grants readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Grants.class, id);
        }
    }

    /**
     * Получение списка всех
     */
    @Override
    public List<Grants> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Grants", Grants.class).list();
        }
    }

    /**
     * Изменение по id
     */
    @Override
    public void update(Grants grants) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.persist(grants);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Удаление по id
     */
    @Override
    public void delete(Grants grants) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.remove(grants);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
