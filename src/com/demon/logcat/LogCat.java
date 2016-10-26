package com.demon.logcat;

import java.io.File;
public class LogCat {
	
	final String TAG = "LOGCAT";

	LogThread mThread_Main ;
	LogThread mThread_System;
	LogThread mThread_Radio;
	LogThread mThread_Events;
	String mFolderName;
	
	LogCat(String foldername)
	{
		mFolderName = foldername;
	}
	
		
	public void start() {
		// TODO Auto-generated method stub
//		String folderName = filenameGen();
		String logPath = "sdcard/LC_logger/logger_" + mFolderName;
		File file = new File("/" + logPath);
		if(!file.exists() && !file.isDirectory())
		{
			file.mkdirs();
			System.out.println("Create");
			
		}
		else
		{
			System.out.println("Exist");
		}
		
		mThread_Main = new LogThread("main", logPath, mFolderName);
		mThread_System = new LogThread("system", logPath, mFolderName);
		mThread_Radio = new LogThread("radio", logPath, mFolderName);
		mThread_Events = new LogThread("events", logPath, mFolderName);

		mThread_Main.start();
		mThread_System.start();
		mThread_Radio.start();
		mThread_Events.start();
	}


	
	
	public void stop()
	{
		mThread_Main.finish();
		mThread_System.finish();
		mThread_Radio.finish();
		mThread_Events.finish();
	}
}