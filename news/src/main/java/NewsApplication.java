import dao.PostDao;
import dao.UserDao;
import models.Post;
import models.User;

import java.time.LocalDate;

public class NewsApplication {
    public static void main(String[] args) {
        // User user = new User(0L, "Artem", "asdfghj", 21, "artem@yahoo.com");

        // UserDao userDao = new UserDao();
        // userDao.save(new User(0L, "ABC", "asdfghjkl", 10, "abc@google.com"));
        // userDao.save(new User(1L, "DEF", "fdsbfdisb", 112, "DEF@google.com"));
        // userDao.save(new User(2L, "AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userDao.save(new User(3L, "AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userDao.delete(1L);
        // userDao.update(new User(0L, "QUQU", "asdfghjkl", 10, "abc@google.com"));
        // User user1 = userDao.getById(0L).orElse(null);
        // System.out.println(user1);
        // System.out.println(userDao.getAll());

        // PostDao postDao = new PostDao();
        // postDao.save(new Post(0L, "Apple post", "New post ...", LocalDate.now(), 100, user));
        // postDao.save(new Post(1L, "Hello World!", "New post ...", LocalDate.now(), 210, user));
        // postDao.save(new Post(2L, "Hello World2", "New post ...", LocalDate.now(), 210, user));
        // postDao.save(new Post(3L, "Hello World4", "New post ...", LocalDate.now(), 210, user));
        // postDao.update(new Post(3L, "Hello World1111", "New post ...", LocalDate.now(), 210, user));
        // Post post = postDao.getById(0L).orElse(null);
        // System.out.println(post);
        // User u = postDao.getPostAuthor(0L).orElse(null);
        // System.out.println(u);
        // System.out.println(postDao.getAll());
    }
}