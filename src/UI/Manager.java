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
	public JMenuItem viewProfile, editProfile, viewInternships, postInternships, editInternships, deleteInternships, viewInternshipRequests, approveInternshipRequests;
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
		postInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsPostNew(frame);
			}
		});
		editInternships = new JMenuItem("Edit internships posted");
		editInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsPostedEdit(frame);
			}
		});
		deleteInternships = new JMenuItem("Delete internships posted");
		deleteInternships.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsPostedDelete(frame);
			}
		});
		internshipsMenu.add(viewInternships);
		internshipsMenu.add(editInternships);
		internshipsMenu.add(postInternships);
		internshipsMenu.add(deleteInternships);
		
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
		approveInternshipRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				welcome.setText("");
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipRequestsApprove(frame);
			}
		});
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
		
		welcome = new JLabel("Welcome " + name + "!");
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
		JLabel unameF, unameV, lnameF, lnameV = null, fnameF, fnameV = null, companyF, companyV = null, designationF, designationV = null, phoneF, phoneV = null;
		unameF = new JLabel("Username:");
		unameV = new JLabel(manager_id);
		fnameF = new JLabel("First name:");
		lnameF = new JLabel("Last name:");
		companyF = new JLabel("Company name");
		designationF = new JLabel("Designation:");
		phoneF = new JLabel("Phone number:");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from projectmanagers where manager_id = '" + manager_id + "'");
			rs.next();
			fnameV = new JLabel(rs.getString(2));
			lnameV = new JLabel(rs.getString(3));
			rs = s.executeQuery("select * from managerprofile where manager_id = '" + manager_id + "'");
			if (rs.next()) {
				companyV = new JLabel(rs.getString(2));
				designationV = new JLabel(rs.getString(3));
				phoneV = new JLabel(rs.getString(4));
			}
			else {
				companyV = new JLabel("");
				designationV = new JLabel("");
				phoneV = new JLabel("");
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
		
		fnameF.setBounds(50, 70, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 70, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		lnameF.setBounds(50, 110, 100, 30);
		lnameF.setFont(new Font("", Font.PLAIN, 20));
		lnameV.setBounds(250, 110, 100, 30);
		lnameV.setFont(new Font("", Font.PLAIN, 20));
		
		companyF.setBounds(50, 150, 150, 30);
		companyF.setFont(new Font("", Font.PLAIN, 20));
		companyV.setBounds(250, 150, 140, 30);
		companyV.setFont(new Font("", Font.PLAIN, 20));
		
		phoneF.setBounds(50, 190, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 190, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		designationF.setBounds(50, 230, 150, 30);
		designationF.setFont(new Font("", Font.PLAIN, 20));
		designationV.setBounds(250, 230, 180, 30);
		designationV.setFont(new Font("", Font.PLAIN, 20));
		
		frame.add(unameF);
		frame.add(unameV);
		frame.add(lnameF);
		frame.add(lnameV);
		frame.add(fnameF);
		frame.add(fnameV);
		frame.add(companyF);
		frame.add(companyV);
		frame.add(phoneF);
		frame.add(phoneV);
		frame.add(designationF);
		frame.add(designationV);
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
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(15, 70, 450, 150);
		frame.add(scroll);
		
	}
	
	public void internshipRequestsApprove(JFrame frame) {
		JTextField iidT = new JTextField("internship_id");
		iidT.setBounds(80, 150, 150, 40);
		iidT.setFont(new Font("student_id", Font.PLAIN, 16));
		
		JTextField sidT = new JTextField("student_id");
		sidT.setBounds(260, 150, 150, 40);
		sidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id and student_id which you want to approve </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton approve = new JButton("Approve");
		approve.setBounds(160, 250, 154, 23);
		approve.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		approve.setBackground(Color.WHITE);
		approve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean iidexists = false, sidexists = false, isValid = true;
				Connection con;
				Statement s;
				ResultSet rs;
				String message = "", iid = iidT.getText(), sid = sidT.getText(); 
				
				
				if (iid.length() == 0 || iid == null) {
					message = message + "internship_id cannot be empty!\n";
				}
				for (char c : iid.toCharArray()) {
					if (!Character.isDigit(c)) {
						message = message + "internship_id cannot have alphabets or special characters!\n";
						isValid = false;
						break;
					}
				}
				if (isValid) {
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						rs = s.executeQuery("select * from internships where internship_id = " + iid);
						if (!rs.next()) {
							message = message + "internship_id does not exist!\n";
						}
						else {
							rs = s.executeQuery("select * from internships where internship_id = " + iid + " and manager_id = '" + manager_id + "'");
							if (!rs.next()) {
								message = message + "This internship_id has not been posted by you, you cannot delete it!\n";
							}
							else {
								iidexists = true;
							}
						}
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				if (sid.length() == 0 || sid.equals("student_id") || sid == null) {
					message = message + "student_id cannot be empty!\n";
				}
				else {
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						rs = s.executeQuery("select * from students where student_id = '" + sid + "'");
						if (!rs.next()) {
							message = message + "student_id does not exist!\n";
						}
						else {
							rs = s.executeQuery("select * from internshipsapplied where student_id = '" + sid + "'");
							if (!rs.next()) {
								message = message + "This student has not applied for any internship!\n";
							}
							else {
								sidexists = true;
							}
						}
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				if (sidexists && iidexists) {
					try {
						con = ConnectionManager.getConnection();
						s = con.createStatement();
						rs = s.executeQuery("delete from internshipsapplied where student_id = '" + sid + "' and internship_id = " + iid);
						if (!rs.next()) {
							message = message + "This student has not applied to this internship!\n";
						}
						else {
							s.executeQuery("commit");
						}
						s.close();
						con.close();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				if (message.length() != 0) {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Approved successfully!");
				}
			}
		});
		
		frame.add(prompt);
		frame.add(iidT);
		frame.add(sidT);
		frame.add(approve);
		
	}
	
	public void profileEdit(JFrame frame) {
		JButton update = new JButton("Update");
		update.setBounds(200, 320, 154, 23);
		update.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		update.setBackground(Color.WHITE);
		
		JLabel unameF, unameV, lnameF, fnameF, companyF, designationF, phoneF;
		unameF = new JLabel("Username:");
		unameV = new JLabel(manager_id);
		fnameF = new JLabel("First name:");
		lnameF = new JLabel("Last name:");
		companyF = new JLabel("Company name");
		designationF = new JLabel("Designation:");
		phoneF = new JLabel("Phone number:");
		
		JTextField fnameV = new JTextField("");
		JTextField lnameV = new JTextField("");
		JTextField companyV = new JTextField(""); 
		JTextField designationV = new JTextField("");
		JTextField phoneV = new JTextField("");
		
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from projectmanagers where manager_id = '" + manager_id + "'");
			rs.next();
			fnameV.setText(rs.getString(2));
			lnameV.setText(rs.getString(3));
			rs = s.executeQuery("select * from managerprofile where manager_id = '" + manager_id + "'");
			if (rs.next()) {
				companyV.setText(rs.getString(2));
				designationV.setText(rs.getString(3));
				phoneV.setText(rs.getString(4));
			}
			else {
				companyV.setText("");
				designationV.setText("");
				phoneV.setText("");
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
		
		fnameF.setBounds(50, 70, 100, 30);
		fnameF.setFont(new Font("", Font.PLAIN, 20));
		fnameV.setBounds(250, 70, 100, 30);
		fnameV.setFont(new Font("", Font.PLAIN, 20));
		
		lnameF.setBounds(50, 110, 100, 30);
		lnameF.setFont(new Font("", Font.PLAIN, 20));
		lnameV.setBounds(250, 110, 100, 30);
		lnameV.setFont(new Font("", Font.PLAIN, 20));
		
		companyF.setBounds(50, 150, 150, 30);
		companyF.setFont(new Font("", Font.PLAIN, 20));
		companyV.setBounds(250, 150, 140, 30);
		companyV.setFont(new Font("", Font.PLAIN, 20));
		
		phoneF.setBounds(50, 190, 150, 30);
		phoneF.setFont(new Font("", Font.PLAIN, 20));
		phoneV.setBounds(250, 190, 140, 30);
		phoneV.setFont(new Font("", Font.PLAIN, 20));
		
		designationF.setBounds(50, 230, 150, 30);
		designationF.setFont(new Font("", Font.PLAIN, 20));
		designationV.setBounds(250, 230, 180, 30);
		designationV.setFont(new Font("", Font.PLAIN, 20));
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validateProfile(fnameV.getText(), lnameV.getText(), companyV.getText(), phoneV.getText(), designationV.getText());
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
		frame.add(companyF);
		frame.add(companyV);
		frame.add(phoneF);
		frame.add(phoneV);
		frame.add(designationF);
		frame.add(designationV);
		frame.add(update);
	}
	
	public void validateProfile(String fname, String lname, String company, String phone, String designation) {
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
		
		if (company.length() == 0) {
			message = message + "Company name cannot be empty!\n";
		}
		else {
			for(char c : company.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)  ) {
					message = message + "Company name cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (designation.length() == 0) {
			message = message + "College name cannot be empty!\n";
		}
		else {
			for(char c : designation.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)  ) {
					message = message + "Designation name cannot have digits or special characters!\n";
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
			ResultSet r;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				s.executeQuery("update projectmanagers set firstname = '" + fname + "', lastname = '" + lname + "' where manager_id = '" + manager_id + "'");
				s.executeQuery("commit");
				r = s.executeQuery("update managerprofile set companyname = '" + company + "', designation = '" + designation + "', phonenumber = " + phone + " where manager_id = '" + manager_id + "'");
				if (!r.next()) {
					s.executeQuery("insert into managerprofile values('" + manager_id + "', '" + company + "', '" + designation + "', " + phone + ")");
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
	
	public void internshipsPostNew(JFrame frame) {
		JTextField iidT = new JTextField();
		iidT.setBounds(150, 150, 150, 40);
		iidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id of the internship which you want to post </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton post = new JButton("Post");
		post.setBounds(150, 220, 154, 23);
		post.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		post.setBackground(Color.WHITE);
		post.addActionListener(new ActionListener() {
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
						ResultSet r;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r = s.executeQuery("select * from internships where internship_id = " + iid);
							if (r.next()) {
								message = message + "internship_id already exists! Please choose another internship_id\n";
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
					welcome.setText("");
					frame.getContentPane().removeAll();
					frame.repaint();
					frame.add(back);
					internshipsPostNewEditPage(frame, iidT.getText(), "post");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		iidT.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			       if(e.getKeyCode() == KeyEvent.VK_ENTER){
			          post.doClick();
			       }
			    }
			    public void keyReleased(KeyEvent e) {}
			    public void keyTyped(KeyEvent e) {}
		});
		
		frame.add(prompt);
		frame.add(post);
		frame.add(iidT);
		
	}
	
	public void internshipsPostNewEditPage(JFrame frame, String iid, String type ) {
		JLabel iidF, iidV, positionF, salaryF, locationF, skillsF;
		iidF = new JLabel("Internship_id:");
		iidV = new JLabel(iid);
		positionF = new JLabel("Role:");
		salaryF = new JLabel("Salary:");
		locationF = new JLabel("Location:");
		skillsF = new JLabel("Skills Required:");
		
		JTextField positionV = new JTextField("");
		JTextField locationV = new JTextField(""); 
		JTextField salaryV = new JTextField("");
		JTextField skillsV = new JTextField("");
		
		if (type.equals("edit")) {
			Connection con;
			Statement s;
			ResultSet r;
			try {
				con = ConnectionManager.getConnection();
				s = con.createStatement();
				r = s.executeQuery("select * from internships where internship_id = " + iid);
				r.next();
				positionV.setText(r.getString(3));
				salaryV.setText(r.getString(4));
				locationV.setText(r.getString(5));
				skillsV.setText(r.getString(6));
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		JButton post = new JButton("Post");
		if (type.equals("edit")) {
			post.setText("Update");
		}
		post.setBounds(150, 260, 154, 23);
		post.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		post.setBackground(Color.WHITE);
		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internshipsValidate(iid, positionV.getText(), locationV.getText(), salaryV.getText(), skillsV.getText(), type);
			}
		});
		
		iidF.setBounds(50, 30, 200, 30);
		iidF.setFont(new Font("", Font.PLAIN, 20));
		iidV.setBounds(250, 30, 100, 30);
		iidV.setFont(new Font("", Font.PLAIN, 20));
		
		positionF.setBounds(50, 70, 100, 30);
		positionF.setFont(new Font("", Font.PLAIN, 20));
		positionV.setBounds(250, 70, 150, 30);
		positionV.setFont(new Font("", Font.PLAIN, 20));
		
		salaryF.setBounds(50, 110, 100, 30);
		salaryF.setFont(new Font("", Font.PLAIN, 20));
		salaryV.setBounds(250, 110, 150, 30);
		salaryV.setFont(new Font("", Font.PLAIN, 20));
		
		locationF.setBounds(50, 150, 100, 30);
		locationF.setFont(new Font("", Font.PLAIN, 20));
		locationV.setBounds(250, 150, 150, 30);
		locationV.setFont(new Font("", Font.PLAIN, 20));
		
		skillsF.setBounds(50, 190, 200, 30);
		skillsF.setFont(new Font("", Font.PLAIN, 20));
		skillsV.setBounds(250, 190, 150, 30);
		skillsV.setFont(new Font("", Font.PLAIN, 20));
		
		frame.add(post);
		frame.add(locationF);
		frame.add(locationV);
		frame.add(skillsF);
		frame.add(skillsV);
		frame.add(positionF);
		frame.add(positionV);
		frame.add(salaryF);
		frame.add(salaryV);
		frame.add(iidF);
		frame.add(iidV);
	}
	
	public void internshipsValidate(String iid, String position, String location, String salary, String skills, String type) {
		String message = "";
		
		if (position == null || position.length() == 0) {
			message = message + "Position cannot be empty!\n";
		}
		else {
			for(char c : position.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Position cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (location == null || location.length() == 0) {
			message = message + "Location cannot be empty!\n";
		}
		else {
			for(char c : location.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Location cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (skills == null || skills.length() == 0) {
			message = message + "Skills required cannot be empty!\n";
		}
		else {
			for(char c : skills.replaceAll(" ", "").toCharArray()) {
				if (!Character.isAlphabetic(c)) {
					message = message + "Skills required cannot have digits or special characters!\n";
					break;
				}
			}
		}
		
		if (salary == null || salary.length() == 0) {
			message = message + "Salary number cannot be empty!\n";
		}
		else {
			try {
				Integer.parseInt(salary);
			}
			catch (NumberFormatException e) {
				message += "Salary cannot have alphabets or special characters!\n";
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
				if (type.equals("post")) {
					s.executeQuery("insert into internships values('" + manager_id + "', " + iid + ", '" + position + "', " + salary + ", '" + location + "', '" + skills + "')");
				}
				else {
					s.executeQuery("update internships set position = '" + position + "', salary =" + salary + ", location = '" + location + "', skills_required = '" + skills + "' where internship_id = " + iid + "and manager_id = '" + manager_id + "'");
				}
				s.executeQuery("commit");
				s.close();
				con.close();
			} 
			catch (Exception e1) {
				e1.printStackTrace();
			}
			if (type.equals("post")) {
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.add(back);
				internshipsPostNew(frame);
				JOptionPane.showMessageDialog(new JFrame(), "Posted successfully!");
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Updated successfully!");
			}
			
		}
		
	}
	
	public void internshipsPostedEdit(JFrame frame) {
		JTextField iidT = new JTextField();
		iidT.setBounds(150, 150, 150, 40);
		iidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id of the internship which you want to edit </p>");
		prompt.setBounds(20, 50, 430, 50);
		prompt.setFont(new Font("", Font.PLAIN, 20));
		
		JButton edit = new JButton("Update");
		edit.setBounds(150, 220, 154, 23);
		edit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		edit.setBackground(Color.WHITE);
		edit.addActionListener(new ActionListener() {
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
						ResultSet r;
						try {
							con = ConnectionManager.getConnection();
							s = con.createStatement();
							r = s.executeQuery("select * from internships where internship_id = " + iid);
							if (!r.next()) {
								message = message + "internship_id does not exist!\n";
							}
							else {
								r = s.executeQuery("select * from internships where internship_id = " + iid + " and manager_id = '" + manager_id + "'");
								if (!r.next() ) {
									message = message + "You have not posted this internship, you cannot edit it!\n";
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
					welcome.setText("");
					frame.getContentPane().removeAll();
					frame.repaint();
					frame.add(back);
					internshipsPostNewEditPage(frame, iidT.getText(), "edit");
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), message, "error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		iidT.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			       if(e.getKeyCode() == KeyEvent.VK_ENTER){
			          edit.doClick();
			       }
			    }
			    public void keyReleased(KeyEvent e) {}
			    public void keyTyped(KeyEvent e) {}
		});
		
		frame.add(prompt);
		frame.add(edit);
		frame.add(iidT);
	}
	
	public void internshipsPostedDelete(JFrame frame) {
		JTextField iidT = new JTextField();
		iidT.setBounds(150, 150, 150, 40);
		iidT.setFont(new Font("", Font.PLAIN, 16));
		
		JLabel prompt = new JLabel("<html><p style=\\\"text-align:center;> Enter the internship_id of the internship which you want to delete </p>");
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
								r2 = s.executeQuery("select * from internships where internship_id = " + iid + " and manager_id = '" + manager_id + "'");
								if (!r2.next() ) {
									message = message + "You have not posted this internship, you cannot delete it!\n";
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
						s.executeQuery("delete from internships where internship_id = " + iid + " and manager_id = '" + manager_id + "' ");
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
}
