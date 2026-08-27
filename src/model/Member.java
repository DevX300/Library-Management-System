package model;

import exception.BorrowLimitExceededException;

public abstract class Member {
    private int memberID;
    private String memberName;
    private String memberEmail;
    private memberTYPE memberType;


    public Member(int memberID, 
        String memberName, 
        String memberEmail,  
        String memberType) 
    {
        this.memberID=memberID;
        this.memberName=memberName;
        this.memberEmail=memberEmail;
        this.memberType=memberTYPE.valueOf(memberType);
    }

    public abstract String showborrowedBooksStatus();
    public abstract void setBorrowBooksCount() throws BorrowLimitExceededException;
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
}
