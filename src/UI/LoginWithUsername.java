package UI;

import javax.swing.*;
import main.ConnectionManager;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginWithUsername {
	public JFrame frame;
	public String typeOfUser;
	public JLabel prompt;
	public JButton submit, back;
	public JTextField username;
	
	public LoginWithUsername(JFrame frame, String typeOfUser ) {
		this.typeOfUser = typeOfUser;
		this.frame = frame;
		frame.setTitle("Login as a " + typeOfUser + "!");
		frame.getContentPane().setBackground(new Color(125, 193, 232));
		
		prompt = new JLabel("Enter your user name:");
		prompt.setBounds(50, 56, 400, 40);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Enter your user name");
		double widthRatio = (double)400 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Enter your user name", Font.ITALIC, Math.min(newFontSize, 40)));
		frame.add(prompt);
		
		back = new JButton("Go back to Home Page");
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Go back to Home Page", Font.ITALIC, 11));
		back.setBounds(10, 350, 154, 23);
		frame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				new HomePage(frame);
			}
		});
		
		submit = new JButton("Submit");
		submit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		submit.setBackground(Color.WHITE);
		submit.setFont(new Font("Submit", Font.ITALIC, 15));
		submit.setBounds(190, 240, 89, 23);
		frame.add(submit);
		 submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate(username.getText());
			}
		});
		
		username = new JTextField();
		username.setBounds(130, 150, 200, 30);
		username.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			       if(e.getKeyCode() == KeyEvent.VK_ENTER){
			          submit.doClick();
			       }
			    }
			    public void keyReleased(KeyEvent e) {}
			    public void keyTyped(KeyEvent e) {}
		});
		frame.add(username);
		
		frame.setSize(500, 440);	
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void validate(String username) {
		Connection con;
		Statement s;
		ResultSet rs;
		try {
			con = ConnectionManager.getConnection();
			s = con.createStatement();
			boolean flag = false;
			if (typeOfUser.equals("student")) {
				rs = s.executeQuery("select student_id from students");
				while(rs.next()) {
					String user = rs.getString(1);
					if (user.equals(username)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					frame.getContentPane().removeAll();
					new Student(frame, username);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Username doesnot exist! Please enter a valid username!", "error", JOptionPane.ERROR_MESSAGE);
				}				
			}
			else {
				rs = s.executeQuery("select manager_id from projectmanagers");
				while(rs.next()) {
					if (rs.getString(1).equals(username)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					frame.getContentPane().removeAll();
						new Manager(frame, username);
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(), "Username doesnot exist! Please enter a valid username!", "error", JOptionPane.ERROR_MESSAGE);
				}
			}
			s.close();
			con.close();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
