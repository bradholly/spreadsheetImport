package com.example.processor.filetype;

import java.util.ArrayList;

public class PimFileDefinition extends FileDefinition{
	public static final String upc = "UPC (13 Digits)"; 
	public static final String vendorPackQty = "Vendor Pack Quantity";
	public static final String price = "Initial Cost"; 
	public static final String vendorSku = "Vendor Stock Number";

	public static final String ordQty1 = "1st Delivery Units";
	public static final String startShipDate1 = "1st Ship Date";
	public static final String inDcDate1 = "1st In-DC Date";
	public static final String cancelDate1 = "1st Cancel Date";
	
	public static final String ordQty2 = "2nd Delivery Units";
	public static final String startShipDate2 = "2nd Ship Date";
	public static final String inDcDate2 = "2nd In-DC Date";
	public static final String cancelDate2 = "2nd Cancel Date";

	public static final String ordQty3 = "3rd Delivery Units";
	public static final String startShipDate3 = "3rd Ship Date";
	public static final String inDcDate3 = "3rd In-DC Date";
	public static final String cancelDate3 = "3rd Cancel Date";
	
	public static final String ordQty4 = "4th Delivery Units";
	public static final String startShipDate4 = "4th Ship Date";
	public static final String inDcDate4 = "4th In-DC Date";
	public static final String cancelDate4 = "4th Cancel Date";
	
	public static final String ordQty5 = "5th Delivery Units";
	public static final String startShipDate5 = "5th Ship Date";
	public static final String inDcDate5 = "5th In-DC Date";
	public static final String cancelDate5 = "5th Cancel Date";
	
	public static final String ordQty6 = "6th Delivery Units";
	public static final String startShipDate6 = "6th Ship Date";
	public static final String inDcDate6 = "6th In-DC Date";
	public static final String cancelDate6 = "6th Cancel Date";

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
		columnNames.add(ordQty3);
		columnNames.add(startShipDate3);
		columnNames.add(inDcDate3);
		columnNames.add(cancelDate3);
		columnNames.add(ordQty4);;
		columnNames.add(startShipDate4);
		columnNames.add(inDcDate4);
		columnNames.add(cancelDate4);
		columnNames.add(ordQty5);
		columnNames.add(startShipDate5);
		columnNames.add(inDcDate5);
		columnNames.add(cancelDate5);
		columnNames.add(ordQty6);
		columnNames.add(startShipDate6);
		columnNames.add(inDcDate6);
		columnNames.add(cancelDate6);
		
		setColumnNames(columnNames);
	}	
	
	public static String getUpc() {
		return upc;
	}



	public static String getVendorpackqty() {
		return vendorPackQty;
	}



	public static String getPrice() {
		return price;
	}



	public static String getVendorsku() {
		return vendorSku;
	}



	public static String getOrdqty1() {
		return ordQty1;
	}



	public static String getStartshipdate1() {
		return startShipDate1;
	}



	public static String getIndcdate1() {
		return inDcDate1;
	}



	public static String getCanceldate1() {
		return cancelDate1;
	}



	public static String getOrdqty2() {
		return ordQty2;
	}



	public static String getStartshipdate2() {
		return startShipDate2;
	}



	public static String getIndcdate2() {
		return inDcDate2;
	}



	public static String getCanceldate2() {
		return cancelDate2;
	}



	public static String getOrdqty3() {
		return ordQty3;
	}



	public static String getStartshipdate3() {
		return startShipDate3;
	}



	public static String getIndcdate3() {
		return inDcDate3;
	}



	public static String getCanceldate3() {
		return cancelDate3;
	}



	public static String getOrdqty4() {
		return ordQty4;
	}



	public static String getStartshipdate4() {
		return startShipDate4;
	}



	public static String getIndcdate4() {
		return inDcDate4;
	}



	public static String getCanceldate4() {
		return cancelDate4;
	}



	public static String getOrdqty5() {
		return ordQty5;
	}



	public static String getStartshipdate5() {
		return startShipDate5;
	}



	public static String getIndcdate5() {
		return inDcDate5;
	}



	public static String getCanceldate5() {
		return cancelDate5;
	}



	public static String getOrdqty6() {
		return ordQty6;
	}



	public static String getStartshipdate6() {
		return startShipDate6;
	}



	public static String getIndcdate6() {
		return inDcDate6;
	}



	public static String getCanceldate6() {
		return cancelDate6;
	}




}
