package io.member;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;
import io.member.impl.ObjectMemberRepository;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberConsoleMain {

//  private static final MemberRepository repository = new MemoryMemberRepository();
//  private static final MemberRepository repository = new FileMemberRepository();
//  private static final MemberRepository repository = new DataMemberRepository();
  private static final MemberRepository repository = new ObjectMemberRepository();

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    while(true){
      System.out.println("1.회원등록 | 2.회원 목록 조회 | 3.종료");
      System.out.println("선택: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); //newLine제거

      switch(choice){
        case 1:
          registerMember(scanner);
          break;
        case 2:
          displayMembers();
          break;
        case 3:
          System.out.println("프로그램을 종료합니다");
          return;
        default:
          System.out.println("잘못된 선택입니다. 다시 입력하세요.");

      }


    }
  }

  private static void registerMember(Scanner scanner) throws IOException {
    System.out.println("ID 입력:");
    String id = scanner.nextLine();

    System.out.println("Name 입력:");
    String name = scanner.nextLine();

    System.out.println("Age 입력: ");
    int age = scanner.nextInt();
    scanner.nextLine();

    Member member = new Member(id,name,age);

    repository.add(member);
    System.out.println("회원이 성공적으로 등록됨");
  }


  private static void displayMembers() {
    final List<Member> members = repository.findAll();
    System.out.println("회원 목록:");
    for(Member member : members){
      System.out.printf("[ID: %s, Name: %s,Age: %d]\n",member.getId(),member.getName(),member.getAge());
    }
  }

}