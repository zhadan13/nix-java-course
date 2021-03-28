import controllers.PostController;
import controllers.UserController;
import models.Post;
import models.User;


public class NewsApplication {
    public static void main(String[] args) {
        User user = new User(0L, "Artem", "asdfghj", 21, "artem@yahoo.com");

        UserController userController = new UserController();
        // userController.insertUser(new User(0L,"ABC", "asdfghjkl", 10, "abc@google.com"));
        // userController.insertUser(new User(1L,"DEF", "fdsbfdisb", 112, "DEF@google.com"));
        // userController.insertUser(new User(1L,"AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userController.insertUser(new User(1L,"AQQD", "sdsdascascscasc", 111, "no@google.com"));
        // userController.deleteUserById(1L);
        // userController.updateUser(new User(0L,"QUQU", "asdfghjkl", 10, "abc@google.com"));
        // User user = userController.getUserById(0L);
        // User user = userController.getUserByUserNameAndPassword("QUQU", "asdfghjkl");
        // System.out.println(userController.getAllUsers());

        PostController postController = new PostController();
        // postController.insertPost(new Post(0L, "Apple post", "New post ...", LocalDate.now(), 100, user));
        // postController.insertPost(new Post(1L, "Hello World!", "New post ...", LocalDate.now(), 210, user));
        // postController.insertPost(new Post(2L, "Hello World2", "New post ...", LocalDate.now(), 210, user));
        // postController.insertPost(new Post(3L, "Hello World4", "New post ...", LocalDate.now(), 210, user));
        // System.out.println(postController.getAllPosts());
    }
}