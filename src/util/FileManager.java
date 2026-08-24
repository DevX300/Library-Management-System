package util;

import exception.BookNotFoundException;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import model.Book;
import repository.BookRepository;

public class FileManager {

    //Book File Manager

    public static void loadBookFile() throws BookNotFoundException {
        Path file = Path.of("data/books.txt");
        try {
            List<String> lines = Files.readAllLines(file);
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
        }
    }


    public static void saveBookFile() {
        Path file = Path.of("data/books.txt");

        try(BufferedWriter updateFile = Files.newBufferedWriter(file)) {
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

    //Member File Manager

}
