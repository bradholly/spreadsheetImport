package com.example.processor.filetype;

import java.util.ArrayList;

import com.example.util.Constants;

public class OrderHeaderFileDefinition extends FileDefinition {
	public OrderHeaderFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();
		
		setFileNameContains(Constants.orderHeaderFile);

		columnNames.add("OrderNoCompany");			//0
		columnNames.add("PONum");					//1
		columnNames.add("Warehouse");				//2
		columnNames.add("Division");				//3
		columnNames.add("Customer");				//4
		columnNames.add("Store");					//5
		columnNames.add("OrderType");				//6
		columnNames.add("OrderReference");			//7
		columnNames.add("OrderDate");				//8
		columnNames.add("MABDate");					//9
		columnNames.add("CancelDate");				//10
		columnNames.add("PriorityDate");			//11
		columnNames.add("ShipOnDate");				//12
		columnNames.add("CfmShipOnDate");			//13
		columnNames.add("CalculatedShipOnDate");	//14
		columnNames.add("InvoiceDate");				//15
		columnNames.add("Open Order Flag");			//16
		columnNames.add("Cancel Order Flag");		//17
		
		setColumnNames(columnNames);
	}
}
