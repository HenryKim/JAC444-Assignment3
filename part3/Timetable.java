package part3;
/**
 * * @author Hyungi Kim
 */
import java.util.*;
import java.io.*;

/**
 * 
 * time table class that will show all the time table info for one student
 *
 */
public class Timetable implements Serializable{

	private static final long serialVersionUID = -6336465461413962331L;
	protected  List<String> list = new ArrayList<String>();

	/**
	 * time table class constructor that take one file name as parameter
	 * @param file name of the time table source file
	 */
	public Timetable(String file){
		
		try{
			
			@SuppressWarnings("resource")
			BufferedReader inputStream = new BufferedReader(new FileReader(file));
		
		String l;
		while ((l = inputStream.readLine()) != null){
			
			String[] element = l.split(",");
			
			list.add(element[0]);
			list.add(element[1]);
			list.add(element[2]);
			list.add(element[3]);
			list.add(element[4]);
		}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}	
	}
	
	/**
	 * getter of the list that contain all the course name in the time table
	 * @return
	 */
	public List<String> getList(){
		return this.list;
	}

	/**
	 * toString method override
	 */
	@Override	
	public String toString(){
		
		String re="";
		int base = 0;
		re += "          ||MON   ||TUE   ||WEN   ||THUR  ||FRI   ||";
		re = re + "\n --------------------------------------------------  \n";
		for(int i = 0; i < 12; i++){
			if(i >= 9){
				re += "Period " + (i+1)+ " ";
			}else{
				re += "Period " + (i+1)+ "  ";
			}
			re += "|";
			for(int j = 0; j < 5; j++){
				if(this.list.get(base+j).length() == 6){
					
					re += "|"+list.get(base+j)+"|";
				}else{
					
					re += "| Free |";
				}
			}
			re += "|";
			re = re + "\n --------------------------------------------------  \n";
			base +=5; 
		}
		 
		return re;
	}
	
	/**
	 * hashCode method override
	 */
	@Override
	public int hashCode(){
		final int p = 31;
		int re = 1;
		
		for(String i : list){
			re = p * i.hashCode();
		}
		
		return re;
	}
	
	/**
	 * equals method override
	 */
	@Override
	public boolean equals(Object obj){
		
		if(this == obj)
			return true;
	
		if( !(obj instanceof Timetable) )
			return false;
		
		Timetable t=(Timetable)obj;
		int index = 0;
		for ( String l : t.getList()){
			boolean re = l.equals(list.get(index));
			index++;
			if (re == false){
				return false;
			}
		}
		
		return true;
	}
	
}