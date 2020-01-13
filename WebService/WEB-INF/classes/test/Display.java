package test;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.FormParam;


@Path("/Display")
public class Display {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String display()
	{
		String res="";
		try{
		    Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();  
		    Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/RegisterDetails");   
		    Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from data");
		  	res="<HTML>"
		     +"<HEAD>"
		 	 +"<TITLE>Display database</TITLE>"
		 	 +"</HEAD>"
		 	 +"<BODY>"
			 +"<style>table, th, td { border: 1px solid black;}</style>"
			 +"<table style='width:100%;border-color:black'><tr><th>Username</th><th>Password</th><th>Firstname</th><th>Lastname</th><th>Gender</th><th>Email Id</th><th>DOB</th></tr>";
			while(rs.next())
			{
		 	 res=res+"<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>";
			}
		 	res=res+"</table><br><a href='http://localhost:8080/WebService/'>return to home</a></BODY>"
		 	 +"</HTML>";
		 	
			stmt.close();
			con.close();
			   }
			   catch(SQLSyntaxErrorException e)
			   {
				System.out.println(e);
			   }
			catch(SQLNonTransientConnectionException e)
			{
				res="Connection lost..";
				return res;
			}
			   catch(Exception e)
			   {
				   System.out.println(e);
			   }
		return res;
	}
}
