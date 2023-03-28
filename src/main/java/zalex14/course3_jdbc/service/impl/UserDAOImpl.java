package zalex14.course3_jdbc.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.*;
import zalex14.course3_jdbc.service.UserDAO;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Операции с пользователями
 */
public class UserDAOImpl implements UserDAO {

    // 1. Получаем список пользователей (без ролей)
    @Override
    public List<User> readAllName() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From User", User.class).list();
        }
    }

    // 2. Получение пользователя по id Obj
    @Override
    public User read(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.get(User.class, id);
            return session.get(User.class, id);
        }
    }

    //  3. Удаление пользователя void
    @Override
    @Transactional
    public void delete(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.remove(user);
                session.flush();
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    // 4 Добавляем пользователя void
    @Override
    public void create(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.persist(user);
                session.flush();
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    // 5 Изменение по пользователя Obj
    @Override
    public void update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.merge(user);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}