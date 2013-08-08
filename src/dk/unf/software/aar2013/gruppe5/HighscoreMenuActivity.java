package dk.unf.software.aar2013.gruppe5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HighscoreMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore_menu);
		Button marathonButton = (Button) findViewById(R.id.marathonButton);
        Button sprintButton = (Button) findViewById(R.id.sprintButton);
        Button multiplayer = (Button) findViewById(R.id.multiplayer);
		marathonButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), HighscoreActivity2.class);
				startActivity(intent);

			}
		});


		sprintButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), HighScoreActivitySprint.class);
				startActivity(intent);

			}
		});
		
	}

	}
