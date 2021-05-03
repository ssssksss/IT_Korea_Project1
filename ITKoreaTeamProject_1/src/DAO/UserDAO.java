package DAO;

import DB.UserDBConnection;
import DTO.UserDTO;

public class UserDAO {
	
	UserDBConnection userDB = new UserDBConnection();
	//[1]
	public UserDAO() {
		
	}
	//[2]
	public int joinUser(UserDTO userDTO) throws ClassNotFoundException {
		//유저DB에서 회원가입이 가능한지에 대한 결과 (UserDBConnection[3]참고)
		int result = userDB.insertJoinUser(userDTO);
		//1. 회원가입성공 2.이미존재하는아이디 3. 회원가입실패
		return result;
	}
	//[3]
	public int loginUser(String userId, String userPwd) {
		//유저DB에서 아이디와 패스워드가 존재하는지에 대한 결과 (UserDBConnection[2]참고)
		int result = userDB.selectLoginUser(userId, userPwd);
		//1 로그인성공, 2 비번틀림 3 아이디 존재하지않음 4 에러발생
		return result;
	}
	
}