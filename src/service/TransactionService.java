package service;

import model.Transaction;
import repository.TransactionRepository;

public class TransactionService {
    public static void transactionHistory(){
   
            if(Library.bookData.isEmpty()){
                System.out.println("No Transactions has been done.");
            }
            TransactionRepository.loadTransactionFile();
            for(Transaction transactions : TransactionRepository.transactionData){
                showTransactions(transactions);
            }

    }

    public static void showTransactions(Transaction transactions){
        String returnDateString = (transactions.getReturnDate() == null)? "NONE" : transactions.getReturnDate().toString();
        System.out.println("[ Transaction ID: " + transactions.getTransactionID() + " | Book Id: " + transactions.getBookID() + " |Member Id: " + transactions.getMemberID() + " | Borrow Date: " + transactions.getBorrowDate().toString() + " | Return Date: " + returnDateString+ " | Transaction Type: " + transactions.getTransactionType()+ " | Late Fee: " + transactions.getLateFee()+" ]");
    }
}
