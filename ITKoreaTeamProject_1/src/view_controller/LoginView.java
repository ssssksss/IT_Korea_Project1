package view_controller;

import java.util.Scanner;

import DAO.UserDAO;

public class LoginView {
	String userId;
	String userPwd;
	
	LoginView() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//View
			System.out.println("=== 로그인 화면 입니다. ===");
			System.out.println("아이디 입력:");
			userId = sc.next();
			System.out.println("패스워드 입력:");
			userPwd = sc.next();
			
			//Controller
			UserDAO login = new UserDAO();
			//return 1: 로그인성공, 2: 아이디틀림, 3:비번틀림
			int num = login.userLogin(userId,userPwd);
			if(num==1) {
				System.out.println("로그인 성공");
				new MainView();
			} else if(num==2) {
				System.out.println("아이디가 존재하지 않습니다");
			} else if(num==3) {
				System.out.println("비번이 틀렸습니다.");
			}
		}
	}
}
