package DAO;

import DB.ItemDBConnection;
import java.util.List;
import java.util.Random;
import DTO.ItemDTO;
import java.util.ArrayList;

public class ItemDAO {
	List<ItemDTO> cartList = new ArrayList<>(); //장바구니로 사용할 리스트
	List<ItemDTO> allList = new ArrayList<>(); //상품의 모든데이터를 보관할 리스트
		
	//[1] 상품DB에서 모든 데이터 가져오기, 한번만 DB에 접속하여 상품DB를 가져오고 두번 이용 하지 않음
	public void getAllItem() {
		//OracleDB에 접속할 환경이 되어있는 객체 선언
		ItemDBConnection itemDB = new ItemDBConnection();//(ItemDBConnection[1]참고)
		// 상품DB에서 모든 상품을 조회하고 모든 상품 리스트를 반환
		this.allList = itemDB.getAllItem(); //(ItemDBConnection[2]참고)
	}
	
	//[2] DAO객체가 가지고 있는 allList에서 상품을 랜덤으로 하나 보여주기
	public ItemDTO showOneItem() {
		Random rand = new Random();
		int num = rand.nextInt(allList.size());
		ItemDTO itemInform = new ItemDTO(allList.get(num));
		System.out.println(itemInform.getItemNo()+"번: "+itemInform.getItemName()+" "+itemInform.getItemPrice()+"원 재고:"+itemInform.getItemNum()+"개 \n");
		return itemInform; //보여준 상품을 장바구니에 담을 경우 필요하여 반환값 존재
	}
	
	//[3] 모든 상품 목록을 보여준다.
	public void showAllItem() {
		for(ItemDTO itemInform : allList) {
			System.out.println(itemInform.getItemNo()+"번: "+itemInform.getItemName()+" "+itemInform.getItemPrice()+"원 재고:"+itemInform.getItemNum()+"개");
		}
	}

	//[4] 보여준 추천 상품을 매개변수로 받아와 DAO객체에 있는 cartList에 상품 추가
	public void addItemCart(ItemDTO randomItem) {
		for(ItemDTO itemInform : cartList) {
			//만일 이미 보여준 상품인데 카트에 중복해서 또 담는경우 기존에 담은 상품에 수량만 추가한다.
			if(itemInform.getItemNo() == randomItem.getItemNo()) {
				//장바구니에 담은 수량 = 기존에 장바구니에 담은 수량 + 추가로 장바구니에 담은 수량
				itemInform.setItemNum(itemInform.getItemNum()+randomItem.getItemNum());
			}
		}
		cartList.add(randomItem); //장바구니 리스트에 추가를 한다. 
	}
	
	//[5] DAO객체가 가지고 있는 cartList(장바구니)에 담긴 목록 보기
	public void showItemCart() {
		for(ItemDTO itemInform : cartList) {
			System.out.println(itemInform.getItemNo()+"번: "+itemInform.getItemName()+" "+itemInform.getItemPrice()+"원 구매할 수량:"+itemInform.getItemNum()+"개 상품 합계:"+itemInform.getItemPrice()*itemInform.getItemNum());
		}
		System.out.println(); //콘솔창에서 보기 좋게 한칸 비워둠
	}
	
	//[6] 장바구니에서 담긴 목록을 제거한다.
	public void removeItemCart(int removeNum) {
		for(ItemDTO removeDTO : cartList) {
			if(removeDTO.getItemNo()==removeNum) {
				cartList.remove(removeDTO);
				break;
			}
		}
	}
	
	//[7] 장바구니에 담긴 상품들의 가격의 총합
	public int sumItemCart() {
		int sum=0;
		for(ItemDTO itemInform : cartList) {
			sum += itemInform.getItemPrice()*itemInform.getItemNum();
		}
		return sum;
	}
	
	//[8] 상품DB에 구매하는 상품들의 수량만큼 전체 재고에서 빼주는 작업
	public void buyItemCart() {
		ItemDBConnection itemDB = new ItemDBConnection();  //OracleDB에 접속할 환경이 되어있는 객체 선언
		//한번 실시간으로 상품리스트를 최신화를 한다.(다른사람이 구매했을것을 가정)
		this.allList = itemDB.getAllItem();//(ItemDBConnection[2]참고)
		//상품의 재고와 구매하려는 장바구니의 재고보다 부족하지 않은지 확인
		boolean pass = true; // 결제 가능 여부
		List<ItemDTO> removeList = new ArrayList<>();
		int removeListIndex = 0;
		for(ItemDTO itemInform : cartList) {
			if(itemInform.getItemNum() > allList.get(itemInform.getItemNo()-1).getItemNum()){
				System.out.println("상품 :"+itemInform.getItemName()+"의 수량이 재고 수량보다 많아 장바구니에서 제외시켰습니다.");
				removeList.add(itemInform);
				removeListIndex++;
				pass = false;
			}
		}
		//구매할 물건이 재고수량과 맞는다면 결제 허용
		if(!pass) { //결제 불가능
			for(int i=0; i<removeListIndex; i++) {
				cartList.remove(removeList.get(i));
			}
		}
		else if(pass) { //결제가능
			//상품DB에서 장바구니에 있는 수량만큼 수량을 뺀다.
			for(ItemDTO itemInform : cartList) {
				itemDB.updateBuyItem(itemInform.getItemNo(),itemInform.getItemNum());//(ItemDBConnection[3]참고)
			}
			//구매를 하였으니 장바구니 초기화
			cartList = new ArrayList<>();
			//구매를 해서 재고 수량이 변경되었으므로 상품리스트 최신화
			this.allList = itemDB.getAllItem();//(ItemDBConnection[2]참고)
			itemDB = null;
		}
	}
	
	
	
	
	
}


