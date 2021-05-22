package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Controller.StartClient;

public class WelcomePage {

	private JFrame frame;
	private JButton btnLogin, btnSignup;
	private JTextField textFieldUsername;
	private JButton btnSubmitS, btnSubmitL;
	private String userName;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldUsername.setVisible(true);
				btnSubmitL.setVisible(true);
				btnLogin.setVisible(false);
				btnSignup.setVisible(false);
			}
		});
		btnLogin.setBounds(442, 220, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldUsername.setVisible(true);
				btnSubmitS.setVisible(true);
				btnLogin.setVisible(false);
				btnSignup.setVisible(false);
			}
		});
		btnSignup.setBounds(127, 220, 97, 25);
		frame.getContentPane().add(btnSignup);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(271, 286, 116, 22);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setVisible(false);
		
		btnSubmitS = new JButton("Submit");
		btnSubmitS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sign Up
				userName = textFieldUsername.getText();	
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
		btnSubmitS.setBounds(281, 321, 97, 25);
		frame.getContentPane().add(btnSubmitS);
		btnSubmitS.setVisible(false);
		
		btnSubmitL = new JButton("Submit");
		btnSubmitL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Login
				userName = textFieldUsername.getText();	
				StartClient sendusername = new StartClient();
				if(sendusername.sendUsernameToServer(userName)){
					JOptionPane.showMessageDialog(null, "Welcome!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(userName);
					System.out.println("did you login?");
				}
				else{
					JOptionPane.showMessageDialog(null, "No User Found");
					btnLogin.setVisible(true);
					btnSignup.setVisible(true);
					textFieldUsername.setVisible(false);
					btnSubmitL.setVisible(false);
				}
				
			}
		});
		btnSubmitL.setBounds(281, 348, 97, 25);
		frame.getContentPane().add(btnSubmitL);
		btnSubmitL.setVisible(false);	
	}
}
