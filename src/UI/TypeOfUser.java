package UI;
import javax.swing.*;
import java.awt.*;

public class TypeOfUser {
	//public JFrame userType;
	public JLabel prompt;
	public JButton student, manager, back;
	
	public TypeOfUser(JFrame userType) {
		userType.setTitle("User Types");
		userType.getContentPane().setBackground(new Color(174, 230, 101));
		
		prompt = new JLabel("Select the type of user:");
		prompt.setBounds(150, 50, 350, 60);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Select the type of user:");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Select the type of user:", Font.ITALIC, Math.min(newFontSize, 60)));
		userType.add(prompt);
		
		student = new JButton("Student");
		student.setBounds(100,150,150, 40);
		student.setFont(new Font("Student", Font.PLAIN, 20));
		student.setBackground(Color.white);
		student.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userType.add(student);
		
		manager = new JButton("Project Manager");
		manager.setBounds(370,150,160, 40);
		manager.setFont(new Font("Project Manager", Font.PLAIN, 18));
		manager.setBackground(Color.white);
		manager.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userType.add(manager);
		
		back = new JButton("<- Go back to Home Page");
		back.setBounds(50, 320, 160, 30);
		back.setFont(new Font("Go back to Home Page",Font.ITALIC, 12));
		back.setBackground(Color.white);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userType.add(back);
		
		userType.setSize(650, 450);
		userType.setLayout(null);
		userType.setVisible(true);
	}

}
