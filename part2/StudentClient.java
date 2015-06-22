package part2;
/**
 * * @author Hyungi Kim
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * main method of student client
 * 
 */

public class StudentClient {

	public static void main(String[] args) {

		Socket clientSocket;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int option = 0, day = 0, period = 0;

		try {

			clientSocket = new Socket(InetAddress.getByName("localhost"), 4128);
			System.out.println("Connected to "
					+ clientSocket.getInetAddress().getHostName());

			ObjectInputStream oisFromServer = new ObjectInputStream(
					clientSocket.getInputStream());
			ObjectOutputStream oosToServer = new ObjectOutputStream(
					clientSocket.getOutputStream());

			System.out.println("I/O streams connected to the socket");

			Student student = new Student();

			do {

				System.out.print("Please give your operation 1 for reguest and 2 for search the time table 3 for exit:  ");
				option = in.nextInt();

				if(option != 1 && option != 2 && option != 3){
					System.out.println("\nInvalid operation, please try again\n");
					continue;
				}
				
				if (option == 1) {
					
					if(student.getRegisteredId() != 100000){
						
						System.out.println("You haven already reguest, please try option(s)");
						continue;
					}
					
					
					try {
						System.out.println("Print before reguest\n" + student.toString());
						
						OperstionTypeRequest regist = new OperstionTypeRequest(student);
						
						oosToServer.writeObject(new OperstionTypeRequest(new Student()));

						oosToServer.flush();

						regist = (OperstionTypeRequest) oisFromServer.readObject();
						student = (Student)regist.getStudent();
						System.out.println("Print after reguest\n" + regist.getStudent().toString());

					} catch (ClassNotFoundException cnf) {
						
						cnf.printStackTrace();
					} catch (EOFException eof) {
						
						System.out.println("The server has terminated connection!");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				else if( option == 2){
				
					if(student.getRegisteredId() == 100000){
						
						System.out.println("You haven't reguest yet, please reguest first");
						continue;
					}
					
					System.out.print("Please select a day(1-5):  ");
					day = in.nextInt();
					if(day > 5){
						System.out.print("Invalid day number");
						continue;
					}
					
					System.out.print("Please select a Period(1-12):  ");
					period = in.nextInt();
					if(period > 12){
						System.out.print("Invalid period number");
						continue;
					}
					try{
						int id = student.getRegisteredId();
						
						OperationTypeDisplay query = new OperationTypeDisplay(day, period, id);
						
						oosToServer.writeObject(query);
						oosToServer.flush();
						
						String course = (String)oisFromServer.readObject();
						
						if(course.length() > 3){
							System.out.println("\nThe course is: " + course+"\n");
						}else{
							System.out.println("\nYou dont have a class at this time\n");
						}
						
						
					} catch (ClassNotFoundException cnf) {
						
						cnf.printStackTrace();
					} catch (EOFException eof) {
						
						System.out.println("The server has terminated connection!");
					} catch (IOException e) {
						
						e.printStackTrace();
					}

				}else if(option == 3){
					
					OperationTypeExit quit = new OperationTypeExit();
					oosToServer.writeObject(quit);
					oosToServer.flush();
					break;
					
				}
				
			} while (option != 3);

			System.out.println("\nClient: closing the connection...");
			oosToServer.close();
			oisFromServer.close();
			clientSocket.close();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		System.out.println("the client is going to stop runing...");
	}
}
