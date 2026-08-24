package util;
// import java.io.*;
// import java.nio.file.*;
// import java.util.*;
// import exception.BookNotFoundException;
import repository.BookRepository;

public class Idgenerator {
    
    //BookID generator
    public static int generateNewBookID=0;
    public int generateBookID() {
        // try {
        //     // FileManager.loadBookFile();
        // } catch (BookNotFoundException e) {
        //     System.out.println("Book not found: " + e.getMessage());
        // }
        generateNewBookID = BookRepository.bookData.getLast().getBookID() + 1;
        return generateNewBookID;
    }


    //MemberID generator
}
