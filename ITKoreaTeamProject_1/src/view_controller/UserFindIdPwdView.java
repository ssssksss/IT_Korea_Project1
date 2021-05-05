package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import DTO.UserDTO;
import Exception.InputException;
import Exception.LetterException;


public class UserFindIdPwdView {
	String userPhone;
	boolean exphonenum=true;
	
	public UserFindIdPwdView() throws ClassNotFoundException, SQLException, LetterException {
		InputException ie = new InputException();
		Scanner sc = new Scanner(System.in);

		//View
		//휴대폰 유효성 검사 : 010|011|016|017|018?019 -XXXX-XXXX
		do{
			try{
				System.out.print("전화번호[xxx-xxxx-xxxx]: ");
				userPhone = sc.next();
				ie.phoneCheck(userPhone);
				exphonenum=false;
			}catch (LetterException e) {
				System.out.println(e.toString());
			}

		} while(exphonenum);
		
		//Controller
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.findUserIdPwd(userPhone);
		if(userDTO==null) {
			System.out.println("일치하는 정보가 없습니다.");
		}
		else { 
			System.out.println(userDTO.getUserName()+"님의 아이디는 "+userDTO.getUserId()+" 이고 비밀번호는 "+userDTO.getUserPwd()+" 입니다."); 
		}
		
	}
}

