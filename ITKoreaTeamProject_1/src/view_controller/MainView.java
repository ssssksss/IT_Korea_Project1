package view_controller;

import java.util.Scanner;

import DAO.UserDAO;

public class MainView {
	void MainView() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//View
			System.out.println("=== 메인 화면 입니다. ===");
			System.out.println("1. 서비스 화면");
			System.out.println("2. 로그아웃");
			int num = sc.nextInt();
			
			//Controller
			if(num==1) {
				new ServiceView();
			} else if(num==2) {
				break;
			}
		}
	}
}
