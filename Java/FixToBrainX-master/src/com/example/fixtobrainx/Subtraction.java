package com.example.fixtobrainx;
import java.util.Random;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Subtraction extends Activity{
	
	 CalculatingGame gameBoard;
	 DifficultyModel subtractionDifficulty;
	 Typeface fontToSetIn;
	 
	 TextView first;
	 TextView second;
	 TextView timeStamp;
	 TextView difficultyLabel;
	 EditText inputField;
	 
	 Button checker;
	 Button testButton;
	 
	 ProgressBar progress;
	 MediaPlayer player;
	 Typeface [] fonts;
	 
	 int changer = 0;
	 int progressPoint = 0;
	 int request_Code;
	 int initialValue = 0;
	 int fontCounter = 0;
	 
	 double startTime;
	 double now;
	 double finalTally;
	 
	 float sizeForLettersAssending = 1.4f;
	 float sizeForLettersDesending = 1.9f;
	 
	 SpannableStringBuilder spannableFirstNumber;
	 SpannableStringBuilder spannableSecondNumber;
	 BackgroundColorSpan[] colorSpans;
	 
	 Random slide;
	 
	 ObjectAnimator slideInFirst;
	 ObjectAnimator slideInSecond;
	 
	 
	public void onCreate(Bundle savedInstanceBundle){
		super.onCreate(savedInstanceBundle);
		setContentView(R.layout.subtraction_activity);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		Bundle extras = getIntent().getExtras();
		this.initialValue = extras.getInt("Value");
		
		this.initializeColorSpans();
		this.initializeFonts();
		this.initializeAnimatorAndLabel();
		this.initializeGame();
		this.initializeCheckButton();

	}

	public void initializeGame(){

		this.subtractionDifficulty = (DifficultyModel)getIntent().getSerializableExtra("DifficultyObject");
		gameBoard = new CalculatingGame(this.subtractionDifficulty.getFirstNumber(),subtractionDifficulty);
		runNumberCheck(this.changer,this.fontToSetIn);
		
		//Set Up count down timer
		this.timeStamp = (TextView)findViewById(R.id.subtractionTime);
		this.timeStamp.setText("Lap: "+0);
		this.timeStamp.setTextColor(Color.WHITE);
		this.timeStamp.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf"));
		
		this.progress = (ProgressBar)findViewById(R.id.progressBar);
		this.progress.setMax(4);
		
		this.startTime = System.currentTimeMillis();
		
		this.inputField = (EditText)findViewById(R.id.answerField);
		this.inputField.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				startTime = now;
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		// Set Up media player
		this.player = MediaPlayer.create(this, R.raw.bell);		
		this.inputField.setTextColor(Color.WHITE);
		
	}
	
	public void initializeCheckButton(){
		// Set up of the buttom click 
		this.checker = (Button)findViewById(R.id.checkButton);
		this.checker.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v) {
			
			Typeface fontPassBack = fonts[fontCounter];
			
			int value = Integer.parseInt(inputField.getText().toString());
			
			if(gameBoard.checkSubtraction(value) == true){
				Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
				inputField.setText("",TextView.BufferType.EDITABLE);
							
				progressPoint++;
				changer++;
							
				progress.setProgress(progressPoint);
							
					if(changer % 5 == 0 && changer != 0 && changer != 1){
						progress.setProgress(0);
						progressPoint = 0;
						fontCounter++;
						
						Toast.makeText(getBaseContext(), "Change Over!!", Toast.LENGTH_SHORT).show();
						//fontToSetIn = Typeface.createFromAsset(getAssets(), fonts[fontCounter]);
						//Change Back
						fontPassBack = fonts[fontCounter];
					}
					
					
					
				now = System.currentTimeMillis();
				finalTally = (now - startTime)/1000;
				
				timeStamp.setText("Lap: "+finalTally);	
				player.start();	
				
				// Change Back
				runNumberCheck(changer,fontPassBack);
				
				}else{
					
				Toast.makeText(getBaseContext(), "Wrong answer: " +  gameBoard.getAnswerSubtraction(), Toast.LENGTH_SHORT).show();
				inputField.setText("",TextView.BufferType.EDITABLE);
							
				progress.setProgress(0);
				progressPoint = 0;
							
				changer--;
					
				now = System.currentTimeMillis();
				finalTally = (now - startTime)/1000;
					
				timeStamp.setText("Lap: "+finalTally);
				
				// Change Back
				runNumberCheck(changer,fontPassBack);
					
				}
					
			}
					
		});
		
		this.checker.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Plaster-Regular.ttf"));
	}
	
	public void initializeColorSpans(){
		this.colorSpans = new BackgroundColorSpan[10];
		//blue
		colorSpans[0] = new BackgroundColorSpan(Color.rgb(0, 34, 255)); 
		colorSpans[1] = new BackgroundColorSpan(Color.rgb(46, 75, 255));
		colorSpans[2] = new BackgroundColorSpan(Color.rgb(74, 98, 255));
		colorSpans[3] = new BackgroundColorSpan(Color.rgb(100, 121, 255)); 
		colorSpans[4] = new BackgroundColorSpan(Color.rgb(0, 34, 255));
		
		//red
		colorSpans[5] = new BackgroundColorSpan(Color.rgb(255, 0, 0)); 
		colorSpans[6] = new BackgroundColorSpan(Color.rgb(255, 65, 65));
		colorSpans[7] = new BackgroundColorSpan(Color.rgb(255, 90, 90));
		colorSpans[8] = new BackgroundColorSpan(Color.rgb(255, 112, 112)); 
		colorSpans[9] = new BackgroundColorSpan(Color.rgb(255, 152, 152));
	}
	
	public void initializeFonts(){
		this.fonts = new Typeface[10];
		this.fonts[0] = Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf");
		this.fonts[1] = Typeface.createFromAsset(getAssets(), "fonts/Plaster-Regular.ttf");
		this.fonts[2] = Typeface.createFromAsset(getAssets(), "fonts/Questlok.ttf");
		this.fonts[3] = Typeface.createFromAsset(getAssets(), "fonts/zephyr_jubilee.ttf");
		this.fonts[4] = Typeface.createFromAsset(getAssets(), "fonts/Modeccio.ttf");
		this.fonts[5] = Typeface.createFromAsset(getAssets(), "fonts/Xenophobia.ttf");
		this.fonts[6] = Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf");
		
		this.fontToSetIn = Typeface.createFromAsset(getAssets(), "fonts/Xeron.ttf");
	}

	public void initializeAnimatorAndLabel(){
		this.first = (TextView)findViewById(R.id.firstNumberLabel);
		this.second = (TextView)findViewById(R.id.secondNumberLabel);
		
		this.slideInFirst = ObjectAnimator.ofFloat(this.first, "X", 500);
		this.slideInFirst.setDuration(2000);
		this.slideInFirst.setInterpolator(new BounceInterpolator());
		
		this.slideInSecond = ObjectAnimator.ofFloat(this.second, "X", 250);
		this.slideInSecond.setDuration(2000);
		this.slideInSecond.setInterpolator(new BounceInterpolator());
	}
	
	public void animatorUpdate(){
		
		this.slide = new Random();
		
		this.slideInFirst = ObjectAnimator.ofFloat(this.first, "X", this.slide.nextInt(300));
		this.slideInFirst.setDuration(1500);
		this.slideInFirst.setInterpolator(new BounceInterpolator());
		
		this.slideInSecond = ObjectAnimator.ofFloat(this.second, "X", this.slide.nextInt(300));
		this.slideInSecond.setDuration(1500);
		this.slideInSecond.setInterpolator(new BounceInterpolator());
	}
	
	
	public void runNumberCheck(int value,Typeface fontToPassIntoLabels){
		
		this.sizeForLettersAssending = 1.4f;
		this.sizeForLettersDesending = 1.9f;
		animatorUpdate();
		
		//Modder for value 5
		if(value == 0 || value <= 5){
			
			slideInFirst.start();
			slideInSecond.start();
			
			gameBoard.makeSubtraction();
			
			spannableFirstNumber = new SpannableStringBuilder(gameBoard.getFirstNumber()+"");
			spannableSecondNumber = new SpannableStringBuilder(gameBoard.getSecondNumber()+"");
			
			for(int runner = 0;runner < spannableFirstNumber.length();runner++){
				spannableFirstNumber.setSpan(new RelativeSizeSpan(sizeForLettersDesending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				spannableFirstNumber.setSpan(colorSpans[runner + 5],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				sizeForLettersDesending -= 0.2f;
			}
			
			for(int runner = 0;runner < spannableSecondNumber.length();runner++){
				spannableSecondNumber.setSpan(new RelativeSizeSpan(sizeForLettersAssending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				spannableSecondNumber.setSpan(colorSpans[runner],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				sizeForLettersAssending += 0.2f;
			}

			
			
			first = (TextView)findViewById(R.id.firstNumberLabel);
			first.setTypeface(fontToPassIntoLabels);
			first.setText(spannableFirstNumber);
			first.setTextSize(30);
			
			second = (TextView)findViewById(R.id.secondNumberLabel);
			second.setTypeface(fontToPassIntoLabels);
			second.setText(spannableSecondNumber);
			second.setTextSize(30);
			
			
			
		}else if(value >= 6){
			
			slideInFirst.start();
			slideInSecond.start();
		
			gameBoard.makeSubtraction(value);
			
			spannableFirstNumber = new SpannableStringBuilder(gameBoard.getFirstNumber()+"");
			spannableSecondNumber = new SpannableStringBuilder(gameBoard.getSecondNumber()+"");
			
			for(int runner = 0;runner < spannableFirstNumber.length();runner++){
				spannableFirstNumber.setSpan(new RelativeSizeSpan(sizeForLettersDesending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				spannableFirstNumber.setSpan(colorSpans[runner + 5],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				sizeForLettersDesending -= 0.2f;
			}
			
			for(int runner = 0;runner < spannableSecondNumber.length();runner++){
				spannableSecondNumber.setSpan(new RelativeSizeSpan(sizeForLettersAssending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				spannableSecondNumber.setSpan(colorSpans[runner],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
				sizeForLettersAssending += 0.2f;
			}

			
			
			first = (TextView)findViewById(R.id.firstNumberLabel);
			first.setTypeface(fontToPassIntoLabels);
			first.setText(spannableFirstNumber);
			first.setTextSize(30);
			
			second = (TextView)findViewById(R.id.secondNumberLabel);
			second.setTypeface(fontToPassIntoLabels);
			second.setText(spannableSecondNumber);
			second.setTextSize(30);
			
		}
		
		
	}
}
