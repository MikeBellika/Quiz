package dk.unf.software.aar2013.gruppe5;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Startpage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startpage);
		
		Button highscoreButton = (Button) findViewById(R.id.highscore);

		highscoreButton.setOnClickListener(new OnClickListener() {
            //ignored
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), HighscoreMenuActivity.class);
				startActivity(intent);

			}
		});
		Button gameModesButton = (Button) findViewById(R.id.startButton);

		gameModesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), HowTo.class);
				startActivity(intent);

			}
		});
		Button aboutButton = (Button) findViewById(R.id.about);

		aboutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), About.class);
				startActivity(intent);

			}
		});

	}
}
