package com.example.fixtobrainx;
import java.util.Random;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Addition extends Activity {
	
	 CalculatingGame gameBoard;
	 DifficultyModel additionDifficulty;
	 Typeface fontToSetIn;
	 
	 TextView first;
	 TextView second;
	 TextView timeLabel;
	 TextView difficultLabel;
	 EditText inputField;
	 
	 Button checker;
	 Button testButton;
	 
	 MediaPlayer player;
	 Typeface [] fonts;
	 ProgressBar progress;
	 
	 int changer = 0;
	 int progressPoint = 0;
	 int request_Code;
	 int initialValue = 0;
	 int fontCounter = 0;
	 
	 double startTime;
	 double now;
	 double finalTimeCalc;
	 
	 float sizeForLettersAssending = 1.4f;
	 float sizeForLettersDesending = 1.9f;
	 
	 SpannableStringBuilder spannableFirstNumber;
	 SpannableStringBuilder spannableSecondNumber;
	 BackgroundColorSpan[] colorSpans;
	 
	 Random slide;

	 ObjectAnimator slideInFirst;
	 ObjectAnimator slideInSecond;
	 	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addition_activity);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		Bundle extras = getIntent().getExtras();
		this.initialValue = extras.getInt("Value");
		
		
		this.initializeColorSpans();
		this.initializeFonts();
		this.initializeAnimatorsAndLabel();
		this.initializeGame();
		this.initializeCheckButton();
		
	}
	
	public void initializeCheckButton(){
		this.checker = (Button)findViewById(R.id.checkButton);
		this.checker.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v) {
			
			int value = Integer.parseInt(inputField.getText().toString());
								
			Typeface fontPassBack = fonts[fontCounter];

			
			// If the answere is correct or incorrect
			if(gameBoard.checkAddition(value) == true){
				
				//Toast a correct answer.
				Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
				inputField.setText("",TextView.BufferType.EDITABLE);
				
				//Increase meta values
				progressPoint++;
				changer++;
				
				//Increase progress
				//progress.setProgress(progressPoint);
				
				//When it becomes modded by 5 increase the number and change the progress bar
				if(changer % 5 == 0 && changer != 0 && changer != 1){
					//progress.setProgress(0);
					progressPoint = 0;
					fontCounter++;
					fontPassBack = fonts[fontCounter];
				}
				
				//Change the time value and assing it to a label
				now = System.currentTimeMillis();
				finalTimeCalc = (now - startTime)/1000;
				
				timeLabel.setText("Lap: "+finalTimeCalc);
				
				//Play the ding!
				player.start();
									
			}else{
				Toast.makeText(getBaseContext(), "Wrong answer: " +  gameBoard.getAnswerAddition(), Toast.LENGTH_SHORT).show();
				inputField.setText("",TextView.BufferType.EDITABLE);
									
				//progress.setProgress(0);
				progressPoint = 0;
									
				changer--;
				
				now = System.currentTimeMillis();
				finalTimeCalc = (now - startTime)/1000;
				
				timeLabel.setText("Lap: "+finalTimeCalc);
			}
								
								
			runNumberCheck(changer,fontPassBack);
			}
							
		});
		
		this.checker.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Plaster-Regular.ttf"));
		
		
	}
	
	public void initializeGame(){
		// Set Up of the Game
		this.additionDifficulty = (DifficultyModel) getIntent().getSerializableExtra("DifficultyObject");
		gameBoard = new CalculatingGame(this.additionDifficulty.getFirstNumber(),additionDifficulty);
		//progress = (ProgressBar)findViewById(R.id.progressBar);
		//progress.setMax(4);
		runNumberCheck(this.changer,this.fontToSetIn);
				
		//Set Up of the Clock
		this.timeLabel = (TextView)findViewById(R.id.additionTime);
		this.timeLabel.setText("Lap: " + 0);
		this.timeLabel.setTextColor(Color.WHITE);
		this.timeLabel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Starjedi.ttf"));
				
		this.startTime = System.currentTimeMillis();
				
		//Current Answer Field
		this.inputField = (EditText)findViewById(R.id.answerField);
		this.inputField.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}
	
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				startTime = now;	
			}
	
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
			
		});
		
		//Set up of media player
		this.player = MediaPlayer.create(this, R.raw.bell);
		this.inputField.setTextColor(Color.WHITE);
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
		//Difficulty Setup
		//this.difficultLabel = (TextView)findViewById(R.id.additionDifficultyLabel);
		//this.difficultLabel.setText("Number Length: " + this.additionDifficulty.getFirstNumber() + "\n" + "Modder: " + this.additionDifficulty.getModder());
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
	
	public void initializeAnimatorsAndLabel(){
		
		this.first = (TextView)findViewById(R.id.firstNumberLabel);
		this.second = (TextView)findViewById(R.id.secondNumberLabel);
		
		this.slideInFirst = ObjectAnimator.ofFloat(this.first, "X", 500);
		this.slideInFirst.setDuration(1500);
		this.slideInFirst.setInterpolator(new BounceInterpolator());
		
		this.slideInSecond = ObjectAnimator.ofFloat(this.second, "X", 250);
		this.slideInSecond.setDuration(1500);
		this.slideInSecond.setInterpolator(new BounceInterpolator());
		
	}
	
	public void animatorUpdate(){
		
		this.slide = new Random();
		
		this.slideInFirst = ObjectAnimator.ofFloat(this.first, "X", this.slide.nextInt(300));
		this.slideInFirst.setDuration(2000);
		this.slideInFirst.setInterpolator(new BounceInterpolator());
		
		this.slideInSecond = ObjectAnimator.ofFloat(this.second, "X", this.slide.nextInt(300));
		this.slideInSecond.setDuration(2000);
		this.slideInSecond.setInterpolator(new BounceInterpolator());
	}
	
	public void runNumberCheck(int value,Typeface fontToPassIntoLabels){
		
		this.sizeForLettersAssending = 1.4f;
		this.sizeForLettersDesending = 1.9f;
		animatorUpdate();
		
		//Movement of Modulus
		if(value == 0 || value <= 5){
			
			slideInFirst.start();
			slideInSecond.start();
			
			gameBoard.makeAddition();
			
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
			
			first.setTypeface(fontToPassIntoLabels);
			first.setText(spannableFirstNumber);
			first.setTextSize(30);
			
			second.setTypeface(fontToPassIntoLabels);
			second.setText(spannableSecondNumber);
			second.setTextSize(30);
			
			
		
		//Keeping of modder
		}else if(value >= 6){
			
			slideInFirst.start();
			slideInSecond.start();
			
			gameBoard.makeAddition(value);
			
			
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
			
			
			first.setTypeface(fontToPassIntoLabels);
			first.setText(spannableFirstNumber);
			first.setTextSize(30);
		
			second.setTypeface(fontToPassIntoLabels);
			second.setText(spannableSecondNumber);
			second.setTextSize(30);
			
			
			
		}
		
		
	}
}
