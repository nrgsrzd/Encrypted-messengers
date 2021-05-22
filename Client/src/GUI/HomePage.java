package GUI;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Controller.StartClient;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.util.Timer;
import java.util.TimerTask;
  

public class HomePage {

	private JFrame frame;
	private JButton btnSendMessage, btnReceiveMessage, btnSearch;
	private JTextField textFieldId;
	private String receiverId, SenderId;
	private Vector<String[]> Data;
	
	StartClient client;
	Timer timer;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	class Helper extends TimerTask
	{
	    public int l = 0;
	    public void run()
	    {
	        System.out.println("Timer ran " + ++l);
	        client = new StartClient();
			boolean notif = client.checkForMessage(SenderId);
			System.out.println("start notif 2");
			if(notif){
				System.out.println("start notif 3");
			//	JOptionPane.showMessageDialog(null, "You have new message!");
				try {
					displayTray();
					client.setNotif(SenderId);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	timer.cancel();
			}
	    }
	}
	
	  
    
	public HomePage(String username) {
		this.SenderId = username;
		initialize();
		System.out.println("username in homepge: "+ SenderId);
		timer = new Timer();
	    TimerTask task = new Helper();
	      
	    timer.schedule(task, 700, 800);
	    
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 522);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		
		btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.setVisible(true);
				textFieldId.setVisible(true);
				btnReceiveMessage.setVisible(false);
				btnSendMessage.setVisible(false);
			}
		});
		btnSendMessage.setBounds(131, 209, 126, 25);
		frame.getContentPane().add(btnSendMessage);
		btnSendMessage.setVisible(true);
		
		btnReceiveMessage = new JButton("Receive Message");
		btnReceiveMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				StartClient client = new StartClient();
				Data = client.getMessageFromServer(SenderId);
				ReceiveMessage receivemessage = new ReceiveMessage(Data, SenderId);
			}
		});
		btnReceiveMessage.setBounds(379, 209, 143, 25);
		frame.getContentPane().add(btnReceiveMessage);
		btnReceiveMessage.setVisible(true);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(260, 178, 116, 22);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setVisible(false);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiverId = textFieldId.getText();
				//check for searching username in database
				client = new StartClient();
				if(client.sendUsernameToServer(receiverId)){
				/*	if(client.checkForOnline(receiverId)){
						
					}
					else{
						
					}*/
					frame.setVisible(false);
					SendMessage sendmessage = new SendMessage(SenderId, receiverId);
				}
				else{
					JOptionPane.showMessageDialog(null, "No User Found");
					btnReceiveMessage.setVisible(true);
					btnSendMessage.setVisible(true);
					textFieldId.setVisible(false);
					btnSearch.setVisible(false);
				}
				
			}
		});
		btnSearch.setBounds(279, 246, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new StartClient();
				client.disconnect();
			//	frame.setVisible(false);
				System.exit(0);
			}
		});
		btnExit.setBounds(23, 437, 97, 25);
		frame.getContentPane().add(btnExit);
		btnSearch.setVisible(false);
		
		System.out.println("start notif");
		
		
//		JOptionPane.showMessageDialog(null, "Welcome!");
		
	}
	 public static void displayTray() throws AWTException {
	        //Obtain only one instance of the SystemTray object
	        SystemTray tray = SystemTray.getSystemTray();

	        //If the icon is a file
	        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
	        //Alternative (if the icon is on the classpath):
	        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

	        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
	        //Let the system resize the image if needed
	        trayIcon.setImageAutoSize(true);
	        //Set tooltip text for the tray icon
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);

	        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
	    }
}
