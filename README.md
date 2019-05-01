# POC-SingletonFactory

This project is a POC for a Singleton Factory.

**NOTE:**<br>
Use the SingletonFactory's class if you want. OPEN SOURCE :) <br>
In this first commit only works with classes with a non args constructor. <br>

The SingletonFactory is a class that only has a unique public static method (**getInstance(Class clazz)**) and others private methods. Basically, you send a Class instance, after that check if this class is into the item's ConcurrentHashMap, if it does not creates a new instance and save into the map, else get the old instance, finally returns the new/old instance. 
