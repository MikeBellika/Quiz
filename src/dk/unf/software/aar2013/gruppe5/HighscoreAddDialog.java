package dk.unf.software.aar2013.gruppe5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HighscoreAddDialog extends DialogFragment implements
		OnEditorActionListener {

	@Override
	public void onCancel(DialogInterface dialog) {
		// TODO Auto-generated method stub
		super.onCancel(dialog);
		
		Intent intent = new Intent(getView().getContext(),
				HighscoreActivity2.class);
		startActivity(intent);
	}

	private EditText mEditText;

	public HighscoreAddDialog() {
		// Empty constructor required for DialogFragment
	}

	public interface HighscoreAddDialogListener {
		void onFinishEditDialog(String inputText);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialogmessage, container);
		mEditText = (EditText) view.findViewById(R.id.txt_your_name);
		getDialog().setTitle("Skriv dit navn:");

		final HighscoreAddDialog ha = this;

		((Button) view.findViewById(R.id.send))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						MainActivity activity = (MainActivity) getActivity();
						activity.onFinishEditDialog(mEditText.getText()
								.toString());
						ha.dismiss();
					}
				});

		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {
			// Return input text to activity
			MainActivity activity = (MainActivity) getActivity();
			activity.onFinishEditDialog(mEditText.getText().toString());
			this.dismiss();
			return true;
		}
		return false;
	}
}
