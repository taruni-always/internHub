package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage {
	public JFrame frame;
	public JLabel title, prompt, marketting;
	public JButton login, signup;
	
	public HomePage(JFrame frame) {
		this.frame = frame;
		frame.setTitle("InternHub - Home Page");
		frame.getContentPane().setBackground(new Color(125, 193, 232));
		
		title = new JLabel("Welcome to InternHub!");
		title.setBounds(150, 50, 350, 60);
		Font titleFont = title.getFont();
		int stringWidth = title.getFontMetrics(titleFont).stringWidth("Welcome to InternHub!");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(titleFont.getSize() * widthRatio);
		title.setFont(new Font("Welcome to InternHub!", Font.ITALIC, Math.min(newFontSize, 60)));
		frame.add(title);
		
		prompt = new JLabel("(Log-in for existing users | Sign-up for new users)");
		prompt.setBounds(170, 100, 350, 40);
		frame.add(prompt);
		
		login = new JButton("Log-in");
		login.setBounds(150,200,150, 40);
		login.setFont(new Font("Log-in", Font.PLAIN, 20));
		login.setBackground(Color.white);
		login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(login);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new UserLoginSignup(frame, "login");
			}
		});
		
		signup = new JButton("Sign-up");
		signup.setBounds(320,200,150, 40);
		signup.setFont(new Font("Sign-up", Font.PLAIN, 20));
		signup.setBackground(Color.white);
		signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(signup);
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new UserLoginSignup(frame, "signup" );
			}
		});
		
		marketting = new JLabel( "<html><span bgcolor=\"yellow\">Looking for an internship? We got you! Providing internship opportunities for students across the globe!</span></html>" );
		marketting.setBounds(18, 380, 600, 20);
		frame.add(marketting);
		
		frame.setSize(650, 450);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}