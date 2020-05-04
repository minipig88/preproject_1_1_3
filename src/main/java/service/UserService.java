package service;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserJdbcDAO getUserDAO() {
        return UserJdbcDAO.getInstance();
    }

    private UserHibernateDAO getUserHibernateDAO() { return UserHibernateDAO.getInstance(); }

    public List<User> getAllUser() {
        return getUserHibernateDAO().getAllUser();
    }

    public boolean addUser(User user) {
        return getUserHibernateDAO().addUser(user);
    }

    public boolean deleteUserByID(long id) {
        return getUserHibernateDAO().deleteUserByID(id);
    }

    public User getUserByID(long id) {
        return getUserHibernateDAO().getUserByID(id);
    }

    public boolean updateUser(long id, String firstName, String secondName, String email) {
        return getUserHibernateDAO().updateUser(id, firstName, secondName, email);
    }
}
