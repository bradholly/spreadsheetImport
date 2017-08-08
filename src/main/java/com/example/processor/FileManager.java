package com.example.processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.processor.filetype.OrderHeaderFile;
import com.example.storage.StorageService;
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

			CSVReader csvReader = null;
			Resource file = storageService.loadAsResource(filename.toString());
			try {
				csvReader = new CSVReader(new FileReader(file.getFile()));
				logger.debug("created csvReader");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (null == csvReader) {
				logger.debug("null csvReader, returning");
				return;
			}

			// instantiate data object based on filename
			if (filename.toString().contains("orderheader")) {
				logger.debug("converting order header type file");
				List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();
				orderHeaderFileList = fileConverter.getOrderHeaderContents(csvReader);
				if (null == orderHeaderFileList) {
					logger.debug("null orderHeaderFileList returned");
					return;
				}
				String orderHeaderJson = fileConverter.getJson(orderHeaderFileList.get(0));
				
				if (null == orderHeaderJson) {
					logger.debug("null orderHeaderJson returned");
					return;
				}
				restService.putOrderHeader(orderHeaderFileList.get(0));
				
			}
		});
		
		storageService.deleteAll();
		
		logger.debug("finish processFiles(StorageService storageService)");
	}
}
