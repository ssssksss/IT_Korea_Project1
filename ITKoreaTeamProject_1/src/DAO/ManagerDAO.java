package DAO;


import java.util.ArrayList;
import java.util.HashSet;

import DB.ItemDBConnection;
import DB.ManagerDBConnection;
import DB.UserDBConnection;

public class ManagerDAO {

	ManagerDBConnection managerDB =new ManagerDBConnection();
	
	//[1] 관리자 로그인
	public int managerLogin(String managerId,String managerPwd) {
		return managerDB.checkLogin(managerId, managerPwd);
	}
	//[2] 상품 종류 추가
	public void addItem(int index,String itemName,int itemPrice,int itemStock) {
		managerDB.addItem(itemName, itemPrice, itemStock);
	}
	//[3] 모든 상품 보여주기
	public void showItem() {
		managerDB.showAllItem();
	}
	//[4] 상품 재고 추가
	public void addStock(int itemNo,int itemNum) {
		managerDB.showAllItem();
		managerDB.addStock(itemNo, itemNum);
	}
	//[5] 상품 삭제
	public void deleteItem(int itemNo) {
		managerDB.showAllItem();
		managerDB.deleteItem(itemNo);
	}
}
