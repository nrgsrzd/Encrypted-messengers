package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Communication {
	
	private Connection con = null;
	private String sql;
	PreparedStatement preparedStmt;
	ResultSet result;
	private Vector<String[]> vect;
	String[] data;
	
	public void SignUp(String username){
		
		sql = "INSERT INTO users(username) VALUES(?)";
		
		Connect connect = new Connect();
		con = connect.connectDB();
		try {
			System.out.println("11111");
			preparedStmt = con.prepareStatement(sql);
			System.out.println("222");
			preparedStmt.setString (1, username);
			System.out.println("333");
		    preparedStmt.execute();
		    con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}  
		
	}
	public boolean Login(String username){
		sql = "SELECT username FROM users";
		
		Connect connect = new Connect();
		
		try {
			con = connect.connectDB();
			System.out.println("Con: "+con);
			preparedStmt = con.prepareStatement(sql);
		    result = preparedStmt.executeQuery();
		    while (result.next()) {
		    	if(username.equals(result.getString(1))){
		    		//FOUND
		    		return true;
		    	}
            }
		    con.close();    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} 
		return false;
	}
	public void saveMessage(String sender, String receiver, String message){
		sql = "INSERT INTO messages(sender, receiver, message, hasseen, hasnotif) VALUES(?, ?, ?, ?, ?)";
		Connect connect = new Connect();
		con = connect.connectDB();
		try {
			System.out.println("save message");
			preparedStmt = con.prepareStatement(sql);
			System.out.println("222");
			preparedStmt.setString (1, sender);
			preparedStmt.setString (2, receiver);
			preparedStmt.setString (3, message);
			preparedStmt.setInt (4, 0);
			preparedStmt.setInt (5, 0);
	//		System.out.println("send message1");
		    preparedStmt.execute();
			
		    con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	public Vector<String[]> giveMessage(String username){
		sql = "SELECT sender, message FROM messages WHERE hasseen = ? AND receiver = ?";
		Connect connect = new Connect();
		con = connect.connectDB();
		try {
			System.out.println("give message");
			preparedStmt = con.prepareStatement(sql);
			System.out.println("222");
			preparedStmt.setInt(1, 0);
			preparedStmt.setString (2, username);
			System.out.println("givemessage1");
			result = preparedStmt.executeQuery();
			
			vect = new Vector<String[]>();
			 while (result.next()) {
				 	data = new String[2];
			    	data[0]= result.getString(1);
			    	data[1]= result.getString(2);
			   // 	System.out.println("message in DB: "+ data[1]);
			    	vect.add(data);
	          }
			 for(int i =0; i<vect.size();i++){
					System.out.println("vec in Staerter server: "+ vect.get(i)[1]);
				}
		    con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return vect;
	}
	public boolean checkMessage(String username){
		sql = "SELECT hasnotif FROM messages WHERE receiver = ?";
		Connect connect = new Connect();
		con = connect.connectDB();
		
		System.out.println("start notif 1 communication");
		
		try {
			System.out.println("start notif 2 communication");
			preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, username);
			System.out.println("start notif 3 communication");
			System.out.println("user: "+ username);
			result = preparedStmt.executeQuery();

			while (result.next()) {
				System.out.println("start notif 4 communication");
				int s = result.getInt(1);
				System.out.println("Has notif: "+s);
				if(s==0){
					System.out.println("start notif 5 communication");
					return true;
				}
	        }
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public void readMessage(String user){
		sql = "UPDATE messages SET hasseen = 1 WHERE receiver = ?";
		Connect connect = new Connect();
		con = connect.connectDB();
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, user);
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setnotif(String username){
		sql = "UPDATE messages SET hasnotif = 1 WHERE receiver = ?";
		Connect connect = new Connect();
		con = connect.connectDB();
		
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
