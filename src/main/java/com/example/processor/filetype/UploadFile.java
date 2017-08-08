package com.example.processor.filetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadFile {
	protected String fileNameContains;
	private ArrayList<String> columnNames;
	private Map<String,Integer> columnPositions;
	
	public UploadFile() {
		columnNames = new ArrayList<String>();
		columnPositions = new HashMap<String,Integer>();
		
		setFileNameContains("orderheader");

		ArrayList<String> columnNames = getColumnNames();
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
	
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}
	public Map<String, Integer> getColumnPositions() {
		return columnPositions;
	}
	public void setColumnPositions(Map<String, Integer> columnPositions) {
		this.columnPositions = columnPositions;
	}
	public String getFileNameContains() {
		return fileNameContains;
	}
	protected void setFileNameContains(String fileNameContains) {
		this.fileNameContains = fileNameContains;
	}
}
