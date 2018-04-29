# C212-final-project
Library

README - Group 10

Jenni Munoz, Jason Wang, Ian Polito, Charles Tostaine

CONTENTS
--------
1. WELCOME
2. PACKAGE
3. LOGIN
4. FUNCTIONALITY

WELCOME
-------
Thank you for viewing our library program! This readme will walk you through all the steps you need to know to run our library program. 

Our program consists of four Java classes and one file where all the books information and user's information is stored. For example, the file contains all the books that our library currently holds and is able to be updated by a librarian, whether that be deleting or adding a book.

PACKAGE
-------
Listed below are the files and their types that are included with our program:
- Account.java : Java class
- Book.java : Java class
- Library.java : Java class
- LibraryDriver.java : Java class
- Library.sav : save file w/ library data

LOGIN
-----
The login process is simple, just enter a username and password when prompted. If you do not already have an account in our library system, simply just entering a username and a password will add you to our system. It will then ask you to indicate whether you are a student or a librarian, which will determine the limitations of your account.

FUNCTIONALITY
-------------

Once the user has logged in, they will be able to choose from the eleven options in the start menu.

SEARCH
------

The first option in the menu, Search, allows the user to find books that have the same title, author, or ISBN. 

BORROW BOOK
-----------

The second option in the menu, Borrow book, allows the user to check out a book. It first checks to see if the user has any sort of late penalties. If not, the user is allowed to  check out an available book of a given title.

RETURN BOOK
-----------

The third option in the menu, Return book, allows the user to return a book. This changes the checked status of the book to false and the date of availability to the current system date. If the book is returned past its previous date of availability, the user is charged $1.00 for each day it is late.

PAY LATE FEES
-------------

The fourth option in the menu, Pay late fees, allows the user to pay off any accrued late fees.

SHOW LENT BOOKS 
----------------

The fifth option in the menu, Show lent books, displays a list of all the books that are currently being borrowed by the user, ordered by date of return.

ADD BOOK TO LIBRARY
--------------------

The sixth option in the menu, Add book to Library, allows a Librarian user to add a book to the Library's inventory.

DELETE BOOK FROM LIBRARY
------------------------

The seventh option in the menu, Delete book from Library, allows a Librarian user to remove a book from the Library's inventory.

MODIFY BOOK IN LIBRARY
----------------------

The eighth option in the menu, Modify book in Library, allows a Librarian user to modify the title, author, ISBN, and other information of a book.

DELETE ACCOUNT
--------------

The ninth option in the menu, Delete account, allows a Librarian user to delete an account from the Library. This user will have to create a new account in order to use the system.

DISPLAY ALL BOOKS
-----------------

The tenth option in the menu, Display all books, allows a user to see all the books currently held by the Library at the time as well as their information such as title, author, checked status, etc.

DISPLAY LENT BOOK STATUS
------------------------

The eleventh option in the menu, Display lent book status, allows a Librarian user to view the status of all books currently being borrowed by students.

QUIT
----

The final option in the menu, Quit, terminates the program and saves all data to a file called Library.sav
