package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewManager {
	public JFrame frame;
	public JLabel prompt, userNameLabel, firstNameLabel, secondNameLabel;
	public JTextField userName, firstName, secondName;
	public JButton back, submit;
	
	public NewManager(JFrame frame) {
		this.frame = frame;
		frame.setTitle("New Project Manager User");
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
		
		firstNameLabel = new JLabel("First name *");
		firstNameLabel.setFont(new Font("First name", Font.PLAIN, 20));
		firstNameLabel.setBounds(50, 197, 114, 29);
		frame.add(firstNameLabel);
		
		secondNameLabel = new JLabel("Second name ");
		secondNameLabel.setFont(new Font("Second name", Font.PLAIN, 20));
		secondNameLabel.setBounds(50, 268, 139, 29);
		frame.add(secondNameLabel);
		
		userName = new JTextField();
		userName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userName.setBounds(240, 127, 173, 28);
		frame.add(userName);
		
		firstName = new JTextField("");
		firstName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		firstName.setBounds(240, 197, 173, 28);
		frame.add(firstName);
		
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
			//frame.getContentPane().removeAll();
			//new newStudentProfile(frame);
		}
	}
}
