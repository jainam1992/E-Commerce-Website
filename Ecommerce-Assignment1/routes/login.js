var databaseUrl = "test"; // "username:password@example.com/mydb"
var collections = ["login_data"];
var db = require("mongojs").connect(databaseUrl, collections);


exports.login = function(req, res){
	db.image_data.find( function(err, login_data) {
		 db.menu_data.find(function(err, menu) {
				menu_data=menu;  	  
				res.render('login', { error:""});
			  
			});
	});
  
}