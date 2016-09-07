import java.util.Scanner; 
import java.io.Serializable; 
import java.io.*; 
/**
 * The <CODE>EmailDriver</CODE> is a Java Applicaton that interacts with the user for email management 
 * @author rezoanhasib
 * <A> HREF= "mailto:rezoan0308@gmail.com">(rezoan0308@gmail.com)</A>
 */
public class EmailDriver implements Serializable
{
	/**
	 * Main method for user interaction for the emails 
	 * @param args
	 * @throws Exception
	 * 		informs if email management was troubled with appropriate message 
	 */
	public static void main(String[] args) throws Exception
	{
		//checking for an obj file
		Mailbox myObject; 
		try
		{
			FileInputStream file=new FileInputStream("mailbox.obj"); 
			ObjectInputStream fin=new ObjectInputStream(file); 
			myObject=(Mailbox)fin.readObject(); 
			file.close();
			fin.close();
			System.out.println("File loaded from previous.\n");
		} 	catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Previous save not found..");
		}
			catch (ClassNotFoundException b)
		{
			System.out.println("Previous save not found....");
		}
		Scanner input=new Scanner(System.in); 
		String choice; 
		String  newFolderName;
		String folderName; 
		String folderToDelete; 
		Mailbox mailbox=new Mailbox(); 
		Folder currentFolder; //=new Folder(); 
		boolean flag=true; 
		
		while(flag)
		{
			System.out.println("Mailbox:");
			System.out.println("--------\n");
			System.out.println("A- Add folder");
			System.out.println("R- Remove folder");
			System.out.println("C- Compose email");
			System.out.println("F- Open folder");
			System.out.println("I- Open inbox"); 
			System.out.println("T- Open trash");
			System.out.println("E- Empty trash");
			System.out.println("Q- Quit\n");
			System.out.print("Enter a user option: ");
			choice=input.nextLine(); 
			choice=choice.toUpperCase(); 
		
			switch(choice)
			{
				case"A": 
				{
					System.out.println("You have choosen to add a new folder!");
					System.out.print("Enter folder name: ");
					newFolderName=input.nextLine(); 
					currentFolder=new Folder(newFolderName);
					mailbox.addFolder(currentFolder);
					System.out.println("\nMailbox: ");
					System.out.println("--------");
					System.out.println("Inbox"+"\n"+"Trash");
					mailbox.printAllFolders(); 
					break; 
				}
				case"R": 
				{
					System.out.println("You have choosen to remove a folder!");
					System.out.print("Please enter the folder name to remove: ");
					folderToDelete=input.nextLine(); 
					mailbox.deleteFolder(folderToDelete);
					break; 
				}
				case"C": 
				{
					System.out.println("You have choosen to compose an email!");
					mailbox.composeEmail();
					System.out.println("\nEmail successfully added to inbox.\n");
					break; 
				}
				case"F": 
				{
					System.out.println("You have choosen to open a folder!");
					System.out.println("Enter the folder name: ");
					folderName=input.nextLine();
					mailbox.folderSubmenu(folderName); 
					break; 
				}
				case"I": 
				{
					System.out.println("You have choosen to open the inbox folder..");
					mailbox.inboxSubmenu(); 
					break; 
				}
				case"T": 
				{
					System.out.println("You have choosen to open the trash folder..");
					mailbox.trashSubmenu();
					break; 
				}
				case"E": 
				{
					System.out.println("You have chooesen to empty the trash folder..");
					mailbox.clearTrash();
					break; 
				}
				case"Q": 
				{
					myObject=mailbox;  
					try
					{
						FileOutputStream file=new FileOutputStream("mailbox.obj"); 
						ObjectOutputStream fout=new ObjectOutputStream(file); 
						fout.writeObject(myObject);
						fout.close();
						file.close();
					}catch(IOException a){}
					
					System.out.println("Thank you for using our system!");
					System.out.println("Program terminating");
					flag=false; 
					break; 
				}
				default: 
				{
					System.out.println("Choice is not valid! Please try again: ");
					break; 
				}
			}
		}
		input.close(); 
	}
}
