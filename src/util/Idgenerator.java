package util;

import repository.*;
import service.Library;

public class Idgenerator {
    
    //BookID generator
    public static int generateNewBookID=1;
    public int generateBookID() {
        if (!Library.bookData.isEmpty()) {
            generateNewBookID = Library.bookData.getLast().getBookID() + 1;
        }
        return generateNewBookID;
    }
    
    // MemberID generator
    public static int generateNewMemberID=1;
    public int generateMemberID() {
        if (!MemberRepository.memberData.isEmpty()) {
            generateNewMemberID = MemberRepository.memberData.getLast().getMemberID() + 1;
        }
        return generateNewMemberID;
    }

    public static int generateNewTransactionID=1;
    public int generateTransactionID() {
        if (!TransactionRepository.transactionData.isEmpty()) {
            generateNewTransactionID = TransactionRepository.transactionData.getLast().getTransactionID() + 1;
        }
        return generateNewTransactionID;
    }
    
}
