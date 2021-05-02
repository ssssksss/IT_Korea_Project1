package view_controller;

import java.util.Scanner;

import DAO.UserDAO;

public class HomeView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//View
			System.out.println("=== 홈 화면 입니다. ===");
			System.out.println("1. 로그인 화면");
			System.out.println("2. 회원가입 화면");
			System.out.println("3. 종료");
		
			//Controller
			//1: 로그인 화면, 2: 회원가입 화면, 3: 종료
			int num = sc.nextInt();
			if(num==1) {
				System.out.println("로그인 화면");
				new LoginView();
			} else if(num==2) {
				System.out.println("회원가입 화면");
				new JoinView();
			} else if(num==3) {
				System.out.println("종료");
				break;
			}
		}
	}
}

