package zalex14.course3_jdbc.service.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.*;
import zalex14.course3_jdbc.service.UserDAO;

import java.util.List;

/**
 * Операции с пользователями
 */
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    // 1. Получаем список пользователей (без ролей)
    @Override
    public List<User> readAllName() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From User", User.class).list();
        }
    }

    //  2. Получение пользователя с правами по id
    @Override
    public User readByIdFaculty(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            return session.get(User.class, id);
            User result = session.get(User.class, id);
            result.getFaculties().size();
            return result;
        }
    }

    // 3. Получение пользователя по id
    @Override
    public User readByIdName(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            return session.get(User.class, id);
            User result = session.get(User.class, id);
            result.getFaculties().size();
            return result;
        }
    }


    //  4. Удаление пользователя по id
    @Override
    public void remove(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.remove(user);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    // 5 Добавляем пользователя
    @Override
    public void create(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.save(user);
//                session.persist(user);
//                session.flush();
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    // Изменение конкретного объекта Employee в базе по id
    @Override
    public void update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.update(user);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}