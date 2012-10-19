package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Parse.initialize(this, "tOEZhF5sN8u5csmsz1Eh6THVUiPTAW5jNad77Mvq",
				"kpbId3mKs77zBiaCctZ1cAt4K59cOS3ixt4CHCcp");
		
		
		
	}
}