package com.example.processor.filetype;

import java.util.ArrayList;

public class OrderDetailFileDefinition extends FileDefinition {
	public OrderDetailFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();
						
		columnNames.add("OrderNoCompany");			//0
		columnNames.add("ItemNo");					//1
		columnNames.add("Order Qty");				//2
		columnNames.add("Open Qty");				//3
		columnNames.add("Cancel Qty");				//4
		columnNames.add("Alloc Qty");				//5
		columnNames.add("Pick Qty");				//6
		columnNames.add("Ship Qty");				//7

		setColumnNames(columnNames);
	}
}