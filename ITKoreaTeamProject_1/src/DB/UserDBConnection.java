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
	public int selectLoginUser(String userId, String userPwd) {
		try {
			//OracleDB에서 User_TB를 조회한다.
			String sql = "SELECT * FROM USER_TB";
			//연결된 오라클드라이버에서 실행할 객체를 생성
			Statement st = con.createStatement();
			//객체에 쿼리문을 담아 오라클DB에서 실행을하고 그 결과를 rs객체에 저장한다.(레코드로 저장)
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getString("userId").equals(userId)) {
					if(rs.getString("userPwd").equals(userPwd)) {
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
		
		return 0;
	}
	
	//[3] DB에 회원가입 정보를 넣는 과정
	public int insertJoinUser(UserDTO userDTO) throws ClassNotFoundException {
	
		String sql = "SELECT * FROM USET_TB";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql); ) {
	
		while(rs.next()) {
			//DB에서 userID가 존재하는지 검색
			if(rs.getString("userId").equals(userDTO.getUserId())) {
				//id가 존재하면 리턴값 0
				return 2;
			}
		}
		
		//DB에 회원정보를 넣기
		sql = "INSERT INTO USER_TB VALUES (";
		String sql2 = userDTO.getUserId()+","+userDTO.getUserPwd()+","+userDTO.getUserName()+","+userDTO.getUserPhone();
		sql = sql + sql2 +")";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
		con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
}

