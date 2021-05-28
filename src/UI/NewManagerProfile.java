package UI;

import javax.swing.*;

import main.ConnectionManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NewManagerProfile {
	public JFrame frame;
	public JButton submit, back;
	public JLabel companyLabel, designationLabel, phoneLabel, prompt;
	public JTextField company, designation, phone;
	public String mid;
	
	public NewManagerProfile(JFrame frame, String mid) {
		this.frame = frame;
		frame.setTitle("New Project Manager Profile");
		this.mid = mid;
		
		frame.getContentPane().setBackground(new Color(174, 230, 101));

		companyLabel = new JLabel("Company Name *");
		companyLabel.setBounds(50, 127, 200, 30);
		companyLabel.setFont(new Font("", Font.PLAIN, 20));
		frame.add(companyLabel);

		company = new JTextField();
		company.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		company.setBounds(250, 127, 150, 28);
		frame.add(company);

		phoneLabel = new JLabel("Phone Number *");
		phoneLabel.setBounds(50, 167, 150, 29);
		phoneLabel.setFont(new Font("", Font.PLAIN, 20));
		frame.add(phoneLabel);

		phone = new JTextField();
		phone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		phone.setBounds(250, 167, 150, 28);
		frame.add(phone);

		designationLabel = new JLabel("Designation *");
		designationLabel.setBounds(50, 207, 150, 29);
		designationLabel.setFont(new Font("", Font.PLAIN, 20));
		frame.add(designationLabel);

		designation = new JTextField();
		designation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		designation.setBounds(250, 207, 150, 28);
		frame.add(designation);
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select firstname from projectmanagers where manager_id = '" + mid + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel prompt = new JLabel("Welcome " +  name + ", Please Enter Your Profile Details");
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
		submit.setBounds(190, 280, 89, 23);
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

		frame.setSize(500, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void validate() {
		String message = "";
		String company = this.company.getText(), phone = this.phone.getText(), designation = this.designation.getText();
		if (company.length() == 0) {
			message = message + "Company name cannot be empty!\n";
		}
		else {
			for(char c : company.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Company name cannot have digits or special characters!\n";
					break;
				}
			}
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
		
		if (designation.length() == 0) {
			message = message + "Company name cannot be empty!\n";
		}
		else {
			for(char c : company.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Company name cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (message.length() != 0) {
			JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			frame.getContentPane().removeAll();
			new Manager(frame, mid);
		}
	}
}
