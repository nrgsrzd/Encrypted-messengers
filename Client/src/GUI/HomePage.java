package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import Controller.StartClient;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.event.ActionEvent;


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
//	private JTextField textFieldId;
	private String receiverId, SenderId;
	private Vector<String[]> Data;
	
	StartClient client;
	Timer timer;
	private JTextField textField;
	private JButton btnExit;
	 public TrayIcon trayIcon;
	 public SystemTray tray;
	// StartClient client;
//	 private static String location = "C:/Windows";
	 
	class Helper extends TimerTask
	{
	    public int l = 0;
	    public void run()
	    {
	        System.out.println("Timer ran " + ++l);
	        client = new StartClient();
			boolean notif =true;
	        client.checkForMessage(SenderId);
			
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
	
	/**
	 * Create the application.
	 */
	public HomePage(String username) {
		this.SenderId = username;
		client = new StartClient();
		initialize();
		System.out.println("username in homepge: "+ SenderId);
		timer = new Timer();
	    TimerTask task = new Helper();
	      
	    timer.schedule(task, 700, 800);
	    
	}

	public HomePage(){
		
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
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 735);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(181, 237, 529, 295);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.setVisible(true);
				textField.setVisible(true);
				btnReceiveMessage.setVisible(false);
				btnSendMessage.setVisible(false);
			}
		});
		btnSendMessage.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnSendMessage.setBounds(74, 124, 158, 37);
		panel_1.add(btnSendMessage);
		
		btnReceiveMessage = new JButton("Reveive Message");
		btnReceiveMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				StartClient client = new StartClient();
				Data = client.getMessageFromServer(SenderId);
				ReceiveMessage receivemessage = new ReceiveMessage(Data, SenderId);
			}
		});
		btnReceiveMessage.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnReceiveMessage.setBounds(283, 124, 169, 37);
		panel_1.add(btnReceiveMessage);
		
		textField = new JTextField();
		textField.setBounds(189, 89, 143, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receiverId = textField.getText();
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
					textField.setVisible(false);
					btnSearch.setVisible(false);
				}
			}
		});
		btnSearch.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnSearch.setBounds(189, 190, 143, 25);
		panel_1.add(btnSearch);
		btnSearch.setVisible(false);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new StartClient();
				client.disconnect();
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
		btnExit.setBounds(51, 669, 97, 25);
		panel.add(btnExit);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(HomePage.class.getResource("/background6.png")));
		lblBackground.setBounds(0, 0, 884, 735);
		panel.add(lblBackground);
		
		Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
		tray = SystemTray.getSystemTray();
		trayIcon = new TrayIcon(image, "Tray Demo");
		trayIcon.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("asdasd");
				frame.setVisible(true);
			//	 Desktop dst = Desktop.getDesktop();
		    //     try {
			//		dst.open(new File(location));
			//	} catch (IOException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}
		            /*
		             * reset the location to root location again.
		             */
		    //        location = "C:/Windows";
			}
			
		});
	}

	
	 public void displayTray() throws AWTException {
	        //Obtain only one instance of the SystemTray object
	        

	        //If the icon is a file
	 ////       Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
	        //Alternative (if the icon is on the classpath):
	        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

	////        trayIcon = new TrayIcon(image, "Tray Demo");
	        //Let the system resize the image if needed
	        trayIcon.setImageAutoSize(true);
	        //Set tooltip text for the tray icon
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);

	        trayIcon.displayMessage("you have new Message!", "notification demo", MessageType.INFO);
	        
	  }
}
