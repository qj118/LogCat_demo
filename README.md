LogCat
-------

- User munual
 + Click start
 + Operate mobiles arbitrarily
 + Click stop
 + Get log file in sdcard/LC_logger/

- Document description
 + LogThread.java -- The main file of log catching.
 + LogCat.java -- Start and stop threads.

- Problems
 + You need to build this project in Android native code to obtain the authority of system.
 + And add codes as follow in Manifest.xml
```xml
<manifest android:sharedUserId="android.uid.system">
```

- Others
 + `simulateHome()`in *MainActivity.java* is used to simulate home button.
 + `onKeyDown()` in *MainActivity.java* is used to simulate home button when users press the back button. 
