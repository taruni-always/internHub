package UI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Manager {
	public JFrame frame;
	public String manager_id;
	public JMenuBar menuBar;
	public JMenu profileMenu, internshipsMenu, internshipRequestsMenu;
	public JMenuItem viewProfile, editProfile, viewInternships, postInternships, editInternships, viewInternshipRequests, approveInternshipRequests;
	public JButton back;
	public JLabel welcome;
	
	public Manager(JFrame frame, String username) {
		this.frame = frame;
		this.manager_id = username;
		frame.setTitle("Project Manager: " + username);
		frame.getContentPane().setBackground(new Color(174, 230, 101));
		
		menuBar = new JMenuBar();
		frame.add(menuBar);
		
		profileMenu = new JMenu("Profile");
		profileMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(profileMenu);
		
		internshipsMenu = new JMenu("Internships Posted by me");
		internshipsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(internshipsMenu);
		
		internshipRequestsMenu = new JMenu("Internship requests available");
		internshipRequestsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menuBar.add(internshipRequestsMenu);
		
		viewProfile = new JMenuItem("View profile");
		editProfile = new JMenuItem("Edit profile");
		profileMenu.add(viewProfile);
		profileMenu.add(editProfile);
		
		viewInternships = new JMenuItem("View internships posted");
		postInternships = new JMenuItem("Post new internships");
		editInternships = new JMenuItem("Edit internships posted");
		internshipsMenu.add(viewInternships);
		internshipsMenu.add(editInternships);
		internshipsMenu.add(postInternships);
		
		viewInternshipRequests = new JMenuItem("View internships requests");
		approveInternshipRequests = new JMenuItem("Approve internship requests");
		internshipRequestsMenu.add(viewInternshipRequests);
		internshipRequestsMenu.add(approveInternshipRequests);
		
		back = new JButton("Go back to Home Page");
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Go back to Home Page", Font.ITALIC, 11));
		back.setBounds(20, 320, 154, 23);
		frame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new HomePage(frame);
			}
		});
		
		welcome = new JLabel("Welcome <name>!");
		welcome.setBounds(60, 120, 400, 40);
		Font promptFont = welcome.getFont();
		int stringWidth = welcome.getFontMetrics(promptFont).stringWidth("Welcome <name>!");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		welcome.setFont(new Font("Welcome", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(welcome);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);	
	}
}
