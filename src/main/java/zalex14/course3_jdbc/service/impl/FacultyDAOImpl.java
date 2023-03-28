package zalex14.course3_jdbc.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.Faculty;
import zalex14.course3_jdbc.service.FacultyDAO;

import java.util.List;

/**
 * Обработка прав пользователей
 */
public class FacultyDAOImpl implements FacultyDAO {

    //  1. Получение списка пользователей по роли
    @Override
    public List<Faculty> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Faculty", Faculty.class).list();
        }
    }

    // 2.  Получение роли по id
    @Override
    public Faculty read(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Faculty.class, id);
        }
    }

    // 5. Добавляем пользователя
    @Override
    public void create(Faculty faculty) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.persist(faculty);   // session.save(faculty);
                session.flush();
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}