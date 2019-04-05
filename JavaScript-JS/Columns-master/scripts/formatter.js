// Promise Object
var Book = {
	getText: function(bookTitle,charLength){
		var promise = $.Deferred();

		$.ajax('./exampleFile/' + bookTitle + '.txt',{

			success : function(result){
				var textValue = formatString(String(result), charLength);
				promise.resolve(textValue);
			},

			error: function(request,errorType,errorMessage){
				var textFailValue = "Error: " +request +"\n"+"Error Type: "+errorType +"\n"+"Error Message: "+errorMessage ;
				promise.reject(textFailValue);
			}

		});

		return promise;
	}
}


function formatString(inputString,segment){

	var rowsArray = [];

	var stringLength = inputString.length;
	var firstIndex = 0;
	var secondIndex = 0;

	inputString = inputString.replace(/\s/gi," ");

	if(segment >= stringLength){
		return rowsArray[0] = "Test";
	}else if(segment == null){
		segment = 10;
	}



	for(var runner = 0; runner < stringLength;runner++){

		secondIndex++;

		if(secondIndex % segment == 0){
			rowsArray.push(inputString.substring(firstIndex,secondIndex));
			firstIndex = secondIndex;
		}

		if(runner == stringLength - 1){
			rowsArray.push(inputString.substring(firstIndex,secondIndex));
		}
	}

	return rowsArray;

}


$(document).ready(function(){

	$('#format').on('click',function(){

		var row = $('#rowInput').val();
		var column = $('#columnInput').val();
		var inputFileName = $('#fileInput').val();


		var displayBook = Book.getText(inputFileName,column);
		
		displayBook.done(function(result){
			console.log(result);
			formatOutputDiv(result,row);
		});

		displayBook.fail(function(result){
			console.log("Fail" + "\n" + result);
		})




	});

	function formatOutputDiv(bookArray,rows){

		$("#outputDiv").empty();

		if(rows == null || rows == 0){
			rows = 10;
		}

		var outputDiv = $('#outputDiv');
		var holderDiv = $("<div></div>").addClass('testing');
		var list = $("<ul></ul>");

		$.each(bookArray,function(index,lineItem){

			if(index % rows == 0 && index != 0){
				$(holderDiv).append($(list));
				outputDiv.append(holderDiv.hide());
				holderDiv.fadeIn();
				holderDiv = $("<div></div>").addClass('testing');
				list = $("<ul></ul>");
			}
			
			list.append("<li>" + lineItem + "</li>");
		});

		$(holderDiv).append($(list));
		outputDiv.append(holderDiv.hide());
		holderDiv.fadeIn();
		holderDiv = $("<div></div>").addClass('testing');
		list = $("<ul></ul>");	
 
	}
});