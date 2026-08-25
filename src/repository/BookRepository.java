package repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import exception.BookNotFoundException;
import model.Book;
import util.FileManager;

public class BookRepository {

    public static ArrayList<Book> bookData = new ArrayList<>();

    public static void loadBookFile() throws BookNotFoundException {
        FileManager.initializeDirectory();

        try {
            List<String> lines = Files.readAllLines(FileManager.initializeBookFile());
            if (lines.isEmpty()) {
                throw new BookNotFoundException("No books found in the file.");                
            }
            for(int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] lineParts = line.split(",");
                String bookID = lineParts[0];
                String bookTitle = lineParts[1];
                String bookAuthor = lineParts[2];
                String bookCategory = lineParts[3];
                String bookStatus = lineParts[4];
                Book book = new Book(Integer.parseInt(bookID), bookTitle, bookAuthor, bookCategory, bookStatus);
                BookRepository.bookData.add(book);
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch (BookNotFoundException e) {
            System.out.println("File is empty: " + e.getMessage());
            System.out.println("Add a new Book");
        }
    }

     public static void saveBookFile() {
        FileManager.initializeDirectory();

        try(BufferedWriter updateFile = Files.newBufferedWriter(FileManager.initializeBookFile())) {
            updateFile.write("ID,  TITLE,      AUTHOR,    CATEGORY,   STATUS");
            updateFile.newLine();
            for (Book book : BookRepository.bookData) {
                String line = String.valueOf(book.getBookID())+","+book.getBookTitle()+","+book.getBookAuthor()+","+book.getBookCategory()+","+book.getBookStatus().toString();
                updateFile.write(line);
                updateFile.newLine();
            } 
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

    }
}
