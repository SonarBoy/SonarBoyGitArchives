package com.example.fixtobrainx;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DifficultyActivity extends Activity {
	
	private DifficultyModel difficultySendBack;
	
	private RatingBar lengthBar;
	private SeekBar seekBar;
	private SeekBar modderNumber;
	private RadioButton patternButtons;
	
	private Button send;
	
	private TextView lengthLabel;
	private TextView modderLabel;
	
	private int numberLength;
	private int modderLength;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty_setup);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		this.initializeLabel();
		
		this.difficultySendBack = new DifficultyModel();
		
		this.modderNumber = (SeekBar)findViewById(R.id.modderSeekBar);
		this.modderNumber.onHoverChanged(true);
		this.seekBar = (SeekBar)findViewById(R.id.difficultySeekBar);
		this.seekBar.onHoverChanged(true);
		
		
		this.send = (Button)findViewById(R.id.sendBackButton);
		this.send.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		this.send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try{
				Intent data = new Intent();
				
				data.setData(Uri.parse("Numbers"));
				
				data.putExtra("numberLength", String.valueOf(seekBar.getProgress() / 20));
				data.putExtra("modderText", String.valueOf(seekBar.getProgress()/10));
				
				
				setResult(RESULT_OK,data);
				finish();
				
				}
				
				catch(Exception ex){
					Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT);
				}
			}
		});
		
	}
	
	public void initializeLabel(){
		this.lengthLabel = (TextView)findViewById(R.id.lenghtLabel);
		this.modderLabel = (TextView)findViewById(R.id.modderLabel);
		
		this.lengthLabel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		this.modderLabel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		
		this.lengthLabel.setTextColor(Color.WHITE);
		this.modderLabel.setTextColor(Color.WHITE);
	}
	
	
	
//	public void onRadioButtonClicked(View view){
//		
//		boolean clicked = ((RadioButton) view).isChecked();
//		
//		switch(view.getId()){
//			
//		case R.id.radioButtonBreaker:
//			if(clicked){
//				Toast.makeText(getBaseContext(), "Pattern 1", Toast.LENGTH_SHORT).show();
//			}
//			break;
//			
//		case R.id.radioButtonTackle:
//			if(clicked){
//				Toast.makeText(getBaseContext(), "Pattern 2", Toast.LENGTH_SHORT).show();
//			}
//			break;
//		}
//		
//	}
	
}
