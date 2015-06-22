package part2;
/**
 * * @author Hyungi Kim
 */
import java.io.Serializable;

/**
 * 
 * the class used to tell the server the client made a request of Register
 *
 */
public class OperstionTypeRequest implements Serializable  {

	private static final long serialVersionUID = -331862336428509355L;
	private Student student;
/**
 * class constructor
 * @param s the student object which has the info about the student who made the request
 */
    public OperstionTypeRequest(Student s){
        
        this.student = s;
    }

    /**
     * getter of the student object
     * @return
     */
    public Student getStudent(){
        
        return this.student;
    }
    
    /**
     * toString method override
     */
    @Override
    public String toString(){
    	
    			
    	return "Operation type display timetable. Student: " + this.student.toString();		
    }
    
    /**
     * hashCode method override
     */
    @Override
    public int hashCode(){
    	
    	int re =1;
    	
    	re += student.hashCode();
    	
    	return re*31;
    }
    
    /**
     * equals method override
     */
    @Override
    public boolean equals(Object obj){
    	
    	if(this == obj)
			return true;
    	
    	OperstionTypeRequest o = (OperstionTypeRequest)obj;
    	
    	if(!o.student.equals(this.student)){
    		return false;
    	}
    	
    	return true;
    }
}