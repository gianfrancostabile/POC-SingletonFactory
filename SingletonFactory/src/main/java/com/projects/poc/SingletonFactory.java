package com.projects.poc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonFactory {
   private static ConcurrentHashMap<String, Object> items = new ConcurrentHashMap<>();

   /**
    * Gets a Class instance, after that check if this class is into the item's ConcurrentHashMap,
    * if it doesn't creates a new instance and save into the map, else get the old instance,
    * finally returns the new/old instance.
    *
    * @param clazz Class to instantiate if this class isn't in items map
    * @param args All args of the constructor
    * @return <b>clazz instance</b>; <b>null</b> if the class does not have constructors or if
    *    does not exists a constructor with the same amount of parameters or an error happens on
    *    instantiate the object
    */
   public static <T> T getInstance(Class<T> clazz, Object... args) {
      T value;
      String key = clazz.getName();
      if (items.get(key) == null) {
         try{
            value = createInstance(clazz, args);
         } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            value = null;
         }
         putItem(key, value);
      } else {
         value = (T) getItem(key);
      }
      return value;
   }

   /**
    * Creates a new instance of the class by reflection. <br>
    * No matter the access if it is public, protected or private. <br>
    *  First checks if the class contains at least one constructor,
    * if it is true check for each constructor if the amount of parameters that
    * it needs is equals to length of args param, if it is true creates the new instance
    * and ends the for loop.
    *
    * @param clazz Class to instantiate
    * @param args All args of the constructor
    * @return <b>class instance</b>; <b>null</b> if the class does not have constructors or if
    *    does not exists a constructor with the same amount of parameters or an error happens on
    *    instantiate the object
    *
    * @throws InstantiationException if the class that declares the underlying constructor
    *    represents an abstract class.
    * @throws IllegalAccessException if this Constructor object is enforcing Java language
    *    access control and the underlying constructor is inaccessible.
    * @throws InvocationTargetException if the number of actual and formal parameters differ;
    *    if an unwrapping conversion for primitive arguments fails; or if, after possible unwrapping,
    *    a parameter value cannot be converted to the corresponding formal parameter type by
    *    a method invocation conversion; if this constructor pertains to an enum type.
    */
   @SuppressWarnings("unchecked")
   private static <T> T createInstance(Class<T> clazz, Object... args)
         throws InstantiationException, IllegalAccessException, InvocationTargetException {
      T value = null;
      if (clazz != null) {
         args = (args == null) ? new Object[]{} : args;
         List<Constructor> constructors = new ArrayList<>(Arrays.asList(clazz.getDeclaredConstructors()));
         for (Constructor<T> constructor : constructors) {
            constructor.setAccessible(true);
            if (constructor.getParameterCount() == args.length) {
               value = constructor.newInstance(args);
               break;
            }
         }
      }
      return value;
   }

   /**
    * Puts a new item into ITEMS map
    *
    * @param key unique value associated with a value
    * @param value an object that will be associated with a key
    * @see ConcurrentHashMap#put(Object, Object)
    */
   private static void putItem(String key, Object value) {
      if (key != null && value != null) {
         items.put(key, value);
      }
   }

   /**
    * Returns the associated value with the key
    *
    * @param key unique value associated with a value
    * @return <b>associated value</b> if it exists; <b>null</b> otherwise or key is null
    * @see ConcurrentHashMap#get(Object)
    */
   private static Object getItem(String key) {
      Object value = null;
      if (key != null) {
         value = items.get(key);
      }
      return value;
   }
}
