package view_controller;

import java.util.Scanner;

import DAO.ManagerDAO;

public class ManagerServiceView {
	

		public ManagerServiceView() {
			Scanner sc=new Scanner(System.in);
			ManagerDAO managerDAO = new ManagerDAO();

			while(true) {
				System.out.println("====== 관리자 모드 입니다 ======");
				System.out.println("[1]상품 추가");
				System.out.println("[2]상품 조회");
				System.out.println("[3]상품 재고 추가");
				System.out.println("[4]상품 삭제");
				System.out.println("[5]종료");
				System.out.print("입력:");
				int act=sc.nextInt();
				
				if(act==1) {
					System.out.print("상품이름:");
					String ItemName=sc.next();
					System.out.print("상품 가격:");
					int ItemPrice=sc.nextInt();
					System.out.print("상품 재고:");
					int ItemStock=sc.nextInt();
					managerDAO.addItem(0,ItemName,ItemPrice,ItemStock);
				}
				else if(act==2) {     
					managerDAO.showItem();
				}
				else if(act==3) {
					System.out.print("재고 추가할 상품 번호:");
					int itemNo=sc.nextInt();
					System.out.print("추가할 재고 개수:");
					int itemNum=sc.nextInt();
					managerDAO.addStock(itemNo, itemNum);
				}
				else if(act==4) {
					System.out.print("삭제할 상품번호:");
					int itemNo=sc.nextInt();
					managerDAO.deleteItem(itemNo);
				}
				else if(act==5) {
					System.out.println("종료");
					break;
				}
				else {
					System.out.println("다시 입력해주십시오");
					continue;
				}
			}

		}
	
}