package UI;

import javax.swing.*;
import main.ConnectionManager;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Student {
	public JFrame frame;
	public String student_id;
	public JMenuBar menuBar;
	public JMenu profileMenu, internshipsApplied, internships;
	public JMenuItem viewProfile, editProfile, viewInternshipsApplied, deleteInternshipsApplied, viewInternships, applyInternships;
	public JLabel welcome;
	public JButton back;
	
	public Student(JFrame frame, String username) {
		
		this.student_id = username;
		this.frame = frame;
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select firstname from students where student_id = '" + username + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		frame.setTitle("Student: " + name);
		frame.getContentPane().setBackground(new Color(174, 230, 101));
		
		back = new JButton("Go back to Home Page");
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Go back to Home Page", Font.ITALIC, 11));
		back.setBounds(20, 320, 154, 23);
		frame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.setJMenuBar(null);
				new HomePage(frame);
			}
		});
		
		welcome = new JLabel("Welcome " + name + "!");
		welcome.setBounds(60, 120, 400, 40);
		Font promptFont = welcome.getFont();
		int stringWidth = welcome.getFontMetrics(promptFont).stringWidth("Welcome namelong!");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		welcome.setFont(new Font("Welcome", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(welcome);
		
		menuBar = new JMenuBar();
		frame.add(menuBar);
		
		profileMenu = new JMenu("Profile");
		profileMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(profileMenu);
		
		internshipsApplied = new JMenu("Internships applied by me");
		internshipsApplied.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(internshipsApplied);
		
		internships = new JMenu("Internships available");
		internships.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(internships);
		
		viewProfile = new JMenuItem("View profile");
		viewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
			}
		});
		
		editProfile = new JMenuItem("Edit profile");
		profileMenu.add(viewProfile);
		profileMenu.add(editProfile);
		
		viewInternshipsApplied = new JMenuItem("View internships applied");
		internshipsApplied.add(viewInternshipsApplied);
		
		viewInternships = new JMenuItem("View available internships");
		applyInternships = new JMenuItem("Apply for new internships");
		internships.add(viewInternships);
		internships.add(applyInternships);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);		
	}
	
}
