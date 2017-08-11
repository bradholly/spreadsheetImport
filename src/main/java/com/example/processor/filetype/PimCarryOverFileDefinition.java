package com.example.processor.filetype;

import java.util.ArrayList;

public class PimCarryOverFileDefinition extends FileDefinition{
	public static final String upc = "13 DIGIT UPC NUMBER";
	public static final String vendorPackQty = "VENDOR PACK SIZE";
	public static final String ordQty1 = "1ST DELIVERY UNITS";
	public static final String ordQty2 = "2ND DELIVERY UNITS";
	public static final String ordQty3 = "3RD DELIVERY UNITS";
	public static final String price = "COST";
	public static final String inDcDate1 = "1ST IN- D.C DATE";
	public static final String inDcDate2 = "2ND IN- D.C DATE";
	public static final String inDcDate3 = "3RD IN- D.C DATE";
	public static final String vendorSku = "VENDOR STOCK NUMBER";
	public static final String startShipDate1 = "1ST SHIP DATE";
	public static final String startShipDate2 = "2ND SHIP DATE";
	public static final String startShipDate3 = "3RD SHIP DATE";
	public static final String cancelDate1 = "1ST CANCEL DATE";
	public static final String cancelDate2 = "2ND CANCEL DATE";
	public static final String cancelDate3 = "3RD CANCEL DATE";
	
	public PimCarryOverFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();
						
		columnNames.add("13 DIGIT UPC NUMBER");		//0 0
		columnNames.add("VENDOR PACK SIZE");		//1 1
		columnNames.add("1ST DELIVERY UNITS");		//2 2
		columnNames.add("2ND DELIVERY UNITS");		//2 3
		columnNames.add("3RD DELIVERY UNITS");		//2 4
		columnNames.add("COST");					//3 5
		columnNames.add("1ST IN- D.C DATE");		//4 6
		columnNames.add("2ND IN- D.C DATE");		//4 7
		columnNames.add("3RD IN- D.C DATE");		//4 8
		columnNames.add("VENDOR STOCK NUMBER");		//5 9
		columnNames.add("1ST SHIP DATE");			//6 10
		columnNames.add("2ND SHIP DATE");			//6 11
		columnNames.add("3RD SHIP DATE");			//6 12
		columnNames.add("1ST CANCEL DATE");			//7 13
		columnNames.add("2ND CANCEL DATE");			//7 14
		columnNames.add("3RD CANCEL DATE");			//7 15
		
		setColumnNames(columnNames);
	}
}
