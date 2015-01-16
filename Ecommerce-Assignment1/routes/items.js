var databaseUrl = "test"; // "username:password@example.com/mydb"
var collections = ["product_data"];
var db = require("mongojs").connect(databaseUrl, collections);


exports.items = function(req, res){
	var name = req.query.category; 
	 db.product_data.find({category:name.toString()}, function(err, product_data) {
	  res.render('items', { product_data: product_data});
	});
	
};

exports.itemsinfo = function(req,res){
	var iteminfo = req.query.id; 
	 db.product_data.find({id:iteminfo.toString()}, function(err, product_data) {
	  
		res.render('itemsinfo', { product_data: product_data});
	});
	
};	
