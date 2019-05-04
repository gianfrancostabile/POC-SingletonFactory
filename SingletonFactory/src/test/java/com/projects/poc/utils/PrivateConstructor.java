package com.projects.poc.utils;

import org.junit.Ignore;

@Ignore
public class PrivateConstructor extends AbstractConstructor {
   private PrivateConstructor() {
      message = "PrivateConstructor - NON ARGS";
   }

   private PrivateConstructor(Object arg1) {
      message = "PrivateConstructor - ONE ARG";
   }

   private PrivateConstructor(Object arg1, Object arg2, Object arg3) {
      message = "PrivateConstructor - MORE ARGS";
   }

   private PrivateConstructor(int arg1, String arg2, boolean arg3, Object arg4) {
      message = "PrivateConstructor - OTHERS TYPES ARGS";
   }
}
