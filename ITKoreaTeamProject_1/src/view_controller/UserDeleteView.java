package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;

public class UserDeleteView {
	String userId;
	String userPwd;
	
	UserDeleteView() throws SQLException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		//View
		System.out.println("아이디 입력:");
		userId = sc.next();
		System.out.println("패스워드 입력:");
		userPwd = sc.next();
		
		//Controller
		UserDAO userDAO = new UserDAO();
		int num = userDAO.deleteUser(userId,userPwd);//(userDAO[3]참고)
		
		if(num==1) {
			System.out.println("회원 탈퇴 성공");
		}
		else if(num==2) {
			System.out.println("비밀번호가 틀렸습니다. 홈 화면으로 돌아갑니다");
		}
		else if(num==3) {
			System.out.println("아이디가 존재하지 않습니다. 홈 화면으로 돌아갑니다");
		}
		else {
			System.out.println("알 수 없는 오류입니다. 홈화면으로 돌아갑니다.");
		}
	}
}
