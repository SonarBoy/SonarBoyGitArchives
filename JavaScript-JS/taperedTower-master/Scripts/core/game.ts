/// <reference path="_reference.ts"/>

// MAIN GAME FILE

// THREEJS Aliases
import Scene = THREE.Scene;
import Renderer = THREE.WebGLRenderer;
import PerspectiveCamera = THREE.PerspectiveCamera;
import BoxGeometry = THREE.BoxGeometry;
import CubeGeometry = THREE.CubeGeometry;
import PlaneGeometry = THREE.PlaneGeometry;
import SphereGeometry = THREE.SphereGeometry;
import Geometry = THREE.Geometry;
import AxisHelper = THREE.AxisHelper;
import LambertMaterial = THREE.MeshLambertMaterial;
import MeshBasicMaterial = THREE.MeshBasicMaterial;
import Material = THREE.Material;
import Mesh = THREE.Mesh;
import Object3D = THREE.Object3D;
import SpotLight = THREE.SpotLight;
import PointLight = THREE.PointLight;
import AmbientLight = THREE.AmbientLight;
import Control = objects.Control;
import GUI = dat.GUI;
import Color = THREE.Color;
import Vector3 = THREE.Vector3;
import Face3 = THREE.Face3;
import Point = objects.Point;
import CScreen = config.Screen;

//Custom Game Objects
import gameObject = objects.gameObject;

//--------------------
var basicMesh: CubeGeometry;
var basicMaterial: LambertMaterial;
var spotLight: SpotLight;
var pointLightTop: PointLight;
var light: AmbientLight;

var firstCube: Mesh;
var secondCube: Mesh;
var thirdCube: Mesh;
var fourthCube: Mesh;
var fifthCube: Mesh;
var plane: Mesh;


// setup an IIFE structure (Immediately Invoked Function Expression)
var game = (() => {

    // declare game objects
    var scene: Scene = new Scene();
    var renderer: Renderer;
    var camera: PerspectiveCamera;
    var control: Control;
    var gui: GUI;
    var stats: Stats;

    function init() {
        // Instantiate a new Scene object
        //scene = new Scene();
        
        setupRenderer(); // setup the default renderer
	
        setupCamera(); // setup the camera
        var light = new THREE.AmbientLight( 0x404040 ); // soft white light
        scene.add( light );
        
        pointLightTop = new PointLight( 0xffffff, 10, 100);
        pointLightTop.position.set(30,30,0);
        pointLightTop.castShadow = true;
        
        spotLight = new SpotLight(0xffffff);
        spotLight.position.set(30, 30, 30);
        spotLight.distance = 20;
        spotLight.intensity = 2;
        spotLight.angle = Math.PI / 2;
        spotLight.castShadow = true;
        scene.add(spotLight);
    console.log("Added Spot Light to Scene");

        /* ENTER CODE HERE */
        
        scene.add(pointLightTop);
        
        
        
        createTower();
        scene.add(firstCube);
        scene.add(secondCube);
        scene.add(thirdCube);
        scene.add(fourthCube);
        scene.add(fifthCube);
        
        scene.add(plane);
        console.log(spotLight);
 
        // add controls
        gui = new GUI();
        control = new Control(5,5,5,5,5);
        addControl(control);

        // Add framerate stats
        addStatsObject();
        console.log("Added Stats to scene...");

        document.body.appendChild(renderer.domElement);
        gameLoop(); // render the scene	

    }
    
    function createTower(){
        
         
        
        basicMesh = new CubeGeometry(3,3,3);
        basicMaterial = new LambertMaterial({color:0x251421,opacity:0});
        
        firstCube = new objects.gameObject(basicMesh,basicMaterial,0,0,0);
        
        basicMesh = new CubeGeometry(2,2,2);
        basicMaterial = new LambertMaterial({color:0x774455 + Math.random(),opacity:0});
        
        secondCube = new objects.gameObject(basicMesh,basicMaterial,0,3,0);
        
        basicMesh = new CubeGeometry(1,1,1);
        basicMaterial = new LambertMaterial({color:0x336622 + Math.random(),opacity:0});
        
        thirdCube = new objects.gameObject(basicMesh,basicMaterial,0,4,0);
        
        basicMesh = new CubeGeometry(1,1,1);
        basicMaterial = new LambertMaterial({color:0x343434,opacity:0});
        
        fourthCube = new objects.gameObject(basicMesh,basicMaterial,0,5,0);
        
        basicMesh = new CubeGeometry(1,1,1);
        basicMaterial = new LambertMaterial({color:0x114422 + Math.random(),opacity:0});
        
        fifthCube = new objects.gameObject(basicMesh,basicMaterial,0,7,0);
        
        
        basicMesh = new CubeGeometry(10,1,10);
        basicMaterial = new LambertMaterial({color:0x448855 + Math.random(),opacity:0});
        
        plane = new objects.gameObject(basicMesh,basicMaterial,0,-2,0);
        
    }

    function addControl(controlObject: Control): void {
        
        gui.add(controlObject, 'rotationSpeed', -0.2, 0.2);
        gui.add(controlObject, 'rotationSpeedSecond', -0.2, 0.2);
        gui.add(controlObject, 'rotationSpeedThird', -0.2, 0.2);
        gui.add(controlObject, 'rotationSpeedFourth', -0.2, 0.2);
        gui.add(controlObject, 'rotationSpeedFifth', -0.2, 0.2);
        
    }

    function addStatsObject() {
        stats = new Stats();
        stats.setMode(0);
        stats.domElement.style.position = 'absolute';
        stats.domElement.style.left = '0px';
        stats.domElement.style.top = '0px';
        document.body.appendChild(stats.domElement);
    }

    // Setup main game loop
    function gameLoop(): void {
        stats.update();
        
        
        firstCube.rotation.y += control.rotationSpeed;
        secondCube.rotation.y += control.rotationSpeedSecond;
        thirdCube.rotation.y += control.rotationSpeedThird;
        fourthCube.rotation.y += control.rotationSpeedFourth;
        fifthCube.rotation.y += control.rotationSpeedFifth;
        
        // render using requestAnimationFrame
        requestAnimationFrame(gameLoop);
	
        // render the scene
        renderer.render(scene, camera);
    }

    // Setup default renderer
    function setupRenderer(): void {
        renderer = new Renderer();
        renderer.setClearColor(0xffffff, 1.0);
        renderer.setSize(CScreen.WIDTH, CScreen.HEIGHT);
        renderer.shadowMap.enabled = true;
        console.log("Finished setting up Renderer...");
    }

    // Setup main camera for the scene
    function setupCamera(): void {
        camera = new PerspectiveCamera(35, config.Screen.RATIO, 0.1, 1000);
        camera.position.x = 30;
        camera.position.y = 30;
        camera.position.z = 30;
        camera.rotation.set(-1.10305, 0.49742, -0.1396);
        camera.lookAt(new Vector3(0, 0, 0));
        console.log("Finished setting up Camera...");
    }

    window.onload = init;

    return {
        scene: scene
    }

})();

