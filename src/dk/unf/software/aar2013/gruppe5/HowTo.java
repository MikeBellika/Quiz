package dk.unf.software.aar2013.gruppe5;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HowTo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_how_to);
		Button marathonButton = (Button) findViewById(R.id.marathonButton);
        Button sprintButton = (Button) findViewById(R.id.sprintButton);
        Button multiplayer = (Button) findViewById(R.id.multiplayer);
		marathonButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);

			}
		});


		sprintButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), SprintActivity.class);
				startActivity(intent);

			}
		});

        multiplayer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(intent);

            }
        });
		
	}

	}
