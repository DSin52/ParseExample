package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LeaderBoards extends Activity {
	ListView scoreListView;
	ArrayList<String> scores;
	ArrayList<ParseObject> parseObjectList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaderboard);

		scoreListView = (ListView) findViewById(R.id.listView1);

		scores = new ArrayList<String>();
		parseObjectList = new ArrayList<ParseObject>();
		Parse.initialize(getBaseContext(),
				"kOZGSvGCAZxPoUarRMmPIiXkssl7ig73QgIqGPzH",
				"T5F0wFraR1kpkxw5FRLoS9Sf9cpPhZ1u7mPRFq6a");
		ParseQuery query = new ParseQuery("VTMaps");
		query.orderByDescending("HighScore");

		query.findInBackground(new FindCallback() {

			@Override
			public void done(List<ParseObject> scoreList, ParseException e) {
				if (e == null) {
					parseObjectList = (ArrayList<ParseObject>) scoreList;
					for (int i = 0; i < scoreList.size(); i++) {

						scores.add("Name: "
								+ scoreList.get(i).getString("name")
								+ "\nScore: "
								+ scoreList.get(i).getInt("HighScore"));

						System.out.println(scores.get(i));
					}

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							LeaderBoards.this,
							android.R.layout.simple_list_item_1, scores);
					scoreListView.setAdapter(adapter);
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}

		});

		scoreListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> list, View v, int position,
					long id) {
				AlertDialog.Builder alert = new AlertDialog.Builder(v
						.getContext());

				alert.setTitle("Leaderboard Information");

				alert.setMessage("Name: "
						+ parseObjectList.get(position).getString("name")
						+ "\nHigh Score: "
						+ parseObjectList.get(position).getInt("HighScore")
						+ "\nPlayed At: "
						+ parseObjectList.get(position).getUpdatedAt()
						+ "\nObject Id: "
						+ parseObjectList.get(position).getObjectId()
						+ "\nLattitude: "
						+ parseObjectList.get(position).getDouble("Lattitude")
						+ "\nLongitude: "
						+ parseObjectList.get(position).getDouble("Longitude"));

				alert.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}

						});
				
				alert.create();
				
				alert.show();

			}
		});
	}
}
