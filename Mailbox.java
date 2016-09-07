import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * The <CODE>Mailbox</CODE> is a Java Applicaton that creates different folders to contain emails inside them
 * @author rezoanhasib
 * <A> HREF= "mailto:rezoan0308@gmail.com">(rezoan0308@gmail.com)</A>
 */
public class Mailbox extends ArrayList<Email> implements Serializable 
{
	private Folder inbox=new Folder("inbox"); 
	private Folder trash=new Folder("trash"); 
	private ArrayList<Folder> folders; 
	public static Mailbox mailbox; 
	Scanner input=new Scanner(System.in); 
	private String to;
	private String cc;
	private String bcc;
	private String subject; 
	private String body;
	private Email currentEmail; 
	boolean debug=false; 
	private GregorianCalendar cal;
	private String userOption; 
	private int emailIndex; 
	private String destFolder; 
	/**
	 * Constructor to create Mailbox objects 
	 */
	public Mailbox()
	{
		folders=new ArrayList<Folder>(); 
	}
	/**
	 * Method to add a new folder to the mailbox 
	 * @param folder
	 * 		the folder to add to mailbox 
	 * @throws Exception
	 * 		informs if the folder already exists in the list
	 * @postcondition 
	 * 		the folder is added to the folder list in the mailbox 
	 */
	public void addFolder(Folder folder) throws Exception
	{
		boolean flag=true; 
		int index=0; 
		if(folders.size()!=0)
		{
			while(flag)
			{
				if((folders.get(index).getName()).equals(folder))
					throw new Exception("Folder already exists!!"); 
				index++;
				if(index==folders.size())
					flag=false; 
			}
		}
		folders.add(folder); 
	}
	/**
	 * Method to delete a folder from mailbox 
	 * @param name
	 * 		the name of the folder to delete 
	 * @postcondition 
	 * 		the folder is deleted from mailbox 
	 */
	public void deleteFolder(String name)
	{
		if(debug)
		{
			System.out.println(folders.get(0).getName()+" "+folders.get(0).getName().length()); ;
			System.out.println(name+" "+name.length());
		}
		try
		{
			//folders.remove(name);
			for(int a=0;a<folders.size();a++)
			{
				if(folders.get(a).getName().equals(name))
					folders.remove(a); 
			}
		}catch(Exception e)
		{
			System.out.println("Email- "+name+" does not exist in the custom folder!");
			e.printStackTrace();
		}
		if(debug)
		{
			if(folders.size()!=0)
			{
				for(int a=0;a<folders.size();a++)
				{
					System.out.println(folders.get(a).getName());
				}
			}
		}
	}
	/**
	 * Method to compose a new email 
	 */
	public void composeEmail()
	{
		System.out.println("Enter recipient (To): ");
		to=input.nextLine(); 
		System.out.println("Enter carbon copy recipients (CC): ");
		cc=input.nextLine(); 
		System.out.println("Enter blind carbon copy recipients (BCC): "); 
		bcc=input.nextLine(); 
		System.out.println("Enter subject line: "); 
		subject=input.nextLine(); 
		System.out.println("Enter body: "); 
		body=input.nextLine();
		cal=new GregorianCalendar(); 
		
		System.out.println("\nEntered information: \n");
		System.out.println("To: "+to);
		System.out.println("Cc: "+cc);
		System.out.println("Bcc: "+bcc);
		System.out.println("Subject: "+subject); 
		System.out.println("Body: "+body);		
		/*
		currentEmail.setTo(to);
		currentEmail.setCc(cc);
		currentEmail.setBcc(bcc);
		currentEmail.setSubject(subject);
		currentEmail.setBody(body);
		*/
		currentEmail=new Email(to,cc,bcc,subject,body,cal); 
		inbox.addEmail(currentEmail); 	
	}
	/**
	 * Method to delete an email from a folder 
	 * @param email
	 * 		the email to delete 
	 * @postcondition 
	 * 		the email is deleted from selected folder and moved to trash 
	 */
	public void deleteEmail(Email email)
	{
		trash.addEmail(email);
		
	}
	/**
	 * Method to delete all emails parmanently from the trash 
	 */
	public void clearTrash()
	{
		trash.clearTrash();
	}
	/**
	 * Method to move one email from one folder to another 
	 * @param email
	 * 		the email to move 
	 * @param target
	 * 		the target folder of selected email 
	 * @postconditon 
	 * 		the email is moved from source to the target folder 
	 */
	public void moveEmail(Email email,Folder target)  
	{
		try
		{
			target.addEmail(email); 
		}catch(Exception e)
		{
			inbox.addEmail(email);
			e.printStackTrace(); 
		}
	}
	/**
	 * Method to obtain a folder 
	 * @param name
	 * 		the name of the folder 
	 * @return
	 * 		the folder with the selected name 
	 */
	public Folder getFolder(String name)
	{
		for(int a=0;a<folders.size();a++)
		{
			if(this.folders.get(a).getName()==name)
				return folders.get(a); 
		}
		return null; 
	}
	/**
	 * Method to print all the custom folders 
	 * @postcondition 
	 * 		all the custom folders are printed 
	 */
	public void printAllFolders() 
	{
		for(int a=0;a<folders.size();a++)
		{
			System.out.println(folders.get(a).getName());
		}
		System.out.print("\n");
	}
	/**
	 * Method to operate the sub menu on a folder 
	 * @param name
	 * 		the name of the folder 
	 * @throws Exception
	 * 		informs if folder doesn't exist 
	 */
	public void folderSubmenu(String name) throws Exception
	{
		int index,emailsIndex; 
		if(folders.size()==0)
			throw new EmptyFolderException("Folder is empty at this time!"); 
		for(index=0;index<folders.size();index++)
		{
			if((folders.get(index).getName()).equals(name))
				break; 
			if(index==(folders.size()-1))
				throw new Exception(name+" is not found in the folder list!"); 
		}
		System.out.println(name+":\n");
		System.out.println("Index |\t\t Time\t\t   | Subject");
		System.out.println("-----------------------------------------------");
		for(emailsIndex=0;emailsIndex<folders.get(index).getEmailsSize();emailsIndex++)
		{
			System.out.printf("%-7d%-30s%-14s",(emailsIndex+1),(folders.get(index).getEmails().get(emailsIndex).getTimestamp().getTime()),(folders.get(index).getEmails().get(emailsIndex).getSubject()));
		}
		if(debug)
			System.out.println(folders.get(index).getEmailsSize());
		submenuOperation(folders.get(index)); 
	}
	/**
	 * Method to operate menu options on inbox 
	 */
	public void inboxSubmenu()
	{
		int index; 
		
		System.out.println("\nInbox:");
		System.out.println("------\n");
		System.out.println("Index |\t\t Time\t\t   | Subject");
		System.out.println("-----------------------------------------------");
		for(index=0;index<inbox.getEmailsSize();index++)
		{
			System.out.printf("%-7d%-30s%-14s",(index+1),(inbox.getEmails().get(index).getTimestamp().getTime()),(inbox.getEmails().get(index).getSubject()));
			System.out.print("\n");
		}
		if(debug)
			System.out.println(inbox.getEmails().size());
		//printSubmenu();
		//System.out.print("Enter a user option: ");
		//userOption=input.nextLine();
		//userOption=userOption.toUpperCase();	
		submenuOperation(inbox);
		/*
		System.out.println("\nInbox:");
		System.out.println("------\n");
		if(inbox.getEmailsSize()==0)
			System.out.println("Inbox is empty.");
		else
		{
			System.out.println("Index |\tTime\t| Subject\t");
			System.out.println("--------------------------------\n");
			for(index=0;index<inbox.getEmailsSize();index++)
			{
				System.out.printf("%3d%-13s%10s",(index+1),(inbox.getEmails().get(index).getTimestamp().toString()),(inbox.getEmails().get(index).getSubject()));
				System.out.print("\n");
			}
		}
		*/
	}
	/**
	 * Method to print the sub menu of a folder
	 * @postcondition 
	 * 		the sub menu of a folder is printed
	 */
	public void printSubmenu()
	{
		System.out.println("\nOption:");
		System.out.println("-------\n");
		System.out.println("M- Move email");
		System.out.println("D- Delete email");
		System.out.println("V- View email contents");
		System.out.println("SA- Sort by subject line in ascending order");
		System.out.println("SD- Sort by subject line in descending order");
		System.out.println("DA- Sort by date in ascending order");
		System.out.println("DD- Sort by date in descending order");
		System.out.println("R- Return to mailbox\n");
	}
	/**
	 * Method to perform the sub menu operation of a folder 
	 * @param from
	 * 		the folder of which the sub menu operation is being done 
	 */
	public void submenuOperation(Folder from)
	{
		boolean flag=true; 
		while(flag)
		{
			printSubmenu();
			System.out.print("Enter a user option: ");
			userOption=input.nextLine();
			userOption=userOption.toUpperCase(); 

			switch(userOption)
			{
				case"M": 
				{
					//taking the index which is 1 more than the actual
					boolean status=true;
					while(status)
					{
						try
						{
							System.out.println("Enter the index of email to move: ");
							emailIndex=input.nextInt();
							input.nextLine();
							status=false; 
						}catch(Exception e)
						{
							e.printStackTrace();
							System.out.println("Index has to be a digit!");
							input.nextLine();
							//System.out.println("Please enter the digit index: ");
							//input.nextLine();
						}
					}
					//adjusting the email index with the actual index
					emailIndex--; 
					System.out.println("Folders:\nInbox\nTrash");
					for(int a=0;a<folders.size();a++)
					{
						System.out.println(folders.get(a).getName());
					}
				
					System.out.println("Enter the name of the folder to move this email to: ");
					destFolder=input.nextLine();
				
					if(destFolder.equals("inbox"))
					moveEmail(from.removeEmail(emailIndex),inbox);
					else if(destFolder.equals("trash"))
						moveEmail(from.removeEmail(emailIndex),trash); 
					else
					{
						int number; 
						for(number=0;number<folders.size();number++)
						{
							if(folders.get(number).getName().equals(destFolder))
								break; 
						}
						//cencelling operation if destination folder not found 
						if(number==folders.size())
						{
							System.out.println("Destination folder is not found in the mailbox!");
							System.out.println("Going back to main menu!");
							break; 
						}
						System.out.println(from.getEmails().get(emailIndex).getSubject()+" successfuly moved to "+destFolder);
						moveEmail(from.removeEmail(emailIndex),folders.get(number)); 
					}
					System.out.println("\n"+from.getName()+":\n");
					if(from.getEmails().size()==0)
					System.out.println(from.getName()+" is empty.");
					else
					{
						System.out.println("Index |\t\t Time\t\t   | Subject");
						System.out.println("-----------------------------------------------");
						for(int index=0;index<from.getEmailsSize();index++)
						{
							System.out.printf("%-7d%-30s%-14s",(index+1),(from.getEmails().get(index).getTimestamp().getTime()),(from.getEmails().get(index).getSubject()));
							System.out.print("\n");	
						}
					}
					break; 
				}
				case"D": 
				{
					//user entering the index which is 1 more than the actual
					boolean status=true;
					int number = 0; 
					while(status)
					{
						try
						{
							System.out.println("Enter the email index to delete: ");
							number=input.nextInt(); 
							input.nextLine();
							status=false; 
						}catch(Exception e)
						{
							e.printStackTrace();
							input.nextLine(); 
							System.out.println("Index input is invalid!");
						}
					}
					//adjusting the number to the actual index
					number--; 
					System.out.println(from.getEmails().get(number).getSubject()+" is moved to the trash.");
					moveEmail(from.removeEmail(number),trash);
					System.out.println("\n"+from.getName()+":\n");
					if(from.getEmails().size()==0)
						System.out.println(from.getName()+" is empty.");
					else
					{
						System.out.println("Index |\t\t Time\t\t   | Subject");
						System.out.println("-----------------------------------------------");
						for(int index=0;index<from.getEmailsSize();index++)
						{
							System.out.printf("%-7d%-30s%-14s",(index+1),(from.getEmails().get(index).getTimestamp().getTime()),(from.getEmails().get(index).getSubject()));
							System.out.print("\n");	
						}
					}
					break; 
				}
				case"V": 
				{
					boolean status=true;
					int index = 0; 
					while(status)
					{
						try
						{
							System.out.println("Enter the email index: ");
							index=input.nextInt(); 
							input.nextLine();
							status=false; 
						}catch(Exception e)
						{
							e.printStackTrace();
							input.nextLine(); 
							System.out.println("Index input is invalid!");
						}
					} 
					//adjusting the number to the actual index
					index--; 
					System.out.println("To: "+from.getEmails().get(index).getTo());
					System.out.println("CC: "+from.getEmails().get(index).getCc());
					System.out.println("BCC: "+from.getEmails().get(index).getBcc());
					System.out.println("Subject: "+from.getEmails().get(index).getSubject());
					System.out.println("Body: "+from.getEmails().get(index).getBody());
					System.out.println("\n"+from.getName()+":\n");
					System.out.println("Index |\t\t Time\t\t   | Subject");
					System.out.println("-----------------------------------------------");
					for(int a=0;a<from.getEmailsSize();a++)
					{
						System.out.printf("%-7d%-30s%-14s",(a+1),(from.getEmails().get(a).getTimestamp().getTime()),(from.getEmails().get(a).getSubject()));
						System.out.print("\n");
					}
					break; 
				}
				case"SA": 
				{
					System.out.println("Emails are being sorted by subject ascending..");
					from.sortBySubjectAscending();
					break; 
				}
				case"SD": 
				{
					System.out.println("Emails are being sorted by subject descending..");
					from.sortBySubectDescending();
					break; 
				}
				case"DA": 
				{
					System.out.println("Emails are being sorted by date ascending: ");
					from.sortByDateAscending();
					break; 
				}
				case"DD": 
				{
					System.out.println("Emails are being sorted by date descending: ");
					from.sortByDateDescending();
					break; 
				}
				case"R": 
				{
					System.out.println("\nMailbox:");
					System.out.println("--------\n");
					System.out.println("Inbox\nTrash"); 
					for(int a=0;a<folders.size();a++)
					{
						System.out.println(folders.get(a).getName());
					}
					System.out.println("\nReturning to the main menu\n");
					flag=false; 
					break; 
				}
				default: 
				{
					System.out.println("Invalid choice!");
					System.out.println("Please try again from the option menu..");
					break; 
				}
			}
		}
	}
	/**
	 * Method to perform the sub menu operation of the trash 
	 */
	public void trashSubmenu()
	{
		int index; 
		
		System.out.println("\nTrash:");
		System.out.println("------\n");
		System.out.println("Index |\t\t Time\t\t   | Subject");
		System.out.println("-----------------------------------------------");
		if(trash.getEmailsSize()==0)
		{
			System.out.println("Trash is empty at this time!");
			System.out.println("Returning to main menu..\n");
			return; 
		}
		else
		{
			for(index=0;index<trash.getEmailsSize();index++)
			{
				System.out.printf("%-7d%-30s%-14s",(index+1),(trash.getEmails().get(index).getTimestamp().getTime()),(trash.getEmails().get(index).getSubject()));
				System.out.print("\n");
			}
		}
		if(debug)
			System.out.println(trash.getEmails().size());
		submenuOperation(trash); 
	}
	
	private Date timeInfo(GregorianCalendar time) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(cal.getTime());
		// Output "Wed Sep 26 14:23:28 EST 2012"
		Date formatted=time.getTime(); 
		//String formatted = format1.format(time.getTime());
		//System.out.println(formatted);
		return formatted;
	}
}
