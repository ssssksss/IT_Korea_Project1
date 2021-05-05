package view_controller;

import java.util.Scanner;

import DAO.ManagerDAO;

public class ManagerLoginView {
	String managerId;
	String managerPwd;
	public ManagerLoginView() {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("=== 관리자 화면입니다.===");
			System.out.print("아이디:");
			managerId=sc.next();
			System.out.println("비밀번호 입력:");
			managerPwd=sc.next();
			
			//controller
			ManagerDAO managerDAO=new ManagerDAO();
			//return 1: 로그인 2. 아이디틀림 3 비번틀림
			int num=managerDAO.managerLogin(managerId, managerPwd);
			if(num==1) {
				System.out.println("로그인 성공!"); 
				new ManagerServiceView();
				break;
			}
			else if(num==2) {
				System.out.println("아이디가 틀렸습니다.");
			}
			else if(num==3) {
				System.out.println("비번이 틀렸습니다.");
			}
			else if(num==4) {
				System.out.println("종료");
				break;
			}
			else {
				System.out.println("잘못된 번호를 입력하였습니다.");
			}
		}
	}
}
