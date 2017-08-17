package com.example.processor;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.processor.filetype.LeadTimeFile;
import com.example.processor.filetype.LeadTimeFileDefinition;
import com.example.processor.filetype.OrderDetailFile;
import com.example.processor.filetype.OrderDetailFileDefinition;
import com.example.processor.filetype.OrderHeaderFile;
import com.example.processor.filetype.OrderHeaderFileDefinition;
import com.example.processor.filetype.PimCarryOverFileDefinition;
import com.example.processor.filetype.PimDelivery;
import com.example.processor.filetype.PimFile;
import com.example.processor.filetype.PimFileDefinition;
import com.example.processor.filetype.SkuFile;
import com.example.processor.filetype.SkuFileDefinition;
import com.opencsv.CSVReader;

/**
 * Class of converters to bring CSVReader raw data data into the appropriate com.example.processor.filetype.* target class
 * 
 * @author Brad
 *
 */
@Component
public class FileConverter {
	Logger logger = LoggerFactory.getLogger(FileConverter.class);
	
	/**
	 * Convert CSVReader into list of OrderHeaderFile
	 * 
	 * @param csvReader
	 * @return
	 */
	public List<OrderHeaderFile> getOrderHeaderContents(CSVReader csvReader) {
		try {
			OrderHeaderFileDefinition uploadFile = new OrderHeaderFileDefinition();
			List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				OrderHeaderFile orderHeaderFile = new OrderHeaderFile();
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
			final SimpleDateFormat sdf1 = new SimpleDateFormat("M/d/y"); 
			java.util.Date date = sdf1.parse(dateStr); 
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			return sqlStartDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Date getDateFromTimestamp(String dateStr) {
		try {
			final SimpleDateFormat sdf1 = new SimpleDateFormat("M/d/y H:m"); 
			java.util.Date date = sdf1.parse(dateStr); 
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			return sqlStartDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<OrderDetailFile> getOrderDetailContents(CSVReader csvReader) {
		try {
			OrderDetailFileDefinition uploadFile = new OrderDetailFileDefinition();
			List<OrderDetailFile> orderDetailFileList = new ArrayList<OrderDetailFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				OrderDetailFile orderDetailFile = new OrderDetailFile();
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

	public List<SkuFile> getSkuContents(CSVReader csvReader) {
		try {
			SkuFileDefinition uploadFile = new SkuFileDefinition();
			List<SkuFile> skuFileList = new ArrayList<SkuFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				SkuFile skuFile = new SkuFile();
				Iterator<String> i = columnNames.iterator();
				Integer count = 0;

				// read file columns
				while (i.hasNext()) {
					String columnName = i.next();
					Integer position = columnPositions.get(columnName);

					switch (count) {
					case 0: 
						skuFile.setItemNo(Integer.valueOf(dataLine[position]));
						break;
					case 1:
						skuFile.setSeason(dataLine[position]);
						break;
					case 2:
						skuFile.setStyle(dataLine[position]);
						break;
					case 3:
						skuFile.setColor(dataLine[position]);
						break;
					case 4:
						skuFile.setDim(dataLine[position]);
						break;
					case 5:
						skuFile.setSize(dataLine[position]);
						break;
					case 6:
						skuFile.setDivision(Integer.valueOf(dataLine[position]));
						break;
					case 7:
						if(NumberUtils.isCreatable(dataLine[position])) {
							skuFile.setUnitPrice(new BigDecimal(dataLine[position]));
						}
						break;
					case 8:
						skuFile.setUpc(dataLine[position]);
						break;
					case 9:
						skuFile.setGtin(dataLine[position]);
						break;
					case 10:
						skuFile.setWmStyle(dataLine[position]);
						break;
					case 11:
						skuFile.setWmItem(dataLine[position]);
						break;
					case 12:
						if(StringUtils.isNumeric(dataLine[position])) {
							skuFile.setFineLine(Integer.valueOf(dataLine[position]));							
						}
						break;
					case 13:
						if(NumberUtils.isCreatable(dataLine[position])) {
							skuFile.setWeight(new BigDecimal(dataLine[position]));							
						}
						break;
					case 14:
						if(NumberUtils.isCreatable(dataLine[position])) {
							skuFile.setVolume(new BigDecimal(dataLine[position]));							
						}
						break;
					}
					count++;
				}

				skuFileList.add(skuFile);
				dataLine = csvReader.readNext();

			}
			return skuFileList;
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return null;
	}

	public List<LeadTimeFile> getLeadTimeContents(CSVReader csvReader) {
		try {
			LeadTimeFileDefinition uploadFile = new LeadTimeFileDefinition();
			List<LeadTimeFile> leadTimeFileList = new ArrayList<LeadTimeFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				LeadTimeFile leadTimeFile = new LeadTimeFile();
				Iterator<String> i = columnNames.iterator();
				Integer count = 0;

				// read file columns
				while (i.hasNext()) {
					String columnName = i.next();
					Integer position = columnPositions.get(columnName);

					switch (count) {
					case 0: 
						leadTimeFile.setOrderType(StringUtils.leftPad(dataLine[position], 4, "0"));
						break;
					case 1:
						leadTimeFile.setWarehouse(dataLine[position]);
						break;
					case 2:
						leadTimeFile.setWalmartDc(StringUtils.leftPad(dataLine[position], 5, "0"));
						break;
					case 3:
						leadTimeFile.setCity(dataLine[position]);
						break;
					case 4:
						leadTimeFile.setState(dataLine[position]);
						break;
					case 5:
						leadTimeFile.setMileage(Integer.valueOf(dataLine[position]));
						break;
					case 6:
						leadTimeFile.setSafetyDays(Integer.valueOf(dataLine[position]));
						break;
					case 7:
						leadTimeFile.setShipLeadTime(Integer.valueOf(dataLine[position]));	
						break;
					case 8:
						leadTimeFile.setCreatedDate(getDateFromTimestamp(dataLine[position]));
						break;
					case 9:
						leadTimeFile.setUpdatedDate(getDate(dataLine[position]));
						break;
					case 10:
						leadTimeFile.setUpdatedUser(dataLine[position]);
						break;
					}
					count++;
				}

				leadTimeFileList.add(leadTimeFile);
				dataLine = csvReader.readNext();

			}
			return leadTimeFileList;
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return null;
	}

	public List<PimFile> getPimContents(CSVReader csvReader) {
		try {
			PimFileDefinition uploadFile = new PimFileDefinition();
			List<PimFile> pimFileList = new ArrayList<PimFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			csvReader.readNext(); // skip first line
			String[] firstLine = trimStringArray(csvReader.readNext());

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			csvReader.readNext(); // skip lines 3-5
			csvReader.readNext();
			csvReader.readNext();
			String[] dataLine = trimStringArray(csvReader.readNext());

			// read file lines
			while(dataLine != null) {
				List<PimDelivery> pimDeliveries = getPimDeliveries(dataLine, columnPositions);
				Iterator<PimDelivery> itr = pimDeliveries.iterator();
				while(itr.hasNext()) {
					PimDelivery pimDelivery = itr.next();
					PimFile pimFile = new PimFile();
					pimFile.setCancelDate(pimDelivery.getCancelDate());
					pimFile.setInDcDate(pimDelivery.getInDcDate());
					pimFile.setShipDate(pimDelivery.getShipDate());
					pimFile.setOrdQty(pimDelivery.getOrdQty());
					pimFile.setPackQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.vendorPackQty)]));
					pimFile.setUnitPrice(new BigDecimal(dataLine[columnPositions.get(PimFileDefinition.price)]));
					pimFile.setUpc(dataLine[columnPositions.get(PimFileDefinition.upc)]);
					pimFile.setVendorSku(dataLine[columnPositions.get(PimFileDefinition.vendorSku)]);
					pimFile.setColor(dataLine[columnPositions.get(PimFileDefinition.color)]);
					pimFile.setSize(dataLine[columnPositions.get(PimFileDefinition.size)]);
					pimFileList.add(pimFile);
				}

				dataLine = trimStringArray(csvReader.readNext());

			}
			return pimFileList;
		} catch (IOException e) {
			logger.error("caught IOException processing pim file contents", e);
		} catch (Exception e) {
			logger.error("caught Exception processing pim file contents", e);
		}
		return null;
	}

	/**
	 * If there's an ordered qty for one of the delivery windows, return the qty and dates as list entries
	 * 
	 * @param dataLine
	 * @param columnPositions
	 * @return
	 */
	private List<PimDelivery> getPimDeliveries(String[] dataLine, Map<String, Integer> columnPositions) {
		List<PimDelivery> pimDeliveries = new ArrayList<PimDelivery>();
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty1)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty1)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty1)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate1)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate1)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate1)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty2)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty2)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty2)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate2)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate2)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate2)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty3)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty3)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty3)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate3)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate3)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate3)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty4)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty4)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty4)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate4)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate4)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate4)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty5)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty5)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty5)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate5)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate5)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate5)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimFileDefinition.ordQty6)])
				&& 0 < Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty6)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimFileDefinition.ordQty6)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimFileDefinition.cancelDate6)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimFileDefinition.inDcDate6)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimFileDefinition.startShipDate6)]));
			pimDeliveries.add(pimDelivery);
		}
		
		return pimDeliveries;
	}

	public List<PimFile> getPimCarryoverContents(CSVReader csvReader) {
		try {
			PimCarryOverFileDefinition uploadFile = new PimCarryOverFileDefinition();
			List<PimFile> pimFileList = new ArrayList<PimFile>();

			ArrayList<String> columnNames = uploadFile.getColumnNames();

			String[] firstLine = csvReader.readNext();

			Map<String,Integer> columnPositions = getColumnPositions(firstLine,columnNames);

			if (columnNames.size() != columnPositions.size()) {
				logger.error("column position map size not equal column name list size");
				return null;
			}

			String[] dataLine = csvReader.readNext();

			// read file lines
			while(dataLine != null) {
				List<PimDelivery> pimDeliveries = getPimCarryoverDeliveries(dataLine, columnPositions);
				Iterator<PimDelivery> itr = pimDeliveries.iterator();
				while(itr.hasNext()) {
					PimDelivery pimDelivery = itr.next();
					PimFile pimFile = new PimFile();
					pimFile.setCancelDate(pimDelivery.getCancelDate());
					pimFile.setInDcDate(pimDelivery.getInDcDate());
					pimFile.setShipDate(pimDelivery.getShipDate());
					pimFile.setOrdQty(pimDelivery.getOrdQty());
					pimFile.setPackQty(Integer.valueOf(dataLine[columnPositions.get(PimCarryOverFileDefinition.vendorPackQty)]));
					pimFile.setUnitPrice(new BigDecimal(dataLine[columnPositions.get(PimCarryOverFileDefinition.price)]));
					pimFile.setUpc(dataLine[columnPositions.get(PimCarryOverFileDefinition.upc)]);
					pimFile.setVendorSku(dataLine[columnPositions.get(PimCarryOverFileDefinition.upc)]);
					
					pimFileList.add(pimFile);
				}

				dataLine = csvReader.readNext();

			}
			return pimFileList;
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return null;
	}
	
	/**
	 * If there's an ordered qty for one of the delivery windows, return the qty and dates as list entries
	 * 
	 * @param dataLine
	 * @param columnPositions
	 * @return
	 */
	private List<PimDelivery> getPimCarryoverDeliveries(String[] dataLine, Map<String, Integer> columnPositions) {
		List<PimDelivery> pimDeliveries = new ArrayList<PimDelivery>();
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty1)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty1)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.cancelDate1)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.inDcDate1)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.startShipDate1)]));
			pimDeliveries.add(pimDelivery);
		}

		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty2)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty2)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.cancelDate2)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.inDcDate2)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.startShipDate2)]));
			pimDeliveries.add(pimDelivery);
		}
		
		if (StringUtils.isNumeric(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty3)])) {
			PimDelivery pimDelivery = new PimDelivery();
			pimDelivery.setOrdQty(Integer.valueOf(dataLine[columnPositions.get(PimCarryOverFileDefinition.ordQty3)]));
			pimDelivery.setCancelDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.cancelDate3)]));
			pimDelivery.setInDcDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.inDcDate3)]));
			pimDelivery.setShipDate(getDate(dataLine[columnPositions.get(PimCarryOverFileDefinition.startShipDate3)]));
			pimDeliveries.add(pimDelivery);
		}
		
		return pimDeliveries;
	}
	
	public String[] trimStringArray(String[] data) {
		if (null == data) {
			return data;
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = data[i].trim();
		}
		return data;
	}
}
