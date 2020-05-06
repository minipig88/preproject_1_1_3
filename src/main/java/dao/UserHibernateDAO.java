package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private static UserHibernateDAO userHibernateDAO;
    private SessionFactory sessionFactory;

    private UserHibernateDAO() {
        sessionFactory = createSessionFactory();
    }

    public static UserHibernateDAO getInstance() {
        if (userHibernateDAO == null) {
            userHibernateDAO = new UserHibernateDAO();
        }
        return userHibernateDAO;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(user);
        transaction.commit();
        session.close();
        return id > 0;
    }

    @Override
    public boolean deleteUserByID(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE id=:paramID");
        query.setParameter("paramID", id);
        int count = query.executeUpdate();
        transaction.commit();
        session.close();
        return count == 1;
    }

    @Override
    public User getUserByID(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id=:paramID");
        query.setParameter("paramID", id);
        User user = (User) query.uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    public boolean updateUser(long id, String firstName, String secondName, String email, String password, String role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "UPDATE User SET firstName=:paramFirstName, secondName=:paramSecondName, email=:paramEmail," +
                        " password=:paramPassword, role=:paramRole WHERE id=:paramID");
        query.setParameter("paramFirstName", firstName);
        query.setParameter("paramSecondName", secondName);
        query.setParameter("paramEmail", email);
        query.setParameter("paramPassword", password);
        query.setParameter("paramRole", role);
        query.setParameter("paramID", id);
        int count = query.executeUpdate();
        transaction.commit();
        session.close();
        return count == 1;
    }
}
