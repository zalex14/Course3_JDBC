package zalex14.course3_jdbc.dao.impl;

import org.hibernate.*;
import zalex14.course3_jdbc.config.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.dao.UserDAO;
import zalex14.course3_jdbc.entity.*;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    // 1. Получаем список пользователей (без ролей)
    @Override
    public List<User> readAllUsers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u ORDER BY u.id ASC", User.class).getResultList();
        }
    }

    // 2. Конкретный пользователь по id
    @Override
    public User readUserById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.createQuery("FROM User u JOIN FETCH u.faculty WHERE u.id = :id", User.class)
                    .setParameter("id", id).getSingleResultOrNull();
            transaction.commit();
            return user;
        }
    }

    // Конкретный пользователь с его ролями из БД по id
    @Override
    public String userAndFaculty(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.createQuery("FROM User u JOIN FETCH u.faculty WHERE u.id = :id", User.class)
                    .setParameter("id", id).getSingleResultOrNull();
            transaction.commit();
            return user.toString() + user.getFaculty(); //  .toString(User.StringFormat.valueOf("ALL"))
        }
    }

    // 3. Список пользователей по конкретной роли
    @Override
    public List<Faculty> readAllFaculty() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Faculty f JOIN FETCH f.users ORDER BY f.name ASC, f.id ASC",
                    Faculty.class).getResultList();
        }
    }

    // 3. Список пользователей по конкретной роли
    @Override
    public List<User> readUsersByFaculty(Access access) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u JOIN FETCH u.faculty f WHERE f.name =:name " +
                    "ORDER BY f.id ASC", User.class).setParameter("name", access.toString()).getResultList();
        }
    }

    // 4. Удаление пользователя
    @Override
    public String delete(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            return "Удален пользователь ID: " + user.getUserId();
        }
    }

    // 5. Добавление нового пользователя с ролями
    @Override
    public Long add(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.persist(user);
                transaction.commit();
                return user.getUserId();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    // 6. Редактировать существующего пользователя
    @Override
    public Boolean update(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.merge(user);
                transaction.commit();
                return true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
