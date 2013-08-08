package dk.unf.software.aar2013.gruppe5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SprintActivity extends FragmentActivity implements
        HighscoreAddDialogSprint.HighscoreAddDialogListener {

	private TextView score;
	private TextView qu;
	private Button ba1;// Button answer 1
	private Button ba2;
	private Button ba3;
	private Button ba4;
	QuizMechanics qm = new QuizMechanics();
	List<Integer> answerList;
	List<Integer> questionList;
	ArrayList<String> answers;
	int i = 0;
	public TextView count;
	boolean cancel;
	CountDownTimer countDownTimer;

    ArrayList<Integer> highScores;
    ArrayList<String> highScoresNames;
    SharedPreferences prefs;
	

	public void reset() {
		i = 0;
	}
	

    @Override
    public void onFinishEditDialog(String inputText) {
        Log.d("yolol", "4202");
        highScores.add(qm.score);
        highScoresNames.add(inputText);

        for (int i = 0; i < highScores.size(); i++) {
            for (int j = highScores.size() - 1; j > i; j--) {
                if (highScores.get(i) > highScores.get(j)) {
                    int tmpint = highScores.get(i);
                    String tmpstr = highScoresNames.get(i);

                    highScores.set(i, highScores.get(j));
                    highScoresNames.set(i, highScoresNames.get(j));
                    highScores.set(j, tmpint);
                    highScoresNames.set(j, tmpstr);
                }
            }
        }

        // Collections.sort(highScores);
        Collections.reverse(highScores);
        Collections.reverse(highScoresNames);
        Editor edit = prefs.edit();
        // Log.d("no!", highScores.get(i) + "");
        for (int i = 0; i != 10; i++) {
            Log.d("no!", highScores.get(i) + "");
            edit.putInt("highscoreSprint" + i, highScores.get(i));
            edit.putString("highscoreNameSprint" + i, highScoresNames.get(i));
        }
        edit.commit();
        // 123

        Intent intent = new Intent(getApplicationContext(),
                HighScoreActivitySprint.class);
        startActivity(intent);
    }

	public void highScore() {
        if (qm.score > highScores.get(9)) {
            FragmentManager fm = getSupportFragmentManager();
            HighscoreAddDialogSprint editNameDialog = new HighscoreAddDialogSprint();
            editNameDialog.show(fm, "fragment_edit_name");
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    HighScoreActivitySprint.class);
            startActivity(intent);
        }
	}

	public boolean checkAnswer(String a, String ca) {
		if (a.equals(ca)) {
			Toast.makeText(getApplicationContext(), "RIGTIGT!",
					Toast.LENGTH_SHORT).show();

			qm.score++;
			score = (TextView) findViewById(R.id.score);
			score.setText("Dine points: " + qm.score);
			Log.d("ASASGAS", qm.allQuestions.size() + "");
			if (i+1 >= qm.allQuestions.size()) {
				Toast.makeText(getApplicationContext(), "Ikke flere spørgsmål tilbage.",
						Toast.LENGTH_LONG).show();
				ArrayList<Integer> temp = new ArrayList<Integer>();
				return false;
			}
            i++;
			nextQuestion();
			return true;
		} else {
			Toast.makeText(getApplicationContext(), "FORKERT!",
					Toast.LENGTH_SHORT).show();
			qm.score--;
			score = (TextView) findViewById(R.id.score);
			score.setText("Dine points: " + qm.score);
			if (i+1 >= qm.allQuestions.size()) {
				Toast.makeText(getApplicationContext(), "Ikke flere spørgsmål tilbage.",
						Toast.LENGTH_LONG).show();
				ArrayList<Integer> temp = new ArrayList<Integer>();
				return false;
			}
            i++;
//            highScore();
			nextQuestion();
			return false;
		}
	}

	public List<Integer> randomList(int x) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i != x; i++) {
			result.add(i);
		}
		Collections.shuffle(result);
		return result;
	}

	public void nextQuestion() {
		answerList = randomList(4);
		Log.d("as", questionList + " " + i);
		answers = qm.allQuestions.get(questionList.get(i)).getAnswers(); // Answer1
		Log.d("heyo", answers + "");
		// a2 = qm.allQuestions.get(i).answers.get(list.get(1));
		// a3 = qm.allQuestions.get(i).answers.get(list.get(2));
		// a4 = qm.allQuestions.get(i).answers.get(list.get(3));

		ba1 = (Button) findViewById(R.id.at1);
		ba1.setText(answers.get(answerList.get(0)));
		ba2 = (Button) findViewById(R.id.at2);
		ba2.setText(answers.get(answerList.get(1)));
		ba3 = (Button) findViewById(R.id.at3);
		ba3.setText(answers.get(answerList.get(2)));
		ba4 = (Button) findViewById(R.id.at4);
		ba4.setText(answers.get(answerList.get(3)));
		qu = (TextView) findViewById(R.id.question);
		qu.setText(qm.allQuestions.get(questionList.get(i)).question + "");
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sprint);
		count = (TextView) findViewById(R.id.counter);
        count.setTextColor(Color.RED);
//		counter();
		countDownTimer = new MyCountdownTimer(60000, 1000, count, this);
		countDownTimer.start();

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = prefs.edit();
		// edit.putInt("highscore1", i);
		// edit.clear();
		edit.commit();
        highScores = new ArrayList<Integer>();
        for (int i = 0; i != 10; i++) {
            highScores.add(prefs.getInt("highscoreSprint" + i, 0));
        }

        highScoresNames = new ArrayList<String>();
        for (int i = 0; i != 10; i++) {
            highScoresNames.add(prefs.getString("highscoreNameSprint" + i, ""));
        }
		
		questionList = randomList(qm.allQuestions.size());

		nextQuestion();
		Log.d("test", answers.get(0) + " " + answerList.get(0));
		
		ba1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(0)),
						answers.get(0))) {
					
				}
			}
		});

		ba2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(1)),
						answers.get(1))) {
				}
			}
		});

		ba3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(2)),
						answers.get(0))) {
				}
			}
		});

		ba4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(3)),
						answers.get(0))) {
				}
			}
		});
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
