import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The <CODE>Folder</CODE> is a Java Applicaton that creates a folder that contains email objects in it 
 * @author rezoanhasib
 * <A> HREF= "mailto:rezoan0308@gmail.com">(rezoan0308@gmail.com)</A>
 */
public class Folder extends ArrayList<Email>implements Serializable 
{
	private ArrayList<Email> emails; 
	private String name; 
	private String currentSortingMethod;
	boolean debug=false; 
	/**
	 * Constructor to create a folder of emails with the given name 
	 * @param name
	 * 		the name of the selected folder 
	 */
	//constructor,accessor and mutator methods
	public Folder(String name)
	{
		emails=new ArrayList<Email>(); 
		this.name=name; 
	}
	/**
	 * Constructor to create a new folder of emails without given name 
	 */
	public Folder()
	{
		emails=new ArrayList<Email>(); 
		name=""; 
	}
	/**
	 * Method to set the arrayList of emails for a folder
	 * @param emails
	 * 		the arrayList object of emails for selected folder 
	 * @postcondition 
	 * 		the arrayList object of emails for selected folder is set 
	 */
	public void setEmails(ArrayList<Email> emails)
	{
		this.emails=emails; 
	}
	/**
	 * Method to set the name of a folder 
	 * @param name
	 * 		name of the selected folder 
	 * @postcondition 
	 * 		the name of the selected folder is set 
	 */
	public void setName(String name)
	{
		this.name=name; 
	}
	/**
	 * Method to set the current sorting method of emails for a folder
	 * @param currentSortingMethod
	 * 		emails sorting method for selected folder
	 * @postcondition 
	 * 		the sorting method for selected folder is set  
	 */
	public void setCurrentSortingMethod(String currentSortingMethod)
	{
		this.currentSortingMethod=currentSortingMethod; 
	}
	/**
	 * Method to obtain the arrayList email object of a folder 
	 * @return
	 * 		the arrayList email object of selected folder 
	 */
	public ArrayList<Email> getEmails()
	{
		return this.emails; 
	}
	/**
	 * Method to obtain the name of a folder 
	 * @return
	 * 		the name of selected folder 
	 */
	public String getName()
	{
		return this.name; 
	}
	/**
	 * Method to obtain the sorting method of emails of a folder 
	 * @return
	 * 		the email sorting method of a folder 
	 */
	public String getCurrentSortingMethod()
	{
		return this.getCurrentSortingMethod(); 
	}
	/**
	 * Method to obtain the number of emails for a folder 
	 * @return
	 * 		the number of emails of selected folder 
	 */
	//additional function method
	public int getEmailsSize()
	{
		return this.emails.size(); 
	}
	/**
	 * Method to add an email in a folder 
	 * @param email
	 * 		the email to add in selected folder 
	 * @postcondition 
	 * 		the email is added to the selected folder 
	 */
	public void addEmail(Email email)
	{
	
		emails.add(email);
		if(debug)
		{
			System.out.println(email.getTo()+" is added");
		}
	}
	/**
	 * Method to remove an email from a folder 
	 * @param index
	 * 		the index of email to remove 
	 * @return
	 * 		the removed email from selected folder 
	 */
	public Email removeEmail (int index)
	{
		Email temp=emails.get(index); 
		emails.remove(index);
		return temp; 
	}
	/**
	 * Method to delete all emails permanently from the trash folder 
	 */
	public void clearTrash()
	{
		for(int a=0;a<emails.size();a++)
		{
			emails.remove(a); 
		}
		System.out.println("\nTrash is now empty!\n");
	}
	/**
	 * Method to sort the emails in a folder by subject ascending 
	 */
	public void sortBySubjectAscending()
	{
		System.out.println("Subject Ascending sorting: ");
		Collections.sort(emails,Email.subjectAscendingComparator);
		for(Email temp: emails)
		{
			System.out.println(temp);
		}
	}
	/**
	 * Method to sort the emails in a folder by subject descending 
	 */
	public void sortBySubectDescending()
	{
		System.out.println("Subject descending sorting: ");
		Collections.sort(emails,Email.subjectDescendingComparator);
		for(Email temp: emails)
		{
			System.out.println(temp);
		}
	}
	/**
	 * Method to sort the emails in a folder by date ascending 
	 */
	public void sortByDateAscending()
	{
		System.out.println("Date ascending sorting: ");
		Collections.sort(emails,Email.dateAscendingComparator);
		for(Email temp: emails)
		{
			System.out.println(temp);
		}
	}
	/**
	 * Method to sort the emails in a folder by deate descending 
	 */
	public void sortByDateDescending()
	{
		System.out.println("Date descending sorting: ");
		Collections.sort(emails,Email.dateDescendingComparator); 
		for(Email temp: emails)
		{
			System.out.println(temp);
		}
	}
	
}
