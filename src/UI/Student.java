package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.ConnectionManager;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

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
		editProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				profileEdit(frame);
			}
		});
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
		deleteInternshipsApplied = new JMenuItem("Delete internships applied");
		deleteInternshipsApplied.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsAppliedDelete(frame);
			}
		});
		
		internshipsApplied.add(viewInternshipsApplied);
		internshipsApplied.add(deleteInternshipsApplied);
		
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
		applyInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsApply(frame);
			}
		});
		internships.add(viewInternships);
		internships.add(applyInternships);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);	
		this.frame = frame;
	}
	
	public void profileView(JFrame frame) {
		JLabel unameF, unameV, fnameF, fnameV = null, lnameF, lnameV = null, dobF, dobV = null, collegeF, collegeV = null, skillsF, skillsV = null, phoneF, phoneV = null, gpaF, gpaV = null;
		unameF = new JLabel("Username:");
		unameV = new JLabel(student_id);
		fnameF = new JLabel("First name:");
		lnameF = new JLabel("Last name:");
		dobF = new JLabel("Date of Birth:");
		phoneF = new JLabel("Phone number:");
		collegeF = new JLabel("College name:");
		gpaF = new JLabel("CGPA:");
		skillsF = new JLabel("Skills:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from students where student_id = '" + student_id + "'");
			rs.next();
			fnameV = new JLabel(rs.getString(2));
			lnameV = new JLabel(rs.getString(3));
			rs = s.executeQuery("select * from studentprofile where student_id = '" + student_id + "'");
			if (rs.next()) {
				dobV = new JLabel((new SimpleDateFormat("dd-MMM-YYYY")).format(rs.getDate(2)));
				collegeV = new JLabel (rs.getString(3));
				skillsV = new JLabel(rs.getString(4));
				phoneV = new JLabel(rs.getString(5));
				gpaV = new JLabel(rs.getString(6));
			}
			else {
				dobV = new JLabel("");
				collegeV = new JLabel ("");
				skillsV = new JLabel("");
				phoneV = new JLabel("");
				gpaV = new JLabel("");
			}
			
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		unameF.setBounds(50, 30, 100, 30);
		unameF.setFont(new Font("", Font.PLAIN, 20));
		unameV.setBounds(250, 30, 100, 30);
		unameV.setFont(new Font("", Font.PLAIN, 20));
		
		fnameF.setBounds(50, 60, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 60, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		lnameF.setBounds(50, 90, 100, 30);
		lnameF.setFont(new Font("", Font.PLAIN, 20));
		lnameV.setBounds(250, 90, 100, 30);
		lnameV.setFont(new Font("", Font.PLAIN, 20));
		
		dobF.setBounds(50, 120, 130, 30);
		dobF.setFont(new Font("", Font.PLAIN, 20));
		dobV.setBounds(250, 120, 140, 30);
		dobV.setFont(new Font("", Font.PLAIN, 20));
		
		phoneF.setBounds(50, 150, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 150, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		collegeF.setBounds(50, 180, 150, 30);
		collegeF.setFont(new Font("", Font.PLAIN, 20));
		collegeV.setBounds(250, 180, 140, 30);
		collegeV.setFont(new Font("", Font.PLAIN, 20));
		
		gpaF.setBounds(50, 210, 150, 30);
		gpaF.setFont(new Font("", Font.PLAIN, 20));
		gpaV.setBounds(250, 210, 140, 30);
		gpaV.setFont(new Font("", Font.PLAIN, 20));
		
		skillsF.setBounds(50, 240, 150, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 240, 200, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		frame.add(unameF);
		frame.add(unameV);
		frame.add(lnameF);
		frame.add(lnameV);
		frame.add(fnameF);
		frame.add(fnameV);
		frame.add(dobF);
		frame.add(dobV);
		frame.add(phoneF);
		frame.add(phoneV);
		frame.add(collegeF);
		frame.add(collegeV);
		frame.add(gpaF);
		frame.add(gpaV);
		frame.add(skillsF);
		frame.add(skillsV);
		
	}
	
	public void profileEdit(JFrame frame) {		
		JButton update = new JButton("Update");
		update.setBounds(200, 320, 154, 23);
		update.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		update.setBackground(Color.WHITE);
		
		JLabel unameF, unameV, fnameF, lnameF, dobF, collegeF, skillsF, phoneF, gpaF;
		JTextField fnameV = new JTextField("");
		JTextField lnameV = new JTextField("");
		JTextField dobV = new JTextField(""); 
		JTextField collegeV = new JTextField("");
		JTextField phoneV = new JTextField("");
		JTextField gpaV = new JTextField(""); 
		JTextField skillsV = new JTextField("");
		
		unameF = new JLabel("Username:");
		unameV = new JLabel(student_id);
		fnameF = new JLabel("First name:");
		lnameF = new JLabel("Last name:");
		dobF = new JLabel("Date of Birth:");
		phoneF = new JLabel("Phone number:");
		collegeF = new JLabel("College name:");
		gpaF = new JLabel("CGPA:");
		skillsF = new JLabel("Skills:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from students where student_id = '" + student_id + "'");
			rs.next();
			fnameV.setText(rs.getString(2));
			lnameV.setText(rs.getString(3));
			rs = s.executeQuery("select * from studentprofile where student_id = '" + student_id + "'");
			if (rs.next()) {
				dobV.setText((new SimpleDateFormat("dd-MMM-YYYY")).format(rs.getDate(2)));
				collegeV.setText(rs.getString(3));
				skillsV.setText(rs.getString(4));
				phoneV.setText(rs.getString(5));
				gpaV.setText(rs.getString(6));
			}
			else {
				dobV.setText("dd-mmm-yyyy");
				collegeV.setText("");
				skillsV.setText("");
				phoneV.setText("");
				gpaV.setText("");
			}
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
		unameF.setBounds(50, 30, 100, 30);
		unameF.setFont(new Font("", Font.PLAIN, 20));
		unameV.setBounds(250, 30, 100, 30);
		unameV.setFont(new Font("", Font.PLAIN, 20));
		
		fnameF.setBounds(50, 60, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 60, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		lnameF.setBounds(50, 90, 100, 30);
		lnameF.setFont(new Font("", Font.PLAIN, 20));
		lnameV.setBounds(250, 90, 100, 30);
		lnameV.setFont(new Font("", Font.PLAIN, 20));
		
		dobF.setBounds(50, 120, 130, 30);
		dobF.setFont(new Font("", Font.PLAIN, 20));
		dobV.setBounds(250, 120, 140, 30);
		dobV.setFont(new Font("", Font.PLAIN, 20));
		
		phoneF.setBounds(50, 150, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 150, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		collegeF.setBounds(50, 180, 150, 30);
		collegeF.setFont(new Font("", Font.PLAIN, 20));
		collegeV.setBounds(250, 180, 140, 30);
		collegeV.setFont(new Font("", Font.PLAIN, 20));
		
		gpaF.setBounds(50, 210, 150, 30);
		gpaF.setFont(new Font("", Font.PLAIN, 20));
		gpaV.setBounds(250, 210, 140, 30);
		gpaV.setFont(new Font("", Font.PLAIN, 20));
		
		skillsF.setBounds(50, 240, 150, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 240, 200, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateProfile(fnameV.getText(), lnameV.getText(), dobV.getText(), phoneV.getText(), collegeV.getText(), gpaV.getText(), skillsV.getText());
				}
				catch (Exception e1 ) {
					e1.printStackTrace();
				}
			}
		});
		
		
		frame.add(unameF);
		frame.add(unameV);
		frame.add(lnameF);
		frame.add(lnameV);
		frame.add(fnameF);
		frame.add(fnameV);
		frame.add(dobF);
		frame.add(dobV);
		frame.add(phoneF);
		frame.add(phoneV);
		frame.add(collegeF);
		frame.add(collegeV);
		frame.add(gpaF);
		frame.add(gpaV);
		frame.add(skillsF);
		frame.add(skillsV);
		frame.add(update);		
		
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
			r1 = s1.executeQuery("select manager_id, internship_id, position, salary, location from internships where internship_id not in (select internship_id from internshipsapplied where student_id = '" + student_id + "')  order by internship_id");
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
	
	public void validateProfile(String fname, String lname, String dob, String phone, String college, String gpa, String skills) {
		String message = "";
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
		
		for(char c : lname.toCharArray()) {
			if (!Character.isAlphabetic(c)) {
				message = message + "Last name cannot have digits or special characters!\n";
				break;
			}
		}
		
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
			for(char c : phone.toCharArray()) {
				if (!Character.isDigit(c)) {
					message = message + "Phone number cannot have alphabets or special characters!\n";
					break;
				}
			}
			if (phone.length() != 10) {
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
			Connection con;
			Statement s;
			ResultSet r;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				s.executeQuery("update students set firstname = '" + fname + "', lastname = '" + lname + "' where student_id = '" + student_id + "'");
				s.executeQuery("commit");
				r = s.executeQuery("update studentprofile set dob = '" + dob + "', collegename = '" + college + "', skills = '" + skills + "', phonenumber = " + phone + ", cgpa = " + gpa + " where student_id = '" + student_id + "'");
				if ( ! r.next()) {
					s.executeQuery("insert into studentprofile values('" + student_id + "', '" + dob + "', '" + college + "', '" + skills + "', " + phone + ", " + gpa + ")");
				}
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(new JFrame(), "Updated successfully!");
		}
	}
	
	public void internshipsAppliedDelete(JFrame frame) {
		JTextField iidT = new JTextField();
		iidT.setBounds(150, 150, 150, 40);
		iidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id of the internship which you want to remove the application </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton delete = new JButton("Delete");
		delete.setFont(new Font("Delete", Font.PLAIN, 15));
		delete.setBounds(170, 220, 100, 30);
		delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delete.setBackground(Color.WHITE);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (iidT.getText().length() == 0) {
					message = message + "internship_id cannot be empty!\n";
				}
				else {
					try {
						int iid = Integer.parseInt(iidT.getText());
						Connection con;
						Statement s;
						ResultSet r1, r2;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r1 = s.executeQuery("select * from internships where internship_id = " + iid);
							if (! r1.next()) {
								message = message + "internship_id does not exist!\n";
							}
							else {
								r2 = s.executeQuery("select * from internshipsapplied where internship_id = " + iid + " and student_id = '" + student_id + "'");
								if (!r2.next() ) {
									message = message + "You have not applied to this internship yet!\n";
								}
							}
							s.close();
							con.close();
						} 
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch (Exception e1) {
						message = message + "Enter a valid internship_id!\n";
					}
				}
				
				if ( message.length() == 0) {
					int iid = Integer.parseInt(iidT.getText());
					Connection con;
					Statement s;
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						s.executeQuery("delete from internshipsapplied where internship_id = " + iid + " and student_id = '" + student_id + "' ");
						s.executeQuery("commit");
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
					iidT.setText("");
					JOptionPane.showMessageDialog(new JFrame(), "Deleted successfully!");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		iidT.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			       if(e.getKeyCode() == KeyEvent.VK_ENTER){
			          delete.doClick();
			       }
			    }
			    public void keyReleased(KeyEvent e) {}
			    public void keyTyped(KeyEvent e) {}
		});
		
		frame.add(iidT);
		frame.add(prompt);
		frame.add(delete);		
	}
	
	public void internshipsApply(JFrame frame) {
		JTextField iidT = new JTextField();
		iidT.setBounds(150, 150, 150, 40);
		iidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id of the internship which you want to apply for </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton apply = new JButton("Apply");
		apply.setFont(new Font("Apply", Font.PLAIN, 15));
		apply.setBounds(170, 220, 100, 30);
		apply.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		apply.setBackground(Color.WHITE);
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if (iidT.getText().length() == 0) {
					message = message + "internship_id cannot be empty!\n";
				}
				else {
					try {
						int iid = Integer.parseInt(iidT.getText());
						Connection con;
						Statement s;
						ResultSet r1, r2;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r1 = s.executeQuery("select * from internships where internship_id = " + iid);
							if (! r1.next()) {
								message = message + "internship_id does not exist!\n";
							}
							else {
								r2 = s.executeQuery("select * from internshipsapplied where internship_id = " + iid + " and student_id = '" + student_id + "'");
								if (r2.next() ) {
									message = message + "You have already applied to this internship!\n";
								}
							}
							s.close();
							con.close();
						} 
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					catch (Exception e1) {
						message = message + "Enter a valid internship_id!\n";
					}
				}
				
				if ( message.length() == 0) {
					int iid = Integer.parseInt(iidT.getText());
					Connection con;
					Statement s;
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						s.executeQuery("insert into internshipsapplied values ('" + student_id + "', " + iid + ", 'no')");
						s.executeQuery("commit");
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
					iidT.setText("");
					JOptionPane.showMessageDialog(new JFrame(), "Applied successfully!");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		iidT.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			       if(e.getKeyCode() == KeyEvent.VK_ENTER){
			          apply.doClick();
			       }
			    }
			    public void keyReleased(KeyEvent e) {}
			    public void keyTyped(KeyEvent e) {}
		});
		
		frame.add(iidT);
		frame.add(prompt);
		frame.add(apply);		
	}
}
