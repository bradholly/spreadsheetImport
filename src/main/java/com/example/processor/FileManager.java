package com.example.processor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.processor.filetype.OrderHeaderFile;
import com.example.storage.StorageService;
import com.opencsv.CSVReader;

@Component
public class FileManager {

	@Autowired
	FileConverter fileConverter;

	public void processFiles(StorageService storageService) {
		Stream<Path> stream = storageService.loadAll();

		// iterate files from the storageService object
		stream.forEach(filename->{

			System.out.println(filename);
			CSVReader csvReader = null;
			Resource file = storageService.loadAsResource(filename.toString());
			try {
				csvReader = new CSVReader(new FileReader(file.getFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (null == csvReader) {
				return;
			}

			// instantiate data object based on filename
			if (filename.toString().contains("orderheader")) {
				List<OrderHeaderFile> orderHeaderFileList = new ArrayList<OrderHeaderFile>();
				//							new OrderHeaderFile();
				//					ArrayList<String> columnNames = orderHeaderFile.getColumnNames();


				orderHeaderFileList = fileConverter.getOrderHeaderContents(csvReader);

			}

			//	        CSVIterator

		});
	}
}
