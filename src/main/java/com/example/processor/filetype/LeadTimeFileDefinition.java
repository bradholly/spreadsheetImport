package com.example.processor.filetype;

import java.util.ArrayList;

import com.example.util.Constants;

public class LeadTimeFileDefinition extends FileDefinition {
	public LeadTimeFileDefinition() {
		ArrayList<String> columnNames = getColumnNames();

		setFileNameContains(Constants.orderHeaderFile);
						
		columnNames.add("ORDERTYPE");			//0
		columnNames.add("WAREHOUSE");			//1
		columnNames.add("WALMARTDC");			//2
		columnNames.add("CITY");				//3
		columnNames.add("STATE");				//4
		columnNames.add("MILAGE");				//5
		columnNames.add("SAFETYDAYS");			//6
		columnNames.add("SHIPLEADTIME");		//7
		columnNames.add("Sc_TimeCreated");		//8
		columnNames.add("Sc_TimeLastMod");		//9
		columnNames.add("Sc_UserIdLastMod");	//10
		
		setColumnNames(columnNames);
	}
}