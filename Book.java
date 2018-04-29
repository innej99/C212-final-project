import java.util.*;

public class Book implements Comparable {
	
    private String title;
    private String author;
    private Boolean checked;
    private String isbn;
    private Date available;
    private String lendID;
    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.checked = false;
        this.isbn = isbn;
        this.lendID = "";
        this.available = new Date();
    }
    // getters and setters
    
    // TITLE // 
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    
    // AUTHOR //
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
    		this.author = author;
    }
    
    // CHECKED //
    public Boolean getChecked() {
        return this.checked;
    }
    
    public void setChecked(Boolean checked) {
    	this.checked = checked;
    }
    
    // ISBN //
    public String getISBN() {
        return this.isbn;
    }
    
    public void setISBN(String isbn) {
    		this.isbn = isbn;
    }
    
    // AVAILABLE //
    public Date getAvailable() {
        return this.available;
    }
    
    @SuppressWarnings("deprecation")
	public void setAvailable(String d) {
    	this.available = new Date(d);
    }
    
    public void setAvailable(Date d) {
		this.available = d;
    }
    
    // LEND ID // 
    public String getLendID() {
        return this.lendID;
    }
    
    public void setLendID(String lendID) {
    	this.lendID = lendID;
    }
    
    @Override
    public String toString() {
    	String result;
    	if (!checked) {
    		result = this.title + " by " + this.author + " " + this.isbn + " and is available for checkout.";
    	} else {
    		result = this.title + " by " + this.author + " " + this.isbn + " and is not available for checkout." + "\nIt will be available on " + this.available;
    	}
    	return result;
    }

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof Book) {
			Book b = (Book) arg0;
			if (available.before(b.available)) {
				return -1;
			} else if (available.after(b.available)){
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
}