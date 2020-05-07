package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();

    boolean addUser(User user);

    boolean deleteUserByID(long id);

    User getUserByID(long id);

    boolean updateUser(long id, String firstName, String secondName, String email, String password, String role);

    User getUserByEmailAndPassword(String email, String password);

    boolean validationUser(String email, String password);
}
