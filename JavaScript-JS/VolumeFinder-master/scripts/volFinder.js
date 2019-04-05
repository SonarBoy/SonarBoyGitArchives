//Radius Always First

function cubeLengths(value){
	var length = [(Math.pow(value , 1/3)).toFixed(2)];
	return length;
}

function sphereRadius(value){
	var input = ((3 * value )/ (4 * Math.PI));
	var radius = [(Math.pow(input,1/3)).toFixed(2)];

	return radius;
}

function cylinderRadiusHeight(value){
	var dimensionsArray = [];
	var height = Math.pow(value,1/3).toFixed(2);
	var subFunction = (value/(Math.PI * height));
	var radius = Math.pow(subFunction,1/2).toFixed(2);

	dimensionsArray.push(radius);
	dimensionsArray.push(height);

	return dimensionsArray;
}

function coneRadiusHeight(value){
	var dimensionsArray = [];
	var height = Math.pow(value,1/3).toFixed(2);
	var subFunction = ((3 * value)/(Math.PI * height));
	var radius = Math.pow(subFunction,1/2).toFixed(2);

	dimensionsArray.push(radius);
	dimensionsArray.push(height);

	return dimensionsArray;
}

$(document).ready(function(event){


	//Calls Functions above
	$('#volumeButton').on('click',function(){
		var inputVolume = $('#volumeInput').val();

		$('.shapeDisplayPanel').remove();

		 if(isNaN(inputVolume)){

		 	alert("Enter a Number!");
		 	
		 }else{
		 	shapeDisplay("Cone",coneRadiusHeight(inputVolume));
		 	shapeDisplay("Cylinder",cylinderRadiusHeight(inputVolume));
		 	shapeDisplay("Sphere",sphereRadius(inputVolume));
		 	shapeDisplay("Cube",cubeLengths(inputVolume));
		 }
	
	});

	//Display creator function
	function shapeDisplay(shapeType,values){

		var imageSource = "";
		var paragraph = "";

		switch(shapeType){

			case "Cube" :
				imageSource = './images/cube.gif';
				paragraph = "<p>Length:" + values[0] + " </p>";
			break;

			case "Sphere" :
				imageSource = './images/sphere.gif';
				paragraph = "<p>Radius:" + values[0] + " </p>";
			break;

			case "Cylinder" :
				imageSource = './images/cylinder.png';
				paragraph = "<p>Radius:" + values[0] +" Height:"+values[1]+ " </p>";
			break;

			case "Cone" :
				imageSource = './images/cone.png';
				paragraph = "<p>Radius:" + values[0] +" Height:"+values[1]+ " </p>";
			break;
		}	

		var outputTitle = $("<h3>Shape Type:"+shapeType+"</h3>").attr('class','shapeInfoText');
		var outputParagraph = $(paragraph).attr('class','paraInfo').appendTo(outputTitle);
		var image = $("<img></img>").attr({src: imageSource ,class:'imageIcon'}).add(outputTitle);
		
		var testDiv = $("<div></div>")
		.prepend(image)
		.addClass('shapeDisplayPanel')
		.fadeIn('easeInQuad');

		$('#basicPanel').after(testDiv);
	}

});