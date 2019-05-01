package main;

import main.controller.UserController;
import main.domain.User;
import main.service.UserService;
import main.utils.SingletonFactory;

public class App {

   public static void main(String[] args) {
      UserController controller = SingletonFactory.getInstance(UserController.class, "3");
      controller.doSomething();
      UserService service = SingletonFactory.getInstance(UserService.class);
      service.dance();
      User user = SingletonFactory.getInstance(User.class);
      user.kill();

      UserController sameController = SingletonFactory.getInstance(UserController.class);
      sameController.doSomething();
   }

}
