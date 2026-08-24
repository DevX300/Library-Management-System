package service;

import model.Book;
import util.*;
import repository.BookRepository;
import exception.*;
public class BookService {
    
    public static void addBook(String bookTitle, String bookAuthor,String bookCategory) {
        Idgenerator idGenerator = new Idgenerator();
        int bookID = idGenerator.generateBookID();
        // adds a new book to the library
        Book newbook = new Book(bookID, bookTitle, bookAuthor, bookCategory, "AVAILABLE");

        // Save the book to the repository
        BookRepository.bookData.add(newbook);
        
        //save the new book data
        FileManager.saveBookFile();
        System.out.println("Book added successfully with ID: " + newbook.getBookID());

    }

    public static void removeBook(int bookID) throws BookNotFoundException, BookNotAvailableException {
        Book deleteID;
        boolean notFound = true;
        if(BookRepository.bookData.isEmpty()){
            System.out.println("No books available to remove.");
            return;
        }
        for(Book book : BookRepository.bookData){
            if(book.getBookID() == bookID && book.getBookStatus() == model.BookStatus.AVAILABLE){
                deleteID = book;
                BookRepository.bookData.remove(deleteID);
                System.out.println("Book removed successfully with ID: " + bookID);
                // notFound = false;
                //save the new book data
                FileManager.saveBookFile();
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
            if(BookRepository.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            for(Book book : BookRepository.bookData){
                System.out.println("ID: " + book.getBookID() + ", Title: " + book.getBookTitle() + ", Author: " + book.getBookAuthor() + ", Category: " + book.getBookCategory() + ", Status: " + book.getBookStatus());
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void searchBook(int searchFilter, String searchValue){
        switch(searchFilter){
            case 1:
                System.out.println("Search by Title");
                searchBookbyTitle(searchValue);
                break;
            case 2:
                System.out.println("Search by Author");
                searchBookbyAuthor(searchValue);
                break;
            case 3:
                System.out.println("Search by Category");
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

    public static void searchBookbyTitle(String bookTitle){
        try{
            if(BookRepository.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            for(Book book : BookRepository.bookData){

                if(book.getBookTitle().equalsIgnoreCase(bookTitle)){
                    System.out.println("ID: " + book.getBookID() + ", Title: " + book.getBookTitle() + ", Author: " + book.getBookAuthor() + ", Category: " + book.getBookCategory() + ", Status: " + book.getBookStatus());
                }
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void searchBookbyAuthor(String bookAuthor){
        
    }
    public static void searchBookbyCategory(String bookCategory){
        
    }
    public static void searchAvailableBook(){
        try{
            if(BookRepository.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            for(Book book : BookRepository.bookData){
                if(book.getBookStatus() == model.BookStatus.AVAILABLE){
                    System.out.println("ID: " + book.getBookID() + ", Title: " + book.getBookTitle() + ", Author: " + book.getBookAuthor() + ", Category: " + book.getBookCategory() + ", Status: " + book.getBookStatus());
                }
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void searchBorrowedBook(){
        try{
            if(BookRepository.bookData.isEmpty()){
                throw new BookNotFoundException("No books available in the library.");
            }
            for(Book book : BookRepository.bookData){
                if(book.getBookStatus() == model.BookStatus.BORROWED){
                    System.out.println("ID: " + book.getBookID() + ", Title: " + book.getBookTitle() + ", Author: " + book.getBookAuthor() + ", Category: " + book.getBookCategory() + ", Status: " + book.getBookStatus());
                }
            }
        }
        catch(BookNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
