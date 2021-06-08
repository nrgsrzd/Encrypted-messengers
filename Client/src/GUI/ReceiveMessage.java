package GUI;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;

import encryption.Encryption;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceiveMessage {

	private JFrame frame;
	String[] columnNames = {"From", "Message"};
	private Vector<String[]> Data;
	String[][] messages;
	String username;
	private final String key = "secret";
	String decryptedMessage;
	Encryption encryption;
	private JTable table;
	JButton btnBack;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ReceiveMessage(Vector<String[]> k, String username) {
		this.Data = k;
		this.username = username;
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
		frame.setVisible(true);
		
		System.out.println("&&&&&&&&");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 735);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(200, 132, 528, 465);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
        
        btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		frame.setVisible(false);
        		HomePage homepage = new HomePage(username);
        	}
        });
        btnBack.setFont(new Font("Eras Demi ITC", Font.PLAIN, 16));
        btnBack.setBounds(87, 627, 101, 27);
        panel.add(btnBack);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(200, 92, 110, 27);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		lblFrom.setBounds(12, 0, 56, 27);
		panel_2.add(lblFrom);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(460, 92, 110, 27);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblMessage = new JLabel(" Message");
		lblMessage.setBounds(0, 0, 86, 29);
		panel_3.add(lblMessage);
		lblMessage.setFont(new Font("Eras Demi ITC", Font.PLAIN, 15));
		
		JLabel lblbackground = new JLabel("");
		lblbackground.setIcon(new ImageIcon(ReceiveMessage.class.getResource("/background6.png")));
		lblbackground.setBounds(0, 0, 884, 735);
		panel.add(lblbackground);
		
		frame.getContentPane().setLayout(null);
		
	//	table = new JTable();
	//	table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	//	table.setBounds(12, 13, 504, 439);
	//	panel_1.add(table);
		
	//	System.out.println("Tableeee: "+ Data.size()+" mesage: "+ Data.get(1)[1]);
		if(Data.size()!=0){
			
			messages = new String[Data.size()][2];
			encryption = new Encryption();
			for(int i =0;i<Data.size();i++){
				messages[i][0]=Data.get(i)[0];
				decryptedMessage = encryption.decrypt(Data.get(i)[1], key);
				messages[i][1] = decryptedMessage;
			}
		//	System.out.println("messageeeeeee"+ messages[1][1]+" mesage: "+ messages[1][0]);
			table = new JTable(messages,columnNames );
			table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			table.setBounds(12, 13, 504, 439);
			panel_1.add(table);
		//	frame.getContentPane().add(table);
		//	frame.getContentPane().add(table);
			
		//	table.setBounds(1, 30, 450, 0);
			  
	        // adding it to JScrollPane
	        
			
		}
		else{
			JOptionPane.showMessageDialog(null, "You don't have new message!");
			frame.setVisible(false);
    		HomePage homepage = new HomePage(username);
		}

		
		
		
		
	}
}
