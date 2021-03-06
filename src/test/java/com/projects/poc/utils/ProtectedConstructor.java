package com.projects.poc.utils;

import org.junit.Ignore;

@Ignore
public class ProtectedConstructor extends AbstractConstructor {
   protected ProtectedConstructor() {
      message = "ProtectedConstructor - NON ARGS";
   }

   protected ProtectedConstructor(Object arg1) {
      message = "ProtectedConstructor - ONE ARG";
   }

   protected ProtectedConstructor(Object arg1, Object arg2, Object arg3) {
      message = "ProtectedConstructor - MORE ARGS";
   }

   protected ProtectedConstructor(int arg1, String arg2, boolean arg3, Object arg4) {
      message = "ProtectedConstructor - OTHERS TYPES ARGS";
   }
}
