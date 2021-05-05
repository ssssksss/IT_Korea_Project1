package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.ItemDTO;
import oracle.sql.ARRAY;

public class ManagerDBConnection {

	Connection con;
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	List<ItemDTO> itemList = new ArrayList<>();
	
	//[1] ManagerDBConnection객체를 가지고 있는 객체는 OracleDB에 접속할 환경을 가지고 있음
	public ManagerDBConnection(){
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
	//[2] 관리자 로그인
	public int checkLogin(String managerId,String managerPwd) {
		try {
			
			String sql="SELECT * FROM MANAGER_TB";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getString("MANAGER_ID").equals(managerId)) {
					if(rs.getString("MANAGER_PWD").equals(managerPwd)) {
						//아이디 비번 일치
						return 1;
					}
					else {
						//일치하지 않음
						return 2;
					}
				}
			}
			return 3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 4;
	}
	//[3] 상품 종류 추가
	public void addItem(String itemName,int itemPrice,int itemStock) {
	
		int index=1;
		try {
			stmt=con.createStatement();
			
			String sql="SELECT ITEM_NO FROM ITEM_TB";
			rs=stmt.executeQuery(sql);

			while(rs.next()) {
				index=rs.getInt(1)+1;
			}
			rs.close();
			sql="INSERT INTO ITEM_TB VALUES("+index+",'"+itemName+"','"+itemPrice+"',"+itemStock+")";
			index++;
			itemList.add(new ItemDTO(index,itemName,itemPrice,itemStock));
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//[4] 상품 모두 보여주기
	public void showAllItem() {
		System.out.println("===== 상품목록 =====");
		String sql="SELECT * FROM ITEM_TB ORDER BY ITEM_NO";
		try(Statement stmt=con.createStatement(); ResultSet rs=stmt.executeQuery(sql);) {
			
			while(rs.next()) {
				System.out.println(rs.getInt("Item_NO")+"번 "+rs.getString("Item_NAME")+":"+rs.getInt("Item_PRICE")+"원 "+rs.getInt("Item_STOCK")+"개");
				System.out.println("-------------------");
			}
			rs.close();
			System.out.println();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//[5] 상품 재고 추가하기
	public void addStock(int itemNo, int itemNum) {
		try {
			stmt=con.createStatement();
			String sql="UPDATE ITEM_TB SET ITEM_STOCK=ITEM_STOCK+"+itemNum+" WHERE ITEM_NO="+itemNo;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//[6] 상품 삭제 하기
	public void deleteItem(int itemNo) {
		try {
			stmt=con.createStatement();
			String sql="delete from ITEM_TB WHERE Item_No="+itemNo;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}











