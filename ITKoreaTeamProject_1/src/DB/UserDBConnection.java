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

public class UserDBConnection {
	Connection con;
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbUserId = "c##coin666"; 
	String dbUserPwd = "1234"; 
	
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
	
	public int selectLogin(String userId, String userPwd) {
		try {
			//OracleDB에서 User_TB를 조회한다.
			String sql = "SELECT * FROM USER_TB";
			//연결된 오라클드라이버에서 실행할 객체를 생성
			Statement st = con.createStatement();
			//객체에 쿼리문을 담아 오라클DB에서 실행을하고 그 결과를 rs객체에 저장한다.(레코드로 저장)
			ResultSet rs = st.executeQuery(sql);
			
//			while(rs.next()) {
//				if(rs.getString("userId").equals(userId)) {
//					if(rs.getString("userPwd").equals(userPwd)) {
//						//아이디와 비번 둘다 일치할 경우
//						return 1;
//					}
//					else {
//						//아이디는 일치 비번은 불일치
//						return 2;
//					}
//				}
//				//아이디가 존재하지 않음
//				return 3;
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
//	
//	//DB조회하는 메소드
//	public int select(userDTO dto){
//		try {
//			String sql = "SELECT * FROM PCMEMBER";
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//
//			while(rs.next()) {
//				//DB에서 userID가 존재하는지 검색
//				if(rs.getString("userID").equals(dto.getUserID())) {
//					//DB에서 아이디를 찾고 비번까지 일치하는지 확인
//					if(rs.getString("userPWD").equals(dto.getUserPWD())) {
//						//아이디와 비번까지 일치했을때 리턴값 1
//						return 1;
//					}
//					else {
//						//아이디는 일치하지만 비번은 틀린경우 리턴값 2
//						return 2;
//					}
//				}
//			}
//			rs.close();
//			st.close();
//			con.close();
//			//아이디를 찾지 못했을 경우 리턴값 3
//			return 3;
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//DB조회 중에 에러
//		return 4;
//	}
//	
//	//DB에 회원정보를 넣는 메소드
//	public int insert(userDTO dto) throws ClassNotFoundException {
//
//		String sql = "SELECT * FROM PCMEMBER";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql); ) {
//
//		while(rs.next()) {
//			//DB에서 userID가 존재하는지 검색
//			if(rs.getString("userID").equals(dto.getUserID())) {
//				//id가 존재하면 리턴값 0
//				return 2;
//			}
//		}
//		
//		//DB에 회원정보를 넣기
//		sql = "INSERT INTO PCMEMBER VALUES(?, ?, ?, ? ,? ,?)";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ps.setString(1, dto.getUserID());
//		ps.setString(2, dto.getUserPWD());
//		ps.setString(3, dto.getUserNAME());
//		ps.setString(4, dto.getUserBIRTH());
//		ps.setString(5, dto.getUserPHONE());
//		ps.setString(6, dto.getUserEMAIL());
//		ps.executeUpdate();
//		ps.close();
//		con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 1;
//	}

