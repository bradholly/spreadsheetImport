package com.example;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.processor.FileManager;
import com.example.storage.StorageService;

@Configuration
@EnableAsync
public class AsyncFileProcessor {
	Logger logger = LoggerFactory.getLogger(AsyncFileProcessor.class);
	
	@Autowired
	private FileManager fileManager;
	
	@Async("threadPoolTaskExecutor")
	public Future<String> processFilesAsync(StorageService storageService) {
		logger.info("starting async processing of uploaded files");
		try {
			fileManager.processFiles(storageService);	
		} catch (Exception e) {
			logger.error("Error encountered processing files in background", e);
			return new AsyncResult<String>("Error: " + e.getMessage());
		}
		
		logger.info("completed async processing of uploaded files");
		return new AsyncResult<String>("Success");

	}


}
