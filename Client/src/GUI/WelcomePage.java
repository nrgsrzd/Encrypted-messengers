package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import Controller.StartClient;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage {

	private JFrame frame;
	public JTextField textFieldUserName;
	private JButton btnLogin, btnSignUp;
	private String userName;
	StartClient client;
	public HomePage homepage ;
	/**
	 * Create the application.
	 */
	public WelcomePage() {
		client = new StartClient();
	//	client.ConnectClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 890, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 735);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(42, 13, 798, 90);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblIcon = new JLabel("New label");
		lblIcon.setIcon(new ImageIcon(WelcomePage.class.getResource("/logo2.png")));
		lblIcon.setBackground(Color.WHITE);
		lblIcon.setBounds(12, 13, 77, 66);
		panel_1.add(lblIcon);
		
		JLabel lblTitle = new JLabel("Welcome to encrypted messenger");
		lblTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 34));
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(173, 13, 613, 66);
		panel_1.add(lblTitle);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(180, 178, 515, 481);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your Username:");
		lblNewLabel.setFont(new Font("Dosis SemiBold", Font.PLAIN, 20));
		lblNewLabel.setBounds(41, 82, 235, 27);
		panel_2.add(lblNewLabel);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(159, 157, 191, 32);
		panel_2.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Login
				userName = textFieldUserName.getText();	
				
				if(client.sendUsernameToServer(userName)){
					JOptionPane.showMessageDialog(null, "Welcome!");
					frame.setVisible(false);
					homepage = new HomePage(userName);
					System.out.println("did you login?");
				}
				else{
					JOptionPane.showMessageDialog(null, "No User Found");
					btnLogin.setVisible(true);
					btnSignUp.setVisible(true);
				}
			//	userName = textFieldUserName.getText();	
			//	StartClient sendusername = new StartClient();
			//	sendusername.ConnectClient();
				
			//	if(sendusername.sendUsernameToServer(userName)){
			//		JOptionPane.showMessageDialog(null, "Welcome!");
			//		frame.setVisible(false);
			//		HomePage homepage = new HomePage(userName);
				//	System.out.println("did you login?");
			//	}
			//	else{
			//		JOptionPane.showMessageDialog(null, "No User Found");
				//	btnLogin.setVisible(true);
				//	btnSignUp.setVisible(true);
			//	}
			}
		});
		btnLogin.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnLogin.setBounds(182, 258, 145, 32);
		panel_2.add(btnLogin);
		
		btnSignUp = new JButton("SignUp");
		btnSignUp.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sign Up
				userName = textFieldUserName.getText();	
				StartClient client = new StartClient();
				if(client.sendUsernameToServer(userName)){
					JOptionPane.showMessageDialog(null, "username Taken.");
				}
				else{
					client.signUp(userName);
				//	System.out.println("555");
					JOptionPane.showMessageDialog(null, "SignUp was successed!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(userName);
					System.out.println("whattttt");
				}
			}
		});
		btnSignUp.setBounds(182, 313, 145, 32);
		panel_2.add(btnSignUp);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setIcon(new ImageIcon(WelcomePage.class.getResource("/background6.png")));
		lblBackground.setBounds(0, 0, 884, 735);
		panel.add(lblBackground);
		frame.setVisible(true);
		
		

	}

}
