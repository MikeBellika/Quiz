package dk.unf.software.aar2013.gruppe5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HighscoreActivity2 extends FragmentActivity  {
	
	public void onBackPressed()  
	{  
	    //do whatever you want the 'Back' button to do  
	    //as an example the 'Back' button is set to start a new Activity named 'NewActivity'  
		Intent intent = new Intent(getApplicationContext(),
				Startpage.class);
		startActivity(intent);
		
	    return;  
	} 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore_activity2);

		TableLayout layout = (TableLayout) findViewById(R.id.tableLayout);

		TableRow header = new TableRow(getApplicationContext());
		
		
		TextView pos = new TextView(getApplicationContext());
		pos.setText("Pos");
		TextView navn = new TextView(getApplicationContext());
		navn.setText("Navn");
		TextView score = new TextView(getApplicationContext());
		score.setText("Score");
		
		
		navn.setTextSize(30);
		pos.setTextSize(30);
		score.setTextSize(30);
		score.setTextColor(Color.BLACK);
		pos.setTextColor(Color.BLACK);
		navn.setTextColor(Color.BLACK);
		
		
		layout.addView(header);
		header.addView(pos);
		header.addView(new LinearLayout(getApplicationContext()), 20, 0);
		header.addView(navn);	
		header.addView(new LinearLayout(getApplicationContext()), 20, 0);
		header.addView(score);

		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(this);
		for (int i = 0; i < 10; i++) {
			TextView tv = new TextView(getApplicationContext());
			tv.setText(i+1 + "");
			TextView tv2 = new TextView(getApplicationContext());
			tv2.setText(p.getString("highscoreName" + i , "Navnet blev ikke fundet"));
			TextView tv3 = new TextView(getApplicationContext());
			tv3.setText(p.getInt("highscore" + i , 0)+"");

			TableRow tr = new TableRow(getApplicationContext());
			tr.addView(tv);	
			tr.addView(new LinearLayout(getApplicationContext()));
			tr.addView(tv2);
			tr.addView(new LinearLayout(getApplicationContext()));
			tr.addView(tv3) ;

			layout.addView(tr);			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.highscore_activity2, menu);
		return true;
	}

}
