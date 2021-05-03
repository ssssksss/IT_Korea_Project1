package view_controller;

import java.util.Scanner;

import DAO.UserDAO;
import DTO.UserDTO;


public class JoinView {

	public JoinView() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		UserDTO userDTO;
		
		while(true) {
			//View
			System.out.println("=== 회원가입 화면 입니다. ===");
			System.out.println("아이디 입력");
			String userId = sc.next();
			System.out.println("비밀번호 입력");
			String userPwd = sc.next();
			System.out.println("이름 입력");
			String userName = sc.next();
			System.out.println("휴대폰번호 입력");
			String userPhone = sc.next();
			
			//Controller
			userDTO = new UserDTO(userId,userPwd,userName,userPhone);
			UserDAO userDAO = new UserDAO();
			int num = (userDAO.joinUser(userDTO)); //(UserDAO[2]참조)
			
			//입력된 정보를 기준으로 판단
			//회원가입 성공
			if(num==1) {
				System.out.println("회원가입 성공");
				new MainView();
			}
			//이미 존재하는 아이디
			else if(num==2) {
				System.out.println("아이디가 이미 존재합니다. 홈 화면으로 돌아갑니다");
				break;
			}
			//기타 오류
			else if(num==3) {
				System.out.println("회원가입에 실패했습니다. 홈 화면으로 돌아갑니다");
				break;
			}
			
		}
	}
}

