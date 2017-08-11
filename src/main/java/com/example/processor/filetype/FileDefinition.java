package com.example.processor.filetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileDefinition {
	private ArrayList<String> columnNames = new ArrayList<String>();
	private Map<String,Integer> columnPositions = new HashMap<String,Integer>();
	
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
	
	
}
