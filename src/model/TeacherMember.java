package model;

public class TeacherMember extends Member{
    public static final byte TEACHERBORROWBOOKLIMIT = 10;
    public static final byte TEACHERBORROWBOOKPERIOD = 30;

    
    public TeacherMember(int memberId, String memberName, String memberEmail,  String memberType, int borrowedCount){
        super(memberId,memberName,memberEmail,memberType, borrowedCount);
    }

    @Override
    public String showborrowedBooksStatus(){
        String countString= String.valueOf(getBorrowedCount());
        String TEACHERBORROWLIMITString= String.valueOf(TEACHERBORROWBOOKLIMIT);
        return countString + "/" + TEACHERBORROWLIMITString;
    }
    
    public void setBorrowedBooks(){
                setBorrowedCount(getBorrowedCount()+1);
    }

    public void setReturnBooks(){
                setBorrowedCount(getBorrowedCount()-1);
    }

    //Not storing borrowedBookID and borrowing date in array collection because it gets deleted if program restarted instead borrowing date and return date is found from transaction file
    //ID of books here and 0 for no books
    // private static int[] TEACHERBORROWEDBOOKSID = {0,0,0,0,0,0,0,0,0,0};
                                                  //0,1,2,3,4,5,6,7,8,9
    // private static LocalDate[] TEACHERBORROWEDBOOKSDATE = new LocalDate[10];

    // LocalDate borrrowedBookIndex;
    // public LocalDate getBorrowDate(int bookID){
    //     for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
    //         if (TEACHERBORROWEDBOOKSID[i]==bookID) {
    //             borrrowedBookIndex= TEACHERBORROWEDBOOKSDATE[i];
    //         }
    //     }
    //     return borrrowedBookIndex;
    // }

    // public void setBorrowedBooks(int bookID, LocalDate borrowDate){
    //     for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
    //         if (TEACHERBORROWEDBOOKSID[i]==0) {
    //             TEACHERBORROWEDBOOKSID[i]=bookID;
    //             TEACHERBORROWEDBOOKSDATE[i]=borrowDate;
    //             setBorrowedCount(getBorrowedCount()+1);
    //             break;
    //         }
    //     }
    // }
    
    // public void setReturnBooks(int bookID){
    //     for (int i = 0; i < TEACHERBORROWEDBOOKSID.length; i++) {
    //         if (TEACHERBORROWEDBOOKSID[i]==bookID) {
    //             TEACHERBORROWEDBOOKSID[i]=0;
    //             TEACHERBORROWEDBOOKSDATE[i]=null;
    //             setBorrowedCount(getBorrowedCount()-1);
    //             break;
    //         }
    //     }
    // }

}
