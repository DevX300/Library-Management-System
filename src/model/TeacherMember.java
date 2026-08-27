package model;
import exception.BorrowLimitExceededException;

public class TeacherMember extends Member{
    private int count=0;
    private static final byte TEACHERBORROWBOOKLIMIT = 10;
    private static final byte TEACHERBORROWBOOKPERIOD = 30;

    private static byte[] TEACHERBORROWEDBOOKSID = {0,0,0,0,0,0,0,0,0,0};
                                                  //0,1,2,3,4,5,6,7,8,9

    public TeacherMember(int memberId, String memberName, String memberEmail,  String memberType){
        super(memberId,memberName,memberEmail,memberType);
    }

    public String showborrowedBooksStatus(){
        String countString= String.valueOf(count);
        String TEACHERBORROWLIMITString= String.valueOf(TEACHERBORROWBOOKLIMIT);
        return countString + "/" + TEACHERBORROWLIMITString;
    }

    public void setBorrowBooksCount() throws BorrowLimitExceededException{
        count = 0;
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if(TEACHERBORROWEDBOOKSID[i]>0){
                if(count>TEACHERBORROWBOOKLIMIT){
                    throw new BorrowLimitExceededException("Borrow Limit exceeded");
                }
                else count++;
            }
        } 
    }


}
