package com.projects.poc;

import com.projects.poc.controller.UserController;
import com.projects.poc.domain.User;
import com.projects.poc.service.UserService;

public class App {

   public static void main(String[] args) {
      UserController controller = SingletonFactory.getInstance(UserController.class);
      controller.doSomething();
      UserService service = SingletonFactory.getInstance(UserService.class);
      service.dance();
      User user = SingletonFactory.getInstance(User.class);
      user.kill();

      UserController sameController = SingletonFactory.getInstance(UserController.class);
      sameController.doSomething();
   }

}
