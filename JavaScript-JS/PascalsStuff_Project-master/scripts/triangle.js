function pascalsTriangle(value){
	var holderArray = [];

	var retrivedArray = [];
	var workArray;

	var holderArrayRunner;
	var firstPos;
	var secondPos;
	
	holderArray.push([1]);
	holderArray.push([1,1]);

	for(holderArrayRunner = 0;holderArrayRunner <= value;holderArrayRunner++){

		if(holderArrayRunner == 2 ){

			workArray = new Array((holderArrayRunner + 1));
			workArray[0] = 1;
			workArray[1] = 2;
			workArray[holderArrayRunner] = 1;

			holderArray.push(workArray);
		}

		if(holderArrayRunner > 2 && holderArrayRunner != 2){

			workArray = new Array(holderArrayRunner + 1);
			workArray[0] = 1;
			workArray[holderArrayRunner] = 1;

			retrivedArray = holderArray[(holderArrayRunner - 1)];

			for(var runner = 1; runner <= (retrivedArray.length - 1); runner++){

				firstPos = retrivedArray[runner - 1];
				secondPos = retrivedArray[runner];

				workArray[runner] = firstPos + secondPos;
			}

			holderArray.push(workArray);
		}

	}

	return holderArray;

}



$(document).ready(function(){

	$('#inputButton').on('click',function(){
		$('.output').html('');

		var passerValue = $('#inputNumber').val();
		var modderNumber = $('#modderNumber').val();

		if(isNaN(passerValue) || isNaN(modderNumber)){
			alert("Enter Correct Numbers!");
		}



		var out = pascalsTriangle(passerValue);

		console.log(out);

		plaster(out,modderNumber);

	});


	function plaster(array,inputMod){

		var itemHolder = undefined;
		var color = "white";
		var divTile = undefined;

		$.each(array,function(index,rowItem){

			itemHolder = $('<div></div>').attr('id',index).attr('class','outputRow');
			$('.output').append(itemHolder);

			$.each(rowItem,function(indexInner,numberItem){

				divTile = $("<div>"+numberItem+ " </div>")
						.addClass('outputTile');

				if(modder(numberItem,inputMod)){
					color = "blue";
					divTile.css('background-color',color);
				}else{
					color = "green";
					divTile.css('background-color',color);
				}


				$('#'+index).append(divTile);
				
			});	
		});
		

	}

	function isPrime(num) {
	    if(num < 2) return false;

		    for (var i = 2; i < num/2; i++) {
		        if(num%i==0)
		            return false;
		    }

	    return true;
	}

	function modder(num,modder){

		if(num % modder == 0){
			return true;
		}else{
			return false;
		}
	}


});