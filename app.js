var express = require('express');
const bcrypt = require('bcrypt');
const saltRounds = 10;
var mysql = require('mysql');

var connection = mysql.createConnection({
    host: 'localhost',
    user: 'aakash',
    password: 'aakash30',
    database: 'vanwalle',
});


var app = express();
app.set('view engine', 'ejs');


var fs=require("fs");
const bodyParser = require("body-parser");

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json());
app.post("/setStudent",function(req,res)
{
var student={
id:req.body.id,
count:req.body.c,
name:req.body.name,
class:req.body.class,
section:req.body.section,
age:req.body.age,
rollno:req.body.rollno,
schoolname:req.body.schoolname,
startdate:req.body.start_Date
}
connection.query('INSERT INTO tbl_register_student SET ?', student, function (err, resp) {
     if (err) throw err;
     // if there are no errors send an OK message.
     res.send('Save succesfull');
   });

});




app.post("/register",function(req,res)
{
console.log(req.body);

 var createUser = {
    name: req.body.name,
    email: req.body.email,
    password: req.body.password,
    phone: req.body.phone
   }
connection.query('SELECT * FROM tbl_user WHERE phone = ?',[createUser.phone], function (error, results, fields) {
  if (error) {
    // console.log("error ocurred",error);
    res.send({
      "code":400,
      "failed":"error ocurred"
    })
  }
else if(results.length>0)
{
console.log("user already exists");
}
else
{
bcrypt.hash(createUser.password, saltRounds, function(err, hash) {
  // Store hash in your password DB.
createUser.password=hash
connection.query('INSERT INTO tbl_user SET ?', createUser, function (err, resp) {
     if (err) throw err;
     // if there are no errors send an OK message.
     res.send('Save succesfull');
   });
});
   }
});
});

app.post("/check",function(req,res)
{
console.log(req.body);

connection.query('SELECT * FROM tbl_user WHERE phone = ?',[req.body.phone], function (error, results, fields) {
  if (error) {
    // console.log("error ocurred",error);
    res.send({
      "code":400,
      "failed":"error ocurred"
    })
  }
else if(results.length==0)
{
console.log("false");
res.send("false");
}
else if(results.length>0)
{

res.send("true");

}
});
});


app.post('/login', function(request, response) {
if(is_active==0)
{
	var email = request.body.email;
	var password = request.body.password;
console.log(is_active)
	if (email && password) {
		connection.query('SELECT * FROM tbl_user WHERE email = ?',[email], function(error, results, fields) {
if (results.length > 0)
{
console.log(results[0].password)

/*
bcrypt.hash(password, saltRounds, function(err, hash) {
console.log(hash)
if(results[0].password==hash)
{
  console.log("login user");
}
});
*/
bcrypt.compare(password, results[0].password, function(err, res) {
  if(res)
  {
console.log(id)
is_active=1
response.send("You Logged On"+results[0].username);
connection.query('UPDATE tbl_user SET is_active=? WHERE email = ? ', [is_active,email], function (err, resp) {
     if (err) throw err;
     // if there are no errors send an OK message.
     //res.send('Save succesfull');
   });
   //response.send("You Logged On"+results[0].username);
   response.end();
  }
  else
  {
   response.send("Incorrect Username and password");
   response.end();
  }
});
}
//	request.session.loggedin = true;
 //	request.session.username = username;
			//	response.redirect('/home');
			else {
				response.send('Incorrect Username and/or Password!');
			}
		});
	} else {
		response.send('Please enter Username and Password!');
		response.end();
	}
}
else
{
//response.redirect("/login/"+id);
console.log("user already login");
}
});



app.listen(3000,function(req,res)
{

console.log("Server started")
connection.connect();

});
app.use(express.static(__dirname + '/public'));