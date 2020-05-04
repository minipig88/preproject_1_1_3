package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static UserJdbcDAO userJdbcDAO;

    private UserJdbcDAO() {
    }

    public static UserJdbcDAO getInstance() {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO();
        }
        return userJdbcDAO;
    }

    private Connection getConnection() {
        return DBHelper.getInstance().getConnection();
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_table");
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO user_table (firstName, secondName, email) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getEmail());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteUserByID(long id) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM user_table WHERE id = ?")) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserByID(long id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM user_table WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("secondName"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(long id, String firstName, String secondName, String email) {
        boolean result = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE user_table SET firstName = ?, secondName = ?, email = ? WHERE id = ?")) {
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, email);
            statement.setLong(4, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
