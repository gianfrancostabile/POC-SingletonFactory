# POC-SingletonFactory

This project is a POC for a Singleton Factory.

**NOTE:**<br>
Use the SingletonFactory's class if you want. OPEN SOURCE :) <br>
In this first commit only works with classes with a non args constructor. <br>

The SingletonFactory is a class that only has a unique public static method (**getInstance(Class clazz)**) and others private methods. Basically, you send a Class instance and N arguments, after that checks if this class was instantiated, if it wasn't, creates a new instance and it is saved into a ConcurrentHashMap; else, get the old instance. Finally returns the new/old instance. 

## DEPENDENCIES
* Maven
* JUnit 4.12

## HOW TO START
**1.** Install **GIT** with this [link](https://git-scm.com/downloads) <br>
**2.** Install **MAVEN** with this [link](https://maven.apache.org/download.cgi) <br>
**3.** Clone this project: <br>
&nbsp;&nbsp;**3.1.** Open a new terminal <br>
&nbsp;&nbsp;**3.2.** Move an empty folder from your PC. Example: (If you are using Windows) **cd C:\folder\another\end** <br>
&nbsp;&nbsp;**3.3.** Use the next command: **git clone https://github.com/gianfrancostabile/POC-SingletonFactory.git** this command 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;will copy all folders and file that have this project to your empty folder <br>
&nbsp;&nbsp;**3.4.** You have this project cloned <br>
&nbsp;&nbsp;**3.5.** The last step type: **cd POC-SingletonFactory** <br>
**4.** Open a console, if you already open it in the last step it is not necessary. And type: **mvn clean install** <br>
**5.** After that your project is ready :D
