package service;

import model.Book;
import util.*;
import repository.BookRepository;
import exception.*;
import java.util.Scanner;
public class BookService {
    
    public static void addBook(String bookTitle, String bookAuthor,String bookCategory) {
        Idgenerator idGenerator = new Idgenerator();
        int bookID = idGenerator.generateBookID();
        // adds a new book to the library
        Book newbook = new Book(bookID, bookTitle, bookAuthor, bookCategory, "AVAILABLE");

        // Save the book to the repository
        Library.bookData.add(newbook);
        
        //save the new book data
        BookRepository.saveBookFile();
        System.out.println("Book added successfully with ID: " + newbook.getBookID());

    }

    public static void removeBook(int bookID) throws BookNotFoundException, BookNotAvailableException {
        Book deleteID;
        boolean notFound = true;
        if(Library.bookData.isEmpty()){
            System.out.println("No books available to remove.");
            return;
        }
        for(Book book : Library.bookData){
            if(book.getBookID() == bookID && book.getBookStatus() == model.BookStatus.AVAILABLE){
                deleteID = book;
                Library.bookData.remove(deleteID);
                System.out.println("Book removed successfully with ID: " + bookID);
                BookRepository.saveBookFile();
                listBook();
                return;
            }
            try{
                if(book.getBookID() == bookID && book.getBookStatus() == model.BookStatus.BORROWED){
                    throw new BookNotAvailableException("Book with ID " + bookID + " is currently borrowed and cannot be removed.");
                }
            }
            catch(BookNotAvailableException e){
                System.out.println(e.getMessage());
            }
        }
        try{
            if(notFound){
                throw new BookNotFoundException("Book with ID " + bookID + " not found.");
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    public static void listBook() throws BookNotFoundException{
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            for(Book book : Library.bookData){
                showBooks(book);
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void searchBook(int searchFilter){
        String searchValue;
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        switch(searchFilter){
            case 1:
                System.out.print("Search by Title:");
                searchValue = input.nextLine();
                searchBookbyTitle(searchValue);
                break;
            case 2:
                System.out.print("Search by Author:");
                searchValue = input.nextLine();
                searchBookbyAuthor(searchValue);
                break;
            case 3:
                System.out.print("Search by Category:");
                searchValue = input.nextLine();
                searchBookbyCategory(searchValue);
                break;
            case 4:
                System.out.println("Search Available Books");
                searchAvailableBook();
                break;
            case 5:
                System.out.println("Search Borrowed Books");
                searchBorrowedBook();
                break;
            default:
                System.out.println("Invalid search filter.");
        }
    }

    public static void searchBookbyTitle(String searchValue){
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            System.out.println("searchnamemethod==" + searchValue);

            Library.bookData
            .stream()
            .filter(book -> book.getBookTitle().toLowerCase().contains(searchValue.toLowerCase()))
            .forEach(book ->showBooks(book));
        }
        catch(BookNotFoundException e){
            System.out.println("Search Error: " + e.getMessage());
        }
    }
    public static void searchBookbyAuthor(String searchValue){
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            Library.bookData
            .stream()
            .filter(book -> book.getBookAuthor().toLowerCase().contains(searchValue.toLowerCase()))
            .forEach(book ->showBooks(book));
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void searchBookbyCategory(String searchValue){
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            Library.bookData
            .stream()
            .filter
            (book -> book.getBookCategory().toLowerCase().contains(searchValue.toLowerCase()))
            .forEach
            (book ->showBooks(book));
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void searchAvailableBook(){
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            Library.bookData
            .stream()
            .filter(book -> book.getBookStatus() == model.BookStatus.AVAILABLE)
            .forEach(book -> showBooks(book));
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void searchBorrowedBook(){
        try{
            if(Library.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            Library.bookData
            .stream()
            .filter(book -> book.getBookStatus() == model.BookStatus.BORROWED)
            .forEach(book -> showBooks(book));  
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    public static void showBooks(Book book){
        System.out.println("[ ID: " + book.getBookID() + " | Title: " + book.getBookTitle() + " | Author: " + book.getBookAuthor() + " | Category: " + book.getBookCategory() + " | Status: " + book.getBookStatus()+" ]");
    }
}
