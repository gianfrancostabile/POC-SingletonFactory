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

      System.out.println("BEFORE GET SAMECONTROLLER: ".concat(controller.extra));
      UserController sameController = SingletonFactory.getInstance(UserController.class);
      System.out.println("AFTER GET SAMECONTROLLER: ".concat(sameController.extra));
      sameController.extra = "I DON'T NOW";
      System.out.println("AFTER VALUE CHANGE: ".concat(sameController.extra));

      UserController otherController = SingletonFactory.getInstance(UserController.class);
      System.out.println("AFTER GET OTHERCONTROLLER ".concat(otherController.extra));
      otherController = null;

      UserController anotherOneController = SingletonFactory.getInstance(UserController.class);
      System.out.println("AFTER GET OTHERCONTROLLER ".concat(anotherOneController.extra));
   }

}
