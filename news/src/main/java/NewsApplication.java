import controllers.PostController;
import controllers.UserController;
import models.Post;
import models.User;

import java.time.LocalDate;
import java.util.Optional;


public class NewsApplication {
    public static void main(String[] args) {
        User user = new User(0L, "Artem", "asdfghj", 21, "artem@yahoo.com");

        UserController userController = new UserController();
        // userController.save(new User(0L, "ABC", "asdfghjkl", 10, "abc@google.com"));
        // userController.save(new User(1L, "DEF", "fdsbfdisb", 112, "DEF@google.com"));
        // userController.save(new User(2L, "AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userController.save(new User(3L, "AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userController.delete(1L);
        // userController.update(new User(0L, "QUQU", "asdfghjkl", 10, "abc@google.com"));
        // User user1 = userController.getById(0L).orElse(null);
        // System.out.println(user1);
        // System.out.println(userController.getAll());

        PostController postController = new PostController();
        // postController.save(new Post(0L, "Apple post", "New post ...", LocalDate.now(), 100, user));
        // postController.save(new Post(1L, "Hello World!", "New post ...", LocalDate.now(), 210, user));
        // postController.save(new Post(2L, "Hello World2", "New post ...", LocalDate.now(), 210, user));
        // postController.save(new Post(3L, "Hello World4", "New post ...", LocalDate.now(), 210, user));
        // postController.update(new Post(3L, "Hello World1111", "New post ...", LocalDate.now(), 210, user));
        // Post post = postController.getById(0L).orElse(null);
        // System.out.println(post);
        // User u = postController.getPostAuthor(0L).orElse(null);
        // System.out.println(u);
        // System.out.println(postController.getAll());
    }
}