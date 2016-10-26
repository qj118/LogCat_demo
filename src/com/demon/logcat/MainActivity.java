package com.demon.logcat;



import java.util.Calendar;

import com.lge.logcat.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends Activity {

	private static final String RECORD_STATUS = "record_status";
	boolean isStart = false;
	TextView mStart;
	TextView mPath;
	LogCat mLogCat;
	String mFolderName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(savedInstanceState != null)
		{
			isStart = savedInstanceState.getBoolean(RECORD_STATUS);
		}
		getView();
	}
	
	private void getView()
	{
		mStart = (TextView)findViewById(R.id.start);
		mPath = (TextView)findViewById(R.id.path);
		if(isStart)
		{
			statusisStart();
		}
		else
		{
			statusisStop();
		}
		mStart.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v)
			{
				
				if(!isStart)
				{
					mFolderName = filenameGen();
					isStart = true;
					mLogCat = new LogCat(mFolderName);
					mLogCat.start();
					statusisStart();
					simulateHome();
				}
				else
				{
					isStart = false;
					mLogCat.stop();
					statusisStop();
				}
				
			}
		});
	}
	
	private void simulateHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		this.startActivity(intent);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			simulateHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@SuppressWarnings("deprecation")
	private void statusisStart() {
		mStart.setText(R.string.stop);
		mPath.setText("sdcard/LC_logger/logger_" + mFolderName);
		mStart.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_stop_red));
	}
	
	@SuppressWarnings("deprecation")
	private void statusisStop() {
		mStart.setText(R.string.start);
		mPath.setText("");
		mStart.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_start_green));
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putBoolean(RECORD_STATUS, isStart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onResume() {
		super.onResume();
		getView();
	}
	
	String filenameGen()
	{
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_YEAR);
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return "log_" + day + hour + minute + second;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle(R.string.action_about);
			builder.setMessage(R.string.about_content);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setCancelable(false);
			builder.setPositiveButton(R.string.about_confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			builder.create().show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
