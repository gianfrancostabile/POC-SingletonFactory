package com.projects.poc;

import com.projects.poc.utils.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(JUnit4.class)
public class SingletonFactoryTest extends TestCase {

   private Class<SingletonFactory> singletonClazz = SingletonFactory.class;
   private Field itemsField = null;
   private Method getItemMethod = null;
   private Method putItemMethod = null;
   private Method createInstanceMethod = null;

   @Before
   public void setUp() throws Exception {
      overrideItemsValuesMap(new ConcurrentHashMap<>());
   }

   @Test
   public void putAndGetItem_ShouldAddAndReturnTheNewItem() throws Exception {
      String expected = "Something";
      getMethod_putItem().invoke(singletonClazz, "com.project.poc.controller.UserController", expected);
      String result = getMethod_getItem()
                        .invoke(singletonClazz, "com.project.poc.controller.UserController")
                        .toString();
      assertEquals("Something", result);
   }

   @Test
   public void getItem_ShouldReturnNull_WhenKeyIsNull() throws Exception {
      getMethod_putItem().invoke(singletonClazz, "com.project.poc.controller.UserController", "Something");
      assertNull(getMethod_getItem().invoke(singletonClazz, new Object[] { null }));
   }

   @Test
   public void putItem_ShouldNotAddNewItem_WhenKeyIsNull() throws Exception {
      getMethod_putItem().invoke(singletonClazz, null, "Something");
      assertNull(getMethod_getItem().invoke(singletonClazz, "com.project.poc.controller.UserController"));
   }

   @Test
   public void putItem_ShouldNotAddNewItem_WhenValueIsNull() throws Exception {
      getMethod_putItem().invoke(singletonClazz, "com.project.poc.controller.UserController", null);
      assertNull(getMethod_getItem().invoke(singletonClazz, "com.project.poc.controller.UserController"));
   }

   @Test
   public void putItem_ShouldNotAddNewItem_WhenKeyAndValueAreNull() throws Exception {
      getMethod_putItem().invoke(singletonClazz, null, null);
      assertNull(getMethod_getItem().invoke(singletonClazz, "com.project.poc.controller.UserController"));
   }

   @Test
   public void createInstance_PublicNonArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, null);
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - NON ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PublicOneArg_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[] { 1 });
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PublicMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[] { 1, 2, 3 });
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_ProtectedNonArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, null);
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - NON ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_ProtectedOneArg_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[] { 1 });
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_ProtectedMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[] { 1, 2, 3 });
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PrivateNonArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, null);
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - NON ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PrivateOneArg_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[] { 1 });
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PrivateMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[] { 1, 2, 3 });
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_NonConstructor_ShouldReturnNewInstance() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, NonConstructors.class, null);
      assertNotNull(instance);
   }

   @Test
   public void createInstance_NullClassParameter_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, null, null);
      assertNull(instance);
   }

   @Test(expected = Exception.class)
   public void createInstance_AbstractConstructor_ShouldThrowException() throws Exception {
      getMethod_createInstance().invoke(singletonClazz, AbstractConstructor.class, null);
   }

   @Test
   public void createInstance_PublicDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[] {1, 2});
      assertNull(instance);
   }

   @Test
   public void createInstance_ProtectedDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[] {1, 2});
      assertNull(instance);
   }

   @Test
   public void createInstance_PrivateDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[] {1, 2});
      assertNull(instance);
   }

   @Ignore
   private void overrideItemsValuesMap(Object value) throws Exception {
      if (itemsField == null) {
         itemsField = singletonClazz.getDeclaredField("items");
         itemsField.setAccessible(true);
      }
      itemsField.set(singletonClazz, value);
   }

   @Ignore
   private Method getMethod_putItem() throws Exception {
      if (putItemMethod == null) {
         putItemMethod = singletonClazz.getDeclaredMethod("putItem", String.class, Object.class);
         putItemMethod.setAccessible(true);
      }
      return putItemMethod;
   }

   @Ignore
   private Method getMethod_getItem() throws Exception {
      if (getItemMethod == null) {
         getItemMethod = singletonClazz.getDeclaredMethod("getItem", String.class);
         getItemMethod.setAccessible(true);
      }
      return getItemMethod;
   }

   @Ignore
   private Method getMethod_createInstance() throws Exception {
      if (createInstanceMethod == null) {
         createInstanceMethod = singletonClazz.getDeclaredMethod("createInstance", Class.class, Object[].class);
         createInstanceMethod.setAccessible(true);
      }
      return createInstanceMethod;
   }
}
