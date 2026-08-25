import service.*;
// import util.*;
import java.util.Scanner;

import repository.BookRepository;



public class App {
    public static void main(String[] args) {
        try {
            BookRepository.loadBookFile();
        } catch (Exception e) {
            System.out.println("Error loading book file: " + e.getMessage());
        }
        System.out.println("===============================================================");
        System.out.println(":                   Library Management System                 :");
        System.out.println("===============================================================");
        int choice;
        while (true){
            Scanner input = new Scanner(System.in);        
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

            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextInt(); 
                continue; 
            }
            // choice = input.nextInt();
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
                    System.out.println("\nSearch Books by: ");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. Category");
                    System.out.println("4. Available Books");
                    System.out.println("5. Borrowed Books");
                    System.out.print("Enter your choice: ");
                    int searchChoice = input.nextInt();
                    // input.nextLine();
                    if(searchChoice == 4 || searchChoice == 5){
                        BookService.searchBook(searchChoice);
                        break;
                    }
                    BookService.searchBook(searchChoice);
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
