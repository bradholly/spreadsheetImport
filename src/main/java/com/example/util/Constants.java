package com.example.util;

public final class Constants {
	public static final String jpaUrl = "https://jpademo.local.pcfdev.io/";
	
	public static final String orderHeaderFile = "orderheader";
	public static final String orderHeaderUrl = jpaUrl.concat("orderHeaders");

	public static final String orderDetailFile = "orderdetail";
	public static final String orderDetailUrl = jpaUrl.concat("orderDetails");
	public static final String orderDetailSearchUrl = jpaUrl.concat("orderDetails/search/findByOrderNoAndItemNo?orderNo={orderNo}&itemNo={itemNo}");
	
	public static final String leadTimeFile = "leadtime";
	public static final String LeadTimeUrl = jpaUrl.concat("leadTimes");
	public static final String LeadTimeSearchUrl = jpaUrl.concat("leadTimes/search/findByOrderTypeAndWarehouseAndWalmartDc?orderType={orderType}&warehouse={warehouse}&walmartDc={walmartDc}");
	
	public static final String pimFile = "pimregular";
	public static final String pimCarryoverFile = "pimcarryover";
	public static final String pimUrl = jpaUrl.concat("pims");
	public static final String pimSearchUrl = jpaUrl.concat("pims/search/findByUpcAndColorAndSizeAndShipDate?upc={upc}&color={color}&size={size}&shipDate={shipDate}");
	
	public static final String skuFile = "sku";
	public static final String skuUrl = jpaUrl.concat("skus");
}
