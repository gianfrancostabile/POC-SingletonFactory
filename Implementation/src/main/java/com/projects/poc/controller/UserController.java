package com.projects.poc.controller;

public class UserController {
   public String extra;

   public UserController(int a, int b, String c, boolean d) {
      extra = "FOUR ARGS";
   }

   protected UserController() {
      extra = "NON ARGS";
   }

   private UserController(String name, int age) {
      extra = "TWO ARGS";
   }

   public void doSomething() {
      System.out.println("YEY I am into UserController#doSomething() - ".concat(extra));
   }
}
