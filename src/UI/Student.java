package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				profileView(frame);
			}
		});
		
		editProfile = new JMenuItem("Edit profile");		
		profileMenu.add(viewProfile);
		profileMenu.add(editProfile);
		
		viewInternshipsApplied = new JMenuItem("View internships applied");
		viewInternshipsApplied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsAppliedView(frame);
			}
		});
		
		internshipsApplied.add(viewInternshipsApplied);
		
		viewInternships = new JMenuItem("View available internships");
		viewInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsView(frame);
			}
		});
		applyInternships = new JMenuItem("Apply for new internships");
		internships.add(viewInternships);
		internships.add(applyInternships);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);	
		this.frame = frame;
	}
	
	public void profileView(JFrame frame) {
		String fname = "", lname = "", dob = "", college = "", skills = "", phone = "", gpa = "";
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from students where student_id = '" + student_id + "'");
			rs.next();
			fname = rs.getString(2);
			lname = rs.getString(3);
			rs = s.executeQuery("select * from studentprofile where student_id = '" + student_id + "'");
			rs.next();
			dob = rs.getString(2);
			college = rs.getString(3);
			skills = rs.getString(4);
			phone = rs.getString(5);
			gpa = rs.getString(6);
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		String data[][] = {{"Username:", student_id}, 
				{"First name:", fname}, 
				{"Last name:", lname}, 
				{"Date of Birth:", dob.substring(0, 11)}, 
				{"Phone number:", phone}, 
				{"College name:", college}, 
				{"CGPA:", gpa}, {"Skills:", skills}};
		String col[] = {"FIELD", "VALUE"};
		JTable details = new JTable(data, col);
		details.setBounds(100, 70, 200, 100);
		details.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(details);
		scroll.setBounds(90, 70, 300, 150);
		frame.add(scroll);
		
	}
	
	public void internshipsAppliedView(JFrame frame) {
		String iid, position, salary, status, location;		
		String header[] = new String[] { "ID", "Role", "Salary", "Location", "Selected (yes/no)"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		Connection con;
		Statement s1, s2;
		ResultSet r1, r2;
		try {
			con = ConnectionManager.getConnection();
			s1 = con.createStatement();
			s2 = con.createStatement();
			r1 = s1.executeQuery("select * from internshipsapplied where student_id = '" + student_id + "'");
			while (r1.next()) {
				iid = r1.getString(2);
				status = r1.getString(3);
				r2 = s2.executeQuery("select salary, location, position from internships where internship_id = " + iid + "" );
				r2.next();
				salary = r2.getString(1);
				location = r2.getString(2);
				position = r2.getString(3);
				dtm.addRow(new Object[] {iid, position, salary, location, status});
			}
			s1.close();
			s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
	}
	
	public void internshipsView(JFrame frame) {
		String iid, position, salary, location, company, mid;
		String header[] = new String[] { "ID", "Role", "Salary", "Company", "Location"};
		
		JTable table = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		Connection con;
		Statement s1, s2;
		ResultSet r1, r2;
		try {
			con = ConnectionManager.getConnection();
			s1 = con.createStatement();
			s2 = con.createStatement();
			r1 = s1.executeQuery("select manager_id, internship_id, position, salary, location from internships where internship_id not in (select internship_id from internshipsapplied where student_id = '" + student_id + "')");
			while (r1.next()) {
				mid = r1.getString(1);
				iid = r1.getString(2);
				position = r1.getString(3);
				salary = r1.getString(4);
				location = r1.getString(5);
				r2 = s2.executeQuery("select companyname from managerprofile where manager_id = '" + mid + "'");
				r2.next();
				company = r2.getString(1);
				dtm.addRow(new Object[] {iid, position, salary, company, location});
			}
			s1.close();
			s2.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		table.setBounds(100, 70, 250, 100);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
	
}
