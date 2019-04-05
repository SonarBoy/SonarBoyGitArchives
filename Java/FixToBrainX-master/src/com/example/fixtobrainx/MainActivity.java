package com.example.fixtobrainx;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	 CalculatingGame gameBoard;
	 
	 Button subtraction_Button;
	 Button addition_Button;
	 Button speech_Button;
	 Button difficulty_Button;
	 Button bluetooth_Button;
	 int request_Code;
	 
	 TextView testerView;
	 
	 RatingBar sizeBar;
	 
	 DifficultyModel difficultyModel;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		// Options Avaiable
		this.testerView = (TextView)findViewById(R.id.testerView);
		this.difficultyModel = new DifficultyModel();
		
		//Subtraction Game
		this.subtraction_Button = (Button)findViewById(R.id.sub_Button);
		this.subtraction_Button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				//float value = sizeBar.getRating();
				Intent data = new Intent(getBaseContext(),Subtraction.class);

				data.putExtra("Value", 100);

				data.putExtra("DifficultyObject", difficultyModel);
				startActivity(data);
			}
			
		});
		
		//Addition Game
		addition_Button = (Button)findViewById(R.id.add_Button);
		addition_Button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){

				//float value = sizeBar.getRating();
				Intent data = new Intent(getBaseContext(),Addition.class);

				data.putExtra("Value", 100);
				
				
				data.putExtra("DifficultyObject", difficultyModel);
				startActivity(data);
			}
		});
		
		//Speech Problem Game
		speech_Button = (Button)findViewById(R.id.speech_Button);
		speech_Button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				
				//float value = sizeBar.getRating();
				Intent data = new Intent(getBaseContext(),SpeechProblems.class);

				data.putExtra("Value", 100);
				
				
				data.putExtra("DifficultyObject", difficultyModel);
				
				startActivity(data);
			}
		});
		
		
		this.difficulty_Button = (Button)findViewById(R.id.difficultyButton);
		this.difficulty_Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent("com.example.fixtobrainx.DifficultyActivity"),request_Code);
				
			}

			
		});
		
		this.bluetooth_Button = (Button)findViewById(R.id.blueTooth);
		this.bluetooth_Button.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v){
				Intent data = new Intent(getBaseContext(),BlueToothTesting.class);
				startActivity(data);
			}
		});
		
		
		this.subtraction_Button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		this.addition_Button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		this.speech_Button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		this.difficulty_Button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf"));
		
		this.subtraction_Button.setTextSize(20);
		this.addition_Button.setTextSize(20);
		this.speech_Button.setTextSize(20);
		this.difficulty_Button.setTextSize(20);
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Checking to see the difficulty and modder were returned from the previous activity
	public void onActivityResult(int requestCode,int resultCode, Intent data){
		
		try{
			if(data.getStringExtra("numberLength") != "" && data.getStringExtra("modderText") != null){
		    			String numbLength = data.getStringExtra("numberLength");
		    			String modderLength = data.getStringExtra("modderText");
		    			
	    			    
		    			testerView.setText("Values: " + numbLength + "-" + modderLength);
		    			
		    			this.difficultyModel.setFirstNumberLength(Double.parseDouble(numbLength));
		    			this.difficultyModel.setSecondNumberLength(Double.parseDouble(numbLength));
		    			
		    			if(modderLength == "" || modderLength == null){
		    				this.difficultyModel.setModder(5);
		    			}else{
			    			this.difficultyModel.setModder(Integer.parseInt(modderLength));
		    			}
		    			
			}
		}
		
		catch(Exception ex){
			Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT);
		}
	    			
	}
}
