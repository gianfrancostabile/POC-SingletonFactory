package com.projects.poc.utils;

import org.junit.Ignore;

@Ignore
public class PublicConstructor extends AbstractConstructor{
   public PublicConstructor() { message = "PublicConstructor - NON ARGS"; }
   public PublicConstructor(Object arg1) { message = "PublicConstructor - ONE ARG"; }
   public PublicConstructor(Object arg1, Object arg2, Object arg3) { message = "PublicConstructor - MORE ARGS"; }
}