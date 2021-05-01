package view_controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import DAO.ItemDAO;
import DTO.ItemDTO;

public class SeviceView {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//유저의 상품데이터를 가지고 있는 DAO객체 생성
		ItemDAO itemDao = new ItemDAO();
		//itemDao객체가 모든 상품의 목록을 가지고 있게 한다.
		itemDao.showAllItem();
		
		//리턴값 ItemDTO
		while(true) {
			//View
			System.out.println("=== 추천 선물 목록 ===");
			//랜덤으로 상품한개를 보여주고 randomItem에 상품을 잠시 기억한다.
			ItemDTO randomItem = itemDao.showOneItem();
			
			System.out.println("1.장바구니 담기");
			System.out.println("2.구매하기 ");
			System.out.println("3.다른 상품 보기");
			System.out.println("4.장바구니 목록보기");
			System.out.println("5.서비스 화면으로 ");
			int num = sc.nextInt();
			
			//Controller
			if(num==1) { //장바구니 담기
				System.out.println("구매할 수량을 입력해주세요");
				//장바구니에 담을 수량을 입력하고 장바구니에 담는다.
				randomItem.setItemNum(sc.nextInt());
				itemDao.itemCart(randomItem);
			} else if(num==2) {
				while(true) {
					System.out.println("=== 장바구니에 담긴 목록입니다. ===");
					itemDao.itemCartShow();
					System.out.println("장바구니의 담긴 총 합계는:"+itemDao.itemCartSum());
					System.out.println("장바구니에 담긴 목록을 구매하시겠습니까? Y/N");
					String choice = sc.next();
					if(choice.equals("Y") || choice.equals("y")) { 
						//구매를 할 쿼리문을 작성
						itemDao.itemBuy();
						break;
					}
					else if(choice.equals("N") || choice.equals("n")) {
						break;
					}
					else {
						System.out.println("잘못된 입력입니다. 다시 입력해주세요");
					}
				}
			} else if(num==3) {
				//3번은 다른 상품을 다시보기이므로 비워둠
			} else if(num==4) {
				System.out.println("=== 장바구니에 담긴 목록입니다. ===");
				itemDao.itemCartShow();
			} else if(num==5) {
				break;
			}
		}
	}
}

