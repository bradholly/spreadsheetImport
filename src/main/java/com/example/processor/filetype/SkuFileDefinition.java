package com.example.processor.filetype;

import java.util.ArrayList;

public class SkuFileDefinition extends FileDefinition {
	public SkuFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();
						
		columnNames.add("ItemNo");			//0
		columnNames.add("Season");			//1
		columnNames.add("Style");			//2
		columnNames.add("Color");			//3
		columnNames.add("Dimension");		//4
		columnNames.add("Size");			//5
		columnNames.add("Division");		//6
		columnNames.add("PriceA");			//7
		columnNames.add("UPC");				//8
		columnNames.add("GTINNumber");		//9
		columnNames.add("WMSTYLE#2");		//10
		columnNames.add("WMITEM#2");		//11
		columnNames.add("Fineline");		//12
		
		setColumnNames(columnNames);
	}
}
