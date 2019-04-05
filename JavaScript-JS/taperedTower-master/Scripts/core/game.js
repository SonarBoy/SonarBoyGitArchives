/// <reference path="_reference.ts"/>
// MAIN GAME FILE
// THREEJS Aliases
var Scene = THREE.Scene;
var Renderer = THREE.WebGLRenderer;
var PerspectiveCamera = THREE.PerspectiveCamera;
var BoxGeometry = THREE.BoxGeometry;
var CubeGeometry = THREE.CubeGeometry;
var PlaneGeometry = THREE.PlaneGeometry;
var SphereGeometry = THREE.SphereGeometry;
var Geometry = THREE.Geometry;
var AxisHelper = THREE.AxisHelper;
var LambertMaterial = THREE.MeshLambertMaterial;
var MeshBasicMaterial = THREE.MeshBasicMaterial;
var Material = THREE.Material;
var Mesh = THREE.Mesh;
var Object3D = THREE.Object3D;
var SpotLight = THREE.SpotLight;
var PointLight = THREE.PointLight;
var AmbientLight = THREE.AmbientLight;
var Control = objects.Control;
var GUI = dat.GUI;
var Color = THREE.Color;
var Vector3 = THREE.Vector3;
var Face3 = THREE.Face3;
var Point = objects.Point;
var CScreen = config.Screen;
//Custom Game Objects
var gameObject = objects.gameObject;
//--------------------
var basicMesh;
var basicMaterial;
var spotLight;
var pointLightTop;
var light;
var firstCube;
var secondCube;
var thirdCube;
var fourthCube;
var fifthCube;
var plane;
// setup an IIFE structure (Immediately Invoked Function Expression)
var game = (function () {
    // declare game objects
    var scene = new Scene();
    var renderer;
    var camera;
    var control;
    var gui;
    var stats;
    function init() {
        // Instantiate a new Scene object
        //scene = new Scene();
        setupRenderer(); // setup the default renderer
        setupCamera(); // setup the camera
        var light = new THREE.AmbientLight(0x404040); // soft white light
        scene.add(light);
        pointLightTop = new PointLight(0xffffff, 10, 100);
        pointLightTop.position.set(30, 30, 0);
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
        control = new Control(5, 5, 5, 5, 5);
        addControl(control);
        // Add framerate stats
        addStatsObject();
        console.log("Added Stats to scene...");
        document.body.appendChild(renderer.domElement);
        gameLoop(); // render the scene	
    }
    function createTower() {
        basicMesh = new CubeGeometry(3, 3, 3);
        basicMaterial = new LambertMaterial({ color: 0x251421, opacity: 0 });
        firstCube = new objects.gameObject(basicMesh, basicMaterial, 0, 0, 0);
        basicMesh = new CubeGeometry(2, 2, 2);
        basicMaterial = new LambertMaterial({ color: 0x774455 + Math.random(), opacity: 0 });
        secondCube = new objects.gameObject(basicMesh, basicMaterial, 0, 3, 0);
        basicMesh = new CubeGeometry(1, 1, 1);
        basicMaterial = new LambertMaterial({ color: 0x336622 + Math.random(), opacity: 0 });
        thirdCube = new objects.gameObject(basicMesh, basicMaterial, 0, 4, 0);
        basicMesh = new CubeGeometry(1, 1, 1);
        basicMaterial = new LambertMaterial({ color: 0x343434, opacity: 0 });
        fourthCube = new objects.gameObject(basicMesh, basicMaterial, 0, 5, 0);
        basicMesh = new CubeGeometry(1, 1, 1);
        basicMaterial = new LambertMaterial({ color: 0x114422 + Math.random(), opacity: 0 });
        fifthCube = new objects.gameObject(basicMesh, basicMaterial, 0, 7, 0);
        basicMesh = new CubeGeometry(10, 1, 10);
        basicMaterial = new LambertMaterial({ color: 0x448855 + Math.random(), opacity: 0 });
        plane = new objects.gameObject(basicMesh, basicMaterial, 0, -2, 0);
    }
    function addControl(controlObject) {
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
    function gameLoop() {
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
    function setupRenderer() {
        renderer = new Renderer();
        renderer.setClearColor(0xffffff, 1.0);
        renderer.setSize(CScreen.WIDTH, CScreen.HEIGHT);
        renderer.shadowMap.enabled = true;
        console.log("Finished setting up Renderer...");
    }
    // Setup main camera for the scene
    function setupCamera() {
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
    };
})();
//# sourceMappingURL=game.js.map