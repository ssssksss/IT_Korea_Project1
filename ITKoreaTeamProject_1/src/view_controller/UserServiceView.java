package view_controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import DAO.ItemDAO;
import DTO.ItemDTO;

public class UserServiceView {
	
	UserServiceView() {
		Scanner sc = new Scanner(System.in);
		//유저의 상품데이터를 가지고 있는 DAO객체 생성
		ItemDAO itemDao = new ItemDAO();
		//itemDao객체가 모든 상품의 목록을 가지고 있게 한다.
		itemDao.getAllItem(); //(ItemDAO[1]참고)
		
		//리턴값 ItemDTO
		while(true) {
			//View
			System.out.println("=== 추천 선물 목록 ===");
			//랜덤으로 상품한개를 보여주고 randomItem에 상품을 잠시 기억한다.
			ItemDTO randomItem = itemDao.showOneItem(); //(ItemDAO[2]참고)
			
			System.out.println("[1]다른 상품 보기");
			System.out.println("[2]모든 상품 보기 ");
			System.out.println("[3]위에 상품 장바구니에 담기");
			System.out.println("[4]장바구니 담긴 목록 보기");
			System.out.println("[5]장바구니 상품 선택 제거하기 ");
			System.out.println("[6]상품 구매하기 ");
			System.out.println("[7]서비스 화면으로 ");
			int num = sc.nextInt();
			
			//Controller
			
			//1. 다른상품보기
			if(num==1) { 
				System.out.println("=== 다른 추천 상품입니다. ===");
			} 
			//2. 모든 상품 보기
			else if(num==2) {
				System.out.println("=== 모든 상품 목록입니다. ===");
				itemDao.showAllItem();//(ItemDAO[3]참고)
			}
			//3. 장바구니 담기
			else if(num==3) {
				System.out.println("구매할 수량을 입력해주세요");
				//랜덤으로 보여준 상품에 담을 수량을 선택한다.
				randomItem.setItemNum(sc.nextInt()); //장바구니에 담을 상품 수량 넣기
				itemDao.addItemCart(randomItem);//(ItemDAO[4]참고) //장바구니에 상품 추가
			} 
			//4. 장바구니에 담긴 목록 보기
			else if(num==4) { 
				System.out.println("=== 장바구니에 담긴 목록입니다. ===");
				itemDao.showItemCart();//(ItemDAO[5]참고)
			} 
			//5. 장바구니 상풍 선택 제거
			else if(num==5) {
				int removeNum = -1;
				while(removeNum!=0) {
					System.out.println("=== 장바구니에 담긴 목록입니다. ===");
					itemDao.showItemCart();//(ItemDAO[5]참고)
					System.out.println("\n제거하고 싶은 품목이 없으면 0, 있다면 상품 번호를 입력");
					removeNum = sc.nextInt();
					if(removeNum>0) {
						itemDao.removeItemCart(removeNum);//(ItemDAO[6]참고)
					}
				}
			}
			//6. 상품 구매하기
			else if(num==6) {
				while(true) {
					System.out.println("\n====== 장바구니에 담긴 구매할 목록입니다. ======");
					itemDao.showItemCart();
					System.out.println("장바구니의 담긴 총 합계는:"+itemDao.sumItemCart());//(ItemDAO[7]참고)
					System.out.println("장바구니에 담긴 목록을 구매하시겠습니까? Y/N");
					String choice = sc.next();
					if(choice.equals("Y") || choice.equals("y")) { 
						//구매를 할 쿼리문을 작성
						itemDao.buyItemCart();//(ItemDAO[8]참고)
						break;
					}
					else if(choice.equals("N") || choice.equals("n")) {
						break;
					}
					else {
						System.out.println("잘못된 입력입니다. 다시 입력해주세요");
					}
				}
			} 
			//7. 서비스화면으로
			else if(num==7) {
				System.out.println("=== 서비스 화면으로 이동하겠습니다. ===");
				break;
			}
			else {
				System.out.println("=== 잘못된 입력을 하였습니다. 1~7번중에 선택해주세요 ===");
			}
		}
	}
}

