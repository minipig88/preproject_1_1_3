package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("pages/LoginPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=utf-8");
        if (email != null && password != null && !email.isBlank() && !password.isBlank()) {
            if (userService.validationUser(email, password)) {
                User user = userService.getUserByEmailAndPassword(email, password);
                HttpSession session = req.getSession();
                session.setAttribute("loginUser", user);
                resp.sendRedirect("/admin/list");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.setAttribute("message", "invalid email or password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ErrorPage.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Bad request");
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ErrorPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
