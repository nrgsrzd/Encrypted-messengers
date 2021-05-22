package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sl8");
		StartServer startserver = new StartServer();
	//	startserver.Start();
		startserver.ConnectServer();
	}

}
