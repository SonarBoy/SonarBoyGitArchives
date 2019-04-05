/// <reference path="typings/tsd.d.ts" />
/// <reference path="typings/threejs/three.d.ts" />
/// <reference path="typings/dat-gui/dat-gui.d.ts" />

module objects {
    export class GameObject extends THREE.Mesh {
        //PRIVATE INSTANCE VARIABLES +++++++++++++++++++++++++++++++++++++
        private _geometry: THREE.Geometry;
        private _material: THREE.Material;
        //CONSTRUCTOR ++++++++++++++++++++++++++++++++++++++++++++++++++++
        constructor(geometry: THREE.Geometry, material: THREE.Material, x:number, y:number, z:number) {
            super(geometry, material);
            this._geometry = geometry;
            this._material = material;
            this.position.x = x;
            this.position.y = y;
            this.position.z = z;
            this.receiveShadow = true;
            this.castShadow = true;
        }
    }
    

}
module controlObject {
    export class DatGuiControl {
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

// Imports of all nessary components
import Scene = THREE.Scene;
import Renderer = THREE.WebGLRenderer;
import PerspectiveCamera = THREE.PerspectiveCamera;
import CubeGeometry = THREE.CubeGeometry;
import LambertMaterial = THREE.MeshLambertMaterial;
import Mesh = THREE.Mesh;
import Object3D = THREE.Object3D;
import SpotLight = THREE.SpotLight;
import CubeColor = THREE.Color;
import Vector3 = THREE.Vector3;
import Sphere = THREE.SphereGeometry;
import GUI = dat.GUI;
import PointLight = THREE.PointLight;
//=-----------------------
var scene:Scene;
var renderer: Renderer;
var camera: PerspectiveCamera;
var spotLight: SpotLight;
var spotLight2: SpotLight;
var pointLight: PointLight;
var pointLightTop: PointLight;
var pointLightBottom: PointLight;
var pointLightNorth: PointLight;
var pointLightSouth: PointLight;
var pointLightEast: PointLight;
var pointLightWest: PointLight;
var spotLightWest: SpotLight;
var basicPlanet: Sphere;
var basicPlanetMaterial: LambertMaterial;
var standardGui: GUI;
var standardControl: controlObject.DatGuiControl;
var secondaryControl: controlObject.DatGuiControl;

//=------------------------
var Sun: Mesh;
var SunTwo: Mesh;
var SunThree: Mesh;
var SunFour: Mesh;
var SunFive: Mesh;
var Mercury: Mesh;
var Venus: Mesh;
var Earth: Mesh;
var Mars: Mesh;
var Jupiter:Mesh;
var Moon:Mesh;

function init(): void{
    console.log("Init Fired");
    
    
    scene = new Scene();
    console.log("Scene Created");
    
    setupRenderer();
    setupCamera();
    solarSystemSetup();
    
    
    var axisHelper = new THREE.AxisHelper(200);
    
    spotLight = new SpotLight(0xffffff);
    spotLight.position.set(0, 20, 20);
    spotLight.distance = 200;
    spotLight.intensity = 3;
    spotLight.angle = Math.PI / 2;
    
    
    spotLight2 = new SpotLight(0xffffff);
    spotLight2.position.set(0, -20, -20);
    spotLight2.distance = 200;
    spotLight2.intensity = 3;
    spotLight2.angle = Math.PI / 2;
    
    
    
    pointLightTop = new PointLight( 0xffffff, 1, 10000);
    pointLightTop.position.set(0,7,0);
    pointLightTop.castShadow = true;
    
    pointLightBottom = new PointLight(0xffffff, 1, 10000);
    pointLightBottom.position.set(0,-7,0);
    
    
    pointLightNorth = new PointLight(0xffffff, 1, 10000);
    pointLightNorth.position.set(7,0,0);
    
    
    pointLightSouth = new PointLight(0xffffff, 1, 10000);
    pointLightSouth.position.set(-7,0,0);
    
    
    pointLightEast = new PointLight(0xffffff, 1, 10000);
    pointLightEast.position.set(0,0,7);
    
    
    pointLightWest = new PointLight(0xffffff, 1, 10000);
    pointLightWest.position.set(0,0,-7);
    
    
     scene.add(pointLightTop);
     scene.add(pointLightBottom);
     scene.add(pointLightNorth);
     scene.add(pointLightSouth);
     scene.add(pointLightEast);
     scene.add(pointLightWest);
     scene.add(spotLight);
     scene.add(spotLight2);
    console.log("Added Spot Light to Scene");
   
    standardGui = new GUI();
    standardControl = new controlObject.DatGuiControl(0.005, basicPlanetMaterial.opacity, basicPlanetMaterial.color.getHex());
    setupControls(standardControl);
    
    
    scene.add(axisHelper);
    scene.add(Sun);
    scene.add(SunTwo);
    scene.add(SunThree);
    scene.add(SunFour);
    scene.add(SunFive);
 
   
    document.body.appendChild(renderer.domElement);
    
    
    gameLoop();
}

function setupRenderer(): void {
    renderer = new Renderer();
    renderer.setClearColor(0x000000, 1.0);
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.shadowMapEnabled = true;
    console.log("Finished setting up Renderer...");
}

function setupControls(controlObject: controlObject.DatGuiControl): void{
    standardGui.add(controlObject, 'rotationSpeed', -0.2, 0.2);
    standardGui.add(controlObject, 'opacity', 0.1, 10);
    standardGui.addColor(controlObject, 'color');
}

function setupCamera(): void {
    camera = new PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 1000);
    camera.position.x = 30;
    camera.position.y = 30;
    camera.position.z = 30;
    camera.lookAt(new Vector3(0, 0, 0));
    console.log("Finished setting up Camera...");
    
  
    
    
}

function solarSystemSetup(): void{
    
   
    // First Sun and Mercury
    basicPlanet = new Sphere(5,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0xff2233,opacity:1,transparent: true});
    
    Sun = new objects.GameObject(basicPlanet,basicPlanetMaterial,0,0,0);
  
    basicPlanet = new Sphere(1,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0xfff333,opacity:0});
    
    Mercury = new objects.GameObject(basicPlanet,basicPlanetMaterial,9,0,0);
    // First Sun and Mercury
    
    
    //Second Sun and Venus
    basicPlanet = new Sphere(1,10,10);
    basicPlanetMaterial = new LambertMaterial({color:0x000000,opacity:0});
    
    SunTwo = new objects.GameObject(basicPlanet,basicPlanetMaterial,0,0,0);
    
    basicPlanet = new Sphere(1,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0x13300f,opacity:0});
    
    Venus = new objects.GameObject(basicPlanet,basicPlanetMaterial,11,0,0);
    //Second Sun and Venus
    
    
    
    //Third Sun and Earth
    basicPlanet = new Sphere(1,10,10);
    basicPlanetMaterial = new LambertMaterial({color:0x000000,opacity:0});
    
    SunThree = new objects.GameObject(basicPlanet,basicPlanetMaterial,0,0,0);
    
    basicPlanet = new Sphere(1.6,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0x1330ff,opacity:0});
    
    Earth = new objects.GameObject(basicPlanet,basicPlanetMaterial,15,0,0);
    //Third Sun and Venus
    
    
    //Fourth Sun and Earth
    basicPlanet = new Sphere(1,10,10);
    basicPlanetMaterial = new LambertMaterial({color:0x000000,opacity:0});
    
    SunFour = new objects.GameObject(basicPlanet,basicPlanetMaterial,0,0,0);
    
    basicPlanet = new Sphere(1.7,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0x552233,opacity:0});
    
    Mars = new objects.GameObject(basicPlanet,basicPlanetMaterial,19,0,0);
    //Fourth Sun and Venus
    
    //Five Sun and Jupiter
    basicPlanet = new Sphere(1,10,10);
    basicPlanetMaterial = new LambertMaterial({color:0x00000,opacity:0});
    
    SunFive = new objects.GameObject(basicPlanet,basicPlanetMaterial,0,0,0);
    
    basicPlanet = new Sphere(2,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0x552233,opacity:0});
    
    Jupiter = new objects.GameObject(basicPlanet,basicPlanetMaterial,30,0,0);
    //Five Sun and Jupiter
    
    
    basicPlanet = new Sphere(1,32,32);
    basicPlanetMaterial = new LambertMaterial({color:0xfff452,opacity:0});
    
    Moon = new objects.GameObject(basicPlanet,basicPlanetMaterial,4,0,0);
    Jupiter.add(Moon);
    
    
    Sun.add(Mercury);    
    SunTwo.add(Venus);
    SunThree.add(Earth);
    SunFour.add(Mars);
    SunFive.add(Jupiter);
}

function gameLoop():void{
    
    Sun.rotation.y += 0.003;
    SunTwo.rotation.y += 0.002;
    SunThree.rotation.y += 0.01;
    SunFour.rotation.y += 0.02;
    SunFive.rotation.y += 0.001;
    Jupiter.rotation.y += standardControl.rotationSpeed;
    
    cameraMovement();
    
    requestAnimationFrame(gameLoop);
    renderer.render(scene, camera);
}


function cameraMovement(): void{

    if(standardControl.opacity > 5 ){
        
        camera.position.x = 40;
        camera.position.y = 40;
        camera.position.z = 40;
        
        camera.lookAt(new Vector3(0,0,0));
        Sun.add(camera);
        
    }else if(standardControl.opacity <= 1 && standardControl.opacity >= 0){
        
        Sun.add(camera);
        camera.lookAt(new Vector3(Mercury.position.x,Mercury.position.y,Mercury.position.z));
    }else if(standardControl.opacity <= 2 && standardControl.opacity >= 1){
        
        SunTwo.add(camera);
        camera.lookAt(new Vector3(Venus.position.x,Venus.position.y,0));
    }else if(standardControl.opacity <= 3 && standardControl.opacity >= 2){
        
        SunThree.add(camera);
        camera.lookAt(new Vector3(Earth.position.x,Earth.position.y,0));
    }else if(standardControl.opacity <= 4 && standardControl.opacity >= 3){
        
        SunFour.add(camera);
        camera.lookAt(new Vector3(Mars.position.x,Mars.position.y,0));
    }else if(standardControl.opacity <= 5 && standardControl.opacity >= 4){
        
        SunFive.add(camera);
        camera.lookAt(new Vector3(Mars.position.x,Mars.position.y,0));
    }
    
    
   
}

