var PlanetModel = Backbone.Model.extend({
	defaults : {
		urlRoot : "http://localhost:8080/BeautifulPlanets/jsonData/",
		url: "http://localhost:8080/BeautifulPlanets/jsonData/",
		tagId: "planetDiv"
	},

	calculatePull : function(value){
		alert("Miami " + value);
	}

});

var PlanetRouter = Backbone.Router.extend({
	routes : {
		"test/:id" : 'show'
	},

	show : function(id){
		alert("Router Function" + id);
	}
});

var PlanetView = Backbone.View.extend({

	id: '',  
    tagName: 'div', 
    className: 'planetDiv',
    escape: "",
    template: _.template('<section class=\'tester\'>' +
    	'<h1><%= Name%></h1><br>' +
    	'<img src=\"' + '<%= ImageUrl%>' +'\" width=\"250px\" height=\"250px\">'+
    	'<ul>'+
    	'<li>Size: <%= Size%></li>'+
    	'<li>Mass: <%= Mass%></li>'+
    	'<li>Aphelion: <%= Aphelion%></li>'+
    	'<li>Perihelion: <%= Perihelion%></li>'+
    	'<li>GravitationalForce: <%= GravitationalForce%></li>'+
    	'</ul>'+
    	'<article><h3>Composition:</h3> <%= Composition%> </article>' +
    	'</section>'),

    events: {
    	"click h1" : function(){
    		alert("Functional Click");
    		var modelInQuestion = new PlanetModel({id:this.model.id});	
    		modelInQuestion.url = "../jsonData/" + modelInQuestion.id ;
    		modelInQuestion.fetch({async:false});
    		var modelInQuestionView = new PlanetView({model:modelInQuestion});
    		modelInQuestionView.render();


    		console.log(modelInQuestion);

    		$('#contentAreaHolder').html(modelInQuestionView.el);

    		var router = new PlanetRouter({
				routes : {
						"test/:id" : 'show'
				},

				show : function(id){
					console.log("Router Function " + id);
				}

    		});

    		router.navigate('test/' + modelInQuestion.attributes.Name, {
    			trigger: true
    		});
    	}

    },

	defaults :{
   		
	},
	initialize: function(){

	},
	render : function(){
		var attributes =  this.model.toJSON();
		console.log(this.model.get('ImageUrl').replace("\"",""));
		this.$el.html(this.template(attributes)).slideToggle()
		.css({'background-image' : 'url("'+this.model.get('ImageUrl')+'")',
			'background-position' : 'top left',
			'background-repeat' : 'repeat',
			});
	}

});

// <% if(status == "Completed") print("Checked") %>

$(document).ready(function(){


	$('#inputButton').on('click',function(){

		$('.planetDiv').remove();

		var samplePlanet = new PlanetModel({id:"mercuryData.json"});
		samplePlanet.url = "../jsonData/mercuryData.json";
		samplePlanet.fetch({async: false});

		var samplePlanet2 = new PlanetModel({id:"venusData.json"});
		samplePlanet2.url = "../jsonData/venusData.json";
		samplePlanet2.fetch({async: false});

		var samplePlanet3 = new PlanetModel({id:"earthData.json"});
		samplePlanet3.url = "../jsonData/earthData.json";
		samplePlanet3.fetch({async: false});

		var samplePlanet4 = new PlanetModel({id:"marsData.json"});
		samplePlanet4.url = "../jsonData/marsData.json";
		samplePlanet4.fetch({async: false});

		var samplePlanet5 = new PlanetModel({id:"jupiterData.json"});
		samplePlanet5.url = "../jsonData/jupiterData.json";
		samplePlanet5.fetch({async: false});

		var samplePlanet6 = new PlanetModel({id:"saturnData.json"});
		samplePlanet6.url = "../jsonData/saturnData.json";
		samplePlanet6.fetch({async: false});

		var samplePlanet7 = new PlanetModel({id:"uranusData.json"});
		samplePlanet7.url = "../jsonData/uranusData.json";
		samplePlanet7.fetch({async: false});

		var samplePlanet8 = new PlanetModel({id:"neptuneData.json"});
		samplePlanet8.url = "../jsonData/neptuneData.json";
		samplePlanet8.fetch({async: false});

		var samplePlanet9 = new PlanetModel({id:"plutoData.json"});
		samplePlanet9.url = "../jsonData/plutoData.json";
		samplePlanet9.fetch({async: false});

		console.log("Hit");

		var samplePlanetView = new PlanetView({model:samplePlanet});
		samplePlanetView.render();
		$('#contentAreaHolder').append(samplePlanetView.el);

		var samplePlanetView2 = new PlanetView({model:samplePlanet2});
		samplePlanetView2.render();
		$('#contentAreaHolder').append(samplePlanetView2.el);

		var samplePlanetView3 = new PlanetView({model:samplePlanet3});
		samplePlanetView3.render();
		$('#contentAreaHolder').append(samplePlanetView3.el);

		var samplePlanetView4 = new PlanetView({model:samplePlanet4});
		samplePlanetView4.render();
		$('#contentAreaHolder').append(samplePlanetView4.el);

		var samplePlanetView5 = new PlanetView({model:samplePlanet5});
		samplePlanetView5.render();
		$('#contentAreaHolder').append(samplePlanetView5.el);

		var samplePlanetView6 = new PlanetView({model:samplePlanet6});
		samplePlanetView6.render();
		$('#contentAreaHolder').append(samplePlanetView6.el);

		var samplePlanetView7 = new PlanetView({model:samplePlanet7});
		samplePlanetView7.render();
		$('#contentAreaHolder').append(samplePlanetView7.el);

		var samplePlanetView8 = new PlanetView({model:samplePlanet8});
		samplePlanetView8.render();
		$('#contentAreaHolder').append(samplePlanetView8.el);

		var samplePlanetView9 = new PlanetView({model:samplePlanet9});
		samplePlanetView9.render();
		$('#contentAreaHolder').append(samplePlanetView9.el);

		Backbone.history.start({pushState:true});
	});



});