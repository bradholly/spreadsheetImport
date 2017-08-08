package com.example.processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.processor.filetype.OrderDetailFile;
import com.example.processor.filetype.OrderHeaderFile;
import com.example.storage.StorageService;
import com.example.util.Constants;
import com.opencsv.CSVReader;

@Component
public class FileManager {
    Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	@Autowired
	FileConverter fileConverter;

	@Autowired
	RestService restService;
	
	public void processFiles(StorageService storageService) {
		logger.debug("start processFiles(StorageService storageService)");
		
		Stream<Path> stream = storageService.loadAll();

		// iterate files from the storageService object
		stream.forEach(filename->{
			logger.debug("the file being processed is " + filename);

			CSVReader csvReader = getCsvReader(storageService, filename);

			if (null == csvReader) {
				logger.debug("null csvReader, returning");
				return;
			}

			// instantiate data object based on filename
			if (filename.toString().contains(Constants.orderHeaderFile)) {
				processOrderHeaderFile(csvReader);
			} else if (filename.toString().contains(Constants.orderDetailFile)) {
				processOrderDetailFile(csvReader);
			} else if (filename.toString().contains(Constants.pimFile)) {
				processPimFile(csvReader);				
			} else if (filename.toString().contains(Constants.skuFile)) {
				processSkuFile(csvReader);
			} else if (filename.toString().contains(Constants.leadTimeFile)) {
				processLeadTimeFile(csvReader);
			}
		});
		
		storageService.deleteAll();
		
		logger.debug("finish processFiles(StorageService storageService)");
	}
	
	private void processLeadTimeFile(CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

	private void processSkuFile(CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

	private void processPimFile(CSVReader csvReader) {
		// TODO Auto-generated method stub
		
	}

	private void processOrderDetailFile(CSVReader csvReader) {
		logger.debug("converting order detail type file");
		List<OrderDetailFile> orderDetailFileList = new ArrayList<OrderDetailFile>();
		orderDetailFileList = fileConverter.getOrderDetailContents(csvReader);
		if (null == orderDetailFileList) {
			logger.debug("null orderHeaderFileList returned");
			return;
		}
//		String orderHeaderJson = fileConverter.getJson(orderDetailFileList.get(0));
		
//		if (null == orderHeaderJson) {
//			logger.debug("null orderHeaderJson returned");
//			return;
//		}
		
		orderDetailFileList.forEach(orderDetailFile->restService.putOrderDetail(orderDetailFile));
		
	}

	private CSVReader getCsvReader(StorageService storageService, Path filename) {
		Resource file = storageService.loadAsResource(filename.toString());
		try {
			CSVReader csvReader = new CSVReader(new FileReader(file.getFile()));
			logger.debug("created csvReader");
			return csvReader;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param csvReader
	 */
	private void processOrderHeaderFile(CSVReader csvReader) {
		logger.debug("converting order header type file");
		List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();
		orderHeaderFileList = fileConverter.getOrderHeaderContents(csvReader);
		if (null == orderHeaderFileList) {
			logger.debug("null orderHeaderFileList returned");
			return;
		}
//		String orderHeaderJson = fileConverter.getJson(orderHeaderFileList.get(0));
//		
//		if (null == orderHeaderJson) {
//			logger.debug("null orderHeaderJson returned");
//			return;
//		}
		
		orderHeaderFileList.forEach(orderHeaderFile->restService.putOrderHeader(orderHeaderFile));
	}
}
