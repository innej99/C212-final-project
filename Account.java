import java.util.*;

public class Account {
	
	private String username;
	private String type;
    private String password;
    private Double lateFees;
    private PriorityQueue<Book> lent;
    
    public Account(String user, String pass, String typeFam) {
    	type = typeFam;
    	username = user;
    	password = pass;
    	lateFees = 0.0;
    	lent = new PriorityQueue<Book>();
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public void setUsername(String user) {
    	this.username = user;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String pass) {
    	this.password = pass;
    }
    
    public Double getLateFees() {
        return this.lateFees;
    }
    
    public void payLateFee(double fees) {
    	this.lateFees -= fees;
    	if (lateFees < 0) {
    		lateFees = 0.0;
    	}
    }
    
    public void setLateFee(double fees) {
    	this.lateFees = fees;
    }
    
    public PriorityQueue<Book> getLent() {
        return this.lent;
    }
    
    public void setLent(PriorityQueue<Book> p) {
    	this.lent = p;
    }
    
    public boolean checkBook(Book b, String id) {
    	//NOTE: if checkout is successful, be sure to give the book the lending id given in this method
    	//this must be done for the book stored in lent here and for the one stored in the library
    	//in addition, both must be marked as checked out
    	if (lateFees > 0) {
    		System.out.println("Patrons with an outstanding late fee balance are unable to check out books.");
    		System.out.println("Your current late fee balane is: " + lateFees);
    		return false;
    	} else {
    		Date today = new Date();
	    	b.setChecked(true);
	    	System.out.println("Please select from the following lending duration options:");
	    	System.out.println("1) 1 week");
	    	System.out.println("2) 2 weeks");
	    	System.out.println("3) 1 month");
	    	@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
	    	int choice = 0;
	    	while (true) {
	    		if (scan.hasNextInt()) {
	    			choice = scan.nextInt();
	    			scan.nextLine();
	    			break;
	    		} else {
	    			scan.nextLine();
	    			System.out.println("Please enter only 1, 2, or 3.");
	    		}
	    	}
	    	if (choice == 1) {
	    		b.setAvailable(new Date(today.getTime() + (7 * 1000 * 60 * 60 * 24)));
	    		b.setChecked(true);
	    	} else if (choice == 2) {
	    		b.setAvailable(new Date(today.getTime() + (14 * 1000 * 60 * 60 * 24)));
	    		b.setChecked(true);
	    	} else if (choice == 3) {
	    		b.setAvailable(new Date(today.getTime() + (30 * 1000 * 60 * 60 * 24)));
	    		b.setChecked(true);
	    	}
	    	lent.add(b);
	    	return true;	
    	}
    }
    
    public void returnBook(Book b) {
    	b.setChecked(false);
    	Date today = new Date();
    	b.setAvailable(today);
    	if (today.compareTo(b.getAvailable()) > 0) 
    	{
    		lateFees += (double) (today.getTime() - b.getAvailable().getTime() / (1000*60*60*24));
    	}
        lent.remove(b);
    }
}