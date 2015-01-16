
/**
 * Module dependencies.
 */

var express = require('express')
,items = require('./routes/items')
  , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path');


var app = express();
//var session = require('express-session');

// all environments
app.set('port', process.env.PORT || 8080);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));
//app.use(session({secret: 'ssshhhhh'}));
// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

app.get("/", routes.login);



app.post('/index', routes.index);
app.get('/users', user.list);
app.get('/items', items.items);
app.get('/iteminfo',items.itemsinfo);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
