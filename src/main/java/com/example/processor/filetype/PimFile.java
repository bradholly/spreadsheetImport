package com.example.processor.filetype;

import java.math.BigDecimal;
import java.sql.Date;

import com.example.processor.filetype.links.Links;

public class PimFile {
	private Integer pimPk;
	private String upc;				//0
	private Integer packQty;		//1
	private Integer ordQty;			//2
	private BigDecimal unitPrice;	//3
	private Date inDcDate;			//4
	private String vendorSku;		//5
	private Date shipDate;			//6
	private Date cancelDate;		//7
	private String color;			//8
	private String size;			//9
	private Links _links;
	
	public Links get_links() {
		return _links;
	}
	public void set_links(Links _links) {
		this._links = _links;
	}
	public Integer getPimPk() {
		return pimPk;
	}
	public void setPimPk(Integer pimPk) {
		this.pimPk = pimPk;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public Integer getPackQty() {
		return packQty;
	}
	public void setPackQty(Integer packQty) {
		this.packQty = packQty;
	}
	public Integer getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Integer ordQty) {
		this.ordQty = ordQty;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Date getInDcDate() {
		return inDcDate;
	}
	public void setInDcDate(Date inDcDate) {
		this.inDcDate = inDcDate;
	}
	public String getVendorSku() {
		return vendorSku;
	}
	public void setVendorSku(String vendorSku) {
		this.vendorSku = vendorSku;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
