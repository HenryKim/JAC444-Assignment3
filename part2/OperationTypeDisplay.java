package part2;
/**
 * * @author Hyungi Kim
 */
import java.io.Serializable;

public class OperationTypeDisplay implements Serializable {
private static final long serialVersionUID = 1L;
private int Day = 0, Period = 0, reguestId = 0;
private String Coures;
/**
 * OperationTypeDisplay constructor
 * @param day: value from 1 to 5 represent from Monday to Friday 
 * @param period: value from 1 to 12 to represent 12 period in one day
 * @param id: student register id
 */
    public OperationTypeDisplay(int day, int period, int id){
        
        this.setDay(day);
        this.setPeriod(period);
        this.setReguestId(id);
        this.Coures = "";
    }

    /**
     * getter of the day		
     * @return
     */
	public int getDay() {
		return Day;
	}
	
	/**
	 * setter of the day
	 * @param day
	 */
	public void setDay(int day) {
		Day = day;
	}

	/**
	 * getter of the period
	 * @return period
	 */
	public int getPeriod() {
		return Period;
	}

	/**
	 * setter of the period
	 * @param period
	 */
	public void setPeriod(int period) {
		Period = period;
	}
	
	/**
	 * setter of the course
	 * @param course
	 */
	public void setCourse(String course){
		Coures = course;
	}
	
	/**
	 * getter of the course
	 * @return
	 */
	public String getCoures(){
		return Coures; 
	}

	/**
	 * getter of the RegisteredId
	 * @return
	 */
	public int getReguestId() {
		return reguestId;
	}
	/**
	 * setter of the RegisteredId
	 * @param reguestId
	 */
	public void setReguestId(int reguestId) {
		this.reguestId = reguestId;
	}
	
	/**
	 * toString method override
	 */
	@Override
	public String toString(){
		
		return "Day "+ this.Day + "Period "+ this.Period + "Id " + this.reguestId + "Course " + this.Coures; 
	}
	
	/**
	 * hashCode method override
	 */
	@Override
	public int hashCode(){
		final int p = 31;
		int re = 1;
		re = p * Coures.hashCode();
		
		re = this.Day *31 + this.Period * 31 + this.reguestId *31;
		return re;
	}
	
	/**
	 * equals method override
	 */
	@Override
	public boolean equals(Object obj){
		
		if(this == obj)
			return true;
		
		OperationTypeDisplay o = (OperationTypeDisplay)obj;
		
		if(!o.Coures.equals(this.Coures)){
			return false;
		}
		if(o.Day != this.Day){
			return false;
		}
		if(o.Period != this.Period){
			return false;
		}
		if(o.reguestId != this.reguestId){
			return false;
		}
		
		return true;
	}
}





















