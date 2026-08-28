package model;
import java.time.LocalDate;

public class TeacherMember extends Member{
    private static final byte TEACHERBORROWBOOKLIMIT = 10;
    public static final byte TEACHERBORROWBOOKPERIOD = 30;

    //ID of books here and 0 for no books
    private static int[] TEACHERBORROWEDBOOKSID = {0,0,0,0,0,0,0,0,0,0};
                                                  //0,1,2,3,4,5,6,7,8,9
    private static LocalDate[] TEACHERBORROWEDBOOKSDATE = new LocalDate[10];

    public LocalDate getBorrowDate(int bookID){
        LocalDate borrrowedBookIndex =null;
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if (TEACHERBORROWEDBOOKSID[i]==bookID) {
                borrrowedBookIndex= TEACHERBORROWEDBOOKSDATE[i];
            }
        }
        return borrrowedBookIndex;
    }

    public TeacherMember(int memberId, String memberName, String memberEmail,  String memberType){
        super(memberId,memberName,memberEmail,memberType);
    }

    @Override
    public String showborrowedBooksStatus(){
        String countString= String.valueOf(getCount());
        String TEACHERBORROWLIMITString= String.valueOf(TEACHERBORROWBOOKLIMIT);
        return countString + "/" + TEACHERBORROWLIMITString;
    }


    public void setBorrowedBooks(int bookID, LocalDate borrowDate){
        setCount(0);
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if (TEACHERBORROWEDBOOKSID[i]==0) {
                TEACHERBORROWEDBOOKSID[i]=bookID;
                TEACHERBORROWEDBOOKSDATE[i]=borrowDate;
                break;
            }
        }
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if(TEACHERBORROWEDBOOKSID[i]>0){
                setCount(getCount()+1);
            }
        } 
    }

    public void setReturnBooks(int bookID){
        setCount(0);
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if (TEACHERBORROWEDBOOKSID[i]==bookID) {
                TEACHERBORROWEDBOOKSID[i]=0;
                TEACHERBORROWEDBOOKSDATE[i]=null;
                break;
            }
        }
        for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
            if(TEACHERBORROWEDBOOKSID[i]>0){
                setCount(getCount()-1);
            }
        } 
    }

}
