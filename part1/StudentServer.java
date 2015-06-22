package part1;
/**
 * * @author Hyungi Kim
 */
import part1.Student;
import java.net.*;
import java.io.*;

public class StudentServer {
/**
 * main method of student server
 * 
 */
	public static void main(String[] args){
	
		ServerSocket serverSocket;
		
		try{
			serverSocket = new ServerSocket(4128);
		
			System.out.println("### Student Server Satrted ###");
			System.out.println("Waitting for Client Request");
		
			Socket socketConnection = serverSocket.accept();
		
			ObjectOutputStream oosToClient = new ObjectOutputStream(
					socketConnection.getOutputStream());

			ObjectInputStream oisFromClient = new ObjectInputStream(
					socketConnection.getInputStream());
		
			Student s;
			
			try{
				
				while(true){
					
					s = (Student)oisFromClient.readObject(); 
					s.setRegisteredId(s.hashCode());
					
					oosToClient.writeObject(s);
					oosToClient.flush();
				}

			}catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
			} catch (EOFException eof) {
				System.out.println("### Client has terminated connection ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			oosToClient.close();
			oisFromClient.close();
			socketConnection.close();
		
		}catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		
		System.out.println("### Server Stoped ###");
	}
}
