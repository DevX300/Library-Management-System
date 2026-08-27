package model;

import java.time.LocalDate;

public class Transaction {
    private int transactionID;
    private int bookID;
    private int memberID;
    private LocalDate borrowDate;
    // private LocalDate returnDate;
    private LocalDate returnDate;
    private TransactionType transactionType;
    private int lateFee;


    public Transaction(int transactionID, 
        int bookID, 
        int memberID,  
        LocalDate borrowDate,
        LocalDate returnDate,
        TransactionType transactionType,
        int lateFee) 
    {
        this.transactionID=transactionID;
        this.bookID=bookID;
        this.memberID=memberID;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
        this.transactionType=transactionType;
        this.lateFee=lateFee;
    }


    //transaction get methods
    public int getTransactionID() {
        return transactionID;
    }

    public int getBookID() {
        return bookID;
    }

    public int getMemberID() {
        return memberID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getLateFee(){
        return lateFee;
    }

    //transaction set methods
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
