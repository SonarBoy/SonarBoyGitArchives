package com.example.fixtobrainx;

import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Random;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SpeechProblems extends Activity{

	TextToSpeech t1;
	DifficultyModel speechDifficulty;
	
	Button speakButton;
	Button checkerButton;
	EditText inputText;
	TextView speakLabel;
	TextView lapLabel;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	Bitmap bitMap;
	Canvas majorCanvas;
	
	
	int buttonPressedCounter;
	int correctRunner;
	int startUp;
	int initialValue;
	int majorCanvasWidth;
	int majorCanvasHeight;
	
	MediaPlayer player;
	
	double startTime;
	double now;
	double finalTimeCalc;
	
	CalculatingGame gameBoard;
	
	
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.speech_layout);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		//Setup for length of numbers
		Bundle extras = getIntent().getExtras();
		this.initialValue = extras.getInt("Value");

		this.initializeMetaVariables();
		this.initializeTimesAndPlayer();
		this.runNumberCheck(this.correctRunner);
		this.initializeCheckerButton();

	}
	
	//Tester for the Game with the speech button implemented
	public void runNumberCheck(int value){
		
		//Value for progress bar movement 
		if(value == 0 || value <= 5){
			
			this.gameBoard.makeSubtraction();
			
			this.speakButton = (Button)findViewById(R.id.speak_Button);
			this.speakButton.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v) {
					t1.speak(gameBoard.getFirstNumber()+" minus "+gameBoard.getSecondNumber(),TextToSpeech.QUEUE_FLUSH,null);
					
					buttonPressedCounter++;
					speakLabel.setText("Pressed #"+buttonPressedCounter);
					
				}
			});
			
		}else if(value >= 6){
		
			this.gameBoard.makeSubtraction(value);
			
			this.speakButton = (Button)findViewById(R.id.speak_Button);
			this.speakButton.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v) {
					t1.speak(gameBoard.getFirstNumber()+" minus "+gameBoard.getSecondNumber(),TextToSpeech.QUEUE_FLUSH,null);
					
					buttonPressedCounter++;
					speakLabel.setText("Pressed #"+buttonPressedCounter);
					
				}
				
			});
			
			
		}
	}//End of Method

	public void initializeCheckerButton(){
		this.checkerButton = (Button)findViewById(R.id.checker_Button);
		this.checkerButton.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Plaster-Regular.ttf"));
		this.checkerButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				
				//Correct Answer True
				if(gameBoard.checkSubtraction(Integer.parseInt(inputText.getText().toString()))){
					
					Toast.makeText(getBaseContext(),"Correct",Toast.LENGTH_SHORT).show();
					
					//Clear the text Area and add the multiplier
					inputText.setText("");
					correctRunner++;
					
					//Mod for case will use soon
					if(correctRunner % 5 == 0){
						startUp++;
					}
					
					//Clean up for correct answer
					buttonPressedCounter = 0;
					speakLabel.setText("Pressed #"+buttonPressedCounter);
					
					now = System.currentTimeMillis();
					finalTimeCalc = (now - startTime)/1000;

					lapLabel.setText("Lap: "+finalTimeCalc);
					
					player.start();
					
				}else{
					
					//Reset Time
					now = System.currentTimeMillis();
					finalTimeCalc = (now - startTime)/1000;
					
					// Accumilate data for incorrect answer
					JSONObject jsonObject = new JSONObject();
					
					try{
						
						jsonObject.accumulate("Time", finalTimeCalc);
						jsonObject.accumulate("FirstNumber", gameBoard.getFirstNumber());
						jsonObject.accumulate("SecondNumber", gameBoard.getSecondNumber());
						jsonObject.accumulate("CorrectAnswer", gameBoard.getAnswerSubtraction());
						jsonObject.accumulate("YourAnswer", inputText.getText());	
					}
					
					catch(Exception ex){
						
					}
					
					//String data for wrong answer
					String inputData = "{\"Time\": " + finalTimeCalc + ", \n";
					inputData += " \"FirstNumber\": " + gameBoard.getFirstNumber() + ", \n";
					inputData += " \"SecondNumber\": " + gameBoard.getSecondNumber() + ", \n";
					inputData += " \"CorrectAnswer\": "+ gameBoard.getAnswerSubtraction() + ", \n";
					inputData += " \"YourAnswer\": " + inputText.getText() + "} \n"; 
					
					
					//Clean up for everything
					Toast.makeText(getBaseContext(),"Incorrect",Toast.LENGTH_SHORT).show();
					t1.speak("Incorrect",TextToSpeech.QUEUE_FLUSH,null);
					correctRunner--;
					
					inputText.setText("");
					
					buttonPressedCounter = 0;
					speakLabel.setText("Pressed #"+buttonPressedCounter);
					
					//Writing to the file
					try{
						Toast.makeText(getBaseContext(), jsonObject.toString(), Toast.LENGTH_SHORT).show();
						
						FileOutputStream loggerOutputStream = openFileOutput("IncorrectAnswers.json", MODE_APPEND);
						loggerOutputStream.write(inputData.getBytes());
						
						inputData = ", \n";
						
						loggerOutputStream.write(inputData.getBytes());
						loggerOutputStream.flush();
						loggerOutputStream.close();
						

						lapLabel.setText("Lap: "+finalTimeCalc);
					}
					
					catch(Exception ex){
						Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
					}
					
				}
				
				//Reset Progress
				runNumberCheck(correctRunner);
				
				//Re-drawing on the canvas
				majorCanvas = surfaceHolder.lockCanvas();
				draw2();
				surfaceHolder.unlockCanvasAndPost(majorCanvas);
			}
		});
		
		
		
	}
		
	public void initializeTimesAndPlayer(){
		this.startTime = System.currentTimeMillis();
		
		this.t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
		         @Override
		         public void onInit(int status) {
		            if(status != TextToSpeech.ERROR) {
		               t1.setLanguage(Locale.US);
		            }
		         }
		      
		  });

		this.player = MediaPlayer.create(this, R.raw.bell);
		
		
		this.inputText = (EditText)findViewById(R.id.input_Text);
		this.inputText.setTextColor(Color.WHITE);
		this.inputText.addTextChangedListener(new TextWatcher(){

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
	}
	
	public void initializeMetaVariables(){
		
		//Simple Variables
		this.buttonPressedCounter = 0;
		this.startUp  = 0;
		this.correctRunner = 0;
		
		//Main Game Piece
		this.speechDifficulty = (DifficultyModel)getIntent().getSerializableExtra("DifficultyObject");
		this.gameBoard = new CalculatingGame(this.speechDifficulty.getFirstNumber(),this.speechDifficulty);
		
		//Labels
		this.speakLabel = (TextView)findViewById(R.id.speechCounterLabel);
		this.speakLabel.setText("Pressed #"+this.buttonPressedCounter);
		this.speakLabel.setTextColor(Color.WHITE);
		
		this.lapLabel = (TextView)findViewById(R.id.countDown);
		this.lapLabel.setText("Lap: " + 0);
		this.lapLabel.setTextColor(Color.WHITE);
		
		//Speak button Style
		this.speakButton = (Button)findViewById(R.id.speak_Button);
		this.speakButton.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Plaster-Regular.ttf"));
		
		//Drawing Canvas and art/animation
		this.surfaceView = (SurfaceView)findViewById(R.id.speechSurfaceView);
		this.surfaceHolder = this.surfaceView.getHolder();
		this.surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				majorCanvas = holder.lockCanvas(null);
				
				majorCanvasWidth = majorCanvas.getWidth();
				majorCanvasHeight = majorCanvas.getHeight();
				
				draw();
				
				holder.unlockCanvasAndPost(majorCanvas);
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	// First Attepmt at drawing
	public void draw(){
		this.bitMap= Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		
		this.majorCanvas.drawColor(Color.BLACK);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		
		//Rect x = new Rect(majorCanvasWidth/2, 0,majorCanvasWidth/2 + 10 ,10);
		
		this.majorCanvas.drawRect(majorCanvasWidth/2, 0,majorCanvasWidth/2 + 10 ,10, paint);
		
		paint.setColor(Color.GREEN);
		this.majorCanvas.drawRect(majorCanvasWidth/2 - 10, 10,majorCanvasWidth/2 + 20 ,20 , paint);
		
		paint.setColor(Color.RED);
		this.majorCanvas.drawRect(majorCanvasWidth/2 - 20, 20,majorCanvasWidth/2 + 30 ,30, paint);
		
		paint.setColor(Color.BLUE);
		this.majorCanvas.drawRect(majorCanvasWidth/2 - 30, 30, majorCanvasWidth/2 + 40, 40, paint);
		
		
	}
	
	// Testing Code for correct answer change
	public void draw2(){
		this.bitMap= Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		
		majorCanvas.drawColor(Color.BLACK);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
	
		
		majorCanvas.drawRect(majorCanvasWidth/2, 0,majorCanvasWidth/2 + 10 ,10, paint);
		
		paint.setColor(Color.GREEN);
		majorCanvas.drawRect(majorCanvasWidth/2 - 10, 10,majorCanvasWidth/2 + 20 ,20 , paint);
		
		paint.setColor(Color.RED);
		majorCanvas.drawRect(majorCanvasWidth/2 - 20, 20,majorCanvasWidth/2 + 30 ,30, paint);
		
		paint.setColor(Color.BLUE);
		majorCanvas.drawRect(majorCanvasWidth/2 - 30, 30, majorCanvasWidth/2 + 40, 40, paint);
		
		
	}

}
