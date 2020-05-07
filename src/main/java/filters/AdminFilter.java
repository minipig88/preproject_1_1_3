package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    private User user = null;
    private HttpServletRequest request = null;
    private HttpSession session = null;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) servletRequest;
        session = request.getSession();
        user = (User) session.getAttribute("loginUser");
        if (user != null) {
            if (user.getRole().equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletRequest.setAttribute("user", user);
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/pages/UserInfo.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/");
        }
    }
}
