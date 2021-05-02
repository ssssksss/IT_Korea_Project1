package DAO;

import java.util.HashSet;

import DB.UserDBConnection;

public class UserDAO {
	
	UserDBConnection userDB = new UserDBConnection();
	
	public UserDAO() {
		
	}
	
	public void joinUser() {
		
	}
	
	
	
}//itemInform.getItemNum()


//package DAO;
//
//import DB.userDBconn;
//import DTO.userDTO;
//
//public class userDAO {
//	String dburl = "jdbc:oracle:thin:@localhost:1521/xe";
//	String dbuserid = "c##coin666"; 
//	String dbuserpwd = "1234"; 
//	userDBconn userdb = new userDBconn(dburl,dbuserid,dbuserpwd);
//	
//	//유저 회원가입 메소드
//	public int join(userDTO dto) {
//			try {
//				//자바문제 -1, DB에 접속하여 회원가입이 가능하면 1, 유저정보가 이미 존재하면 2
//				return userdb.insert(dto);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		return -1;
//	}
//	
//	//유저 로그인 메소드
//	public int login(userDTO dto) {
//		try {
//			// 자바문제 -1 ,로그인 성공 1, 비번틀릴경우 2, 아이디존재하지않는경우 3 , DB조회중에 오류발생 4
//			return userdb.select(dto);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//그외의 에러
//		return -1;
//	}
//}