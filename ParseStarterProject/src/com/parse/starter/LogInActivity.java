package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends Activity {

	EditText nameField;
	Button logInButton, leaderBoards;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		nameField = (EditText) findViewById(R.id.editText1);

		logInButton = (Button) findViewById(R.id.button1);
		
		leaderBoards = (Button) findViewById(R.id.button2);

		logInButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				name = nameField.getText().toString();
				if (!name.equals("") && name != null) {
					System.out.println("Name: " + name);
					Intent gameIntent = new Intent(v.getContext(),
							GameActivity.class);
					Bundle passName = new Bundle();
					passName.putString("name", name);
					gameIntent.putExtras(passName);
					startActivity(gameIntent);

				}
			}

		});

		leaderBoards.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent leaderBoardIntent = new Intent(v.getContext(),
						LeaderBoards.class);
				startActivity(leaderBoardIntent);
			}
		});
	}

}
