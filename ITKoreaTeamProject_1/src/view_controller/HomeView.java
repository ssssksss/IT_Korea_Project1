package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import Exception.LetterException;

public class HomeView {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, LetterException {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//View
			System.out.println("===== 홈 화면 입니다. =====");
			System.out.println("[1] 유저 로그인 화면");
			System.out.println("[2] 유저 회원가입 화면");
			System.out.println("[3] 유저 아이디/비번 찾기 화면");
			System.out.println("[4] 관리자 로그인 화면");
			System.out.println("[5] 종료");
		
			//Controller
			System.out.print("입력 : ");
			int num = sc.nextInt();
			//[1] 유저 로그인 화면
			if(num==1) {
				new UserLoginView();
			} 
			//[2] 유저 회원가입 화면
			else if(num==2) { 
				new UserJoinView();
			}
			//[3] 유저 아이디/비번 찾기 화면
			else if(num==3) {
				new UserFindIdPwdView();
			}
			//[4] 관리자 로그인 화면
			else if(num==4) {
				new ManagerLoginView();
			}
			//[5] 종료
			else if(num==5) {
				System.out.println("종료");
				break;
			}
			//[6] 잘못된 번호 입력
			else {
				System.out.println("1~5번 중에 번호를 선택해 주세요.");
			}
		}
	}
}

