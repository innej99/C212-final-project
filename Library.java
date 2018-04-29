import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Library  {

    private ArrayList<Book> inventory;
    private ArrayList<Account> accounts;
    
    public Library() {
    	inventory = new ArrayList<Book>();
    	accounts = new ArrayList<Account>();
    }
    
    public ArrayList<Book> getInventory() {
        return inventory;
    }
    
    public void addToInventory()  {
    	String title;
    	String author;
    	String isbn;
    	@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
    	System.out.println("Enter the title, author and ISBN of the new book.");
    	System.out.println("Title: ");
    	title = s.nextLine();
    	System.out.println("Author: ");
    	author = s.nextLine();
    	System.out.println("ISBN: ");
    	isbn = s.nextLine();
    	Book b = new Book(title,author,isbn);
    	inventory.add(b);
    }
    
    public void removeInventory() {
        //get the book that should be removed and remove it
    	String input;
    	@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
    	System.out.println("Enter the title or ISBN of the book to be removed.");
    	input = s.nextLine();
    	for (int i = 0; i < inventory.size(); i++) {
    		Book b = inventory.get(i);
    		if (b.getTitle().equals(input) || b.getISBN().equals(input)) {
    			if (!(b.getChecked())) {
    				inventory.remove(b);
    				System.out.println("Book removed successfully.");
    				break;
    			} else {
    				System.out.println("Book is currently checked out and cannot be removed.");
    				break;
    			}
    		}
    	}
    }
    
    public void addAccount(Account a) {
        accounts.add(a);
    }
    
    public void removeAccount() {
    	//get the account that should be removed and remove it
    	String user;
    	boolean found = false;
    	@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
    	System.out.println("Please enter the username of the account to be removed.");
    	user = s.nextLine();
    	for (int i = 0; i < accounts.size(); i++) {
    		Account a = accounts.get(i);
    		if (a.getUsername().equals(user)) {
    			accounts.remove(a);
    			found = true;
    			System.out.println("Account removed successfully.");
    			break;
    		}
    	}
    	if (found == false) {
    		System.out.println("Account not found in system.");
    	}
    }
    
    public void modifyBook() {
    	String input;
    	Book b = null;
    	@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
    	System.out.println("Please enter the ISBN of the book to be modified.");
    	input = s.nextLine();
    	for (int i = 0; i < inventory.size(); i++) {
    		if (inventory.get(i).getISBN().equals(input)) {
    			b = inventory.get(i);
    			break;	
    		}
    	}
    	if (b == null) {
    		System.out.println("Book not found.");
    		return;
    	}
    	System.out.println("Would you like to modify the Title, Author or ISBN?");
    	String input2 = s.nextLine().toLowerCase();
    	if (input2.equals("title")) {
    		System.out.println("Please input the new title.");
    		int spot = inventory.indexOf(b);
    		inventory.get(spot).setTitle(s.nextLine());
    		System.out.println("Book modified.");
    	} else if (input2.equals("author")) {
    		System.out.println("Please input the new author.");
    		int spot = inventory.indexOf(b);
    		inventory.get(spot).setAuthor(s.nextLine());
    		System.out.println("Book modified.");
    	} else if (input2.equals("isbn")) {
    		System.out.println("Please input the new ISBN.");
    		int spot = inventory.indexOf(b);
    		inventory.get(spot).setISBN(s.nextLine());
    		System.out.println("Book modified.");
    	} else {
    		System.out.println("Invalid Selection.");
    	}
    }
    
    public void search() {
        String input;
        @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
        System.out.println("Enter either the title, author, or ISBN to search.");
        input = s.nextLine();
        System.out.println("Fetching results...");
        for (int i = 0; i < inventory.size(); i++) {
        	Book b = inventory.get(i);
        	if (b.getTitle().equals(input) || b.getAuthor().equals(input) || b.getISBN().equals(input) || b.getTitle().contains(input) || b.getAuthor().contains(input)) {
        		System.out.println(b);
        	}
        }
    }
    
    //function creates a random string to be used as a LendID
    public String createLendID() {
        String result = "";
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        result = new String(array, Charset.forName("UTF-8"));
        return result;
    }
    
    public void load() {
    	File temp = new File("Library.sav");
    	if (!temp.exists()) {
    		return;
    	}
    	try {
            Scanner s = new Scanner(new File("Library.sav"));
            while (s.hasNextLine()) {
                String input = s.nextLine();
                if (input.equals("[BOOK]")) {
                	String title = s.nextLine();
                	String author = s.nextLine();
                	Boolean checked = s.nextBoolean();
                	s.nextLine();
                	String isbn = s.nextLine();
                	String date = s.nextLine();
                	String id = s.nextLine();
                	Book b = new Book(title, author, isbn);
                	b.setChecked(checked);
                	b.setAvailable(date);
                	b.setLendID(id);
                	inventory.add(b);
                } else if (input.equals("[ACCOUNT]")) {
                    String user = s.nextLine();
                    String pass = s.nextLine();
                    String type = s.nextLine();
                    Double fee = s.nextDouble();
                    s.nextLine();
                    String books = s.nextLine();
                    Account a = new Account(user,pass,type);
                    a.setLateFee(fee);
                    PriorityQueue<Book> p = new PriorityQueue<Book>();
                    if (!(books.equals("EMPTY"))) {
                    	while (true) {
                    		String title = s.nextLine();
                        	String author = s.nextLine();
                        	Boolean checked = s.nextBoolean();
                        	s.nextLine();
                        	String isbn = s.nextLine();
                        	String date = s.nextLine();
                        	String id = s.nextLine();
                        	Book b = new Book(title, author, isbn);
                        	b.setChecked(checked);
                        	b.setAvailable(date);
                        	b.setLendID(id);
                        	a.getLent().add(b);
                    		if ((s.nextLine()).equals("EMPTY")) {
                    			break;
                    		}
                    	}
                    }
                    a.setLent(p);
                    accounts.add(a);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
    	try {
            PrintStream out = new PrintStream(new File("Library.sav"));
            //save books
            for (int i = 0; i < inventory.size(); i++) {
                out.println("[BOOK]");
                Book b = inventory.get(i);
                out.println(b.getTitle());
                out.println(b.getAuthor());
                out.println(b.getChecked());
                out.println(b.getISBN());
                out.println(b.getAvailable());
                out.println(b.getLendID());
            }
            //save accounts
            for (int i = 0; i < accounts.size(); i++) {
                out.println("[ACCOUNT]");
                Account a = accounts.get(i);
                out.println(a.getUsername());
                out.println(a.getPassword());
                out.println(a.getType());
                out.println(a.getLateFees());
                //save lent books
                PriorityQueue<Book> p = a.getLent();
                if (p.size() == 0) {
                	out.println("EMPTY");
                } else {
                	for (int j = 0; j < p.size(); j++) {
                		out.println("[BOOK]");
                		Book b = p.remove();
                		out.println(b.getTitle());
                		out.println(b.getAuthor());
                		out.println(b.getChecked());
                		out.println(b.getISBN());
                		out.println(b.getAvailable());
                		out.println(b.getLendID());
                	}
                	out.println("EMPTY");
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Account login(String user, String pass) {
        for (int i = 0; i < accounts.size(); i++) {
        	Account a = accounts.get(i);
        	if (a.getUsername().equals(user) && a.getPassword().equals(pass)) {
        		return a;
        	}
        }
        return null;
    }
    
    public void start() {
    	Account logged;
    	int loggedIndex;
    	Scanner s = new Scanner(System.in);
    	load();
    	while (true) {
    		System.out.println("Welcome to the Library. Please enter a username and password to login.");
    		System.out.println("If you do not have an account enter a username and password and one will be made.");
    		System.out.println("Username: ");
    		String user;
    		String pass;
    		while (true) {
    			if (s.hasNext()) {
    				user = s.next();
    				break;
    			} else {
    				s.nextLine();
    			}
    		}
    		System.out.println("Password: ");
    		while (true) {
    			if (s.hasNext()) {
    				pass = s.next();
    				break;
    			} else {
    				s.nextLine();
    			}
    		}
    		logged = login(user,pass);
    		loggedIndex = accounts.indexOf(logged);
    		if (logged != null) {
    			break;
    		} else {
    			int input;
    			while (true) {
    				System.out.println("Making new account, are you a");
        			System.out.println("1. Student");
        			System.out.println("2. Librarian");
    				if (s.hasNextInt()) {
    					input = s.nextInt();
    					if (input == 1 || input == 2) {
    						break;
    					}
    				} else {
    					s.nextLine();
    				}
    			}
    			if (input == 1) {
    				logged = new Account(user,pass, "Student");
    				accounts.add(logged);
    				loggedIndex = accounts.indexOf(logged);
    				break;
    			} else {
    				logged = new Account(user,pass,"Librarian");
    				accounts.add(logged);
    				loggedIndex = accounts.indexOf(logged);
    				break;
    			}
    		}
    	}
    	//we're logged in
    	while (true) {
    		int input;
    		System.out.println("Please select an option.");
    		System.out.println("1. Search");
    		System.out.println("2. Borrow book");
    		System.out.println("3. Return book");
    		System.out.println("4. Pay late fees");
    		System.out.println("5. Show lent books");
    		System.out.println("6. Add book to Library");
    		System.out.println("7. Delete book from Library");
    		System.out.println("8. Modify book in Library");
    		System.out.println("9. Delete account");
    		System.out.println("10. Display all Books");
    		System.out.println("11. Display all lent Books");
    		System.out.println("12. Quit");
    		if (s.hasNextInt()) {
    			input = s.nextInt();
    			s.nextLine();
    			if (input == 1) {
    				//search books
    				search();
    			} else if (input == 2) {
    				//borrow book
    				String temp;
    		        System.out.println("Enter the title or ISBN of the book you wish to borrow.");
    		        temp = s.nextLine();
    		        for (int i = 0; i < inventory.size(); i++) {
    		        	Book b = inventory.get(i);
    		        	if (b.getTitle().equals(temp) || b.getISBN().equals(temp) && b.getChecked() == false) {
    		        		boolean checkout = logged.checkBook(b, createLendID());
    		        		if (checkout) {
    		        			System.out.println("Book checked out.");
    		        			inventory.get(i).setChecked(true);
    		        		} else {
    		        			System.out.println("Book not checked out.");
    		        		}
    		        	}
    		        }
    			} else if (input == 3) {
    				//return book
    				String temp;
    				System.out.println("Enter the title or ISBN of the book you wish to return.");
    		        temp = s.nextLine();
    		        PriorityQueue<Book> lent = logged.getLent();
    		        for (int i = 0; i < lent.size(); i++) {
    		        	Book b = lent.remove();
    		        	if (b.getTitle().equals(temp) || b.getISBN().equals(temp)) {
    		        		//we need to return this book
    		        		b.setChecked(false);
    		        		b.setAvailable(new Date());
    		        		inventory.get(i).setChecked(false);
    		        		inventory.get(i).setAvailable(new Date());
    		        		logged.returnBook(b);
    		        		System.out.println("Book returned.");
    		        	}
    		        }
    			} else if (input == 4) {
    				//pay late fees
    				if (logged.getLateFees() > 0.0) {
    					System.out.println("Your total fees: " + logged.getLateFees());
    					System.out.println("How much would you like to pay?");
    					double temp = s.nextDouble();
    					logged.payLateFee(temp);
    					accounts.set(loggedIndex, logged);
    				} else {
    					System.out.println("You have no late fees.");
    				}
    			} else if (input == 5) {
    				//show lent books
    				PriorityQueue<Book> lent = logged.getLent();
    				System.out.println("List of checked out books...");
    				for (int i = 0; i < lent.size(); i++) {
    					Book b = lent.remove();
    					System.out.println(b);
    				}
    			} else if (input == 6) {
    				//add book to library
    				if (logged.getType().equals("Librarian")) {
    					addToInventory();
    				} else {
    					System.out.println("You are not authorized to perform this action.");
    				}
    			} else if (input == 7) {
    				//delete book from library
    				if (logged.getType().equals("Librarian")) {
    					removeInventory();
    				} else {
    					System.out.println("You are not authorized to perform this action.");
    				}
    			} else if (input == 8) {
    				//modify book in library
    				if (logged.getType().equals("Librarian")) {
    					modifyBook();
    				} else {
    					System.out.println("You are not authorized to perform this action.");
    				}
    			} else if (input == 9) {
    				//delete account
    				if (logged.getType().equals("Librarian")) {
    					removeAccount();
    				} else {
    					System.out.println("You are not authorized to perform this action.");
    				}
    			} else if (input == 10) {
    				//display all books
    				for (int i = 0; i < inventory.size(); i++) {
    					Book b = inventory.get(i);
    					System.out.println(b);
    				}
    			} else if (input == 11) {
    				//display all lent books
    				if (logged.getType().equals("Librarian")) {
    					for (int i = 0; i < inventory.size(); i++) {
    						Book b = inventory.get(i);
    						if (b.getChecked()) {
    							System.out.println(b);
    						}
    					}
    				} else {
    					System.out.println("You are not authorized to perform this action.");
    				}
    			} else if (input == 12) {
    				//quit
    				save();
    				System.out.println("Exiting...");
    				break;
    			} else {
    				System.out.println("Invalid option.");
    			}
    		} else {
    			s.nextLine();
    		}
    	}
    	s.close();
    }
}