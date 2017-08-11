package com.example.processor.filetype;

import java.util.ArrayList;

public class PimFileDefinition extends FileDefinition{
	public static final String upc = "UPC (13 Digits)"; 
	public static final String vendorPackQty = "Vendor Pack Quantity";
	public static final String price = "Initial Cost"; 
	public static final String vendorSku = "Vendor Stock Number";
	public static final String ordQty1 = "1st Delivery Units";
	public static final String ordQty2 = "2nd Delivery Units";
	public static final String inDcDate1 = "1st In-DC Date";
	public static final String inDcDate2 = "2nd In-DC Date";
	public static final String startShipDate1 = "1st Ship Date";
	public static final String startShipDate2 = "2nd Ship Date";
	public static final String cancelDate1 = "1st Cancel Date";
	public static final String cancelDate2 = "2nd Cancel Date";

	
	
	public PimFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();
		//											  pim object position
		//												pim csv column order
		columnNames.add("UPC (13 Digits)");			//0 0
		columnNames.add("Vendor Pack Quantity");	//1 1
		columnNames.add("1st Delivery Units");		//2 2
		columnNames.add("2nd Delivery Units");		//2 3
		columnNames.add("Initial Cost");			//3 4
		columnNames.add("1st In-DC Date");			//4 5
		columnNames.add("2nd In-DC Date");			//4 6
		columnNames.add("Vendor Stock Number");		//5 7
		columnNames.add("1st Ship Date");			//6 8
		columnNames.add("2nd Ship Date");			//6 9
		columnNames.add("1st Cancel Date");			//7 10
		columnNames.add("2nd Cancel Date");			//7 11
		
		setColumnNames(columnNames);
	}
}
