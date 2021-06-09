package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.ConnectionManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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
		
		String name = " name";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select firstname from projectmanagers where manager_id = '" + username + "'");
			rs.next();
			name = rs.getString(1);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		frame.setTitle("Project Manager: " + name);
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
		viewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				profileView(frame);
			}
		});
		
		editProfile = new JMenuItem("Edit profile");
		profileMenu.add(viewProfile);
		profileMenu.add(editProfile);
		
		viewInternships = new JMenuItem("View internships posted");
		viewInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsPostedView(frame);
			}
		});
		postInternships = new JMenuItem("Post new internships");
		editInternships = new JMenuItem("Edit internships posted");
		internshipsMenu.add(viewInternships);
		internshipsMenu.add(editInternships);
		internshipsMenu.add(postInternships);
		
		viewInternshipRequests = new JMenuItem("View internships requests");
		viewInternshipRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipRequestsView(frame);
			}
		});
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
				frame.setJMenuBar(null);
				new HomePage(frame);
			}
		});
		
		welcome = new JLabel("Welcome " +name + "!");
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
	
	public void profileView(JFrame frame) {
		String fname = "", lname = "", company = "", designation = "", phone = "";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from projectmanagers where manager_id = '" + manager_id + "'");
			rs.next();
			fname = rs.getString(2);
			lname = rs.getString(3);
			rs = s.executeQuery("select * from managerprofile where manager_id = '" + manager_id + "'");
			rs.next();
			company = rs.getString(2);
			designation = rs.getString(3);
			phone = rs.getString(4);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		String data[][] = {{"Username:", manager_id}, 
				{"First name:", fname}, 
				{"Last name:", lname}, 
				{"Company name", company}, 
				{"Designation:", designation},
				{"Phone number:", phone}};
		String col[] = {"FIELD", "VALUE"};
		JTable details = new JTable(data, col);
		details.setBounds(100, 70, 200, 140);
		details.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		details.setFont(new Font("", Font.PLAIN, 15));
		details.getColumnModel().getColumn(0).setPreferredWidth(150);
		details.getColumnModel().getColumn(1).setPreferredWidth(150);
		JScrollPane scroll = new JScrollPane(details);
		scroll.setBounds(90, 70, 280, 134);
		frame.add(scroll);
		
	}
	
	public void internshipsPostedView(JFrame frame) {
		String iid, position, salary, location, skills;		
		String header[] = new String[] { "ID", "Role", "Salary", "Location", "Skills Required"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		Connection con;
		Statement s;
		ResultSet r;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			r = s.executeQuery("select * from internships where manager_id = '" + manager_id + "'");
			while (r.next()) {
				iid = r.getString(2);
				position = r.getString(3);
				salary = r.getString(4);
				location = r.getString(5);
				skills = r.getString(6);
				dtm.addRow(new Object[] {iid, position, salary, location, skills});
			}
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.setFont(new Font("", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
	}
	
	public void internshipRequestsView(JFrame frame) {
		String iid, position, student_id, skills, status;		
		String header[] = new String[] { "ID", "Role", "studentid", "Skills", "Selected(yes/no)"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		Connection con;
		Statement s1, s2, s3;
		ResultSet r1, r2, r3;
		try {
			con = ConnectionManager.getConnection();
			s1 = con.createStatement();
			s2 = con.createStatement();
			s3 = con.createStatement();
			r1 = s1.executeQuery("select * from internshipsapplied where internship_id in (select internship_id from internships where manager_id = '" + manager_id + "') and selectedyesno = 'no'");
			while (r1.next()) {
				student_id = r1.getString(1);
				iid = r1.getString(2);
				status = r1.getString(3);
				r2 = s2.executeQuery("select position from internships where internship_id = " + iid);
				r2.next();
				position = r2.getString(1);
				r3 = s3.executeQuery("select skills from studentprofile where student_id = '" + student_id + "'");
				r3.next();
				skills = r3.getString(1);
				dtm.addRow(new Object[] {iid, position, student_id, skills, status});
			}
			s1.close();
			s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
}
