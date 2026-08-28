package service;

import exception.MemberNotFoundException;
import model.*;
import repository.MemberRepository;
import util.Idgenerator;

public class MemberService {

    private MemberService (){};

    public static void addMember(String memberName, String memberEmail,int memberTypeInitialize) {
        Idgenerator idGenerator = new Idgenerator();
        int memberID = idGenerator.generateMemberID();
        
        String memberType=null;
        if(memberTypeInitialize==1){
            memberType=memberTYPE.STUDENT.toString();
            MemberRepository.memberData.add(new StudentMember(memberID, memberName, memberEmail, memberType));
        } 
        
        if(memberTypeInitialize==2){
            memberType=memberTYPE.TEACHER.toString();
            MemberRepository.memberData.add(new TeacherMember(memberID, memberName, memberEmail, memberType));
        } 
        
        // Save the book to the repository
        
        //save the new member data
        MemberRepository.saveMemberFile();
        System.out.println("Member added successfully with ID: " + memberID);
    }
    
    public static void listMembers(){
        try{
            if(MemberRepository.memberData.isEmpty()){
                throw new MemberNotFoundException("No Members Found ");
            }
            for(Member book : MemberRepository.memberData){
                showMembers(book);
            }
        }
        catch(MemberNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void showMembers(Member member){
        System.out.println("[ ID: " + member.getMemberID() + " | Name: " + member.getMemberName() + " | Email: " + member.getMemberEmail() + " | Type: " + member.getMemberType()+" |"+member.showborrowedBooksStatus()+ " ]");
    }
}
