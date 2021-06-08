package Controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import Database.Communication;



public class StartServer {
	
	private ServerSocket listener;
//	private Socket socket;
	private final int port = 9090;
//    private	PrintWriter out;
	
//	private String codedMessage;
	private String sender, receiver, message;
	private static String username="";
	private Vector<String[]> vec;
	private String[] From, Data;
	private ArrayList<String> connections = new ArrayList<String>();
	
	public StartServer(){}
	
	//Start Connection
	public void ConnectServer(){	
		 try {
			 System.out.println("Server Connect 1");
			ServerSocket ss = new ServerSocket(port);
			 while (true) 
		        {
				 System.out.println("Server Connect 2");
		            Socket socket = null;
		              
		            try 
		            {
		            	 System.out.println("Server Connect 3");
		                // socket object to receive incoming client requests
		            	socket = ss.accept();
		            	 System.out.println("Server Connect 4");  
		                System.out.println("A new client is connected : " + socket);
		                  
		                // obtaining input and out streams
		         //       DataInputStream dis = new DataInputStream(socket.getInputStream());
		         //       DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		                  
		                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		                
		                System.out.println("Assigning new thread for this client");
		  
		                // create a new thread object
		                Thread t = new ClientHandler(socket, input, out);
		  
		                // Invoking the start() method
		                t.start();
		                  
		            }
		            catch (Exception e){
		                socket.close();
		                e.printStackTrace();
		            }
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	class ClientHandler extends Thread 
	{
	//    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
	//    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
	    final BufferedReader input;
	    final PrintWriter out;
	    final Socket s;
	      
	  
	    // Constructor
	    public ClientHandler(Socket s, BufferedReader dis, PrintWriter dos) 
	    {
	        this.s = s;
	        this.input = dis;
	        this.out = dos;
	    }
	  
		public void checkNewMessage(String id){
			Communication communication = new Communication();
			System.out.println("start notif 2 Server");
			boolean s = communication.checkMessage(username);
			System.out.println("start notif 3 Server");
			if(s){
				System.out.println("have message");
				System.out.println("username: "+ username);
				out.println("True");
			}
			else{
				System.out.println("Server you don't have new message");
				out.println("False");
			}
		}
		
	    @Override
	    public void run() 
	    {
	        while (true) 
	        {
	       // 	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			//	BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg="", operation = "";
				try {
					operation = input.readLine();
					
//					msg = input.readLine();
					System.out.println("Operation: "+ operation);
				//	System.out.println("message: "+ msg);
					System.out.println("server1");
					 Communication communication = new  Communication();
					System.out.println("server2");
				//	System.out.println("444");
					if(operation.equalsIgnoreCase("verify")){
						System.out.println("Server verify 1");
						username = input.readLine();
						if(communication.Login(username)){
							System.out.println("Server verify 2");
							connections.add(username);
							System.out.println("Server verify 3");
							for(int i =0;i<connections.size();i++){
								System.out.println("Connections: "+connections.get(i));
							}
							System.out.println("Server verify 4");
							out.println("True");
						}
						else{
							System.out.println("Server verify 5");
							out.println("False");
						}
					}
					if(operation.equalsIgnoreCase("message")){
						System.out.println("777");
						sender = input.readLine();
						System.out.println("sender: "+ sender);
						receiver = input.readLine();
						System.out.println("receiver: "+ receiver);
						message = input.readLine();
						System.out.println("message text: "+ message);
						communication.saveMessage(sender, receiver, message);
					}
					if(operation.equalsIgnoreCase("GetMessage")){
						username = input.readLine();
						System.out.println("user: "+username);
						System.out.println("***********");
						vec = communication.giveMessage(username);
						System.out.println("server data size: "+ vec.size());
						From = new String[vec.size()];
						System.out.println("(((((((");
						Data = new String[vec.size()];
			//			for(int i =0; i<vec.size();i++){
			//				System.out.println("vec in Staerter server: "+ vec.get(i)[1]);
			///			}
			//			System.out.println("000000");
					//	socket = listener.accept();
			//			System.out.println("accepted");
						out.println(String.valueOf(vec.size()));
			//			System.out.println("size sent");
						for(int i =0; i<vec.size();i++){
							From[i] = vec.get(i)[0];
							Data[i] = vec.get(i)[1];
					//		System.out.println("Daya in starter erver: "+ Data[i]);
						}
			//			System.out.println("waiting 2 size From: "+ From.length);
					//	System.out.println("waiting 2 size Vec: "+ vec.size());
					//	socket = listener.accept();
				//		System.out.println("accepted2");
						for(int i =0;i<From.length;i++){
							out.println(From[i]);
						}
				//		System.out.println("sent43");
						for(int i =0;i<From.length;i++){
							out.println(Data[i]);
						}
			//			System.out.println("Data: "+ Data[0]);
					//	out.println("#");
						/**set hasseen = 1*/
						communication.readMessage(username);
						
					}
					if(operation.equalsIgnoreCase("check")){

						username = input.readLine();
						for(int i =0; i<connections.size();i++){
							if(connections.get(i).equalsIgnoreCase(username)){
								System.out.println("user is online!");
								out.println("True");
							}
							else{
								System.out.println("user is not online!");
								out.println("False");
							}
						}
					}
			/*		if(operation.equalsIgnoreCase("checkMessage")){

						username = input.readLine();
						for(int i =0; i<connections.size();i++){
							if(connections.get(i).equalsIgnoreCase(username)){
								out.println("True");
							}
							else{
								out.println("False");
							}
						}
					}*/
			/*		if(operation.equalsIgnoreCase("give notif")){
						username = input.readLine();
						System.out.println("start notif 1 Server");
						communication = new Communication();
						System.out.println("start notif 2 Server");
						boolean s = communication.checkMessage(username);
						System.out.println("start notif 3 Server");
						if(s){
							System.out.println("have message");
							System.out.println("username: "+ username);
							out.println("True");
						}
						else{
							System.out.println("Server you don't have new message");
							out.println("False");
						}
					}*/
					if(operation.equalsIgnoreCase("setnotif")){
						username = input.readLine();
				//		System.out.println("start notif 1 Server");
						communication = new Communication();
				//		System.out.println("start notif 2 Server");
						communication.setnotif(username);
				//		System.out.println("start notif 3 Server");
					}
					if(operation.equalsIgnoreCase("signup")){
						username = input.readLine();
				//		System.out.println("start notif 1 Server");
						communication = new Communication();
				//		System.out.println("start notif 2 Server");
						communication.SignUp(username);
				//		System.out.println("start notif 3 Server");
					}
					System.out.println("777");
			//		checkNewMessage(username);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	        }
	    }
	}
	

	
	/****/
	
/*	public void Start(){
		try {
			listener = new ServerSocket(port);
			System.out.println("Server is running");
			Socket socket = listener.accept();
			while (true)
			{
			//	Socket socket = listener.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg="", operation = "";
				operation = input.readLine();
			//	msg = input.readLine();
				System.out.println("Operation: "+ operation);
			//	System.out.println("message: "+ msg);
				System.out.println("server1");
				 Communication communication = new  Communication();
				System.out.println("server2");
			//	System.out.println("444");
				if(operation.equalsIgnoreCase("verify")){
					System.out.println("Server verify 1");
					username = input.readLine();
					if(communication.Login(username)){
						System.out.println("Server verify 2");
						connections.add(username);
						System.out.println("Server verify 3");
						for(int i =0;i<connections.size();i++){
							System.out.println("Connections: "+connections.get(i));
						}
						System.out.println("Server verify 4");
						out.println("True");
					}
					else{
						System.out.println("Server verify 5");
						out.println("False");
					}
				}
				if(operation.equalsIgnoreCase("message")){
					System.out.println("777");
					sender = input.readLine();
					System.out.println("sender: "+ sender);
					receiver = input.readLine();
					System.out.println("receiver: "+ receiver);
					message = input.readLine();
					System.out.println("message text: "+ message);
					communication.saveMessage(sender, receiver, message);
				}
				if(operation.equalsIgnoreCase("GetMessage")){
					username = input.readLine();
					System.out.println("user: "+username);
					System.out.println("***********");
					vec = communication.giveMessage(username);
					System.out.println("server data size: "+ vec.size());
					From = new String[vec.size()];
					System.out.println("(((((((");
					Data = new String[vec.size()];
		//			for(int i =0; i<vec.size();i++){
		//				System.out.println("vec in Staerter server: "+ vec.get(i)[1]);
		///			}
		//			System.out.println("000000");
				//	socket = listener.accept();
		//			System.out.println("accepted");
					out.println(String.valueOf(vec.size()));
		//			System.out.println("size sent");
					for(int i =0; i<vec.size();i++){
						From[i] = vec.get(i)[0];
						Data[i] = vec.get(i)[1];
				//		System.out.println("Daya in starter erver: "+ Data[i]);
					}
		//			System.out.println("waiting 2 size From: "+ From.length);
				//	System.out.println("waiting 2 size Vec: "+ vec.size());
				//	socket = listener.accept();
			//		System.out.println("accepted2");
					for(int i =0;i<From.length;i++){
						out.println(From[i]);
					}
			//		System.out.println("sent43");
					for(int i =0;i<From.length;i++){
						out.println(Data[i]);
					}
		//			System.out.println("Data: "+ Data[0]);
				//	out.println("#");
					/**set hasseen = 1*/
	/*				communication.readMessage(username);
					
				}
				if(operation.equalsIgnoreCase("check")){

					username = input.readLine();
					for(int i =0; i<connections.size();i++){
						if(connections.get(i).equalsIgnoreCase(username)){
							out.println("True");
						}
						else{
							out.println("False");
						}
					}
				}
		/*		if(operation.equalsIgnoreCase("checkMessage")){

					username = input.readLine();
					for(int i =0; i<connections.size();i++){
						if(connections.get(i).equalsIgnoreCase(username)){
							out.println("True");
						}
						else{
							out.println("False");
						}
					}
				}*/
	/*			if(operation.equalsIgnoreCase("give notif")){
					username = input.readLine();
					System.out.println("start notif 1 Server");
					communication = new Communication();
					System.out.println("start notif 2 Server");
					boolean s = communication.checkMessage(username);
					System.out.println("start notif 3 Server");
					if(s){
						out.println("True");
					}
					else{
						out.println("False");
					}
				}
				System.out.println("777");

	//			out.close();
	//			input.close();
	//			socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}*/
}
