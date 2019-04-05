//Released under the MIT License - Please reuse change and share
//Using the easy stepper with your arduino
//use rotate and/or rotateDeg to controll stepper motor
//speed is any number from .01 -> 1 with 1 being fastest - 
//Slower Speed == Stronger movement
/////////////////////////////////////////////////////////////////

#define DIR_PIN 2
#define STEP_PIN 3

long sensorsum = 0;
int n = 1;
int mean = 0;
int lastmean = 0;

void setup() { 
  pinMode(DIR_PIN, OUTPUT); 
  pinMode(STEP_PIN, OUTPUT); 
  Serial.begin(9600);
} 

void loop(){ 
    String val = Serial.readString();
  
  
    Serial.print(val);
    
    int sensorValue = analogRead(A0);
 
    // Calculate mean of measures of a specified distance in order to smooth the results and after create the regression line for distance
    /*sensorsum = (sensorsum + sensorValue);
    mean = (sensorsum / n);
    n = n + 1;
    
    
    Serial.print("Check: ");
    Serial.print(mean);
    Serial.println("");
    
    */
    
    /*
    
    // -35 clockwise
    rotateDeg(-35, .5);  //reverse
    delay(1000); 
    
    // 35 counter clockwise    
    rotateDeg(35, .5);
    delay(1000);
    
    */

  
  if(val.charAt(0) == '1'){ 
    
    rotateDeg(-35, .5);  //reverse
    shootOut();
    delay(1000); 
    
    rotateDeg(35, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(35, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(-35, .5);
    shootOut();
    delay(1000);
    
  }else if(val.charAt(0) == '2'){
    //rotate a specific number of microsteps (8 microsteps per step)
    //a 200 step stepper would take 1600 micro steps for one full revolution
    rotateDeg(-25, .5);  //reverse
    shootOut();
    delay(1000); 
    
    rotateDeg(25, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(25, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(-25, .5);
    shootOut();
    delay(1000);
    
  }else if(val.charAt(0) == '3'){
    //rotate a specific number of microsteps (8 microsteps per step)
    //a 200 step stepper would take 1600 micro steps for one full revolution
    
    rotateDeg(-15, .5);  //reverse
    shootOut();
    delay(1000); 
    
    rotateDeg(15, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(15, .5);
    shootOut();
    delay(1000);
    
    rotateDeg(-15, .5);
    shootOut();
    delay(1000);
  }
  

 
}



void rotate(int steps, float speed){ 
  //rotate a specific number of microsteps (8 microsteps per step) - (negitive for reverse movement)
  //speed is any number from .01 -> 1 with 1 being fastest - Slower is stronger
  int dir = (steps > 0)? HIGH:LOW;
  steps = abs(steps);

  digitalWrite(DIR_PIN,dir); 

  float usDelay = (1/speed) * 70;

  for(int i=0; i < steps; i++){ 
    digitalWrite(STEP_PIN, HIGH); 
    delayMicroseconds(usDelay); 

    digitalWrite(STEP_PIN, LOW); 
    delayMicroseconds(usDelay); 
  } 
} 

void shootOut(){
    
    n = 1;
    sensorsum = 0;
    lastmean = mean;
  
  
    for(int runner = 1; runner < 50;runner++){
      int sensorValue = analogRead(A0);
      sensorsum = (sensorsum + sensorValue);
      mean = (sensorsum / n);
      n = n + 1;
    }
    
    
    Serial.print("Check: ");
    Serial.print(mean);
    Serial.println("");
  
  }
  
void rotateDeg(float deg, float speed){ 
  //rotate a specific number of degrees (negitive for reverse movement)
  //speed is any number from .01 -> 1 with 1 being fastest - Slower is stronger
  int dir = (deg > 0)? HIGH:LOW;
  digitalWrite(DIR_PIN,dir); 

  int steps = abs(deg)*(1/0.225);
  float usDelay = (1/speed) * 70;

  for(int i=0; i < steps; i++){ 
    digitalWrite(STEP_PIN, HIGH); 
    delayMicroseconds(usDelay); 

    digitalWrite(STEP_PIN, LOW); 
    delayMicroseconds(usDelay); 
  } 
  

}


