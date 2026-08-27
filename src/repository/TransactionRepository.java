package repository;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import model.TransactionType;
import util.FileManager;

public class TransactionRepository {
     public static ArrayList<Transaction> transactionData = new ArrayList<>();

     public static boolean count = true;
     public static void loadTransactionFile(){
        FileManager.initializeDirectory();
        try {
            List<String> lines = Files.readAllLines(FileManager.initializeTransactionFile());
            if (count ==false) {
                return;
            }
            if (!lines.isEmpty()) {              
                for(int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] lineParts = line.split("\\|");
                    int transactionID = Integer.parseInt(lineParts[0]);
                    int booksID = Integer.parseInt(lineParts[1]);
                    int membersID = Integer.parseInt(lineParts[2]);
                    LocalDate borrowDate = LocalDate.parse(lineParts[3]);
                    LocalDate returnDate = LocalDate.parse(lineParts[4]);
                    TransactionType transactionType = TransactionType.valueOf(lineParts[5]);
                    int lateFee = Integer.parseInt(lineParts[6]) ;
    
                    Transaction data = new Transaction(transactionID, booksID, membersID, borrowDate, returnDate, transactionType,lateFee);
                    transactionData.add(data);
                }
            }
            else System.out.println("No Transaction Found"); 
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        count=false;
    }

     public static void saveTransactionFile() {
        FileManager.initializeDirectory();
        //save transaction attributes
        try(BufferedWriter updateFile = Files.newBufferedWriter(FileManager.initializeMemberFile())) {
            updateFile.write("ID BOOK MEMBER  BORROW_DATE  RETURN_DATE  TYPE  LATE_FEE");
            updateFile.newLine();
            for (Transaction transaction : transactionData) {
                String line = String.valueOf(transaction.getTransactionID())+"|"+transaction.getBookID()+"|"+transaction.getMemberID()+"|"+transaction.getBorrowDate().toString()+"|"+transaction.getReturnDate().toString()+"|"+transaction.getTransactionType().toString()+"|"+transaction.getLateFee();
                updateFile.write(line);
                updateFile.newLine();
            } 
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

    }

}
