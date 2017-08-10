package com.example.processor.filetype;

import java.math.BigDecimal;

public class SkuFile {
	private Integer itemNo;			//0
	private String season;			//1
	private String style;			//2
	private String color;			//3
	private String dim;				//4
	private String size;			//5
	private Integer division;		//6
	private BigDecimal unitPrice;	//7
	private String upc;				//8
	private String gtin;			//9
	private String wmStyle;			//10
	private String wmItem;			//11
	private Integer fineLine;		//12
	
	
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDim() {
		return dim;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getDivision() {
		return division;
	}
	public void setDivision(Integer division) {
		this.division = division;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getGtin() {
		return gtin;
	}
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}
	public String getWmStyle() {
		return wmStyle;
	}
	public void setWmStyle(String wmStyle) {
		this.wmStyle = wmStyle;
	}
	public Integer getFineLine() {
		return fineLine;
	}
	public void setFineLine(Integer fineLine) {
		this.fineLine = fineLine;
	}
	public String getWmItem() {
		return wmItem;
	}
	public void setWmItem(String wmItem) {
		this.wmItem = wmItem;
	}
	
	
	
}
