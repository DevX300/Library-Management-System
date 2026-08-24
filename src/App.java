import service.*;
import util.*;
import java.util.Scanner;



public class App {
    public static void main(String[] args) {
        try {
            FileManager.loadBookFile();
        } catch (Exception e) {
            System.out.println("Error loading book file: " + e.getMessage());
        }
        Scanner input = new Scanner(System.in);        
        
        System.out.println("Welcome to the Library Management System!");
        
        while (true){
            System.out.println("\nPlease select an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book by ID");
            System.out.println("3. List books");
            System.out.println("4. Search books");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    // Add a book
                    System.out.print("Enter book title: ");
                    input.nextLine();
                    String bookTitle = input.nextLine();
                    System.out.print("Enter book Author Name: ");
                    
                    String bookAuthor = input.nextLine();
                    System.out.print("Enter book category : ");
                    // input.nextLine();
                    String bookCategory = input.nextLine();
                    // input.next();
                    System.out.println("Adding book: " + bookTitle + ", Author: " + bookAuthor + ", Category: " + bookCategory);
                    BookService.addBook(bookTitle, bookAuthor, bookCategory);
                    break;
                case 2:
                    // Remove a book
                    System.out.print("Enter book ID to remove: ");
                    int bookId = input.nextInt();
                    // input.nextLine();
                    try{
                        BookService.removeBook(bookId);
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    // List books
                    try{
                        BookService.listBook();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    // Search books
                    System.out.println("Search Books by: ");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. Category");
                    System.out.println("4. Available Books");
                    System.out.println("5. Borrowed Books");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    input.nextLine();
                    if(searchChoice == 4 || searchChoice == 5){
                        BookService.searchBook(searchChoice, null);
                        break;
                    }
                    System.out.print("Enter search value: ");
                    String searchValue = input.nextLine();
                    BookService.searchBook(searchChoice, searchValue);
                    break;  
                case 0:
                    // Exit
                    System.out.println("App Closed Successfully");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // input.Close();

    }
}
