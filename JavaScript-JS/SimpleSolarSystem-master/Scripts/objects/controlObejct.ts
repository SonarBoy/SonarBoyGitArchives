module controlObject {
    export class Control {


        rotationSpeed: number;
        opacity: number;
        color: number;
        numberOfObjects: number;
        planeWidth: number;
        planeHeight: number;

        constructor(rotationSpeed: number, opacity: number, color: number) {

            this.rotationSpeed = rotationSpeed;
            this.opacity = opacity;
            this.color = color;
        }
    }


}