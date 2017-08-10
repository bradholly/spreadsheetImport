package com.example.processor.filetype;

import java.sql.Date;

import com.example.processor.filetype.links.Links;

public class LeadTimeFile {
	private Integer leadTimePk;
	//0
	private String orderType;
	//1
	private String warehouse;
	//2
	private String walmartDc;
	//3
	private String city;
	//4
	private String state;
	//5
	private Integer mileage;
	//6
	private Integer safetyDays;
	//7
	private Integer shipLeadTime;
	//8
	private Date createdDate;
	//9
	private Date updatedDate;
	//10
	private String updatedUser;
	
	private Links _links;
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWalmartDc() {
		return walmartDc;
	}
	public void setWalmartDc(String walmartDc) {
		this.walmartDc = walmartDc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getMileage() {
		return mileage;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	public Integer getSafetyDays() {
		return safetyDays;
	}
	public void setSafetyDays(Integer safetyDays) {
		this.safetyDays = safetyDays;
	}
	public Integer getShipLeadTime() {
		return shipLeadTime;
	}
	public void setShipLeadTime(Integer shipLeadTime) {
		this.shipLeadTime = shipLeadTime;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Links get_links() {
		return _links;
	}
	public void set_links(Links _links) {
		this._links = _links;
	}
	public Integer getLeadTimePk() {
		return leadTimePk;
	}
	public void setLeadTimePk(Integer leadTimePk) {
		this.leadTimePk = leadTimePk;
	}
	
	
}
