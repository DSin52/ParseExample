package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class GameActivity extends Activity {
	TextView gameInput, scoreUpdate;
	EditText guessNum;
	Button guessButton, leaderBoard;
	String player;
	int score;
	GameProgram gamer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		gameInput = (TextView) findViewById(R.id.textView1);
		scoreUpdate = (TextView) findViewById(R.id.textView2);

		guessNum = (EditText) findViewById(R.id.editText1);
		guessButton = (Button) findViewById(R.id.button1);
		leaderBoard = (Button) findViewById(R.id.button2);

		player = getIntent().getExtras().getString("name");
		System.out.println("Player Name: " + player);

		gamer = new GameProgram();

		guessButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String guessNumber = guessNum.getText().toString();
				int numberGuess = Integer.parseInt(guessNumber);

				System.out.println(numberGuess);

				if (!guessNumber.equals("") && guessNumber != null) {
					switch (gamer.guess(numberGuess)) {
					case -1:
						score = -100;
						scoreUpdate.setText("Score: " + score);
						gameInput.setText("You Lost!");
						gamer.isLoser = true;
						break;
					case 1:
						score += 50;
						scoreUpdate.setText("Score: " + score);
						gameInput.setText("Cold!");
						break;
					case 2:
						score += 100;
						scoreUpdate.setText("Score: " + score);
						gameInput.setText("WARM!");
						break;
					case 3:
						score += 200;
						scoreUpdate.setText("Score: " + score);
						gameInput.setText("You Won!");
						gamer.isWon = true;
						break;
					}
				}
			}
		});

		leaderBoard.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Parse.initialize(v.getContext(),
				// "tOEZhF5sN8u5csmsz1Eh6THVUiPTAW5jNad77Mvq",
				// "kpbId3mKs77zBiaCctZ1cAt4K59cOS3ixt4CHCcp");
				//
				// ParseObject VTTester = new ParseObject("VTTester");
				// VTTester.put("name", player);
				// VTTester.put("HighScore", score);
				// VTTester.saveInBackground();

				LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				Location location = locationManager
						.getLastKnownLocation(locationManager.getBestProvider(
								criteria, false));
				if (location != null) {
					System.out.println("Provider : "
							+ locationManager.getBestProvider(criteria, false)
							+ "has ben selected.");
					// Toast t = Toast.makeText(
					// getBaseContext(),
					// "Your Location is: Lattitude: "
					// + location.getLatitude()
					// + " \n Longitude: "
					// + location.getLongitude(),
					// Toast.LENGTH_LONG);
					Toast t = Toast
							.makeText(
									getBaseContext(),
									"Score Uploaded! Press Back Button to return to home!",
									Toast.LENGTH_LONG);
					t.show();
					Parse.initialize(getBaseContext(),
							"kOZGSvGCAZxPoUarRMmPIiXkssl7ig73QgIqGPzH",
							"T5F0wFraR1kpkxw5FRLoS9Sf9cpPhZ1u7mPRFq6a");

					ParseObject VTTester = new ParseObject("VTMaps");
					VTTester.put("name", player);
					VTTester.put("Lattitude", location.getLatitude());
					VTTester.put("Longitude", location.getLongitude());
					VTTester.put("HighScore", score);
					VTTester.saveInBackground();
				}

			}

		});

	}

}
