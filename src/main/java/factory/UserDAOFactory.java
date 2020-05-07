package factory;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDAOFactory {
    private static UserDAO userDAO;

    private UserDAOFactory() {
    }

    public static UserDAO getUserDAO() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = UserDAOFactory.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String daotype = properties.getProperty("daotype");
        if (daotype.equals("dao.UserHibernateDAO")) {
            userDAO = UserHibernateDAO.getInstance();
        } else if (daotype.equals("dao.UserJdbcDAO")) {
            userDAO = UserJdbcDAO.getInstance();
        }
        return userDAO;
    }
}
