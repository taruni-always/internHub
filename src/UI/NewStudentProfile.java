package UI;

import javax.swing.*;

import main.ConnectionManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewStudentProfile {
	public JFrame frame;
	public JButton submit, back;
	public JLabel dobLabel, collegeLabel, skillsLabel, phoneLabel, gpaLabel, prompt;
	public JTextField dob, college, phone, cgpa;
	public JTextArea skills;
	public String sid;

	public NewStudentProfile(JFrame frame, String sid) {
		this.frame = frame;
		frame.setTitle("New Student Profile");
		this.sid = sid;
		frame.getContentPane().setBackground(new Color(174, 230, 101));

		dobLabel = new JLabel("Date of Birth (Ex:05-dec-2001)*");
		dobLabel.setBounds(50, 127, 200, 29);
		frame.add(dobLabel);

		dob = new JTextField("dd-mmm-yyyy");
		dob.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dob.setBounds(250, 127, 150, 28);
		frame.add(dob);

		phoneLabel = new JLabel("Phone Number *");
		phoneLabel.setBounds(50, 167, 114, 29);
		frame.add(phoneLabel);

		phone = new JTextField();
		phone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		phone.setBounds(250, 167, 150, 28);
		frame.add(phone);

		collegeLabel = new JLabel("College Name *");
		collegeLabel.setBounds(50, 207, 114, 29);
		frame.add(collegeLabel);

		college = new JTextField();
		college.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		college.setBounds(250, 207, 150, 28);
		frame.add(college);

		gpaLabel = new JLabel("CGPA *");
		gpaLabel.setBounds(50, 247, 114, 29);
		frame.add(gpaLabel);

		cgpa = new JTextField();
		cgpa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cgpa.setBounds(250, 247, 150, 28);
		frame.add(cgpa);

		skillsLabel = new JLabel("Skills *");
		skillsLabel.setBounds(50, 287, 114, 29);
		frame.add(skillsLabel);

		skills = new JTextArea();
		skills.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		skills.setBounds(250, 287, 150, 80);
		frame.add(skills);
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select firstname from students where student_id = '" + sid + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel prompt = new JLabel("Welcome " + name + ", Please Enter Your Profile Details");
		prompt.setBounds(50, 50, 400, 30);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont)
				.stringWidth("Welcome ____ Please Enter Your Profile Details");
		double widthRatio = (double) 400 / (double) stringWidth;
		int newFontSize = (int) (promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Please Enter Your Profile Details", Font.ITALIC, Math.min(newFontSize, 30)));
		frame.add(prompt);

		submit = new JButton("Submit");
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		submit.setBackground(Color.WHITE);
		submit.setFont(new Font("Submit", Font.ITALIC, 18));
		submit.setBounds(190, 400, 89, 23);
		frame.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate();
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
				new HomePage(frame);
			}
		});

		frame.setSize(500, 500);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void validate() {
		String message = "";
		String dob = this.dob.getText(), college = this.college.getText(), phone = this.phone.getText(), gpa = this.cgpa.getText(), skills = this.skills.getText();
		if (dob.length() == 0 || dob.equals("dd-mmm-yyyy")) {
			message = message + "Date of Birth cannot be empty!\n";
		}
		else if (dob.length() !=  11 ) {
			message += "Enter a valid Date of Birth!\n";
		}

		if (phone.length() == 0) {
			message = message + "Phone number cannot be empty!\n";
		}
		else {
			try {
				int num = Integer.parseInt(phone);
				if (phone.length() != 10) {
					message += "Phone number must have exactly 10 digits!\n";
				}
			}
			catch (NumberFormatException e) {
				message += "Phone number cannot have alphabets or special characters!\n";
			}
			catch (Exception e) {
				message += "Phone number must have exactly 10 digits!\n";
			}
		}
		
		if (college.length() == 0) {
			message = message + "College name cannot be empty!\n";
		}
		else {
			for(char c : college.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)  ) {
					message = message + "College name cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (gpa.length() == 0) {
			message = message + "CGPA cannot be empty!\n";
		}
		else {
			try {
				float f = Float.parseFloat(gpa);
				if (f > 10 || f < 0) {
					message += "CGPA must lie between 0 to 10!\n";
				}
			}
			catch (Exception e) {
				message += "CGPA must be a valid Number!\n";
			}
		}
		
		if (skills.length() == 0) {
			message = message + "Skills cannot be empty!\n";
		}
		
		if (message.length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			frame.getContentPane().removeAll();
			new Student(frame, sid);
		}
	}
}
