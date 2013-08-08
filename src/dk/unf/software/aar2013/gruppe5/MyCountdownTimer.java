package dk.unf.software.aar2013.gruppe5;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class MyCountdownTimer extends CountDownTimer {
	TextView count;
	SprintActivity mactivity;

	public MyCountdownTimer(long startTime, long interval, TextView text, SprintActivity mactivity) {
		super(startTime, interval);
		count = text;
		this.mactivity = mactivity;
	}

	@Override
	public void onTick(long millisUntilFinished) {
        count.setText(millisUntilFinished / 1000 + "");
     
    }
	@Override
    public void onFinish() {
		mactivity.highScore();
    }

}
