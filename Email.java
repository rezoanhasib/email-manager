import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
/**
 * The <CODE>Email</CODE> is a Java Applicaton that creates an email object with associated data fields to,
 * cc,bcc,subject,body and a timeStamp 
 * @author rezoanhasib
 * <A> HREF= "mailto:rezoan0308@gmail.com">(rezoan0308@gmail.com)</A>
 */
public class Email implements Serializable
{
	private String to; 
	private String cc; 
	private String bcc; 
	private String subject; 
	private String body; 
	private GregorianCalendar timestamp;
	boolean debug=false ;
	/**
	 * Constructor to create an email object 
	 * @param to
	 * 		the address an email is being sent to 
	 * @param cc
	 * 		the carbon copy of the email body 
	 * @param bcc
	 * 		the blind carbon copy of the email body 
	 * @param subject
	 * 		the subject of the email 
	 * @param body
	 * 		the email body 
	 * @param timeStamp
	 * 		the time when the email is created 
	 * @postcondition 
	 * 		an email object is created with the specified fields 
	 */
	//constructor
	//Is it necessary to assign all the varibles to null in the constructor or can leave blank? 
	public Email(String to,String cc,String bcc,String subject,String body,GregorianCalendar timeStamp)
	{
		this.to=to; 
		this.cc=cc; 
		this.bcc=bcc;
		this.subject=subject; 
		this.body=body; 
		this.timestamp=timeStamp; 
		if (debug)
		{
			System.out.println("New email is created");
		}
	}
	/**
	 * Method to set the address an email is being sent to  
	 * @param to
	 * 		the sending address of selected email 
	 * @postcondition 
	 * 		the sending address of selected email is set  
	 */
	//mutator
	public void setTo(String to)
	{
		this.to=to; 
	}
	/**
	 * Method to set the carbon copy of an email   
	 * @param cc
	 * 		the carbon copy of selected email 
	 * @postcondition 
	 * 		the carbon copy of selected email is set  
	 */
	public void setCc(String cc)
	{
		this.cc=cc; 
	}
	/**
	 * Method to set the blind carbon copy of an email 
	 * @param bcc
	 * 		the blind carbon copy of selected email 
	 * @postcondition 
	 * 		the blind carbon copy of selected email is set  
	 */
	public void setBcc(String bcc)
	{
		this.bcc=bcc; 
	}
	/**
	 * Method to set the subject of an  email   
	 * @param subject
	 * 		the subject of selected email 
	 * @postcondition 
	 * 		the subject of selected email is set  
	 */
	public void setSubject(String subject)
	{
		this.subject=subject; 
	}
	/**
	 * Method to set the body of an email   
	 * @param body
	 * 		the body of selected email 
	 * @postcondition 
	 * 		the body of selected email is set  
	 */
	public void setBody(String body)
	{
		this.body=body; 
	}
	/**
	 * Method to set the time when an email is created  
	 * @param timestamp
	 * 		the time selected email was created
	 * @postcondition 
	 * 		the time selected email is created is set   
	 */
	public void setTimestamp(GregorianCalendar timestamp)
	{
		this.timestamp=timestamp; 
	}
	/**
	 * Method to obtain the address an email is being sent to 
	 * @return
	 * 		the address selected email is being sent to 
	 */
	public String getTo()
	{
		return this.to; 
	}
	/**
	 * Method to obtain the carbon copy of selected email 
	 * @return
	 * 		the carbon copy of selected email 
	 */
	public String getCc()
	{
		return this.cc; 
	}
	/**
	 * Method to obtain the blind carbon copy of selected email 
	 * @return
	 * 		the blind carbon copy of selected email 
	 */
	public String getBcc()
	{
		return this.bcc; 
	}
	/**
	 * Method to obtain the subject of selected email 
	 * @return
	 * 		the subject of selected email 
	 */
	public String getSubject()
	{
		return this.subject; 
	}
	/**
	 * Method to obtain the body of selected email 
	 * @return
	 * 		the body of selected email 
	 */
	public String getBody()
	{
		return this.body; 
	}
	/**
	 * Method to obtain the time of selected email creation
	 * @return
	 * 		the creation time of selected email 
	 */
	public GregorianCalendar getTimestamp()
	{
		return this.timestamp; 
	}
	/**
	 * Comparator method to sort the email objects according to subject ascending 
	 */
	public static Comparator<Email> subjectAscendingComparator=new Comparator<Email>()
	{
		public int compare(Email first,Email second)
		{
			String subject_first=first.getSubject().toUpperCase(); 
			String subject_second=second.getSubject().toUpperCase(); 
			return subject_first.compareTo(subject_second); 
		}		
	};
	/**
	 * Comparator method to sort the email objects according to subject descending 
	 */
	public static Comparator<Email> subjectDescendingComparator=new Comparator<Email>()
	{
		public int compare(Email first,Email second)
		{
			String subject_first=first.getSubject().toUpperCase(); 
			String subject_second=second.getSubject().toUpperCase(); 
			return subject_second.compareTo(subject_first); 
		}
	};
	/**
	 * Comparator method to sort the email objects according to date ascending 
	 */
	public static Comparator<Email> dateAscendingComparator=new Comparator<Email>()
	{
		public int compare(Email first,Email second)
		{
			GregorianCalendar date_first=first.getTimestamp(); 
			GregorianCalendar date_second=second.getTimestamp(); 
			return date_first.compareTo(date_second); 
			
		}
	};
	/**
	 * Comparator method to sort the email objects according to date descending 
	 */
	public static Comparator<Email> dateDescendingComparator=new Comparator<Email>()
	{
		public int compare(Email first,Email second)
		{
			GregorianCalendar date_first=first.getTimestamp(); 
			GregorianCalendar date_second=second.getTimestamp(); 
			return date_second.compareTo(date_first); 
		}
		
	}; 
	/**
	 * Method to override the output format for the calendar object 
	 */
	public String toString()
	{
		return "Subject: "+this.subject+", To: "+this.to+", CC: "+this.cc+", BCC: "+this.bcc+", Body: "+this.body+"."; 
	}
	
	
}
