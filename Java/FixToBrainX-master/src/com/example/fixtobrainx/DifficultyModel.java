package com.example.fixtobrainx;

import java.io.Serializable;
//NRD8G-T43GT-MX7B3-VY399-WTWRM

public class DifficultyModel implements Serializable{
	
	private int firstNumberLength;
	private int secondNumberLength;
	private int modder;
	
	
	private String valueString = "";
	
	//Constructor
	public DifficultyModel(){
		this.firstNumberLength = 100;
		this.secondNumberLength = 100;
		this.modder = 5;
		
		this.valueString = "Default Values: " + this.firstNumberLength +","+this.secondNumberLength;
	}
	
	public void setFirstNumberLength(double first){
		
		if(first <= 1.0 || first == 1.5){
			this.firstNumberLength = 100;
		}else if(first == 2.0 || first == 2.5){
			this.firstNumberLength = 100;
		}else if(first == 3.0 || first == 3.5){
			this.firstNumberLength = 1000;
		}else if(first == 4.0 || first == 4.5){
			this.firstNumberLength = 10000;
		}else if(first == 5.0 ){
			this.firstNumberLength = 100000;
		}
		
		this.valueString = "Second Values: " + this.firstNumberLength +","+this.secondNumberLength;
	}
	
	public void setSecondNumberLength(double second){
		
		if(second <= 1.0 || second == 1.5){
			this.secondNumberLength = 100;
		}else if(second == 2.0 || second == 2.5){
			this.secondNumberLength = 100;
		}else if(second == 3.0 || second == 3.5){
			this.secondNumberLength = 1000;
		}else if(second == 4.0 || second == 4.5){
			this.secondNumberLength = 10000;
		}else if(second == 5.0 ){
			this.secondNumberLength = 100000;
		}
		
		this.valueString = "Second Values: " + this.firstNumberLength +","+this.secondNumberLength;
	}
	
	public int getFirstNumber(){
		return this.firstNumberLength;
	}
	
	public int getSecondNumber(){
		return this.secondNumberLength;
	}
	
	//Setting the progress bar value
	public void setModder(int mod){
		this.modder = mod;
		this.valueString = "Second Values: " + this.firstNumberLength +","+this.secondNumberLength;
	}
	
	//Getting the progress bar value
	public int getModder(){
		return this.modder;
	}
	

}
