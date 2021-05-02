package view_controller;

import java.util.Scanner;

import DAO.UserDAO;
import DTO.UserDTO;


public class JoinView {

	public static void main(String[] args) {
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
			userDAO.
		}
	}
}

