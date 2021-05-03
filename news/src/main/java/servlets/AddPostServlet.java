package servlets;

import dao.PostDao;
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
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@WebServlet("/addPostServlet")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        PostDao postDao = new PostDao();
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        // SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        // DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().toFormatter(Locale.US);
        Post post = new Post((long) new SecureRandom().nextInt(), title, text, LocalDate.parse(date), 0,
                new User((long) new SecureRandom().nextInt(), "Artem", "artem@google.com"), 1L);
        boolean result = postDao.save(post);
        // System.out.println(result);
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