package model;

import java.time.LocalDate;

import exception.BorrowLimitExceededException;

public abstract class Member {
    private int memberID;
    private String memberName;
    private String memberEmail;
    private memberTYPE memberType;
    private int borrowedCount;


    public Member(int memberID, 
        String memberName, 
        String memberEmail,  
        String memberType,
        int borrowCount) 
    {
        this.memberID=memberID;
        this.memberName=memberName;
        this.memberEmail=memberEmail;
        this.memberType=memberTYPE.valueOf(memberType);
        this.borrowedCount=borrowCount;
    }

    public abstract String showborrowedBooksStatus();
    public abstract void setBorrowedBooks(int bookId, LocalDate borrowDate) throws BorrowLimitExceededException;
    public abstract LocalDate getBorrowDate(int bookID);
    public abstract void setReturnBooks(int bookID);
    // Setters
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberTYPE.valueOf(memberType);
    }

    public void setBorrowedCount(int count){
        this.borrowedCount=count;
    }
    
    
    // Getters
    public int getMemberID() {
        return memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public memberTYPE getMemberType() {
        return memberType;
    }

    public int getBorrowedCount(){
        return borrowedCount;
    }
}
