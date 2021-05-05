package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import DTO.UserDTO;
import Exception.InputException;
import Exception.LetterException;


public class UserJoinView {
	String userId;
	String userPwd;
	String userName;
	String userPhone;
	String pw2=null; //확인용 비밀번호
	boolean exid=true;
	boolean expw=true;
	boolean exname=true;
	boolean exphonenum=true;
	
	public UserJoinView() throws ClassNotFoundException, SQLException, LetterException {
		InputException ie = new InputException();
		Scanner sc = new Scanner(System.in);

		//View
		//아이디 유효성 검사 : 5~15자 이내의 아이디만 가능합니다 아이디는 영문자와 숫자를 혼용해서 만들어주세요
		do {
			try {
				System.out.print("아이디 입력 : ");
				userId = sc.next();
				ie.idFormat(userId);
				exid=false;
			} catch (LetterException e) {
				System.out.println(e.toString());
			}
		}while(exid);
		//비밀번호 유효성 검사 : 비밀번호는 영문자와 숫자를 혼용해서 만들어주세요
		do {
			try {
				System.out.print("비밀번호 입력 : ");
				userPwd = sc.next();
				System.out.print("비밀번호 확인:");
				pw2 = sc.next();
				ie.pwCheck(userPwd,pw2);
				expw=false;
			} catch (LetterException e) {
				System.out.println(e.toString());
			}
		}while(expw);
		//이름 유효성 검사 : 이름은 한글로 입력해주세요
		do{
			try{
				System.out.print("이름 입력 : ");
				userName = sc.next();
				ie.nameCheck(userName);
				exname=false;
			}catch (LetterException e) {
				System.out.println(e.toString());
			}
		} while(exname);
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
		int num = userDAO.joinUser(new UserDTO(userId,userPwd,userName,userPhone)); //(UserDAO[2]참조)
		
		//회원가입 성공
		if(num==1) {
			System.out.println("회원가입 성공");
			new UserMainView();
		}
		//이미 존재하는 아이디
		else if(num==2) {
			System.out.println("아이디가 이미 존재합니다. 홈 화면으로 돌아갑니다");
		}
		//기타 오류
		else {
			System.out.println("알 수 없는 오류입니다. 홈 화면으로 돌아갑니다");
		}
	}
}

