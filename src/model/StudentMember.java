package model;
import java.time.LocalDate;

public class StudentMember extends Member{
    private static final byte STUDENTBORROWBOOKLIMIT = 3;
    public static final byte STUDENTBORROWBOOKPERIOD = 15;

    //ID of books here and 0 for no books
    private static int[] STUDENTBORROWEDBOOKSID = {0,0,0};
                                                  //0,1,2
    private static LocalDate[] STUDENTBORROWEDBOOKSDATE = new LocalDate[3];

    public LocalDate getBorrowDate(int bookID){
        LocalDate borrrowedBookIndex = null;
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if (STUDENTBORROWEDBOOKSID[i]==bookID) {
                borrrowedBookIndex= STUDENTBORROWEDBOOKSDATE[i];
            }
        }
        return borrrowedBookIndex;
    }
    
    public StudentMember(int memberId, String memberName, String memberEmail,  String memberType){
        super(memberId,memberName,memberEmail,memberType);
    }

    @Override
    public String showborrowedBooksStatus(){
        String countString= String.valueOf(getCount());
        String STUDENTBORROWLIMITString= String.valueOf(STUDENTBORROWBOOKLIMIT);
        return countString + "/" + STUDENTBORROWLIMITString;
    }


    public void setBorrowedBooks(int bookID, LocalDate borrowDate){
        setCount(0);
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if (STUDENTBORROWEDBOOKSID[i]==0) {
                STUDENTBORROWEDBOOKSID[i]=bookID;
                STUDENTBORROWEDBOOKSDATE[i]=borrowDate;
                break;
            }
        }
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if(STUDENTBORROWEDBOOKSID[i]>0){
                setCount(getCount()+1);
            }
        } 
    }

    
    public void setReturnBooks(int bookID){
        setCount(0);
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if (STUDENTBORROWEDBOOKSID[i]==bookID) {
                STUDENTBORROWEDBOOKSID[i]=0;
                STUDENTBORROWEDBOOKSDATE[i]=null;
                break;
            }
        }
        for (int i = 0; i < STUDENTBORROWEDBOOKSID.length; i++) {
            if(STUDENTBORROWEDBOOKSID[i]>0){
                setCount(getCount()-1);
            }
        } 
    }
    

}
