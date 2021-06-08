package Controller;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

import GUI.HomePage;
import GUI.WelcomePage;

public class StartClient {
	
	public static Socket socket;
	private BufferedReader input;
	private PrintWriter out;
	private final int port = 9090;
	private Vector<String[]> vecData;
	String Data[];
	String From[];
	String[] temp;
	int size;
	String username;
	static int counter=0;
	WelcomePage welcomepage;
//	static int counter
	
	public void ConnectClient(){
		try {
			socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//	String msg="";
			
			Thread t = new ClientListener();
			t.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ClientListener extends Thread{
		
		@Override
	    public void run() 
	    {
			while (true) 
	        {
				if(counter==0){
					counter++;
				//	welcomepage = new WelcomePage();
					username=welcomepage.textFieldUserName.getText();
					System.out.println("welcome!"+ username);
				}
				else{
					try {
						input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String msg;
					try {
						msg = input.readLine();
						if(msg.equals("True")){
							HomePage h = new HomePage(username);
							System.out.println("%%%%%%");
							try {
								h.displayTray();
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			//	System.out.println("***********");
	        }
	    }
	}
	
	public boolean sendUsernameToServer(String username){
		
		try {
			socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
			//	while(true){
			//	System.out.println("start client check user 1");
			out.println("verify"); //check for login or search
			//	System.out.println("start client check user 2");
			out.println(username);
			//	System.out.println("start client check user 3");
			String check = input.readLine();
			System.out.println("start client check user 4");
			if(check.equalsIgnoreCase("true")){	
				
					
				socket.setKeepAlive(true);
			//		out.close();
			//		input.close();
				return true;
			}
			else{
				socket.setKeepAlive(true);
				//	out.close();
				//	input.close();
				return false;
			}
				
		//		socket.close();
		//		System.exit(0);
		} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	public void sendMessageToServer(String sender, String receiver, String message){
		try {
		//	socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
		//	while(true){
		//	System.out.println("startclient1");
			out.println("Message");
			out.println(sender);
			out.println(receiver);
			out.println(message);
		//	System.out.println("startclient2");
//			msg = input.readLine();
			System.out.println(msg);
			socket.setKeepAlive(true);
		//	System.out.println("startclient3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Vector<String[]> getMessageFromServer(String username){
		
		try {
		//	socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
		//	while(true){
		//	System.out.println("startclient1");
			out.println("GetMessage");
			out.println(username);
			
	//		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("!@#");
			msg = input.readLine();
			size = Integer.parseInt(msg);
			System.out.println("size gerfte shode: "+size);
			Data = new String[size];
			From = new String[size];
			
			for(int i =0;i<size; i++){
				From[i] = input.readLine();
				System.out.println("f: "+ From[i]);
			}
			for(int i =0 ;i<size; i++){
				Data[i] = input.readLine();
			}
			
		//	System.out.println("D: "+ Data[0]);
			vecData = new Vector<String[]>();
			for(int i=0;i<size;i++){
				temp = new String[2];
				temp[0] = From[i];
				temp[1] = Data[i];
				vecData.add(temp);
			}
			socket.setKeepAlive(true);
		/*	while(true){
				msg = input.readLine();
				System.out.println("Message from server: "+ msg);
				
				if(msg.equals("#")){
					break;
				}*/
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println("Size of Data: "+ vecData.size());
		return vecData;
		
	}
	public boolean checkConnections(String username){
		
		try {
	//		socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
			
			out.println("check");
			out.println(username);
			
			String ch = input.readLine();
			
			if(ch.equals("True")){
				socket.setKeepAlive(true);
				return true;
			}
			else{
				socket.setKeepAlive(true);
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.setKeepAlive(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	/*public boolean checkForMessage(String id){
		try {
		//	socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
			
			System.out.println("start notif 1 client");
			
			out.println("give notif");
			out.println(id);
			
			System.out.println("start notif 2 client");
			String bol = input.readLine();
	//		socket.setKeepAlive(true);
			if(bol.equalsIgnoreCase("True")){
				//haveMessage
			//	socket.setKeepAlive(true);
			//	socket.close();
			//	socket = new Socket("localhost", port);
		//		System.out.println("have message");
		//		out = new PrintWriter(socket.getOutputStream(), true);
		//		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
		//		out.println("give notif");
		//		out.println(id);
		//		bol = input.readLine(); //if bol is True it means user is online and have message
		//		if(bol.equalsIgnoreCase("True")){
					socket.setKeepAlive(true);
					return true;
		//		}
		//		else{
		//			socket.setKeepAlive(true);
		//			return false;
		//		}
			}
			else{
				System.out.println("client you don't have new message");
				socket.setKeepAlive(true);
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.setKeepAlive(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/
	public boolean checkForOnline(String id){
		try {
		//	socket = new Socket("localhost", port);
			out = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg="";
			
			System.out.println("start notif 1 client");
			
			out.println("check");
			out.println(id);
			
			System.out.println("start notif 2 client");
			String bol = input.readLine();
			socket.setKeepAlive(true);
			if(bol.equalsIgnoreCase("True")){
				//haveMessage
			//	socket.setKeepAlive(true);
			//	socket.close();
			//	socket = new Socket("localhost", port);
				System.out.println("have message");
				out = new PrintWriter(socket.getOutputStream(), true);
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				out.println("give notif");
				out.println(id);
				bol = input.readLine(); //if bol is True it means user is online and have message
				if(bol.equalsIgnoreCase("True")){
					socket.setKeepAlive(true);
					return true;
				}
				else{
					socket.setKeepAlive(true);
					return false;
				}
			}
			else{
				socket.setKeepAlive(true);
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.setKeepAlive(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
    public void setNotif(String id){
    	try {
    		//	socket = new Socket("localhost", port);
    		out = new PrintWriter(socket.getOutputStream(), true);
    		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		String msg="";
    			
    		out.println("setnotif");
    		out.println(id);
    			
    		socket.setKeepAlive(true);
    		
    		} catch (UnknownHostException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    public void signUp(String id){

    		//	socket = new Socket("localhost", port);
    		try {
				out = new PrintWriter(socket.getOutputStream(), true);
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    		String msg="";
	    			
	    		out.println("signup");
	    		out.println(id);
	    			
	    		socket.setKeepAlive(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    }
    public void disconnect(){
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
