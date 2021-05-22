import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class App2Client {
	public static void main(String[] args){
		
	//	String serverAddress = JOptionPane.showInputDialog("Enter Server Name");
	//	String serverPort = JOptionPane.showInputDialog("Enter Port Number");
	//	int port = Integer.parseInt(serverPort);
		Socket s;
		try {
			s = new Socket("localhost", 9090);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String msg="";
			while(true){
				String toServer = JOptionPane.showInputDialog(null,"Send something to server");
				out.println(toServer);
				msg = input.readLine();
				System.out.println(msg);
				if (toServer.equals("#")) {break;}
			}
			out.close();
			input.close();
			s.close();
			System.exit(0);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
