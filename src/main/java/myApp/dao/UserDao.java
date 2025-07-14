package myApp.dao;

import myApp.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import myApp.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao implements HibernateDao<User> {
    public User getById(int user_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, user_id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> getAll() {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    public User findByEmail(String email) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, email);
    }
}
