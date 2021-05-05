package DAO;

import java.sql.SQLException;

import DB.UserDBConnection;
import DTO.UserDTO;

public class UserDAO {
	
	UserDBConnection userDB = new UserDBConnection();
	//[1]
	public UserDAO() {
		
	}
	//[2]
	public int joinUser(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		
		//유저DB에서 회원가입이 가능한지에 대한 결과 (UserDBConnection[3]참고)
		//1. 회원가입성공 2.이미존재하는아이디 3. 회원가입실패
		return userDB.addJoinUser(userDTO);
	}
	//[3]
	public int loginUser(String userId, String userPwd) throws SQLException {
		
		//유저DB에서 아이디와 패스워드가 존재하는지에 대한 결과 (UserDBConnection[2]참고)
		//1 로그인성공, 2 비번틀림 3 아이디 존재하지않음 4 에러발생
		return userDB.checkLoginUser(userId, userPwd);
	}
	//[4]
	public int deleteUser(String userId, String userPwd) throws SQLException, ClassNotFoundException {
		int num = userDB.checkLoginUser(userId,userPwd); //데이터 베이스에 유저가 존재
		if(num==1) {
			return userDB.deleteUser(userId, userPwd);
		}
		else {
			return 2;
		}
	}
	//[5]
	public int changerInformUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
		int num = userDB.changeInformUser(userDTO); //데이터 베이스에 유저 데이터 변경
		if(num==1) {
			return 1;
		}
		else {
			
			return 2;
		}
	}
	//[6] 휴대폰번호로 아이디와 비밀번호찾기
	public UserDTO findUserIdPwd(String userPhone) throws ClassNotFoundException, SQLException{
		UserDTO userDTO = userDB.findUserIdPwd(userPhone); //데이터 베이스에 유저 데이터 변경
		if(userDTO==null) { return null; }
		return userDTO; //존재하면 제대로 반환
		
	}
}