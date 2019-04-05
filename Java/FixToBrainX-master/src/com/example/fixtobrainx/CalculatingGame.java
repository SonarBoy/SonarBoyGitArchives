package com.example.fixtobrainx;

import java.util.Random;


public class CalculatingGame {
	
	public int firstNumber = 0;
	public int secondNumber = 0;
	public int answer = 0;
	
	public Random number;
	public int numberLength = 100;
	public DifficultyModel difficulty;
	
	public CalculatingGame(){
		this.number = new Random();
		System.out.print("Works");
		this.difficulty = new DifficultyModel();
	}
	
	public CalculatingGame(int initialValue){
		this.number = new Random();
		this.numberLength = initialValue;
		System.out.print("Works");
	}
	
	public CalculatingGame(int initialValue,DifficultyModel diff){
		this.number = new Random();
		this.difficulty = diff;
		this.numberLength = this.difficulty.getFirstNumber();
		System.out.print("Works");
	}
	
	
	
	public void makeSubtraction(){
		
		this.firstNumber = this.number.nextInt(this.numberLength);
		this.secondNumber = this.number.nextInt(this.numberLength);
		this.answer = this.firstNumber - this.secondNumber;
		
	}
	
	public void makeAddition(){
		
		this.firstNumber = this.number.nextInt(this.numberLength);
		this.secondNumber = this.number.nextInt(this.numberLength);
		this.answer = this.firstNumber + this.secondNumber;

	}
	
	public void makeSubtraction(int value){
		// Adding for larger number modder
		if(value % 5 == 0){
			this.numberLength = numberLength * 10;
		}
		
		this.firstNumber = this.number.nextInt(this.numberLength);
		this.secondNumber = this.number.nextInt(this.numberLength);
		this.answer = this.firstNumber - this.secondNumber;
	}
	
	public void makeAddition(int value){
		//Adding for larger number modder
		if(value % 5 == 0){
			this.numberLength = numberLength * 10;
		}
		
		this.firstNumber = this.number.nextInt(this.numberLength);
		this.secondNumber = this.number.nextInt(this.numberLength);
		this.answer = this.firstNumber + this.secondNumber;
	}
	
	public int getFirstNumber(){
		return this.firstNumber;
	}
	
	public int getSecondNumber(){
		return this.secondNumber;
	}
	
	public boolean checkSubtraction(int value){
		
		if(this.firstNumber - this.secondNumber == value){
			return true;
		}else if(this.firstNumber - this.secondNumber != value){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean checkAddition(int value){
		
		if(this.firstNumber + this.secondNumber == value){
			return true;
		}else if(this.firstNumber + this.secondNumber != value){
			return false;
		}else{
			return true;
		}
	}
	
	public int getAnswerSubtraction(){
		return this.firstNumber - this.secondNumber;
	}
	
	public int getAnswerAddition(){
		return this.firstNumber + this.secondNumber;
	}
	
}