package util;

import java.io.*;
import java.nio.file.*;

public class FileManager {
    
    private FileManager() {}
    private final static String DATA_DIR = "data";

    public static void initializeDirectory(){
        Path folder = Path.of(DATA_DIR);
        try {
            Files.createDirectories(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //Book File Manager
    private final static String BOOK_FILE = "data/books.txt";

    public static Path initializeBookFile(){
        Path file = Path.of(BOOK_FILE);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Did not create File:"+ e.getLocalizedMessage());
            }
        }
        return file;
    }


    //Member File Manager
    private final static String MEMBER_FILE = "data/members.txt";

    public static Path initializeMemberFile(){
        Path file = Path.of(MEMBER_FILE);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Did not create File:"+ e.getLocalizedMessage());
            }
        }
        return file;
    }


    //Transaction File Manager
    private final static String TRANSACTION_FILE = "data/transactions.txt";

    public static Path initializeTransactionFile(){
        Path file = Path.of(TRANSACTION_FILE);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                System.out.println("Did not create File:"+ e.getLocalizedMessage());
            }
        }
        return file;
    }
}
