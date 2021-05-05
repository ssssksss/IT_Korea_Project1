package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import DTO.ItemDTO;
import DTO.UserDTO;

public class UserDBConnection {
	Connection con;
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbUserId = "c##coin666"; 
	String dbUserPwd = "1234"; 
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	//[1] 객체를 생성하면 OracleDB에 접속할 권한을 갖게 만듬
	public UserDBConnection(){
		try {
			//jdbc와 oracleDB를 연결해주는 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//OracleDB에 접속주소, DB유저정보를 conn객체에 저장
			this.con = DriverManager.getConnection(dbUrl, dbUserId, dbUserPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//[2] DB에서 로그인이 가능한지 확인하는 과정
	public int checkLoginUser(String userId, String userPwd) throws SQLException {
		String sql = "SELECT * FROM USER_TB";
		try(Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {
			while(rs.next()) {
				if(rs.getString("USER_ID").equals(userId)) {
					if(rs.getString("USER_PWD").equals(userPwd)) {
						//아이디와 비번 둘다 일치할 경우
						return 1;
					}
					else {
						//아이디는 일치 비번은 불일치
						return 2;
					}
				}
			}
			//아이디가 존재하지 않음
			return 3;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 4;
	}
	
	//[3] DB에 회원가입 정보를 넣는 과정
	public int addJoinUser(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM USER_TB";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql); ) {
			while(rs.next()) {
				//DB에서 userID가 존재하는지 검색
				if(rs.getString("USER_ID").equals(userDTO.getUserId())) {
					//id가 존재하면 리턴값 2
					return 2;
				}
			}
			//DB에 회원정보를 넣기
			sql = "INSERT INTO USER_TB VALUES ('";
			sql += userDTO.getUserId()+"','"+userDTO.getUserPwd()+"','"+userDTO.getUserName()+"','"+userDTO.getUserPhone()+"')";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			//회원가입 성공하면 리턴값1
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//그 외의 오류 리턴값 3
		return 3;
	}
	
	//[4] DB에 회원 탈퇴 하는 과정
	public int deleteUser(String userId, String userPwd) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM USER_TB WHERE USER_ID='"+userId+"'";
		try (PreparedStatement pst = con.prepareStatement(sql); ) {
			//DB에 회원정보 삭제
			pst.executeUpdate();
			pst.close();
			//회원가입 탈퇴 성공하면 리턴값1
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//그 외의 오류 리턴값 3
		return 2;
	}
	
	//[5] DB에 있는 유저의 회원 정보를 바꾼다.
	public int changeInformUser(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE USER_TB SET USER_PWD='"+userDTO.getUserPwd()+"', USER_NAME='";
		sql+=userDTO.getUserName()+"', USER_PHONE='"+userDTO.getUserPhone()+"' WHERE USER_ID='"+userDTO.getUserId()+"'";
		
		try (PreparedStatement pst = con.prepareStatement(sql); ) {
			pst.executeUpdate();
			pst.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}
	
	//[6] DB에 휴대폰번호를 넣어 유저의 아이디와 비밀번호를 가져온다.
	public UserDTO findUserIdPwd(String userPhone) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM USER_TB WHERE USER_PHONE='"+userPhone+"'";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql); ) {
			while(rs.next()) {
				//DB에서 userID가 존재하는지 검색
				if(rs.getString("USER_PHONE").equals(userPhone)) {
					UserDTO userDTO = new UserDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					return userDTO;
				}
			}
			return null;
		}
	}//userDTO.getUserId()+"','"+userDTO.getUserPwd()+"','"+userDTO.getUserName()+"','"+userDTO.getUserPhone()+"')"
	
}

