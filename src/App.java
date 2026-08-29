import java.util.Scanner;
import util.FileManager;
import util.InputValidator;
import exception.*;
import service.*;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("..............Welcome............");
            FileManager.LoadFiles();
        } catch (Exception e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
        System.out.println("===============================================================");
        System.out.println(":                   Library Management System                 :");
        System.out.println("===============================================================");
        System.out.println
                            ("Please select an option:\n"+
                            "1. Add a book\n"+
                            "2. Remove a book by ID\n"+
                            "3. List books\n"+
                            "4. Search books\n"+
                            "5. Add Member\n"+
                            "6. List Members\n"+
                            "7. Borrow Book\n"+
                            "8. Return Book\n"+
                            "9. Transaction History\n"+ 
                            "10.Save Data\n"+
                            "11.Load Data"+
                            "\n0. Exit");
        int choice;
        while (true){
            Scanner input = new Scanner(System.in);        
            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; 
            }
            switch (choice){
                case 1:
                    // Add a book
                    System.out.println(".......Add a new Book.....");

                    System.out.print("Enter book title: ");
                    input.nextLine();
                    String bookTitle = input.nextLine();

                    System.out.print("Enter book Author Name: ");
                    String bookAuthor = input.nextLine();

                    System.out.print("Enter book category : ");
                    String bookCategory = input.nextLine();
                   
                    try {
                        BookService.addBook(bookTitle, bookAuthor, bookCategory);
                        System.out.println("Adding book: " + bookTitle + ", Author: " + bookAuthor + ", Category: " + bookCategory);
                    } catch (InputValidator e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    // Remove a book
                    System.out.print("Enter book ID to remove: ");
                    try {
                        int bookId = input.nextInt();
                        try {
                            BookService.removeBook(bookId);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case 3:
                    // List books
                    try{
                        System.out.println("Book List: ");
                        BookService.listBook();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    // Search books
                    System.out.println("\nSearch Books by: ");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. Category");
                    System.out.println("4. Available Books");
                    System.out.println("5. Borrowed Books");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    input.nextLine();
                    BookService.searchBook(searchChoice, input);
                    break; 
                case 5:
                    System.out.print("Enter Your Name: ");
                    input.nextLine();
                    String memberName = input.nextLine();

                    System.out.print("Enter Email: ");
                    String memberEmail = input.nextLine();

                    System.out.println("Press:");
                    System.out.println("1. Student Member");
                    System.out.println("2. Teacher Member");
                    System.out.print("Choose MemberType: ");
                    // validate member input
                    
                    int memberType = input.nextInt();
                    if(memberType==1||memberType==2){
                        try {
                            MemberService.addMember(memberName, memberEmail, memberType);
                            if (memberType==1)  System.out.println("Adding new Student: " + memberName + ", Email: " + memberEmail);
                            if (memberType==2)  System.out.println("Adding new Teacher: " + memberName + ", Email: " + memberEmail);
                        } catch (InputValidator e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    else{
                        System.out.println("Choose valid memberType");
                        break;
                    }
                case 6:
                    System.out.println("Members List: ");
                    MemberService.listMembers();
                    break;
                case 7:
                    System.out.println(".....Borrow a book from the library....");
                    System.out.print("Enter Book Id: ");

                    try {
                        input.nextLine();
                        int BookID = input.nextInt();
                        System.out.print("Enter your Member Id: ");
                        int memberID = input.nextInt();
                        try {
                            Library.borrowBooks(BookID, memberID);
                        } catch (BookNotFoundException | MemberNotFoundException | BookNotAvailableException
                                | BorrowLimitExceededException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case 8:
                    System.out.println(".....Return your Book.....");
                    System.out.print("Enter Book Id: ");
                    try {
                        input.nextLine();
                        int returnBookID = input.nextInt();
    
                        System.out.print("Enter your Member Id: ");
                        int returnMemberID = input.nextInt();
                        
                        try {
                            Library.returnBooks(returnBookID, returnMemberID);
                        } catch (BookNotFoundException | BookNotAvailableException | MemberNotFoundException e) {
                            System.out.println("Error: "+ e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;
                case 9:
                    System.out.println("Transaction History: ");
                    TransactionService.transactionHistory();
                    break;
                case 10:
                    FileManager.SaveFiles();
                    break;
                case 11:
                    FileManager.LoadFiles();
                    break;
                case 0:
                    // Exit
                    FileManager.SaveFiles();
                    System.out.println("App Closed Successfully");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
