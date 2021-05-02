package DTO;

public class ItemDTO {

	private int itemNo;
	private String itemName;
	private int itemPrice;
	private int itemNum; //상품 DB에서는 전체 갯수로 사용되고 장바구니에 담을 떄는 담은 갯수로 사용
	
	public ItemDTO(int itemNo, String itemName, int itemPrice, int itemNum) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemNum = itemNum;
	}
	
	public ItemDTO(ItemDTO dto) {
		this.itemNo = dto.itemNo;
		this.itemName = dto.itemName;
		this.itemPrice = dto.itemPrice;
		this.itemNum = dto.itemNum;
	}
	
	
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	
	
	
	
}
