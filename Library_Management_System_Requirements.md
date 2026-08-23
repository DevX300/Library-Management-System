# Library Management System — Console Project Requirements

**Project Type:** Java Console Application  
**Difficulty:** Medium  
**Purpose:** Core Java/OOP learning project  
**Technology:** Java 17+  
**UI:** Console/CLI only  
**Database:** Not required  
**External Libraries:** Not required

## 1. Project Objective

Develop a **console-based Library Management System** using standard Java.

The main purpose of this project is to demonstrate practical understanding of:

- Java basic syntax
- Variables and data types
- Methods
- Classes and objects
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Interfaces/abstract classes
- `static` and `final`
- Enums
- Collections
- Exception handling
- Custom exceptions
- File operations
- Java I/O
- Date and time API
- Streams
- Sorting and filtering
- Basic application architecture

The application must be completely usable from the command line.

---

# 2. Main Features

The system must provide the following menu:

```text
========================================
       LIBRARY MANAGEMENT SYSTEM
========================================

1. Add Book
2. Remove Book
3. List Books
4. Search Book
5. Add Member
6. List Members
7. Borrow Book
8. Return Book
9. Transaction History
10. Save Data
11. Load Data
0. Exit
```

---

# 3. Book Management

## 3.1 Add Book

The user should be able to add a new book.

Required information:

- Book ID
- Title
- Author
- Category
- Status

Book ID should be generated automatically.

New books must initially have:

```text
AVAILABLE
```

Example:

```text
ID    TITLE             AUTHOR          CATEGORY
1     Clean Code        Robert Martin   Programming
```

## 3.2 Remove Book

The user should be able to remove a book using its ID.

Example:

```text
Book ID: 5
```

If the book doesn't exist, an appropriate exception must be generated.

A borrowed book should not be removed.

## 3.3 List Books

Display all books.

Example:

```text
ID    TITLE                     AUTHOR               CATEGORY       STATUS
1     Clean Code                Robert Martin        Programming    AVAILABLE
2     Effective Java            Joshua Bloch         Programming    BORROWED
3     The Hobbit                J.R.R. Tolkien       Fantasy         AVAILABLE
```

---

# 4. Book Searching

The application must support searching books by:

1. Title
2. Author
3. Category
4. Available books
5. Borrowed books

Search should be case-insensitive.

For example:

```text
Search title: java
```

should find:

```text
Effective Java
Java Concurrency in Practice
Head First Java
```

Sorting search results alphabetically is recommended.

---

# 5. Member Management

The system must support two types of members.

```text
Member
   |
   +--- StudentMember
   |
   +--- TeacherMember
```

The base `Member` class should be abstract.

## 5.1 Student Member

A student can borrow a maximum of:

```text
3 books
```

## 5.2 Teacher Member

A teacher can borrow a maximum of:

```text
10 books
```

## 5.3 Add Member

Required information:

- Member ID
- Name
- Email
- Member type

Member ID should be generated automatically.

Example:

```text
Name: John
Email: john@example.com

1. Student
2. Teacher

Member type: 1
```

## 5.4 List Members

Display:

```text
ID    NAME           EMAIL                  TYPE       BORROWED/LIMIT
1     John           john@example.com       STUDENT    2/3
2     Sarah          sarah@example.com      TEACHER    4/10
```

---

# 6. Borrowing Books

The user must be able to borrow a book.

Required:

```text
Book ID
Member ID
```

Example:

```text
Book ID: 10
Member ID: 3
```

Before borrowing, the system must check:

1. Book exists.
2. Member exists.
3. Book is available.
4. Member has not reached the borrowing limit.

If everything is valid:

```text
Book status = BORROWED
```

The book ID must also be added to the member's borrowed-book collection.

A transaction must be created.

---

# 7. Borrowing Period

Different member types have different borrowing periods.

### Student

```text
14 days
```

### Teacher

```text
30 days
```

When a book is borrowed, display:

```text
Borrow date: 2026-08-23
Due date: 2026-09-06
```

Use Java's `LocalDate` rather than manually manipulating dates.

---

# 8. Returning Books

The user must be able to return a book using:

```text
Book ID
Member ID
```

The system must verify:

1. Book exists.
2. Member exists.
3. The member actually borrowed the book.

After returning:

```text
Book status = AVAILABLE
```

The book ID must be removed from the member's borrowed-book collection.

A return transaction must be created.

---

# 9. Late Fee

The system must calculate a late fee.

Required fee:

```text
10 per late day
```

Example:

```text
Due date: 2026-09-06
Return date: 2026-09-09

Late days: 3
Late fee: 30
```

If the book is returned on or before the due date:

```text
Late fee: 0
```

---

# 10. Transaction Management

Every borrowing and returning operation must create a transaction.

Transaction information:

- Transaction ID
- Book ID
- Member ID
- Borrow date
- Return date
- Transaction type
- Late fee

Transaction types:

```text
BORROW
RETURN
```

Use an enum for transaction type.

Example:

```text
ID    BOOK    MEMBER    BORROW DATE    RETURN DATE    TYPE      LATE FEE
1     2       1         2026-08-23     -              BORROW    0
2     2       1         2026-08-23     2026-09-01     RETURN    0
```

---

# 11. Exception Handling

The project must use proper exception handling.

Create custom exceptions for at least:

```text
BookNotFoundException
BookNotAvailableException
MemberNotFoundException
BorrowLimitExceededException
```

Example:

```java
try {
    library.borrowBook(bookId, memberId);
} catch (BookNotAvailableException e) {
    System.out.println(e.getMessage());
}
```

The program must **not crash because of normal user mistakes**.

For example:

```text
Book ID: abc

Please enter a valid number.
```

---

# 12. Collections

The project must demonstrate Java Collections.

Use appropriate collections for different purposes.

### Books

```java
ArrayList<Book>
```

### Members

```java
HashMap<Integer, Member>
```

The member ID should be used as the map key.

### Transactions

```java
ArrayList<Transaction>
```

### Borrowed book IDs

```java
ArrayList<Integer>
```

A `HashSet` may also be used where appropriate, for example for unique categories/tags.

---

# 13. OOP Requirements

The developer must demonstrate proper OOP rather than putting everything inside `Main`.

Required classes include:

```text
Book
Member
StudentMember
TeacherMember
Transaction
Library
```

The following OOP concepts must be demonstrated:

## Encapsulation

Fields should generally be private/protected and accessed through methods.

## Inheritance

```text
Member
 ├── StudentMember
 └── TeacherMember
```

## Abstraction

`Member` should be an abstract class.

## Polymorphism

The application should be able to work with:

```java
Member member;
```

regardless of whether the actual object is:

```text
StudentMember
```

or:

```text
TeacherMember
```

For example:

```java
member.getBorrowLimit();
```

should behave according to the actual member type.

---

# 14. File Persistence

The application must persist data using files.

No database is required.

Create:

```text
data/
├── books.txt
├── members.txt
└── transactions.txt
```

The application must be able to:

## Save

Write current application data to the files.

## Load

Read existing data when the application starts.

---

# 15. File Format

A simple text format should be used.

### books.txt

Example:

```text
1|Clean Code|Robert Martin|Programming|AVAILABLE
2|Effective Java|Joshua Bloch|Programming|BORROWED
```

### members.txt

Example:

```text
1|STUDENT|John Doe|john@example.com|2,5
2|TEACHER|Sarah Smith|sarah@example.com|10
```

### transactions.txt

Example:

```text
1|2|1|2026-08-23||BORROW|0
2|2|1|2026-08-23|2026-09-01|RETURN|0
```

The developer may improve the internal format if necessary, but it must remain a simple file-based solution.

---

# 16. Java I/O Requirements

The project should demonstrate standard Java I/O APIs such as:

```java
BufferedReader
BufferedWriter
FileReader
FileWriter
Path
Files
```

Use **try-with-resources** when working with streams/readers/writers.

Example:

```java
try (BufferedReader reader = ...) {
    ...
}
```

---

# 17. Streams

The project must use Java Stream API in appropriate places.

For example, book searching:

```java
books.stream()
     .filter(...)
     .sorted(...)
     .toList();
```

Streams should be used where they make the code clearer, not simply added unnecessarily.

---

# 18. Sorting

The system should support sorting where appropriate.

Examples:

- Books by title
- Books by author
- Members by ID
- Transactions by ID

Use:

```java
Comparator
```

and/or Stream sorting.

---

# 19. Input Validation

The application must validate user input.

Examples:

### Invalid integer

```text
Book ID: abc

Please enter a valid number.
```

### Empty title

```text
Title:

Title cannot be empty.
```

### Invalid email

```text
Email: hello

Please enter a valid email.
```

### Invalid menu option

```text
Choice: 99

Invalid option.
```

The program must continue running after these errors.

---

# 20. ID Generation

IDs must be generated automatically.

Separate IDs should be maintained for:

```text
Books
Members
Transactions
```

For example:

```text
Book IDs:
1, 2, 3, 4...

Member IDs:
1, 2, 3...

Transaction IDs:
1, 2, 3...
```

IDs must remain unique.

After loading existing files, the application must determine the next available ID.

For example, if the largest book ID is:

```text
25
```

the next book should receive:

```text
26
```

---

# 21. Application Architecture

The developer should not put all functionality inside `Main.java`.

Use a structure similar to:

```text
src/
│
├── Main.java
│
├── model/
│   ├── Book.java
│   ├── Member.java
│   ├── StudentMember.java
│   ├── TeacherMember.java
│   ├── Transaction.java
│   ├── BookStatus.java
│   └── TransactionType.java
│
├── service/
│   ├── Library.java
│   ├── BookService.java
│   ├── MemberService.java
│   └── TransactionService.java
│
├── repository/
│   ├── BookRepository.java
│   ├── MemberRepository.java
│   └── TransactionRepository.java
│
├── exception/
│   ├── BookNotFoundException.java
│   ├── BookNotAvailableException.java
│   ├── MemberNotFoundException.java
│   └── BorrowLimitExceededException.java
│
└── util/
    ├── FileManager.java
    ├── InputValidator.java
    └── IdGenerator.java
```

---

# 22. Main Class Responsibility

`Main.java` should primarily handle:

- Starting the application
- Displaying the menu
- Reading console input
- Calling application/service methods
- Displaying results/errors
- Closing the application

Business logic should not be unnecessarily placed inside `Main`.

---

# 23. Library Class Responsibility

`Library` should act as the main application/facade layer.

It should coordinate:

```text
BookService
MemberService
TransactionService
```

For example:

```java
library.borrowBook(bookId, memberId);
```

rather than having `Main` directly manipulate repositories.

---

# 24. Repository Responsibility

Repositories should manage in-memory data.

For example:

```text
BookRepository
    ↓
Book collection

MemberRepository
    ↓
Member collection

TransactionRepository
    ↓
Transaction collection
```

Repositories should not contain console/UI logic.

---

# 25. Service Responsibility

Services should contain business logic.

For example:

```text
BookService
    → Add/remove/search books

MemberService
    → Add/find members

TransactionService
    → Borrow/return books
    → Borrow limits
    → Late fees
    → Transactions
```

---

# 26. Program Startup

When the program starts:

```text
Start
  ↓
Create data directory if necessary
  ↓
Load books
  ↓
Load members
  ↓
Load transactions
  ↓
Initialize next IDs
  ↓
Show menu
```

If the data files don't exist, the program should start with empty collections.

It must not crash.

---

# 27. Program Shutdown

When the user chooses:

```text
0. Exit
```

the application should:

```text
Save books
Save members
Save transactions
Close Scanner
Exit
```

---

# 28. Error Handling Requirements

File errors must also be handled.

For example:

```text
Could not load books.
Could not save transactions.
```

The application should provide a meaningful error message instead of showing an uncontrolled stack trace to the user.

During development, stack traces may be logged for debugging.

---

# 29. Expected User Flow

A typical session should work like this:

```text
Application starts
        ↓
Load existing data
        ↓
Add Book
        ↓
Add Member
        ↓
List Books
        ↓
Borrow Book
        ↓
Book becomes BORROWED
        ↓
Transaction created
        ↓
Return Book
        ↓
Late fee calculated
        ↓
Book becomes AVAILABLE
        ↓
Return transaction created
        ↓
Save Data
        ↓
Exit
```

---

# 30. Functional Test Cases

The developer should test at least these scenarios.

## Test 1 — Add book

```text
Add a valid book

Expected:
Book created successfully
```

## Test 2 — Search book

```text
Add "Effective Java"
Search "java"

Expected:
Effective Java appears
```

## Test 3 — Invalid book

```text
Search Book ID 9999

Expected:
BookNotFoundException
```

## Test 4 — Borrow available book

```text
Create book
Create member
Borrow book

Expected:
Book = BORROWED
Member contains book ID
Transaction created
```

## Test 5 — Borrow borrowed book

```text
Borrow same book again

Expected:
BookNotAvailableException
```

## Test 6 — Borrow limit

For a student:

```text
Borrow 3 books
Borrow 4th book

Expected:
BorrowLimitExceededException
```

## Test 7 — Return book

```text
Return borrowed book

Expected:
Book = AVAILABLE
Member no longer contains book ID
Return transaction created
```

## Test 8 — Invalid return

```text
Return a book that member never borrowed

Expected:
Error message
```

## Test 9 — Save/load

```text
Add books
Add members
Borrow a book
Exit

Restart application

Expected:
All previous data is restored
```

## Test 10 — Invalid input

```text
Book ID: abc

Expected:
Application continues
```

---

# 31. Technical Restrictions

The project **must not use**:

- Spring Boot
- Hibernate
- JPA
- MySQL
- PostgreSQL
- Firebase
- External libraries
- GUI frameworks
- JavaFX
- Swing
- Web frameworks

This project is specifically intended to demonstrate **core Java**.

---

# 32. Required Java Version

Use:

```text
Java 17+
```

The code should use modern standard Java APIs but avoid unnecessarily complicated features.

---

# 33. Deliverables

The developer must provide:

1. Complete Java source code
2. Correct package structure
3. `README.md`
4. Sample data files
5. Compilation instructions
6. Run instructions
7. Explanation of the project architecture
8. Test cases / testing evidence

The final project should compile and run from the command line without requiring an IDE.

---

# 34. Acceptance Criteria

The project will be considered complete when:

- [ ] Application starts without errors.
- [ ] Books can be added.
- [ ] Books can be removed.
- [ ] Books can be listed.
- [ ] Books can be searched.
- [ ] Members can be created.
- [ ] Students and teachers have different borrowing limits.
- [ ] Books can be borrowed.
- [ ] Borrowed books cannot be borrowed again.
- [ ] Borrow limits are enforced.
- [ ] Books can be returned.
- [ ] Late fees are calculated correctly.
- [ ] Transactions are recorded.
- [ ] Custom exceptions are used.
- [ ] Invalid console input is handled.
- [ ] Data is saved to files.
- [ ] Data is loaded from files.
- [ ] IDs remain unique after restarting.
- [ ] Collections are used appropriately.
- [ ] OOP concepts are clearly demonstrated.
- [ ] Java Stream API is used appropriately.
- [ ] Java Date/Time API is used.
- [ ] Resources are closed using try-with-resources.
- [ ] No external database/library is required.
- [ ] The complete application runs from the command line.

---

# Final Goal

The finished application should feel like a **small but properly structured real-world Java application**, rather than a collection of unrelated Java exercises.

The developer should prioritize:

- Clean OOP design
- Separation of responsibilities
- Exception handling
- Collections
- File-based persistence
- Readable and maintainable code

over adding unnecessary features.
