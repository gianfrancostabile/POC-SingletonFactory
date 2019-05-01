package main.utils;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonFactory {
   private final static ConcurrentHashMap<String, Object> items = new ConcurrentHashMap<>();

   /**
    * Gets a Class instance, after that check if this class is into the item's ConcurrentHashMap,
    * if it doesn't creates a new instance and save into the map, else get the old instance,
    * finally returns the new/old instance.
    *
    * @param clazz Class to instantiate if this class isn't in items map
    * @return <b>clazz instance</b>; <b>null</b> if the class only have args constructors
    */
   public static <T> T getInstance(Class<T> clazz) {
      T value;
      String key = clazz.getName();
      if (items.get(key) == null) {
         value = createInstance(clazz);
         putItem(key, value);
      } else {
         value = (T) getItem(key);
      }
      return value;
   }

   /**
    * Creates a new instance of the class by reflection. <br>
    * No matter the access if it is public, protected or private;
    * but only can instantiate classes with non args constructor.
    *
    * @param clazz Class to instantiate
    * @return <b>class instance</b>; <b>null</b> if the class only have args constructors
    */
   private static <T> T createInstance(Class<T> clazz) {
      T value;
      try {
         Constructor<T> constructorClazz = clazz.getDeclaredConstructor();
         constructorClazz.setAccessible(true);
         value = constructorClazz.newInstance();
      } catch (Exception e) {
         value = null;
      }
      return value;
   }

   /**
    * Puts a new item into ITEMS map
    *
    * @param key
    * @param value
    *
    * @see ConcurrentHashMap#put(Object, Object)
    */
   private static void putItem(String key, Object value) {
      items.put(key, value);
   }

   /**
    * Returns the associated value with the key
    * @param key key value associated with a value
    * @return <b>associated value</b> if it exists; <b>null</b> otherwise
    *
    * @see ConcurrentHashMap#get(Object)
    */
   private static Object getItem(String key) {
      return items.get(key);
   }
}
