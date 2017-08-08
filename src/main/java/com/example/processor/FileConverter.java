package com.example.processor;

import java.io.IOException;
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

import com.example.processor.filetype.OrderDetailFile;
import com.example.processor.filetype.OrderDetailFileDefinition;
import com.example.processor.filetype.OrderHeaderFile;
import com.example.processor.filetype.OrderHeaderFileDefinition;
import com.opencsv.CSVReader;

/**
 * Class of converters to bring CSVReader raw data data into the appropriate com.example.processor.filetype.* target class
 * 
 * @author Brad
 *
 */
@Component
public class FileConverter {
	/**
	 * Convert CSVReader into list of OrderHeaderFile
	 * 
	 * @param csvReader
	 * @return
	 */
	public List<OrderHeaderFile> getOrderHeaderContents(CSVReader csvReader) {
		try {
			OrderHeaderFile orderHeaderFile = new OrderHeaderFile();
			OrderHeaderFileDefinition uploadFile = new OrderHeaderFileDefinition();
			List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

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
						orderHeaderFile.setWalmartDc(StringUtils.substring(dataLine[position],0,5));
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

//	public String getJson(OrderHeaderFile orderHeaderFile) {
//		String json = null;
//		final ObjectMapper mapper = new ObjectMapper();
//		final OutputStream out = new ByteArrayOutputStream();
//
//		try {
//			mapper.writeValue(out, orderHeaderFile);
//
//			String result = out.toString();
//			System.out.println(new String(result));
//			return result;
//		} catch (JsonGenerationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	public List<OrderDetailFile> getOrderDetailContents(CSVReader csvReader) {
		try {
			OrderDetailFile orderDetailFile = new OrderDetailFile();
			OrderDetailFileDefinition uploadFile = new OrderDetailFileDefinition();
			List<OrderDetailFile> orderDetailFileList = new ArrayList<OrderDetailFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				System.out.println("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				orderDetailFile = new OrderDetailFile();
				Iterator<String> i = columnNames.iterator();
				Integer count = 0;
				
				// read file columns
				while (i.hasNext()) {
					String columnName = i.next();
					Integer position = columnPositions.get(columnName);

					switch (count) {
					case 0: 
						orderDetailFile.setOrderNo(Integer.valueOf(dataLine[position]));
						break;
					case 1:
						orderDetailFile.setItemNo(Integer.valueOf(dataLine[position]));
						break;
					case 2:
						orderDetailFile.setOrdQty(Integer.valueOf(dataLine[position]));
						break;
					case 3:
						orderDetailFile.setOpenQty(Integer.valueOf(dataLine[position]));
						break;
					case 4:
						orderDetailFile.setCancelQty(Integer.valueOf(dataLine[position]));
						break;
					case 5:
						orderDetailFile.setAllocQty(Integer.valueOf(dataLine[position]));
						break;
					case 6:
						orderDetailFile.setPickQty(Integer.valueOf(dataLine[position]));
						break;
					case 7:
						orderDetailFile.setShipQty(Integer.valueOf(dataLine[position]));
						break;
					}
					count++;
				}

				orderDetailFileList.add(orderDetailFile);
				dataLine = csvReader.readNext();

			}
			return orderDetailFileList;
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return null;
	}
}
