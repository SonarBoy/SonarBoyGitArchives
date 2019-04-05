function cleanup(inputValue){
		var array = [];
		var leng = inputValue.length;
		var lastPos = 0;

		if(leng < 80){
			array.push(inputValue);
			return array;
		}

		for(var runner = 0; runner < leng; runner++){

			if(runner % 80 == 0){
				array.push(inputValue.substring(lastPos,runner));
				lastPos = runner;
			}

		}

		array.shift();
		return array;
	}


$(document).ready(function(){

	var divId = 0;

	$("#buttonTest").on('click',function(){

		$(".contentArea").remove();

		var theArray = "0";
		var count = 0;
		var topOut = $("#inputBox").val();
		var holder = "";

		if(isNaN(topOut)){
			alert("Enter A Number");
		}else{

			while(count <= topOut){

				for(var runner = 0; runner <= theArray.length; runner ++){

					if(theArray.charAt(runner) == '0'){
						holder += "1";
					}else if(theArray.charAt(runner) == '1'){
						holder += "0";
					}
				}

				adderNode(theArray);

				theArray += holder;
				holder = "";
				count++ ;
			}

		}	

	});

	function adderNode(value){
		divId++;

		var elements = cleanup(value);
		var eleLength = elements.length;

		var basic = "<div>  </div>"
		$('#place').before($(basic)
			.attr('id',divId)
			.addClass('contentArea')
			.hide().fadeIn("slow")
			.on('mouseenter',function(){
				$(this).removeClass('contentArea');
				$(this).addClass('contentAreaTwo');
			}
			).on('mouseleave',function(){
				$(this).removeClass('contentAreaTwo');
				$(this).addClass('contentArea');
			}));

		for(var run = 0; run < eleLength; run++){

			var divHeight = $('#'+divId).children().length;

			$('#'+divId).css('height',((60 * divHeight) + 100)  +"px")
			.append("<p>" + elements[run] + "</p>");
		}

	}

});