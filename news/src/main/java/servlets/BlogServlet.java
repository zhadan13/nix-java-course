package servlets;

import dao.PostDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "blog",
        urlPatterns = "/blog"
)
public class BlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        PostDao postDao = new PostDao();
        context.setAttribute("posts", postDao.getAll());

        RequestDispatcher view = req.getRequestDispatcher("jsp/blog.jsp");
        view.forward(req, resp);
    }
}