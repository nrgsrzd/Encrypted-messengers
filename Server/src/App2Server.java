

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App2Server {
	
	public static void main(String[] args){
		ServerSocket listener;
		try {
			listener = new ServerSocket(9090);
			System.out.println("Server is running");
			while (true)
			{
				Socket socket = listener.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg="";
				while(true){
					msg = input.readLine();
					if (msg.equals("#"))
					{
					out.println("From Server: terminatin connection");
					break;
					}
				//	else out.println("From Server: "+msg);
				}
				out.close();
				input.close();
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}