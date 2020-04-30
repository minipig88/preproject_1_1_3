package servlet;

import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=utf-8");
        try {
            long id = Long.parseLong(req.getParameter("id"));
            if (id > 0 && userService.deleteUserByID(id)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("message", "Error delete");
                RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ErrorPage.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("message", "Bad request");
            RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ErrorPage.jsp");
            dispatcher.forward(req, resp);
            e.printStackTrace();
        }
    }
}
