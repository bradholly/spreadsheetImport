package com.example.processor.filetype;

import java.sql.Date;
import java.util.ArrayList;

public class OrderHeaderFile {


	private Integer orderNo;
	private String poNbr;
	private String warehouse;
	private Integer division;
	private Integer customer;
	private String walmartDc;
	private String orderType;
	private String orderReference;
	private Date orderDate;
	private Date mabDate;
	private Date cancelDate;
	private Date priorityDate;
	private Date shipOnDate;
	private Date confirmShipOnDate;
	private Date calcShipOnDate;
	private Date invoiceDate;
	private String openFlag;
	private String cancelFlag;
	
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getPoNbr() {
		return poNbr;
	}
	public void setPoNbr(String poNbr) {
		this.poNbr = poNbr;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public Integer getDivision() {
		return division;
	}
	public void setDivision(Integer division) {
		this.division = division;
	}
	public Integer getCustomer() {
		return customer;
	}
	public void setCustomer(Integer customer) {
		this.customer = customer;
	}
	public String getWalmartDc() {
		return walmartDc;
	}
	public void setWalmartDc(String walmartDc) {
		this.walmartDc = walmartDc;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderReference() {
		return orderReference;
	}
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getMabDate() {
		return mabDate;
	}
	public void setMabDate(Date mabDate) {
		this.mabDate = mabDate;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public Date getPriorityDate() {
		return priorityDate;
	}
	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}
	public Date getShipOnDate() {
		return shipOnDate;
	}
	public void setShipOnDate(Date shipOnDate) {
		this.shipOnDate = shipOnDate;
	}
	public Date getConfirmShipOnDate() {
		return confirmShipOnDate;
	}
	public void setConfirmShipOnDate(Date confirmShipOnDate) {
		this.confirmShipOnDate = confirmShipOnDate;
	}
	public Date getCalcShipOnDate() {
		return calcShipOnDate;
	}
	public void setCalcShipOnDate(Date calcShipOnDate) {
		this.calcShipOnDate = calcShipOnDate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
}
