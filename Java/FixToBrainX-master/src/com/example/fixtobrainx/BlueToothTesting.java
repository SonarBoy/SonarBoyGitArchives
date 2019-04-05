package com.example.fixtobrainx;



import java.util.Random;
import java.util.Set;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.text.style.CharacterStyle;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.text.style.RelativeSizeSpan;

public class BlueToothTesting extends Activity{

	Button check;
	Button spanButton;
	Button faderButton;
	TextView drawingBoard;
	TextView drawingBoardTwo;
	LinearLayout testingLayout;
	int toggle = 0;
	ObjectAnimator fadeAnim;
	ObjectAnimator fadeAnimSecond;
	
	public void onCreate(Bundle savedInstanceBundle){
		super.onCreate(savedInstanceBundle);
		setContentView(R.layout.bluetooth_example);
		
		
		this.drawingBoard = (TextView)findViewById(R.id.spanExampleOne);
		this.drawingBoardTwo = (TextView)findViewById(R.id.spanExampleTwo);
		
	//	this.check = (Button)findViewById(R.id.basic_Bluetooth_Check);
//		this.check.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//				if(adapter != null){
//					Toast.makeText(getBaseContext(), "YES!!", Toast.LENGTH_SHORT).show();
//					
//					
//					if(!adapter.isEnabled()){
//						Intent enableBtIntent = new 
//								Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//						startActivityForResult(enableBtIntent, 1);
//					}
//					
//					Set <BluetoothDevice> devices = adapter.getBondedDevices();
//					
//					// This is the quering of devices that are already there
////					if (devices.size() > 0) {
////					    // Loop through paired devices
////					    for (BluetoothDevice device : devices) {
////					        // Add the name and address to an array adapter to show in a ListView
////					    	Toast.makeText(getBaseContext(), device.getName(), Toast.LENGTH_SHORT).show();
////					    }
////					}
////					
//					
//					BroadcastReceiver x = new BroadcastReceiver() {
//						
//						
//						
//						@Override
//						public void onReceive(Context context, Intent intent) {
//							// TODO Auto-generated method stub
//							
//							String action = intent.getAction();
//							
//							
//							if(BluetoothDevice.ACTION_FOUND.equals(action)){
//								
//								BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//								
//								Toast.makeText(getBaseContext(), device.getName(), Toast.LENGTH_SHORT).show();
//								Toast.makeText(getBaseContext(), device.getAddress(), Toast.LENGTH_SHORT).show();
//								
//								
//							}
//						}
//					};
//					IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//					registerReceiver(x, filter);
//					
//					
//					
//				}else{
//					Toast.makeText(getBaseContext(), "NO!!", Toast.LENGTH_SHORT).show();
//				}
//				
//			}
//		});
		
		this.testingLayout = (LinearLayout)findViewById(R.id.testingLinearLayout);
		this.spanButton = (Button)findViewById(R.id.spanButton);
		this.spanButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				//"abcdefghijklmnopqrstuvwxyz1234567890"
				
				Random dice = new Random();

				SpannableStringBuilder stringBuilderFirstExample = new SpannableStringBuilder(""+dice.nextInt(10000));
				SpannableStringBuilder stringBuilderSecondExample = new SpannableStringBuilder(""+dice.nextInt(10000));
				
				
				
				BackgroundColorSpan[] colorSpans = new BackgroundColorSpan[10];
				
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
				
				
				
				
				//StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
				float sizeForLettersAssending = 1.3f;
				float sizeForLettersDesending = 1.9f;
				
				for(int runner = 0;runner < stringBuilderSecondExample.length();runner++){
					stringBuilderSecondExample.setSpan(new RelativeSizeSpan(sizeForLettersAssending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
					stringBuilderSecondExample.setSpan(colorSpans[runner],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
					sizeForLettersAssending += 0.2f;
				}
				
				
				for(int runner = 0;runner < stringBuilderFirstExample.length();runner++){
					stringBuilderFirstExample.setSpan(new RelativeSizeSpan(sizeForLettersDesending),runner,runner+1,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
					stringBuilderFirstExample.setSpan(colorSpans[runner + 5],runner,runner+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
					sizeForLettersDesending -= 0.2f;
				}
				
				
				drawingBoard.setTextColor(Color.WHITE);
				drawingBoard.setText(stringBuilderFirstExample);
				
				drawingBoardTwo.setTextColor(Color.WHITE);
				drawingBoardTwo.setText(stringBuilderSecondExample);
				
				
				
				
				
				ProgressBar x = new ProgressBar(getBaseContext(),null,
                        android.R.attr.progressBarStyleHorizontal);
				
				
				x.setProgress(dice.nextInt(100));
				
				testingLayout.addView(x);
			}
			
		});
		
		
		this.fadeAnim = ObjectAnimator.ofFloat(this.drawingBoard, "alpha", 0.0f);
		this.fadeAnimSecond = ObjectAnimator.ofFloat(this.drawingBoard, "alpha", 1.0f);
		
		
		this.faderButton = (Button)findViewById(R.id.faderButton);
		this.faderButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(toggle % 2 == 0){
					fadeAnim.start();
				}else if(toggle % 2 != 0){
					fadeAnimSecond.start();
				}
				
				toggle++;
			
			}
		});
		
	
	} //End of onCreate
	
	
	
		

	
	
}// End of Class
