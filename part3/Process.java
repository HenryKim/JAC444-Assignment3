package part3;
/**
 * * @author Hyungi Kim
 */
import java.io.*;
import java.util.List;
import java.net.*;

/**
 * 
 * the class that extends thread, used to handle different request from client
 *
 */
public class Process extends Thread {

	Object request;
	ObjectOutputStream oosToClient;
	ObjectInputStream oisFromClient;
	Socket socketConnection;

	/**
	 * class constructor
	 * @param s Socket that contain the connect info for one client
	 */
	public Process(Socket s) {

		try {
			socketConnection = s;
			oosToClient = new ObjectOutputStream(
					socketConnection.getOutputStream());
			oisFromClient = new ObjectInputStream(
					socketConnection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * run method that inherit from the Thread class, logic to run to handle different request 
 */
	public void run() {
		do {
			try {
				request = oisFromClient.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (request.getClass() == OperstionTypeRequest.class) {

				System.out.println("\nReceive client request: Regiest\n");
				
				OperstionTypeRequest regist;
				try {

					regist = (OperstionTypeRequest) request;
					regist.getStudent().setRegisteredId(
							regist.getStudent().hashCode());

					Integer tbPerfix = regist.getStudent().getRegisteredId();
					String tbName = tbPerfix.toString() + "_Timetable";

					FileOutputStream fos = new FileOutputStream(tbName);
					ObjectOutputStream oos = new ObjectOutputStream(fos);

					oos.writeObject(regist.getStudent().getTimetable());

					oosToClient.writeObject(regist);
					oosToClient.flush();
					oos.close();

				} catch (EOFException eof) {
					System.out
							.println("### Client has terminated connection ***");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (NullPointerException ntp) {
					ntp.printStackTrace();
				}
			} else if (request.getClass() == OperationTypeDisplay.class) {
				
				System.out.println("\nReceive client request: Search timetable\n");

				OperationTypeDisplay query;
				String course = "";
				int index = 0;
				Timetable timetable = null;

				query = (OperationTypeDisplay) request;

				try {

					FileInputStream fis;
					Integer id = query.getReguestId();
					String fileName = id.toString();
					fileName = fileName.concat("_Timetable");
					fis = new FileInputStream(fileName);

					ObjectInputStream ois;
					ois = new ObjectInputStream(fis);
					timetable = (Timetable) ois.readObject();
					index = (query.getPeriod() - 1) * 5 + (query.getDay() - 1);
					List<String> list = timetable.getList();
					course = list.get(index);
					oosToClient.flush();
					ois.close();
					oosToClient.writeObject(course);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} else if (request.getClass() == OperationTypeExit.class) {
				
				System.out.println("\nReceive client request: Terminate connection\n");

				try {
					socketConnection.close();
					oosToClient.close();
					oisFromClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		} while (true);
	}

}
