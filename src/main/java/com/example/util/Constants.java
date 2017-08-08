package com.example.util;

public final class Constants {
	public static final String jpaUrl = "https://jpademo.local.pcfdev.io/";
	
	public static final String orderHeaderFile = "orderheader";
	public static final String orderHeaderUrl = jpaUrl + "orderHeaders";

	public static final String orderDetailFile = "orderdetail";
	public static final String orderDetailUrl = jpaUrl + "orderDetails";
	public static final String orderDetailSearchUrl = jpaUrl.concat("orderDetails/search/findByOrderNoAndItemNo?orderNo={orderNo}&itemNo={itemNo}");
	
	public static final String leadTimeFile = "leadtime";
	public static final String LeadTimeUrl = jpaUrl + "LeadTimes";
	
	public static final String pimFile = "pim";
	public static final String pimUrl = jpaUrl + "orderPims";
	
	public static final String skuFile = "sku";
	public static final String skuUrl = jpaUrl + "skus";
}
