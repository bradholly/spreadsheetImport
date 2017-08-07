package com.example.processor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.example.processor.filetype.OrderHeaderFile;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

@Component
public class FileConverter {
	public List<OrderHeaderFile> getOrderHeaderContents(CSVReader csvReader) {
		try {
			OrderHeaderFile orderHeaderFile = new OrderHeaderFile();
			List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();

			ArrayList<String> columnNames = orderHeaderFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				System.out.println("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				orderHeaderFile = new OrderHeaderFile();
				Iterator<String> i = columnNames.iterator();
				Integer count = 0;
				// read file columns
				while(i.hasNext()) {
					String columnName = i.next();
					Integer position = columnPositions.get(columnName);

					switch (count) {
					case 0: 
						orderHeaderFile.setOrderNo(Integer.valueOf(dataLine[position]));
						break;
					case 1:
						orderHeaderFile.setPoNbr(dataLine[position]);
						break;
					case 2:
						orderHeaderFile.setWarehouse(dataLine[position]);
						break;
					case 3:
						orderHeaderFile.setDivision(Integer.valueOf(dataLine[position]));
						break;
					case 4:
						orderHeaderFile.setCustomer(Integer.valueOf(dataLine[position]));
						break;
					case 5:
						orderHeaderFile.setWalmartDc(StringUtils.substring(dataLine[position],0,4));
						break;
					case 6:
						orderHeaderFile.setOrderType(dataLine[position]);
						break;
					case 7:
						orderHeaderFile.setOrderReference(dataLine[position]);
						break;
					case 8:
						orderHeaderFile.setOrderDate(getDate(dataLine[position]));
						break;
					case 9:
						orderHeaderFile.setMabDate(getDate(dataLine[position]));
						break;
					case 10:
						orderHeaderFile.setCancelDate(getDate(dataLine[position]));
						break;
					case 11:
						orderHeaderFile.setPriorityDate(getDate(dataLine[position]));
						break;
					case 12:
						orderHeaderFile.setShipOnDate(getDate(dataLine[position]));
						break;
					case 13:
						orderHeaderFile.setConfirmShipOnDate(getDate(dataLine[position]));
						break;
					case 14:
						orderHeaderFile.setCalcShipOnDate(getDate(dataLine[position]));
						break;
					case 15:
						orderHeaderFile.setInvoiceDate(getDate(dataLine[position]));
						break;
					case 16:
						orderHeaderFile.setOpenFlag(dataLine[position]);
						break;
					case 17:
						orderHeaderFile.setCancelFlag(dataLine[position]);
						break;
					}

					//					columnNames.add("OrderNoCompany");			//0
					//					columnNames.add("PONum");					//1
					//					columnNames.add("Warehouse");				//2
					//					columnNames.add("Division");				//3
					//					columnNames.add("Customer");				//4
					//					columnNames.add("Store");					//5
					//					columnNames.add("OrderType");				//6
					//					columnNames.add("OrderReference");			//7
					//					columnNames.add("OrderDate");				//8
					//					columnNames.add("MABDate");					//9
					//					columnNames.add("CancelDate");				//10
					//					columnNames.add("PriorityDate");			//11
					//					columnNames.add("ShipOnDate");				//12
					//					columnNames.add("CfmShipOnDate");			//13
					//					columnNames.add("CalculatedShipOnDate");	//14
					//					columnNames.add("InvoiceDate");				//15
					//					columnNames.add("Open Order Flag");			//16
					//					columnNames.add("Cancel Order Flag");		//17					

					count++;
				}

				orderHeaderFileList.add(orderHeaderFile);
				dataLine = csvReader.readNext();

			}
			return orderHeaderFileList;
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return null;
	}

	/**
	 * Accepts array of column names from file, and list of columns to search for, and returns the searched columns and position in the file as a map
	 * 
	 * @param line String array of all columns
	 * @param columnNames List of columns that need their positions found
	 * @return Map of column names and positions in file
	 */
	public Map<String,Integer> getColumnPositions(String[] line, ArrayList<String> columnNames) {
		Map<String,Integer> columnPositions = new HashMap<String,Integer>();

		//iterate file column array
		for (int i=0; i<line.length; i++) {
			if (columnNames.contains(line[i])) {
				columnPositions.put(line[i], i);
			}
		}

		return columnPositions;
	}

	public Date getDate(String dateStr) {
		try {
			final SimpleDateFormat sdf1 = new SimpleDateFormat("mm/dd/yy"); 
			java.util.Date date = sdf1.parse(dateStr); 
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			return sqlStartDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getJson(List<OrderHeaderFile> orderHeaderFileList) {
		String json = null;
		final ObjectMapper mapper = new ObjectMapper();
		final OutputStream out = new ByteArrayOutputStream();

		try {
			mapper.writeValue(out, orderHeaderFileList);

			String result = out.toString();
			System.out.println(new String(result));
			return result;
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
