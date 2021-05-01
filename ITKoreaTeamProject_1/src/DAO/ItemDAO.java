package DAO;

import DB.ItemDBConnection;
import java.util.List;
import java.util.Random;
import DTO.ItemDTO;
import java.util.ArrayList;

public class ItemDAO {
	//장바구니로 사용할 리스트
	List<ItemDTO> cartList = new ArrayList<>();
	//상품의 모든데이터를 보관할 리스트
	List<ItemDTO> allList = new ArrayList<>();
	
	//상품DB에서 모든 데이터 가져오기
	public void showAllItem() {
		ItemDBConnection itemDB = new ItemDBConnection();
		allList = itemDB.selectAllItem();
		//모든 상품을 출력하는 방법
//		for(ItemDTO li : allList) {
//			System.out.println(li.getItemNo()+"번: "+li.getItemName()+" "+li.getItemPrice()+"원 "+li.getItemStock()+"개 ");
//		}
	}
	
	//상품DB에서 랜덤으로 하나 보여주기
	public ItemDTO showOneItem() {
		Random rand = new Random();
		int num = rand.nextInt(allList.size());
		ItemDTO itemInform = allList.get(num); 
		System.out.println(itemInform.getItemNo()+"번: "+itemInform.getItemName()+" "+itemInform.getItemPrice()+"원 재고:"+itemInform.getItemNum()+"개 \n");
		return itemInform;
	}

	//1.장바구니 담기
	public int itemCart(ItemDTO randomItem) {
		for(ItemDTO itemInform : cartList) {
		}
		cartList.add(randomItem); //장바구니 리스트에 추가를 한다. 
		return 0;
	}
	
	//장바구니에 담긴 목록 보기
	public int itemCartShow() {
		for(ItemDTO itemInform : cartList) {
			System.out.println(itemInform.getItemNo()+"번: "+itemInform.getItemName()+" "+itemInform.getItemPrice()+"원 구매할 수량:"+itemInform.getItemNum()+"개");
		}
		System.out.println();
		return 0;
	}
	
	//장바구니에 담긴 목록의 총합
	public int itemCartSum() {
		int sum=0;
		for(ItemDTO itemInform : cartList) {
			sum += itemInform.getItemPrice()*itemInform.getItemNum();
		}
		return sum;
	}
	
	//2.구매하기 cartList이용
	public int itemBuy() {
		ItemDBConnection itemDB = new ItemDBConnection();
		for(ItemDTO itemInform : cartList) {
			itemDB.updateBuyItem(itemInform.getItemNo(),itemInform.getItemNum());
		}
		return 0;
	}
	
	public static void main(String[] args) {
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.showAllItem();
	}
}


