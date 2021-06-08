package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import Controller.StartClient;
import encryption.Encryption;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class SendMessage {

	private JFrame frame;
	private String message, senderId, receiverId;
	JTextArea textArea;
	private final String key = "secret";
	String encryptedMessage;
	StartClient client;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public SendMessage(String sender, String receiver) {
		this.senderId = sender;
		this.receiverId = receiver;
		client = new StartClient();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 890, 770);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 735);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(182, 84, 539, 552);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEnterYourMessage = new JLabel("Enter your Message:");
		lblEnterYourMessage.setFont(new Font("Eras Demi ITC", Font.PLAIN, 17));
		lblEnterYourMessage.setBounds(34, 31, 194, 31);
		panel_1.add(lblEnterYourMessage);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message = textArea.getText();
				
				//encrypt message
				Encryption encryption = new Encryption();
				encryptedMessage = encryption.encrypt(message, key);
				
				
			//	StartClient client = new StartClient();
				if(client.checkConnections(receiverId)){
				//	JOptionPane.showMessageDialog(null, "User is Online!");
					client.sendMessageToServer(senderId, receiverId,encryptedMessage);
					JOptionPane.showMessageDialog(null, "Your message Sent Successfully!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(senderId);
				}
				else{
					client.sendMessageToServer(senderId, receiverId,encryptedMessage);
					JOptionPane.showMessageDialog(null, "Your message Sent Successfully!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(senderId);		
				}
			}
		});
		btnSendMessage.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnSendMessage.setBounds(44, 475, 164, 40);
		panel_1.add(btnSendMessage);
		
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.inactiveCaptionBorder);
		textArea.setBounds(44, 75, 460, 373);
		panel_1.add(textArea);
		
		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon(SendMessage.class.getResource("/background6.png")));
		lblbackground.setBounds(0, 0, 884, 735);
		panel.add(lblbackground);
		frame.setVisible(true);
	}
}
