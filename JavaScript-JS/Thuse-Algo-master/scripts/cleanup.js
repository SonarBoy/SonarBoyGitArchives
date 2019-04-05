$(document).ready(function(){

	$('#button').on('click',function(){
		var x = $('#text').val();
		var array = [];
		var leng = x.length;
		var lastPos = 0;



		for(var runner = 0; runner < leng; runner++){

			if(runner % 80 == 0){
				array.push(x.substring(lastPos,runner));
				lastPos = runner;
			}

		}

		array.shift();
		console.log(array);

		
	});

});