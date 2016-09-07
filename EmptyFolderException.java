import java.io.Serializable;

/**
 * The <CODE>EmailDriver</CODE> is a Java Exception class that informs if a selected folder is empty and 
 * some operation is trying to be done on that  
 * @author rezoanhasib
 * <A> HREF= "mailto:rezoan0308@gmail.com">(rezoan0308@gmail.com)</A>
 */
public class EmptyFolderException extends Exception implements Serializable {
	/**
	 * Constructor for the exception class 
	 * @param message
	 * 		message to be displayed with the exception 
	 */
	public EmptyFolderException(String message)
	{
		super(message); 
	}

}
