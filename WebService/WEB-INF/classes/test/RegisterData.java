package test;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;


@Path("/Register")
public class RegisterData {

	String s="",res="";
	@POST
	@Consumes()
	public String regist(@FormParam("fname") String fname,@FormParam("lname") String lname,@FormParam("email") String email,@FormParam("gender") String gender,@FormParam("dob") String dob,@FormParam("uname") String uname,@FormParam("pass") String pass)
	{
	try{ 
	    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();  
	    Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/RegisterDetails");   
	    Statement stmt=con.createStatement();

		s="insert into data values('"+uname+"','"+pass+"','"+fname+"','"+lname+"','"+gender+"','"+email+"','"+dob+"')";
		stmt.executeUpdate(s);

	  	res="<HTML>"
	  	+"<HEAD>"
	 	 +"<TITLE>Registered succesfully</TITLE>"
	 	 +"</HEAD>"
	 	 +"<BODY>"
		+"Succesfully Registered"
		+"<br><a href='/WebService/login.html'>login</a>"
	 	 +"</BODY>"
	 	 +"</HTML>";

		stmt.close();
		con.close();
		   }
		   catch(SQLSyntaxErrorException e)
		   {
			res=res+"Enter values without blank "
			+"<a href='/WebService/register.html'>register</a>";
			return res;
		   }
		   catch(NullPointerException e)
			{
				res="Enter values without blank "
			+"<a href='/WebService/register.html'>register</a>";
			return res;
			}
		catch(SQLDataException e)
			{
				res="Enter values without blank "
			+"<a href='/WebService/register.html'>register</a>";
			return res;
			}
			catch(SQLNonTransientConnectionException e)
			{
				res="Connection lost..";
				return res;
			}
		   catch(Exception e)
		   {
			System.out.println(e);
			 res="Username is already exist :"
			+"<a href='/WebService/register.html'>register</a>";
			return res;
		   }
		   return res;
	}

}
