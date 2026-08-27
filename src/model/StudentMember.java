package model;

import exception.BorrowLimitExceededException;

public class StudentMember extends Member{
    private int count=0;
    private static final byte STUDENTBORROWBOOKLIMIT = 3;
    private static final byte STUDENTBORROWBOOKPERIOD = 15;

    //ID of books here and 0 for no books
    private static byte[] STUDENTBORROWEDBOOKSID = {0,0,0};
                                                  //0,1,2
    
    public StudentMember(int memberId, String memberName, String memberEmail,  String memberType){
        super(memberId,memberName,memberEmail,memberType);
    }

    public String showborrowedBooksStatus(){
        String countString= String.valueOf(count);
        String TEACHERBORROWLIMITString= String.valueOf(STUDENTBORROWBOOKLIMIT);
        return countString + "/" + TEACHERBORROWLIMITString;
    }

    public void setBorrowBooksCount() throws BorrowLimitExceededException{
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if(STUDENTBORROWEDBOOKSID[i]>0){
                if(count>STUDENTBORROWBOOKLIMIT){
                    throw new BorrowLimitExceededException("Borrow Limit exceeded");
                }
                else count++;
            }
        } 
    }

    

}
