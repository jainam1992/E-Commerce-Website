<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">  
<head>  

<meta charset="utf-8">  
<title>JavaScript form validation - checking non-empty</title>  
<link rel='stylesheet' href='form-style.css' type='text/css' />        
</head><br><body onload='document.form1.text1.focus()' background="css/images/mintyellow.jpg">
  

<div class="mail">
<form name="form1" action="confirmAndPlaceOrder">
<h1 align="center">Enter Card details</h1>  

<table>
<tr>
   <td height="12" align="right" valign="middle">First Name:</td>
    <td colspan="2" align="left"><input name="Firstname" type="text" size="20"></td>
</tr>
<tr> 
    <td>
    <td height="22" align="left" valign="middle">Expiry Date:</td>
    <td colspan="2" align="left">
      <SELECT NAME="CCExpiresMonth" >
        <OPTION VALUE="" SELECTED>--Month--
        <OPTION VALUE="01">January (01)
        <OPTION VALUE="02">February (02)
        <OPTION VALUE="03">March (03)
        <OPTION VALUE="04">April (04)
        <OPTION VALUE="05">May (05)
        <OPTION VALUE="06">June (06)
        <OPTION VALUE="07">July (07)
        <OPTION VALUE="08">August (08)
        <OPTION VALUE="09">September (09)
        <OPTION VALUE="10">October (10)
        <OPTION VALUE="11">November (11)
        <OPTION VALUE="12">December (12)
      </SELECT> /
      <SELECT NAME="CCExpiresYear">
        <OPTION VALUE="" SELECTED>--Year--
        <OPTION VALUE="04">2004
        <OPTION VALUE="05">2005
        <OPTION VALUE="06">2006
        <OPTION VALUE="07">2007
        <OPTION VALUE="08">2008
        <OPTION VALUE="09">2009
        <OPTION VALUE="10">2010
        <OPTION VALUE="11">2011
        <OPTION VALUE="12">2012
        <OPTION VALUE="13">2013
        <OPTION VALUE="14">2014
        <OPTION VALUE="15">2015
      </SELECT>
    </td>
  </tr>
  </table>
  
<ul>  
<li>card number<input type='text' name='text1'/></li>  
<li>&nbsp;</li>  
<li class="submit"><input type="submit" name="submit" value="Submit" onclick="return cardnumber(document.form1.text1)"/></li>  
<li>&nbsp;</li>  
</ul>  
</form>  
</div>  
<script src="credit-card-americal-express-validation.js"></script>  
</body>  
 

<style> 
li {list-style-type: none;  
font-size: 16pt;  
}  
.mail {  
margin: auto;  
padding-top: 10px;  
padding-bottom: 10px;  
width: 400px;  
background : #D8F1F8;  
border: 1px soild silver;  
}  
.mail h2 {  
margin-left: 38px;  
}  
input {  
font-size: 20pt;  
}  
input:focus, textarea:focus{  
background-color: lightyellow;  
}  
input submit {  
font-size: 12pt;  
}  
.rq {  
color: #FF0000;  
font-size: 10pt;  
}  

</style>

<script>
function cardnumber(inputtxt)  
{  
  var cardno = /^(?:3[47][0-9]{13})$/;  
  if(inputtxt.value.match(cardno))  
        {  
      return true;  
        }  
      else  
        {  
        alert("Not a valid Amercican Express credit card number!");  
        return false;  
        }  
}  

</script>
</html>