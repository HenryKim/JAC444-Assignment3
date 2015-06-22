package part2;
/**
 * * @author Hyungi Kim
 */
import java.net.*;
import java.io.*;
import java.util.*;

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
        
            OperstionTypeRequest regist;
            OperationTypeDisplay query;
           
            do{
            	Object o = oisFromClient.readObject();
            	 if(o.getClass() == OperstionTypeRequest.class) {
            	
		            try{

                        regist = (OperstionTypeRequest)o; 
                        regist.getStudent().setRegisteredId(regist.getStudent().hashCode());
                        
                        Integer tbPerfix = regist.getStudent().getRegisteredId();
                        String tbName = tbPerfix.toString() + "_Timetable";
                        
                        FileOutputStream fos = new FileOutputStream(tbName);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        
                        oos.writeObject(regist.getStudent().getTimetable());
                        oosToClient.writeObject(regist);
                        oosToClient.flush();
                        oos.close();

		            }catch (EOFException eof) {
		                System.out.println("### Client has terminated connection ***");
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
            	 }else if(o.getClass() == OperationTypeDisplay.class){
            		 String course = "";
            		 int index = 0;
            		 Timetable timetable = null;
            		
        			 query = (OperationTypeDisplay)o;
        			 Integer id = query.getReguestId();
        			 String fileName = id.toString();
        			 fileName = fileName.concat("_Timetable");
        			 
        			 FileInputStream fis = new FileInputStream(fileName);
        			 ObjectInputStream ois = new ObjectInputStream(fis);
        			 
        			 timetable = (Timetable)ois.readObject();
        			 
        			 index = (query.getPeriod() - 1) * 5 + (query.getDay() - 1);
        			 List<String> list = timetable.getList();
        			 course = list.get(index);

        			 oosToClient.writeObject(course);
        			 oosToClient.flush();
        			 ois.close();
	 
            	 }else if(o.getClass() == OperationTypeExit.class){
            		 
            		 break;
            	 }else{
            		 
            		 break;
            	 }
            }while(true);
            
            oosToClient.close();
            oisFromClient.close();
            socketConnection.close();
        
        }catch(Exception e){
            
            System.out.println(e.getMessage());
        }
        
        System.out.println("### Server Stoped ###");
    }
}

