package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import Exception.LetterException;

public class UserMainView {
	
	UserMainView() throws SQLException, ClassNotFoundException, LetterException {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//View
			System.out.println("===유저 메인 화면 입니다. ===");
			System.out.println("[1]상품 보러 가기");
			System.out.println("[2]유저 회원정보 변경");
			System.out.println("[3]유저 회원 탈퇴");
			System.out.println("[4]로그아웃");
			int num = sc.nextInt();
			
			//Controller
			//[1] 상품 보러 가기
			if(num==1) {
				System.out.println("상품 보러 가기");
				new UserServiceView();
			}
			//[2] 유저 회원정보 변경
			else if(num==2) {
				System.out.println("회원 정보를 변경합니다.");
				new UserChangeInformView();
			}
			//[3] 유저 회원 탈퇴
			else if(num==3) {
				System.out.println("회원 탈퇴 화면입니다.");
				new UserDeleteView();
				break;
			}
			//[4] 로그아웃
			else if(num==4) {
				System.out.println("로그아웃 되었습니다.");
				break;
			}
			//잘못된 번호를 입력하였습니다.
			else {
				System.out.println("잘못된 번호를 입력하였습니다.");
			}
		}
	}
}
