package com.example.processor.filetype;

import java.sql.Date;

public class PimDelivery {
	private Integer ordQty;
	private Date inDcDate;
	private Date shipDate;
	private Date cancelDate;
	
	public Integer getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}
	public Date getInDcDate() {
		return inDcDate;
	}
	public void setInDcDate(Date inDcDate) {
		this.inDcDate = inDcDate;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	
}
