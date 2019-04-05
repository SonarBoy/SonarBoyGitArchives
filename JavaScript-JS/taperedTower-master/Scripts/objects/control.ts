/// <reference path="../../typings/tsd.d.ts"/>

module objects {
    // CONTROL CLASS ++++++++++++++++++++++++++++++++++++++++++
    export class Control { 
        
        public rotationSpeed: number;
        public rotationSpeedSecond: number;
        public rotationSpeedThird: number;
        public rotationSpeedFourth: number;
        public rotationSpeedFifth: number;
        
        
        
        // CONSTRUCTOR ++++++++++++++++++++++++++++++++++++++++
        constructor(numberOne:number,numberTwo:number,numberThree:number,numberFour:number,numberFive:number) {
            
        this.rotationSpeed = numberOne;
        this.rotationSpeedSecond = numberTwo;
        this.rotationSpeedThird = numberThree;
        this.rotationSpeedFourth = numberFour;
        this.rotationSpeedFifth = numberFive;
        }
        
        public add(numbers:number,stringName:String){
            
           
            
        }
        
        //PUBLIC METHODS +++++++++++++++++++++++++++++++++++++++
       
    }
}
