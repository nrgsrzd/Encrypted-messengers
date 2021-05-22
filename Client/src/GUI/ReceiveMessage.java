package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceiveMessage {

	private JFrame frame;
	private JTable table;
	String[] columnNames = {"From", "Message"};
	private Vector<String[]> Data;
	String[][] messages;
	String username;

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
		frame.setBounds(100, 100, 688, 522);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		if(Data.size()!=0){
			messages = new String[Data.size()][2];
			
			for(int i =0;i<Data.size();i++){
				messages[i][0]=Data.get(i)[0];
				messages[i][1]=Data.get(i)[1];
			}
			frame.getContentPane().setLayout(null);
			
			table = new JTable(messages,columnNames );
			frame.getContentPane().add(table);
			
			table.setBounds(1, 30, 450, 0);
			  
	        // adding it to JScrollPane
	        JScrollPane sp = new JScrollPane(table);
	        sp.setBounds(109, 22, 452, 431);
	        frame.getContentPane().add(sp);
	        
	        JButton btnBack = new JButton("Back");
	        btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		frame.setVisible(false);
	        		HomePage homepage = new HomePage(username);
	        	}
	        });
	        btnBack.setBounds(0, 428, 97, 25);
	        frame.getContentPane().add(btnBack);
		}
		else{
			JOptionPane.showMessageDialog(null, "You don't have new message!");
			frame.setVisible(false);
    		HomePage homepage = new HomePage(username);
		}
		
        
        
        // Frame Size
  //      f.setSize(500, 200);
        // Frame Visible = true
    //    f.setVisible(true);
		
	}
}
