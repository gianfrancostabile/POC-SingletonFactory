package com.projects.poc;

import com.projects.poc.utils.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
      assertNull(getMethod_getItem().invoke(singletonClazz, new Object[]{null}));
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
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[]{1});
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PublicMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[]{1, 2, 3});
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PublicOthersTypesArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[]{1, "2", true, 4});
      if (instance instanceof PublicConstructor) {
         isValid = ((PublicConstructor) instance).message.equals("PublicConstructor - OTHERS TYPES ARGS");
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
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[]{1});
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_ProtectedMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[]{1, 2, 3});
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_ProtectedOthersTypesArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[]{1, "2", false, 4});
      if (instance instanceof ProtectedConstructor) {
         isValid = ((ProtectedConstructor) instance).message.equals("ProtectedConstructor - OTHERS TYPES ARGS");
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
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[]{1});
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - ONE ARG");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PrivateMoreArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[]{1, 2, 3});
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - MORE ARGS");
      }
      assertTrue(isValid);
   }

   @Test
   public void createInstance_PrivateOthersTypesArgs_ShouldReturnNewInstance() throws Exception {
      boolean isValid = false;
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[]{1, "2", false, 4});
      if (instance instanceof PrivateConstructor) {
         isValid = ((PrivateConstructor) instance).message.equals("PrivateConstructor - OTHERS TYPES ARGS");
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

   @Test(expected = InvocationTargetException.class)
   public void createInstance_AbstractConstructor_ShouldThrowException() throws Exception {
      getMethod_createInstance().invoke(singletonClazz, AbstractConstructor.class, null);
   }

   @Test
   public void createInstance_PublicDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[]{1, 2});
      assertNull(instance);
   }

   @Test
   public void createInstance_ProtectedDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[]{1, 2});
      assertNull(instance);
   }

   @Test
   public void createInstance_PrivateDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() throws Exception {
      Object instance = getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[]{1, 2});
      assertNull(instance);
   }

   @Test(expected = InvocationTargetException.class)
   public void createInstance_PublicErrorTypeArguments_ShouldThrowException() throws Exception {
      getMethod_createInstance().invoke(singletonClazz, PublicConstructor.class, new Object[]{"error", "2", false, 4});
   }

   @Test(expected = InvocationTargetException.class)
   public void createInstance_ProtectedErrorTypeArguments_ShouldThrowException() throws Exception {
      getMethod_createInstance().invoke(singletonClazz, ProtectedConstructor.class, new Object[]{"error", "2", false, 4});
   }

   @Test(expected = InvocationTargetException.class)
   public void createInstance_PrivateErrorTypeArguments_ShouldThrowException() throws Exception {
      getMethod_createInstance().invoke(singletonClazz, PrivateConstructor.class, new Object[]{"error", "2", false, 4});
   }

   @Test
   public void getInstance_PublicNonArgs_ShouldReturnNewInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class);
      assertEquals(value.message, "PublicConstructor - NON ARGS");
   }

   @Test
   public void getInstance_PublicOneArg_ShouldReturnNewInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1);
      assertEquals(value.message, "PublicConstructor - ONE ARG");
   }

   @Test
   public void getInstance_PublicMoreArg_ShouldReturnNewInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PublicConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_PublicOthersTypesArg_ShouldReturnNewInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, "2", true, 4);
      assertEquals(value.message, "PublicConstructor - OTHERS TYPES ARGS");
   }

   @Test
   public void getInstance_ProtectedNonArgs_ShouldReturnNewInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class);
      assertEquals(value.message, "ProtectedConstructor - NON ARGS");
   }

   @Test
   public void getInstance_ProtectedOneArg_ShouldReturnNewInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1);
      assertEquals(value.message, "ProtectedConstructor - ONE ARG");
   }

   @Test
   public void getInstance_ProtectedMoreArg_ShouldReturnNewInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "ProtectedConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_PrivateNonArgs_ShouldReturnNewInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class);
      assertEquals(value.message, "PrivateConstructor - NON ARGS");
   }

   @Test
   public void getInstance_PrivateOneArg_ShouldReturnNewInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1);
      assertEquals(value.message, "PrivateConstructor - ONE ARG");
   }

   @Test
   public void getInstance_PrivateMoreArg_ShouldReturnNewInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PrivateConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_PrivateOthersTypesArg_ShouldReturnNewInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, "2", true, 4);
      assertEquals(value.message, "PrivateConstructor - OTHERS TYPES ARGS");
   }

   @Test
   public void getInstance_PublicNonArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class);
      assertEquals(value.message, "PublicConstructor - NON ARGS");
      value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PublicConstructor - NON ARGS");
   }

   @Test
   public void getInstance_PublicOneArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1);
      assertEquals(value.message, "PublicConstructor - ONE ARG");
      value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PublicConstructor - ONE ARG");
   }

   @Test
   public void getInstance_PublicMoreArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PublicConstructor - MORE ARGS");
      value = SingletonFactory.getInstance(PublicConstructor.class, 1);
      assertEquals(value.message, "PublicConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_PublicOthersTypesArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "PublicConstructor - OTHERS TYPES ARGS");
      value = SingletonFactory.getInstance(PublicConstructor.class, 1);
      assertEquals(value.message, "PublicConstructor - OTHERS TYPES ARGS");
   }

   @Test
   public void getInstance_ProtectedNonArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class);
      assertEquals(value.message, "ProtectedConstructor - NON ARGS");
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "ProtectedConstructor - NON ARGS");
   }

   @Test
   public void getInstance_ProtectedOneArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1);
      assertEquals(value.message, "ProtectedConstructor - ONE ARG");
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "ProtectedConstructor - ONE ARG");
   }

   @Test
   public void getInstance_ProtectedMoreArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "ProtectedConstructor - MORE ARGS");
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1);
      assertEquals(value.message, "ProtectedConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_ProtectedOthersTypesArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "ProtectedConstructor - OTHERS TYPES ARGS");
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1);
      assertEquals(value.message, "ProtectedConstructor - OTHERS TYPES ARGS");
   }

   @Test
   public void getInstance_PrivateNonArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class);
      assertEquals(value.message, "PrivateConstructor - NON ARGS");
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PrivateConstructor - NON ARGS");
   }

   @Test
   public void getInstance_PrivateOneArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1);
      assertEquals(value.message, "PrivateConstructor - ONE ARG");
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PrivateConstructor - ONE ARG");
   }

   @Test
   public void getInstance_PrivateMoreArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PrivateConstructor - MORE ARGS");
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1);
      assertEquals(value.message, "PrivateConstructor - MORE ARGS");
   }

   @Test
   public void getInstance_PrivateOthersTypesArgs_WhenAnInstanceWasCreated_ShouldReturnOldInstance() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "PrivateConstructor - OTHERS TYPES ARGS");
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1);
      assertEquals(value.message, "PrivateConstructor - OTHERS TYPES ARGS");
   }

   @Test
   public void getInstance_PublicNonArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class);
      assertEquals(value.message, "PublicConstructor - NON ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PublicOneArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1);
      assertEquals(value.message, "PublicConstructor - ONE ARG");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PublicMoreArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PublicConstructor - MORE ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PublicConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PublicOthersTypesArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PublicConstructor value = SingletonFactory.getInstance(PublicConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "PublicConstructor - OTHERS TYPES ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PublicConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_ProtectedNonArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class);
      assertEquals(value.message, "ProtectedConstructor - NON ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_ProtectedOneArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1);
      assertEquals(value.message, "ProtectedConstructor - ONE ARG");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_ProtectedMoreArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2, 3);
      assertEquals(value.message, "ProtectedConstructor - MORE ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(ProtectedConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_ProtectedOthersTypesArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      ProtectedConstructor value = SingletonFactory.getInstance(ProtectedConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "ProtectedConstructor - OTHERS TYPES ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(ProtectedConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PrivateNonArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class);
      assertEquals(value.message, "PrivateConstructor - NON ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PrivateOneArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1);
      assertEquals(value.message, "PrivateConstructor - ONE ARG");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PrivateMoreArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2, 3);
      assertEquals(value.message, "PrivateConstructor - MORE ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PrivateConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_PrivateOthersTypesArgs_WhenAnInstanceWasCreatedAndAValueChanges_ShouldReturnOldInstanceWithUpdatedValue() {
      PrivateConstructor value = SingletonFactory.getInstance(PrivateConstructor.class, 1, "2", false, 4);
      assertEquals(value.message, "PrivateConstructor - OTHERS TYPES ARGS");
      value.message = "VALUE CHANGED";
      value = SingletonFactory.getInstance(PrivateConstructor.class);
      assertEquals(value.message, "VALUE CHANGED");
   }

   @Test
   public void getInstance_NonConstructor_ShouldReturnNewInstance() {
      Object instance = SingletonFactory.getInstance(NonConstructors.class);
      assertNotNull(instance);
   }

   @Test
   public void getInstance_NullClassParameter_ShouldReturnNull() {
      Object instance = SingletonFactory.getInstance(null);
      assertNull(instance);
   }

   @Test
   public void getInstance_AbstractConstructor_ShouldThrowException() {
      AbstractConstructor instance = SingletonFactory.getInstance(AbstractConstructor.class);
      assertNull(instance);
   }

   @Test
   public void getInstance_PublicDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() {
      Object instance = SingletonFactory.getInstance(PublicConstructor.class, 1, 2);
      assertNull(instance);
   }

   @Test
   public void getInstance_ProtectedDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() {
      Object instance = SingletonFactory.getInstance(ProtectedConstructor.class, 1, 2);
      assertNull(instance);
   }

   @Test
   public void getInstance_PrivateDoesNotExistsConstructorWithThatArgsAmount_ShouldReturnNull() {
      Object instance = SingletonFactory.getInstance(PrivateConstructor.class, 1, 2);
      assertNull(instance);
   }

   @Test
   public void getInstance_PublicErrorTypeArguments_ShouldThrowException() {
      PublicConstructor instance = SingletonFactory.getInstance(PublicConstructor.class, "error", "2", false, 4);
      assertNull(instance);
   }

   @Test
   public void getInstance_ProtectedErrorTypeArguments_ShouldThrowException() {
      ProtectedConstructor instance = SingletonFactory.getInstance(ProtectedConstructor.class, "error", "2", false, 4);
      assertNull(instance);
   }

   @Test
   public void getInstance_PrivateErrorTypeArguments_ShouldThrowException() {
      PrivateConstructor instance = SingletonFactory.getInstance(PrivateConstructor.class, "error", "2", false, 4);
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
