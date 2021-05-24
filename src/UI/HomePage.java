package UI;
import javax.swing.*;
import java.awt.*;

public class HomePage {
	public static void main(String args[]) {
		JFrame home = new JFrame("InternHub - Home Page");
		home.getContentPane().setBackground(new Color(125, 193, 232));
		
		JLabel title = new JLabel("Welcome to InternHub!");
		title.setBounds(150, 50, 350, 60);
		Font titleFont = title.getFont();
		int stringWidth = title.getFontMetrics(titleFont).stringWidth("Welcome to InternHub!");
		double widthRatio = (double)350 / (double)stringWidth;
		int newFontSize = (int)(titleFont.getSize() * widthRatio);
		title.setFont(new Font("Welcome to InternHub!", Font.ITALIC, Math.min(newFontSize, 60)));
		home.add(title);
		
		JLabel prompt = new JLabel("(Log-in for existing users | Sign-up for new users)");
		prompt.setBounds(170, 100, 350, 40);
		home.add(prompt);
		
		JButton login = new JButton("Log-in");
		login.setBounds(150,200,150, 40);
		login.setFont(new Font("Log-in", Font.PLAIN, 20));
		login.setBackground(Color.white);
		login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		home.add(login);
		
		JButton signup = new JButton("Sign-up");
		signup.setBounds(320,200,150, 40);
		signup.setFont(new Font("Sign-up", Font.PLAIN, 20));
		signup.setBackground(Color.white);
		signup.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		home.add(signup);
		
		
		home.setSize(650, 450);
		home.setLayout(null);
		home.setVisible(true);
	}
}