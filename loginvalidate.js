function validate()
{
	var user_name=document.form.user_name;
	var pwd=document.form.pwd;

	if(user_name.value.trim()=="")
	{
		alert("username should not be blank");
       user_name.style.border="red solid 3px";
       document.getElementById("invalid username").style.visibility="visible";
       	return false;
       }
       
       
       else if(pwd.value.trim()=="")
	{
		alert("password should not be balnk");
		pwd.style.border="red solid 3px";
       return false;
   }
      

       else if(pwd.value.trim().length<6)
	{
		alert("password should  be atleat 6 characters");
       pwd.style.border="red solid 3px";
       document.getElementById("passwordinvalid").style.visibility="visible";
       	return false;
       }
       else
       {
       	return true;
       }


}