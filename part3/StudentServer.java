package part3;
/**
 * * @author Hyungi Kim
 */
import java.io.IOException;
import java.net.*;

public class StudentServer {
	/**
	 * main method of student server
	 * 
	 */
	public static void main(String[] args) {

		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(4128);

			System.out.println("### Student Server Satrted ###");
			System.out.println("Waitting for Client Request");

			do {
				Socket socketConnection = null;
				socketConnection = serverSocket.accept();

				if (socketConnection != null) {

					new Process(socketConnection).start();
				}
			} while (true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
