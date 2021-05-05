package view_controller;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import DTO.UserDTO;
import Exception.InputException;
import Exception.LetterException;

public class UserChangeInformView {
	String userId;
	String userPwd;
	String userName;
	String userPhone;
	String pw2=null; //확인용 비밀번호
	boolean exid=true;
	boolean expw=true;
	boolean exname=true;
	boolean exphonenum=true;
	
	
	UserChangeInformView() throws SQLException, ClassNotFoundException, LetterException {
			InputException ie = new InputException();
			Scanner sc = new Scanner(System.in);
			UserDAO userDao = new UserDAO();

			System.out.print("수정 할 아이디:");
			userId = sc.next();
			
			do {
				try {
					System.out.println("비밀번호는 영문자숫자 혼용으로 입력해주세요");
					System.out.print("변경할 비밀번호 입력 : ");
					userPwd = sc.next();
					System.out.print("변경할 비밀번호 확인:");
					pw2 = sc.next();
					ie.pwCheck(userPwd,pw2);
					expw=false;
				} catch (LetterException e) {
					System.out.println(e.toString());
				}
			}while(expw);
			
			do{
				try{
					System.out.print("이름은 한글로 입력해주세요");
					System.out.print("이름 입력:");
					userName = sc.next();
					ie.nameCheck(userName);
					exname=false;
				}catch (LetterException e) {
					System.out.println(e.toString());
				}
			} while(exname);

			do{
				try{
					System.out.print("010|011|016|017|018|019 시작해주세요");
					System.out.print("가운데는 3~4자리 끝에는 4자리로 입력해주세요");
					System.out.print("전화번호[xxx-xxxx-xxxx]: ");
					userPhone = sc.next();
					ie.phoneCheck(userPhone);
					exphonenum=false;
				}catch (LetterException e) {
					System.out.println(e.toString());
				}

			} while(exphonenum);
			
			userDao.changerInformUser(new UserDTO(userId,userPwd,userName,userPhone));
			
	}
}
