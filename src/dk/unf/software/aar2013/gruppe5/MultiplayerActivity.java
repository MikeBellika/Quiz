package dk.unf.software.aar2013.gruppe5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import dk.unf.software.aar2013.gruppe5.HighscoreAddDialog.HighscoreAddDialogListener;

public class MultiplayerActivity extends FragmentActivity implements
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
	ArrayList<String> answers;
	int i = 0;
	ArrayList<Integer> highScores;
	ArrayList<String> highScoresNames;
	SharedPreferences prefs;
	
	private boolean gameClient;
	private boolean gameServer;

	private Context mContext;
	private NetworkService netService;
	private BluetoothAdapter mBluetoothAdapter;
	private static final int REQUEST_ENABLE_BT = 1;
	private static final String tag = "BluetoothFrame";
	private ArrayList<BluetoothDevice> discoveredPeers;
	private AlertDialog serverDialog;
	private AlertDialog clientDialog;
	private EditText input_serverName;
	private EditText input_clientServerName;
	private String clientServerName;
	private Button server;
	private Button client;

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_READ = 1;
	public static final int MESSAGE_START = 2;
	public static final int MESSAGE_CONNECTED = 3;

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

			qm.correct();
			score = (TextView) findViewById(R.id.score);
			score.setText("Dine points: " + qm.score);
			Log.d("ASASGAS", qm.allQuestions.size() + "");
			if (qm.score == qm.allQuestions.size()) {
				qm.reset();
				ArrayList<Integer> temp = new ArrayList<Integer>();
				return false;
			}
			return true;
		} else {
			Toast.makeText(getApplicationContext(), "FORKERT!",
					Toast.LENGTH_SHORT).show();
			qm.score--;
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

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplayer);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// Don't
		// touch-----------------------------------------------------------------------
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// Device does not support Bluetooth
		}
		netService = new NetworkService(mContext, mBluetoothAdapter, mHandler);
		IntentFilter deviceFilter = new IntentFilter(
				BluetoothDevice.ACTION_FOUND);
		Log.d(tag, "Registering deviceReceiver");
		registerReceiver(deviceReceiver, deviceFilter);
		IntentFilter adapterFilter = new IntentFilter(
				BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		Log.d(tag, "Registering adapterReceiver");
		registerReceiver(adapterReceiver, adapterFilter);
		server = (Button) findViewById(R.id.start_server_button);
		client = (Button) findViewById(R.id.start_client_button);
		serverDialog = new AlertDialog.Builder(this).create();
		clientDialog = new AlertDialog.Builder(this).create();
		input_serverName = new EditText(this);
		input_clientServerName = new EditText(this);
		discoveredPeers = new ArrayList<BluetoothDevice>();
		server.setOnClickListener(startServerListener);
		client.setOnClickListener(startClientListener);
		// Touch
		// again-----------------------------------------------------------------------
		
		Log.d("test", "test");
//		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = prefs.edit();
		// edit.putInt("highscore1", i);
		// edit.clear();
		edit.commit();
		answerList = randomList(4);
		answers = qm.allQuestions.get(i).getAnswers(); // Answer1

		

//		nextQuestion();

		
		

	}

	// Create a BroadcastReceiver for ACTION_FOUND
	private final BroadcastReceiver deviceReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// When discovery finds a device
			Log.d(tag, action);
			Log.d(tag,
					String.valueOf(BluetoothDevice.ACTION_FOUND.equals(action)));
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				// Get the BluetoothDevice object from the Intent
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				try {
					discoveredPeers.add(device);
					Log.d(tag, device.getName() + " added to discovered list.");
				} catch (Exception e) {
					Log.d(tag, "Failed adding device to device list.");
				}
			}
		}
	};

	// Create a BroadcastReceiver for ACTION_DISCOVERY
	private final BroadcastReceiver adapterReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// When discovery finds a device
			Log.d(tag, action);
			if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				Log.d(tag, "Trying to connect as client.");
				boolean bo = false;
				for (BluetoothDevice device : discoveredPeers) {
					Log.d(tag, "DEVICE NAME: " + device.getName());
					if (device.getName().equals(clientServerName)) {
						Log.d(tag, "Discovered, OK.");
						netService.connect(device);
						bo = true;
					}
				}
				if (!bo) {
					Toast.makeText(
							getApplicationContext(),
							"Could not find server with name: "
									+ clientServerName, Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	};

	@Override
	protected void onActivityResult(int request, int result, Intent intent) {
		switch (request) {
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (result == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				Toast.makeText(this, "BT Enabled", Toast.LENGTH_SHORT).show();
			} else {
				// User did not enable Bluetooth or an error occurred
				Log.d(tag, "BT not enabled");
				Toast.makeText(this, "BT Not Enabled", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private OnClickListener startServerListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			serverDialog.setTitle("Start server");
			serverDialog.setView(input_serverName);
			serverDialog.setMessage("Name your server:");
			serverDialog.setCancelable(false);
			serverDialog.setButton(DialogInterface.BUTTON_POSITIVE,
					"Start server", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (!mBluetoothAdapter.isEnabled()) {
								Intent enableBtIntent = new Intent(
										BluetoothAdapter.ACTION_REQUEST_ENABLE);
								startActivityForResult(enableBtIntent,
										REQUEST_ENABLE_BT);
							}
							Intent discoverableIntent = new Intent(
									BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
							discoverableIntent
									.putExtra(
											BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
											0);
							startActivity(discoverableIntent);
							mBluetoothAdapter.setName(input_serverName
									.getText().toString());
							Log.d(tag, "Bluetooth name set to: "
									+ mBluetoothAdapter.getName());
							Log.d(tag, "Starting server.");
							netService.start();
						}
					});
			serverDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							serverDialog.cancel();
						}
					});
			serverDialog.show();
		}
	};

	private OnClickListener startClientListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clientDialog.setTitle("Connect to server");
			clientDialog.setView(input_clientServerName);
			clientDialog
					.setMessage("Type which server you want to connect to:");
			clientDialog.setCancelable(false);
			clientDialog.setButton(DialogInterface.BUTTON_POSITIVE,
					"Connect to server", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (!mBluetoothAdapter.isEnabled()) {
								Intent enableBtIntent = new Intent(
										BluetoothAdapter.ACTION_REQUEST_ENABLE);
								startActivityForResult(enableBtIntent,
										REQUEST_ENABLE_BT);
							}
							clientServerName = input_clientServerName.getText()
									.toString();
							Log.d(tag, "clientServerName is: "
									+ clientServerName);
							mBluetoothAdapter.startDiscovery();
						}
					});
			clientDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							clientDialog.cancel();
						}
					});
			clientDialog.show();
		}
	};

	// Sender teksten i input til den anden telefon.
	private void writeDevice(String input) {
		netService.writemessage(input);
	}


	
	public void nextQuestion(int q) {
		answerList = randomList(4);
		Log.d("as", questionList + " " + q);
		
				

		answers = qm.allQuestions.get(questionList.get(q)).getAnswers(); // Answer1
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
		qu.setText(qm.allQuestions.get(questionList.get(q)).question + "");
		if(gameServer){
			writeDevice(qm.allQuestions.indexOf(questionList.get(i)) + "");
		}
	}
	
	public void test(final int q){
		answerList = randomList(4);
		answers = qm.allQuestions.get(q).getAnswers(); // Answer1
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
		qu.setText(qm.allQuestions.get(q).question + "");
		
		ba1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(0)),
						qm.allQuestions.get(q)
								.answers
								.get(0))) {
					i++;
					if(gameClient){
						writeDevice("client");
					}else{
						writeDevice(i + "");
                        test(i);
					}
//					nextQuestion();
				}
			}
		});

		ba2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(1)),
						qm.allQuestions.get(q)
								.answers
								.get(0))) {
					i++;
					if(gameClient){
						writeDevice("client");
					}else{
						writeDevice(i + "");
                        test(i);
					}
//					nextQuestion();
				}
			}
		});

		ba3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(2)),
						qm.allQuestions.get(q)
								.answers
								.get(0))) {
					i++;
					if(gameClient){
						writeDevice("client");
					}else{
						writeDevice(i + "");
                        test(i);
					}
//					nextQuestion();
				}
			}
		});

		ba4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkAnswer(answers.get(answerList.get(3)),
						qm.allQuestions.get(q)
						.answers
						.get(0))) {
					i++;
					if(gameClient){
						writeDevice("client");
					}else{
						writeDevice(i + "");
                        test(i);
					}
				}
			}
		});
	}
	
	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {

			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				// vinder string
                int q = 0;
				String readMessage = new String(readBuf, 0, msg.arg1);
				Log.d("MODTAGET", readMessage + "  loool");
				if(readMessage.equals("client")){
                    i++;
					writeDevice(i + "");
					q = i;
				}else if(gameClient){
					q = Integer.parseInt(readMessage);
				}
                test(q);
				
				break;

			case MESSAGE_START:
				Toast.makeText(getApplicationContext(), "Player connected",
						Toast.LENGTH_LONG).show();
				server.setVisibility(View.INVISIBLE);
				server.setEnabled(false);
				client.setVisibility(View.INVISIBLE);
				client.setEnabled(false);
				questionList = randomList(qm.allQuestions.size());
				gameServer = true;
				writeDevice(questionList.get(0) + "");
				test(questionList.get(0));
				
				
				break;

			case MESSAGE_CONNECTED:
				Toast.makeText(getApplicationContext(), "Connected to server",
						Toast.LENGTH_LONG).show();
				server.setVisibility(View.INVISIBLE);
				server.setEnabled(false);
				client.setVisibility(View.INVISIBLE);
				client.setEnabled(false);
				
				gameClient = true;
								
				break;

			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(tag, "Unregistering deviceReceiver");
		unregisterReceiver(deviceReceiver);
		Log.d(tag, "Unregistering adapterReceiver");
		unregisterReceiver(adapterReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
