package test;

//import java.io.*;
import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.FormParam;

@Path("/Validation")
public class Hello {
    String s="";
    boolean flag=false;
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String validate(@FormParam("username") String uname,@FormParam("password") String pass)
	{
		String res="Hello "+uname+" "+pass;
		System.out.println(res);
		try{
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();  
		    Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/RegisterDetails");   
		    Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from data");
			while(rs.next())
			{
			if(uname.equals(rs.getString(1)))
			{
			if(pass.equals(rs.getString(2)))
			{
		  	s="<HTML>"
		  	+"<HEAD>"
		 	+"<TITLE>Login</TITLE>"
		 	+"</HEAD>"
		 	+"<BODY>"
			+"Succesfully loged in"
		 	+"<a href='http://localhost:8080/WebService/rest/Display'>Display</a>"
			+"</BODY>"
		 	+"</HTML>";
			flag=true;
			}
			}
			/*else
			{
			s="<HTML>"
		  	+"<HEAD>"
		 	+"<TITLE>Login</TITLE>"
		 	+"</HEAD>"
		 	+"<BODY>"
			+"Invalid password"
			+"<br><a href='http://localhost:8080/WebService/'>return to home</a>"
			+"</BODY>"
		 	+"</HTML>";
			flag=true;
			break;
			}
			}
			else
			{
			s="<HTML>"
		  	+"<HEAD>"
		 	+"<TITLE>Login</TITLE>"
		 	+"</HEAD>"
		 	+"<BODY>"
			+"Invalid password"
			+"<br><a href='http://localhost:8080/WebService/'>return to home</a>"
			+"</BODY>"
		 	+"</HTML>";
			flag=true;
			break;
			}*/
			}
			if(!flag)
			{
				stmt.close();
			    con.close();
				s="<HTML>"
		  	+"<HEAD>"
		 	+"<TITLE>Login</TITLE>"
		 	+"</HEAD>"
		 	+"<BODY>"
			+"Invalid username/password"
			+"<br><a href='/rest/register.html'>register</a>"
			+"</BODY>"
		 	+"</HTML>";
			}
			stmt.close();
			con.close();
			   }
			   catch(SQLSyntaxErrorException e)
			   {
				s="Invalid username/password";
				return s;
			   }
			   catch(SQLNonTransientConnectionException e)
			{
				s="Connection lost..";
				return s;
			}
			   catch(Exception e)
			   {
				   return e.toString();
			   }
			   return s;
	}
	
}
