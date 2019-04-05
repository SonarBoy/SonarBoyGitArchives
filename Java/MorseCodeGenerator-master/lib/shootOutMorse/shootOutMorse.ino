void setup(){
  pinMode(13,OUTPUT);
  Serial.begin(9600);
}

void loop(){
  
    while(Serial.available() > 0){
      String numb = Serial.readString();
      //Serial.println(Serial.read());
      
      for(int runner = 0; runner <= numb.length() - 1; runner++){
        //Serial.println(numb.charAt(runner));
        
        if(numb.charAt(runner) == '.'){
          Serial.print("Dot");
          dot();
          delay(20);
        }else if(numb.charAt(runner) == '-'){
          Serial.print("Dash");          
          dash();
          delay(25);
        }
        
      }
      
      Serial.println();
    }
  
    
   /* if(numb == 1){
      digitalWrite(13,HIGH);
      delay(50);
      digitalWrite(13,LOW);
    }else if(numb == 2){
      digitalWrite(13,HIGH);
      delay(100);
      digitalWrite(13,LOW);
    } */
    
  
}

void dot(){
  digitalWrite(13,HIGH);
  delay(100);
  digitalWrite(13,LOW);
}

void dash(){
 digitalWrite(13,HIGH);
 delay(200);
 digitalWrite(13,LOW); 
}
