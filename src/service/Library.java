package service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import exception.BookNotAvailableException;
import exception.BookNotFoundException;
import exception.BorrowLimitExceededException;
import exception.MemberNotFoundException;
import model.Book;
import model.Member;
import model.StudentMember;
import model.TeacherMember;
import model.Transaction;
import model.TransactionType;
import model.memberTYPE;
import model.BookStatus;
import repository.BookRepository;
import repository.MemberRepository;
import repository.TransactionRepository;
import util.Idgenerator;

public class Library {
        public static ArrayList<Book> bookData = new ArrayList<>();

        public static void borrowBooks(int bookId, int memberId) throws BookNotFoundException, MemberNotFoundException,BookNotAvailableException, BorrowLimitExceededException{
                boolean bookExists=false;
                boolean memberExists=false;
                boolean bookStatusCheck=false;
                boolean memberBorrowLimitCheck=false;
                boolean isBorrow=false;
                //check 4 categories to borrow a book
                try {
                        for (Book book : bookData) {
                                if (book.getBookID()==bookId) {
                                        bookExists=true;
                                        if (book.getBookStatus().equals(BookStatus.AVAILABLE)) {
                                                bookStatusCheck=true;
                                        }
                                }
                        }
                        if(!bookExists){
                                throw new BookNotFoundException("No Such Books Found with this ID.");
                        }
                        if(bookExists && !bookStatusCheck){
                                throw new BookNotAvailableException("Book is already Borrowed.");
                        }
        
                        for (Member members : MemberRepository.memberData) {
                                if (members.getMemberID()==memberId) {
                                        memberExists=true;
                                        if (members.getMemberType().equals(memberTYPE.STUDENT)) {
                                                if (members.getCount()<3) {
                                                        memberBorrowLimitCheck=true;
                                                }
                                        }
                                        if (members.getMemberType().equals(memberTYPE.TEACHER)) {
                                                if (members.getCount()<10){
                                                        memberBorrowLimitCheck=true;
                                                }
                                        }
                                }
                        }
                        if (!memberExists) {
                                throw new MemberNotFoundException("No such Member found with this ID.");
                        }
                        if (memberExists && !memberBorrowLimitCheck) {
                                throw new BorrowLimitExceededException("The Member has reached their borrowing limit.");
                        }

                        if(bookExists && bookStatusCheck && memberExists && memberBorrowLimitCheck){
                                isBorrow=true;
                        }
                        //if everything check borrow the book and print message
                        if (isBorrow) {       
                                for (Book book : bookData) {
                                        if (book.getBookID()==bookId) {
                                                LocalDate borrowDate = LocalDate.now();
                                                book.setBookStatus(BookStatus.BORROWED.toString());
                                                for (Member member : MemberRepository.memberData) {
                                                        if (member.getMemberID()==memberId) {
                                                                member.setBorrowedBooks(bookId, borrowDate);
                                                        }
                                                }
                                                //adds a new transaction
                                                Idgenerator generateID = new Idgenerator();
                                                Transaction bookBorrowTransaction = new Transaction(generateID.generateTransactionID(), bookId, memberId, borrowDate, null, TransactionType.BORROW, 0);
                                                TransactionRepository.transactionData.add(bookBorrowTransaction);
                                                BookRepository.saveBookFile();
                                                MemberRepository.saveMemberFile();
                                                TransactionRepository.saveTransactionFile();
                                                //Show borrow mesage status
                                                System.out.println("The book with ID:"+ bookId + " has been borowed.");
                                                System.out.println("Borrow date: " + borrowDate);
                                                for (Member member : MemberRepository.memberData) {
                                                        if (member.getMemberID()==memberId) {
                                                                LocalDate dueDate;
                                                                if (member.getMemberType().equals(memberTYPE.STUDENT)) {
                                                                        dueDate= borrowDate.plusDays(StudentMember.STUDENTBORROWBOOKPERIOD);
                                                                        System.out.println("Due Date: " + dueDate );
                                                                }
                                                                if (member.getMemberType().equals(memberTYPE.TEACHER)) {
                                                                        dueDate= borrowDate.plusDays(StudentMember.STUDENTBORROWBOOKPERIOD);
                                                                        System.out.println("Due Date: " + dueDate );
                                                                }
                                                        }
                                                }
                                        }

                                }
                        }
                } catch (BookNotFoundException|BookNotAvailableException|MemberNotFoundException|BorrowLimitExceededException e) {
                        System.out.println(e.getMessage());
                }
        }

        public static int getLateFee(LocalDate borrowDate, LocalDate returnDate, memberTYPE memberType){
                int studentBorrowPeriod= StudentMember.STUDENTBORROWBOOKPERIOD;
                int teacherBorrowPeriod= TeacherMember.TEACHERBORROWBOOKPERIOD;
                int totalBorrowPeriod;
                Period DaysBorrowed= Period.between(borrowDate, returnDate);
                totalBorrowPeriod= DaysBorrowed.getDays();
                int lateFee=0;
                if (memberType.equals(memberTYPE.STUDENT)) {
                        if(totalBorrowPeriod>studentBorrowPeriod){
                                lateFee=(totalBorrowPeriod-studentBorrowPeriod)*10;
                        }
                }
                if (memberType.equals(memberTYPE.TEACHER)) {
                        if (totalBorrowPeriod>teacherBorrowPeriod) {
                                lateFee=(totalBorrowPeriod-teacherBorrowPeriod)*10;
                        }
                }
                return lateFee;
        }

        public static void returnBooks(int bookId, int memberId) throws BookNotFoundException,BookNotAvailableException, MemberNotFoundException{
                boolean bookExists=false;
                boolean memberExists=false;
                boolean bookStatusCheck=false;
                boolean isReturn=false;
                
                try {
                        for (Book book : bookData) {
                                if (book.getBookID()==bookId) {
                                        bookExists=true;
                                        if (book.getBookStatus().equals(BookStatus.BORROWED)) {
                                                bookStatusCheck=true;
                                        }
                                }
                        }
                        if(!bookExists){
                                throw new BookNotFoundException("No Such Books Found with this ID.");
                        }
                        if(bookExists && !bookStatusCheck){
                                throw new BookNotAvailableException("Book is not Borrowed to be returned.");
                        }
        
                        for (Member members : MemberRepository.memberData) {
                                if (members.getMemberID()==memberId) {
                                        memberExists=true;
                                }
                        }
                        if (!memberExists) {
                                throw new MemberNotFoundException("No such Member found with this ID.");
                        }

                        if(bookExists && bookStatusCheck && memberExists){
                                isReturn=true;
                        }
                        //if everything check return the book and print message
                        System.out.println("is everything ok : "+ isReturn);
                        if (isReturn) {       
                                for (Book book : bookData) {
                                        if (book.getBookID()==bookId) {
                                                LocalDate returnDate = LocalDate.now();
                                                LocalDate borrowDate = null;
                                                book.setBookStatus(BookStatus.AVAILABLE.toString());
                                                for (Member member : MemberRepository.memberData) {
                                                        if (member.getMemberID()==memberId) {
                                                                borrowDate =  member.getBorrowDate(bookId);
                                                                member.setReturnBooks(bookId);
                                                                System.out.println(borrowDate);
                                                                Idgenerator generateID = new Idgenerator();
                                                                
                                                                Transaction bookReturnTransaction = new Transaction(
                                                                        generateID.generateTransactionID(),
                                                                         bookId,
                                                                          memberId,
                                                                           borrowDate,
                                                                            returnDate,
                                                                             TransactionType.RETURN,
                                                                              getLateFee(borrowDate,returnDate, member.getMemberType()));
                                                                TransactionRepository.transactionData.add(bookReturnTransaction);
                                                        }
                                                }
                                                //Save Files
                                                BookRepository.saveBookFile();
                                                MemberRepository.saveMemberFile();
                                                TransactionRepository.saveTransactionFile();
                                                //Show borrow mesage status
                                                System.out.println("The book with ID:"+ bookId + " has been returned.");
                                                System.out.println("Return Date: " + returnDate );
                                                for (Member member : MemberRepository.memberData) {
                                                        if (member.getMemberID()==memberId) {
                                                                        System.out.println("Borrow date: " + borrowDate);
                                                        }
                                                }
                                        }

                                }
                        }
                } catch (BookNotFoundException|BookNotAvailableException|MemberNotFoundException e) {
                        System.out.println(e.getMessage());
                }                       
        }
}
