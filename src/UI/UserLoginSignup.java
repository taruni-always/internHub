package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLoginSignup {
	public JFrame frame;
	public JLabel prompt;
	public JButton student, manager, back;
	
	public UserLoginSignup(JFrame frame, String userType) {
		this.frame = frame;
		if (userType.equals("login")) {
			frame.setTitle("User Log-in");
		}
		else {
			frame.setTitle("User Sign-up");
		}
		frame.getContentPane().setBackground(new Color(174, 230, 101));
		
		prompt = new JLabel("Select the type of user:");
		prompt.setBounds(80, 50, 350, 60);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Select the type of user:");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Select the type of user:", Font.ITALIC, Math.min(newFontSize, 60)));
		frame.add(prompt);
		
		student = new JButton("Student");
		student.setBounds(70,150,150, 40);
		student.setFont(new Font("Student", Font.PLAIN, 20));
		student.setBackground(Color.white);
		student.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(student);
		student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				if (userType.equals("login")) {
					frame.repaint();
					new LoginWithUsername(frame, "student");
				}
				else {
					frame.repaint();
					new NewUserSignup(frame, "student");
				}
				
			}
		});
		
		manager = new JButton("Project Manager");
		manager.setBounds(250,150,160, 40);
		manager.setFont(new Font("Project Manager", Font.PLAIN, 18));
		manager.setBackground(Color.white);
		manager.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(manager);
		manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				if (userType.equals("login")) {
					frame.repaint();
					new LoginWithUsername(frame, "manager");
				}
				else {
					frame.repaint();
					new NewUserSignup(frame, "manager");
				}
			}
		});
		
		back = new JButton("Go back to Home Page");
		back.setBounds(50, 320, 160, 30);
		back.setFont(new Font("Go back to Home Page",Font.ITALIC, 12));
		back.setBackground(Color.white);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new HomePage(frame);
			}
		});
		
		frame.setSize(500, 440);
		frame.setLayout(null);
		frame.setVisible(true);
	}

}
