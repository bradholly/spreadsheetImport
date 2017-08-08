package com.example.processor.filetype;

public class OrderDetailFile {
	private Integer orderDetailPk;
	private Integer orderNo;		//0
	private Integer itemNo;			//1
	private Integer ordQty;			//2
	private Integer openQty;		//3
	private Integer cancelQty;		//4
	private Integer allocQty;		//5
	private Integer pickQty;		//6
	private Integer shipQty;		//7
	
	public Integer getOrderDetailPk() {
		return orderDetailPk;
	}
	public void setOrderDetailPk(Integer orderDetailPk) {
		this.orderDetailPk = orderDetailPk;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}
	public Integer getOpenQty() {
		return openQty;
	}
	public void setOpenQty(Integer openQty) {
		this.openQty = openQty;
	}
	public Integer getCancelQty() {
		return cancelQty;
	}
	public void setCancelQty(Integer cancelQty) {
		this.cancelQty = cancelQty;
	}
	public Integer getAllocQty() {
		return allocQty;
	}
	public void setAllocQty(Integer allocQty) {
		this.allocQty = allocQty;
	}
	public Integer getPickQty() {
		return pickQty;
	}
	public void setPickQty(Integer pickQty) {
		this.pickQty = pickQty;
	}
	public Integer getShipQty() {
		return shipQty;
	}
	public void setShipQty(Integer shipQty) {
		this.shipQty = shipQty;
	}
	
	
}
