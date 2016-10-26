package com.demon.logcat;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public class LogThread extends Thread
{
	final String TAG = "LOGTHREAD";
	
	String mType;
	String mLogPath;
	String mFolderName;
	Process mProc = null;
	String mCommand = "logcat -v time -b ";
	
	LogThread(String type, String logPath, String folderName)
	{
		mType = type;
		mFolderName = folderName;
		mLogPath = logPath;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run()
	{
		try
		{
			mProc = Runtime.getRuntime().exec(mCommand + mType);
			Log.i(TAG, mType + " start!");
			String str;
			BufferedReader reader = new BufferedReader(new InputStreamReader(mProc.getInputStream()));
			FileOutputStream file;
			file = new FileOutputStream(mLogPath + "/" + mFolderName + "_" + mType + ".log");
			while((str = reader.readLine()) != null)
			{
				str += "\n";
				file.write(str.getBytes());
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			Log.i(TAG, mType + " error!");
		}
	}
	
	public void finish()
	{
		mProc.destroy();
	}
}
