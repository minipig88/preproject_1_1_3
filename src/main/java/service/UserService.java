package service;

import dao.UserDAO;
import factory.UserDAOFactory;
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

    private UserDAO getUserDAO() {
        return UserDAOFactory.getUserDAO();
    }

    public List<User> getAllUser() {
        return getUserDAO().getAllUser();
    }

    public boolean addUser(User user) {
        return getUserDAO().addUser(user);
    }

    public boolean deleteUserByID(long id) {
        return getUserDAO().deleteUserByID(id);
    }

    public User getUserByID(long id) {
        return getUserDAO().getUserByID(id);
    }

    public boolean updateUser(long id, String firstName, String secondName, String email, String password, String role) {
        return getUserDAO().updateUser(id, firstName, secondName, email, password, role);
    }

    public boolean validationUser(String email, String password) {
        return getUserDAO().validationUser(email, password);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return getUserDAO().getUserByEmailAndPassword(email, password);
    }
}
