package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import main.*;

public class NewUserSignup {
	public JFrame frame;
	public JLabel prompt, userNameLabel, firstNameLabel, secondNameLabel;
	public JTextField userName, firstName, secondName;
	public JButton back, submit;
	public String userType;
	
	public NewUserSignup(JFrame frame, String userType) {
		this.frame = frame;
		this.userType = userType;
		if (userType.equals("student")) {
			frame.setTitle("New Student User");
		}
		else {
			frame.setTitle("New Project Manager User");
		}
		frame.getContentPane().setBackground(new Color(125, 193, 232));
		
		JLabel prompt = new JLabel("Please Enter Your Details");
		prompt.setBounds(50, 56, 400, 40);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Please Enter Your Details");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Please Enter Your Details", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(prompt);
		
		userNameLabel = new JLabel("User name *");
		userNameLabel.setFont(new Font("User name", Font.PLAIN, 20));
		userNameLabel.setBounds(50, 127, 114, 29);
		frame.add(userNameLabel);
		
		userName = new JTextField();
		userName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userName.setBounds(240, 127, 173, 28);
		frame.add(userName);
		
		firstNameLabel = new JLabel("First name *");
		firstNameLabel.setFont(new Font("First name", Font.PLAIN, 20));
		firstNameLabel.setBounds(50, 197, 114, 29);
		frame.add(firstNameLabel);

		firstName = new JTextField("");
		firstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		firstName.setBounds(240, 197, 173, 28);
		frame.add(firstName);
		
		secondNameLabel = new JLabel("Second name ");
		secondNameLabel.setFont(new Font("Second name", Font.PLAIN, 20));
		secondNameLabel.setBounds(50, 268, 139, 29);
		frame.add(secondNameLabel);
		
		secondName = new JTextField();
		secondName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		secondName.setBounds(240, 268, 173, 28);
		frame.add(secondName);
		
		submit = new JButton("Submit");
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		submit.setBackground(Color.WHITE);
		submit.setFont(new Font("Submit", Font.ITALIC, 15));
		submit.setBounds(190, 340, 89, 23);
		frame.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate(userName.getText(), firstName.getText(), secondName.getText());
			}
		});
		
		back = new JButton("Go back to Home Page");
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Go back to Home Page", Font.ITALIC, 11));
		back.setBounds(10, 11, 154, 23);
		frame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new HomePage(frame);
			}
		});
		
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void validate(String uname, String fname, String lname ) {
		String message = "";
		if (uname.length() == 0) {
			message = "Username cannot be empty!\n";
		}
		else {
			Connection con;
			Statement s;
			ResultSet rs;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				if (userType.equals("student")) {
					rs = s.executeQuery("select student_id from students");
				}
				else {
					rs = s.executeQuery("select manager_id from projectmanagers");
				}
				while (rs.next()) {
					if (rs.getString(1).equals(uname)) {
						message += "The username already exists. Please choose a different username!\n";
						break;
					}
				}
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		if (fname.length() == 0) {
			message = message + "First name cannot be empty!\n";
		}
		else {
			for(char c : fname.toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "First name cannot have digits or special characters!\n";
					break;
				}
			}
		}
		if (message.length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			Connection con;
			Statement s;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				frame.getContentPane().removeAll();
				if (userType.equals("student")){
					s.executeQuery("Insert into students values('" + uname + "', '" + fname + "', '" +lname + "')" );
					new NewStudentProfile(frame, userName.getText());
				}
				else {
					s.executeQuery("Insert into projectmanagers values('" + uname + "', '" + fname + "', '" +lname + "')" );
					new NewManagerProfile(frame, userName.getText());
				}
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
