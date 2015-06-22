package part1;
/**
 * * @author Hyungi Kim
 */
import part1.Student;
import java.net.*;
import java.io.*;

public class StudentClient {
/**
 * main method of student client
 * 
 */
	public static void main(String[] args){
		
		Socket clientSocket;
		
		try{
			
			clientSocket = new Socket(InetAddress.getByName("localhost"), 4128);
			System.out.println("Connected to " + clientSocket.getInetAddress().getHostName());
			
			ObjectInputStream oisFromServer = new ObjectInputStream( clientSocket.getInputStream() );
			ObjectOutputStream oosToServer = new ObjectOutputStream( clientSocket.getOutputStream() );
			
			System.out.println("I/O streams connected to the socket");
			
			Student student = new Student();
			System.out.println("### Before Reguest ###");
			System.out.println(student.toString());
			
			try{
				
				oosToServer.writeObject(student );
				oosToServer.flush();
				
				student  = (Student) oisFromServer.readObject();
				
			}catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
			} catch (EOFException eof) {
				System.out.println("The server has terminated connection!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("\nClient: closing the connection...");
			oosToServer.close();
			oisFromServer.close();
			clientSocket.close();
		}catch(Exception e){
			
			System.out.println( e.getMessage() );
		}
		
		System.out.println("the client is going to stop runing...");
	}
}

