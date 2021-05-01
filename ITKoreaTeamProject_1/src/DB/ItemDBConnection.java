package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.ItemDTO;

public class ItemDBConnection {
	Connection con;
	List<ItemDTO> itemList = new ArrayList<>(); //상품의 정보를 보관하는 리스트
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbUserId = "c##coin666"; 
	String dbUserPwd = "1234"; 
	
	//ItemDBConnection객체에 OracleDB드라이버와 Oracle접속할 유저의 정보를 저장
	public ItemDBConnection(){
		try {
			//jdbc와 oracleDB를 연결해주는 드라이버
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//OracleDB에 접속주소, DB유저정보를 con객체에 저장
			this.con = DriverManager.getConnection(dbUrl, dbUserId, dbUserPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//모든 상품을 조회하여 List로 반환, 전체 흐름에서 한번만 실행됨
	//DB에서 매번 꺼내사용하는 것이 아닌 Dao객체에 모든상품의 정보를 보관하게 함
	public List selectAllItem() {
		try {
			//OracleDB에 ITEM_TB테이블을 조회한다.
			String sql = "SELECT * FROM ITEM_TB";
			//연결된 오라클드라이버에서 실행할 객체를 생성, 질의문을 담을 그릇??
			Statement st = con.createStatement();
			//객체에 쿼리문을 담아 오라클DB에서 실행을하고 그 결과를 rs객체에 저장한다.(레코드로 저장)
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int obj1 = Integer.parseInt(rs.getString(1));
				String obj2 = rs.getString(2);
				int obj3 = Integer.parseInt(rs.getString(3));
				int obj4 = Integer.parseInt(rs.getString(4));
				itemList.add(new ItemDTO(obj1,obj2,obj3,obj4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
	
	public void updateBuyItem(int itemNO, int itemNum) {
		try {
			//변경할 값과 변경되는 레코드의 조건 넣기
			String sql = "UPDATE ITEM_TB SET ITEM_STOCK=ITEM_STOCK-"+itemNum+" WHERE ITEM_NO="+itemNO;
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

