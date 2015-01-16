
/*
 * GET home page.
 */
var databaseUrl = "test"; // "username:password@example.com/mydb"
var collections = ["image_data","menu_data","login_data"];
var db = require("mongojs").connect(databaseUrl, collections);
var usercollections = ["login_data"];
var dbUserDetail = require("mongojs").connect(databaseUrl, usercollections);


exports.login = function(req, res){
				res.render('login', { error:""});			  
}

exports.index = function(req, res){
	var uname = req.param("user");
	var password = req.param("pass");
	dbUserDetail.login_data.find({Username:uname,Password:password},function(err,user){
		if(user.length == 0){
			db.image_data.find( function(err, login_data) {
				 db.menu_data.find(function(err, menu) {
						menu_data=menu;  	  
						res.render('login', { login_data: login_data,error:"Invalid email/password"});
					  
					});
			});
		}
		else
			{
				if(user[0].Password == password)
				{
				db.image_data.find( function(err, image_data) {
					 db.menu_data.find(function(err, menu) {
							menu_data=menu;  	  
							res.render('index', { image_data: image_data, menu_data:menu_data});
						  
						});
				});
				}
			}
	});
	
	}
  


