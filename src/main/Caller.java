package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import UI.*;

public class Caller {
	public static void main(String[] args) throws SQLException {
		
		JFrame frame = new JFrame();
		Connection  con;
		////new NewStudent(frame);
		//new HomePage(frame);
		//new TypeOfUser(frame);
		//new NewStudentProfile(frame, "abc");
		new HomePage(frame);
		//new LoginWithUsername(frame);
		//new UserLogin(frame);
		//new Student(frame, "joe123");
		//new Manager(frame, "da_vid");
		/*
		 * try {
		 *
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//registering driver
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it19737120", "123");
			//establishing the connection
			
			Statement s = con.createStatement();
			//getting statement object
					
			new HomePage(frame, s);
			con.close();
			//closing the connection again
		}
		catch (Exception e) {
			System.out.println(e);
		}
		*/
	}

}
