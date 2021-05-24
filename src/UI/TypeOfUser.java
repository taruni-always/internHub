package UI;
import javax.swing.*;
import java.awt.*;

public class TypeOfUser {
	public static void main(String[] args) {
		JFrame userType = new JFrame("User Types");
		userType.getContentPane().setBackground(new Color(174, 230, 101));
		
		JLabel prompt = new JLabel("Select the type of user:");
		prompt.setBounds(150, 50, 350, 60);
		Font promptFont = prompt.getFont();
		int stringWidth = prompt.getFontMetrics(promptFont).stringWidth("Select the type of user:");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(promptFont.getSize() * widthRatio);
		prompt.setFont(new Font("Select the type of user:", Font.ITALIC, Math.min(newFontSize, 60)));
		userType.add(prompt);
		
		JButton student = new JButton("Student");
		student.setBounds(100,150,150, 40);
		student.setFont(new Font("Student", Font.PLAIN, 20));
		student.setBackground(Color.white);
		student.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userType.add(student);
		
		JButton manager = new JButton("Project Manager");
		manager.setBounds(370,150,150, 40);
		manager.setFont(new Font("Project Manager", Font.PLAIN, 20));
		manager.setBackground(Color.white);
		manager.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		userType.add(manager);
		
		userType.setSize(650, 450);
		userType.setLayout(null);
		userType.setVisible(true);
	}

}
