package repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import exception.MemberNotFoundException;
import model.Member;
import model.StudentMember;
import model.TeacherMember;
import model.memberTYPE;
import util.FileManager;

public class MemberRepository {
    public static ArrayList<Member> memberData = new ArrayList<>();
    public static boolean count = true;

     public static void loadMemberFile() throws MemberNotFoundException {
        FileManager.initializeDirectory();

        try {
            List<String> lines = Files.readAllLines(FileManager.initializeMemberFile());
            if (count ==false) {
                return;
            }
            if (lines.isEmpty()) {
                throw new MemberNotFoundException("No members found in the file.");                
            }
            for(int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] lineParts = line.split("\\|");
                String memberID = lineParts[0];
                String memberName = lineParts[1];
                String memberEmail = lineParts[2];
                String memberType = lineParts[3];
                String[] borrowInfo = lineParts[4].split("/");
                int borrowedCount = Integer.parseInt(borrowInfo[0]);

                
                if (memberType.equals(memberTYPE.STUDENT.toString())) {
                    Member Studentmemberdata = new StudentMember(Integer.parseInt(memberID), memberName, memberEmail, memberType, borrowedCount);
                    memberData.add(Studentmemberdata);
                }
                if (memberType.equals(memberTYPE.TEACHER.toString())) {
                    Member Teachermemberdata = new TeacherMember(Integer.parseInt(memberID), memberName, memberEmail, memberType, borrowedCount);
                    memberData.add(Teachermemberdata);
                }
            }
        } 
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch (MemberNotFoundException e) {
            System.out.println("File is empty: " + e.getMessage());
            System.out.println("Add a new member");
        }
        count=false;
    }

     public static void saveMemberFile() {
        FileManager.initializeDirectory();
        //save member attributes
        try(BufferedWriter updateFile = Files.newBufferedWriter(FileManager.initializeMemberFile())) {
            updateFile.write("ID  NAME     EMAIL                TYPE    BORROW_STATUS");
            updateFile.newLine();
            for (Member member : memberData) {
                String line = String.valueOf(member.getMemberID())+"|"+member.getMemberName()+"|"+member.getMemberEmail()+"|"+member.getMemberType().toString()+"|"+member.showborrowedBooksStatus();  
                updateFile.write(line);
                updateFile.newLine();
            } 
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }


}
