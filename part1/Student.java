package part1;
import java.io.*;
/**
 * 
 * @author Hyungi Kim
 *
 */
public class Student implements Serializable{

	/**
	 * class student: used to store student information
	 */
	private static final long serialVersionUID = -6778531646255550842L;
	private String LastName;
	private String FirstName;
	private int Age = 0;
	private int RegisteredId = 0;
	
	/**
	 * student constructor initialize  the object with default value
	 */
	public Student(){
		
		this.FirstName = "Zhaohu";
		this.LastName = "Zhu";
		this.Age = 22;
		this.RegisteredId = 100000;
	}
	
	/**
	 * setter of Registered Id
	 * @param id: value to be set to
	 */
	public void setRegisteredId(int id){
		
		this.RegisteredId = id;
		
	}
	
	/**
	 * getter of Registered Id
	 * @return Registered Id
	 */
	public int getRegisteredId(){
		
		return this.RegisteredId;
	}
	
	/**
	 * toString method overload
	 */
	@Override
	public String toString(){
		
		return "Name: " + this.LastName + ", " + this.FirstName + "\nAge: " + this.Age + "\nRegisteredId: " + this.RegisteredId;
	}
	
	/**
	 * hashCode method overload
	 */
	@Override
	public int hashCode(){
		
		final int p = 31;
		int rt = 1;
		
		rt = p * ( this.FirstName.hashCode() + this.LastName.hashCode() );
		rt += this.Age;
		rt += this.RegisteredId;
		
		return rt;
	}
	
	/**
	 * equals method overload
	 */
	@Override
	public boolean equals(Object obj){
		
		if(this == obj)
			return true;
		
		if( !(obj instanceof Student) )
			return false;
		
		Student s = (Student)obj;
		
		if(s.Age!= this.Age) return false;
		
		if(!s.FirstName.equals(this.FirstName) ) return false;
		
		if(!s.LastName.equals(this.LastName) ) return false;
		
		if(this.RegisteredId != s.RegisteredId) return false;
			
		return true;		
	}
	
	
}

