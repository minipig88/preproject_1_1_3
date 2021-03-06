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
import java.io.PrintWriter;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            user = userService.getUserByID(id);
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/UserForm.jsp");
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        if (firstName != null && secondName != null && email != null && id > 0 &&
                !firstName.isBlank() && !secondName.isBlank() && !email.isBlank()) {
            if (userService.updateUser(id, firstName, secondName, email)) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.sendRedirect("/");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("message", "Error update");
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
