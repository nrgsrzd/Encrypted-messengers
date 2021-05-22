package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import Controller.StartClient;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SendMessage {

	private JFrame frame;
	JTextArea textArea;
	JLabel lblEnterYourMessage;
	JButton btnSend;
	private String message, senderId, receiverId;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public SendMessage(String sender, String receiver) {
		this.senderId = sender;
		this.receiverId = receiver;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 522);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(140, 97, 399, 296);
		frame.getContentPane().add(textArea);
		
		lblEnterYourMessage = new JLabel("Enter your message:");
		lblEnterYourMessage.setBounds(140, 68, 140, 16);
		frame.getContentPane().add(lblEnterYourMessage);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message = textArea.getText();
				StartClient client = new StartClient();
				if(client.checkConnections(receiverId)){
				//	JOptionPane.showMessageDialog(null, "User is Online!");
					client.sendMessageToServer(senderId, receiverId,message);
					JOptionPane.showMessageDialog(null, "Your message Sent Successfully!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(senderId);
				}
				else{
					client.sendMessageToServer(senderId, receiverId,message);
					JOptionPane.showMessageDialog(null, "Your message Sent Successfully!");
					frame.setVisible(false);
					HomePage homepage = new HomePage(senderId);		
				}
			}
		});
		btnSend.setBounds(150, 403, 97, 25);
		frame.getContentPane().add(btnSend);
		frame.setVisible(true);
	}
}
