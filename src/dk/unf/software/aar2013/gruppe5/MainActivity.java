package dk.unf.software.aar2013.gruppe5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dk.unf.software.aar2013.gruppe5.HighscoreAddDialog.HighscoreAddDialogListener;

public class MainActivity extends FragmentActivity implements
        HighscoreAddDialogListener {
    private TextView score;
    private TextView qu;
    private Button ba1;// Button answer 1
    private Button ba2;
    private Button ba3;
    private Button ba4;
    QuizMechanics qm = new QuizMechanics();
    List<Integer> answerList;
    List<Integer> questionList;
    ArrayList<String> answers = new ArrayList<String>();
    int i = 0;
    ArrayList<Integer> highScores;
    ArrayList<String> highScoresNames;
    SharedPreferences prefs;
    private final int questionAmount = 14;


    public void reset() {
        i = 0;
    }

    public void highScore() {
        if (qm.score > highScores.get(9)) {
            FragmentManager fm = getSupportFragmentManager();
            HighscoreAddDialog editNameDialog = new HighscoreAddDialog();
            editNameDialog.show(fm, "fragment_edit_name");
            //
            // Log.d("yolol", "4202");
            // highScores.add(qm.score);
            // Collections.sort(highScores);
            // Collections.reverse(highScores);
            // Editor edit = prefs.edit();
            // // Log.d("no!", highScores.get(i) + "");
            // for (int i = 0; i != 10; i++) {
            // Log.d("no!", highScores.get(i) + "");
            // edit.putInt("highscore" + i, highScores.get(i));
            // }
            // edit.commit();
            // //123
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    HighscoreActivity2.class);
            startActivity(intent);
        }
        // Intent intent = new Intent(getApplicationContext(),
        // HighscoreActivity2.class);
        // startActivity(intent);
    }

    public boolean checkAnswer(String a, String ca) {
        if (a == ca) {
            Toast.makeText(getApplicationContext(), "RIGTIGT!",
                    Toast.LENGTH_SHORT).show();
            //ignore this commentdk
            qm.correct();
            score = (TextView) findViewById(R.id.score);
            score.setText("Dine points: " + qm.score);
            Log.d("ASASGAS", qm.allQuestions.size() + "");
            if (qm.score == 61) {
                qm.reset();
                ArrayList<Integer> temp = new ArrayList<Integer>();
                highScore();
                return false;
            }
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "FORKERT!",
                    Toast.LENGTH_SHORT).show();
            qm.reset();
            Log.d("yolo", highScores.get(0) + " 420");
            highScore();
            return false;
        }
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
            edit.putInt("highscore" + i, highScores.get(i));
            edit.putString("highscoreName" + i, highScoresNames.get(i));
        }
        edit.commit();
        // 123

        Intent intent = new Intent(getApplicationContext(),
                HighscoreActivity2.class);
        startActivity(intent);
    }

    public List<Integer> randomList(int x) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i != x; i++) {
            result.add(i);
        }
        Collections.shuffle(result);
        return result;
    }


    static InputStream is = null;
    static String json = "";
    private final static String TAG_QUESTIONS = "questions";
    private final static String TAG_QUESTION = "question";
    private final static String TAG_ANSWERS = "answers";
    private final static String TAG_ANSWER = "answer";
    JSONArray jQuestions = null;
    JSONArray jAnswers = null;
    ArrayList<String> answersTest = new ArrayList<String>();
    String answer;
    ArrayList<String> questions = new ArrayList<String>();
    public void nextQuestion() {


        JSONParser jParser = new JSONParser(getApplicationContext());
        JSONObject json = jParser.parseJson(loadJSONFromAsset());
        Log.d("yoloswags", "test");
        try {
            jQuestions = json.getJSONArray(TAG_QUESTIONS);
            Log.d("yoloswag", "test123");

            JSONObject q = jQuestions.getJSONObject(questionList.get(i));
            String question = q.getString(TAG_QUESTION);
            Log.d("JSON", question + " zomg");
            jAnswers = q.getJSONArray(TAG_ANSWERS);
            qu = (TextView) findViewById(R.id.question);
            qu.setText(question);
            questions.add(question);
            answers.clear();
            for (int e = 0; e < jAnswers.length(); e++) {
                JSONObject a = jAnswers.getJSONObject(e);
                answer = a.getString(TAG_ANSWER);
                answers.add(answer);

                Log.d("JSON", answer + " zomganswer " + answers.get(0) + " " + e + " " + i);
            }

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + json);
            e.printStackTrace();
        }

        answerList = randomList(4);
        Log.d("as", questionList + " " + i);

//        answers = qm.allQuestions.get(questionList.get(i)).getAnswers(); // Answer1

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
//        qu = (TextView) findViewById(R.id.question);
//        qu.setText(qm.allQuestions.get(questionList.get(i)).question + "");
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            String category = getIntent().getStringExtra("category");
//            Toast.makeText(this, "LOL" + category, Toast.LENGTH_SHORT).show();
            InputStream is = getAssets().open(category +".json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionList = randomList(questionAmount);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Editor edit = prefs.edit();
        // edit.putInt("highscore1", i);
        // edit.clear();
        edit.commit();
        highScores = new ArrayList<Integer>();
        for (int i = 0; i != 10; i++) {
            highScores.add(prefs.getInt("highscore" + i, 0));
        }

        highScoresNames = new ArrayList<String>();
        for (int i = 0; i != 10; i++) {
            highScoresNames.add(prefs.getString("highscoreName" + i, ""));
        }


        nextQuestion();
        Log.d("test", answers.get(0) + " " + answerList.get(0));

        ba1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("lil0",
                        answers.get(answerList.get(0))
                                + " " + answers.get(0));
                if (checkAnswer(answers.get(answerList.get(0)),
                        answers.get(0))) {
                    i++;
                    nextQuestion();
                }
            }
        });

        ba2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("lil1",
                        answers.get(answerList.get(1))
                                + " " + answers.get(1));
                if (checkAnswer(answers.get(answerList.get(1)),
                        answers.get(0))) {
                    i++;
                    nextQuestion();
                }
            }
        });

        ba3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("lil2",
                        answers.get(answerList.get(2))
                                + " " + answers.get(2));
                if (checkAnswer(answers.get(answerList.get(2)),
                        answers.get(0))) {
                    i++;
                    nextQuestion();
                }
            }
        });

        ba4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("lil3",
                        answers.get(answerList.get(3))
                                + " " + answers.get(3));
                if (checkAnswer(answers.get(answerList.get(3)),
                        answers.get(0))) {
                    i++;
                    nextQuestion();
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
