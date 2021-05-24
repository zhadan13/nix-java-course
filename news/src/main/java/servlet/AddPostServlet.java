package servlet;

import dao.PostDAO;
import dao.UserDAO;
import model.Post;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "addPostServlet", urlPatterns = "/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        PostDAO postDao = new PostDAO();
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        User user = new User("testUser", "123456", 25, "user@google.com");
        userDao.save(user);
        postDao.save(new Post(title, text, LocalDate.now(), 0, user.getId()));
        resp.sendRedirect("/blog");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/addPost.jsp").forward(req, resp);
    }
}
