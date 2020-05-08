package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/new")
public class NewUserServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    private User user;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/admin/pages/UserForm.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        resp.setContentType("text/html;charset=utf-8");
        if (firstName != null && secondName != null && email != null && password != null && role != null &&
                !firstName.isBlank() && !secondName.isBlank() && !email.isBlank() && !password.isBlank() && !role.isBlank()) {
            if (userService.addUser(user = new User(firstName, secondName, email, password, role))) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.sendRedirect("/admin/list");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("message", "Error create");
                req.getRequestDispatcher("/pages/ErrorPage.jsp").forward(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Bad request");
            req.getRequestDispatcher("/pages/ErrorPage.jsp").forward(req, resp);
        }
    }
}

