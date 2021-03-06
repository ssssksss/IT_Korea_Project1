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
	 //상품의 데이터를 보관하는 리스트(리턴값 반환 용도)
	List<ItemDTO> itemList = new ArrayList<>();
	
	//[1] ItemDBConnection객체를 가지고 있는 객체는 OracleDB에 접속할 환경을 가지고 있음
	public ItemDBConnection(){
		try {
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbUserId = "c##coin666"; 
			String dbUserPwd = "1234"; 
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
	
	//[2]상품DB에 모든 상품 데이터를 가져오는 과정
	public List<ItemDTO> getAllItem() {
		String sql = "SELECT * FROM ITEM_TB";
		try(Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {
			
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
	//[3] 상품DB에 구매한 상품의 수량만큼 전체 재고에서 빼는 과정
	public void updateBuyItem(int itemNO, int itemNum) {
		try {
			//상품의 번호를 찾아 재고에서 구매할 수량만큼 뺀다.
			String sql = "UPDATE ITEM_TB SET ITEM_STOCK=ITEM_STOCK-"+itemNum+" WHERE ITEM_NO="+itemNO;
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

