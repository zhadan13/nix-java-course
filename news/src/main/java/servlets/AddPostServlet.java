package servlets;

import dao.PostDao;
import dao.UserDao;
import models.Post;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addPostServlet")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserDao userDao = new UserDao();
        PostDao postDao = new PostDao();
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        User user = new User("testUser", "asdfghjkl", 10, "sample@google.com");
        userDao.save(user);
        postDao.save(new Post(title, text, LocalDate.now(), 0, user.getId()));
        RequestDispatcher view = req.getRequestDispatcher("jsp/blog.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher view = req.getRequestDispatcher("jsp/addPost.jsp");
        view.forward(req, resp);
    }
}