package com.example.processor.filetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UploadFile {
	protected String fileNameContains;
	private ArrayList<String> columnNames;
	private Map<String,Integer> columnPositions;
	
	public UploadFile() {
		fileNameContains = null;
		columnNames = new ArrayList<String>();
		columnPositions = new HashMap<String,Integer>();
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
