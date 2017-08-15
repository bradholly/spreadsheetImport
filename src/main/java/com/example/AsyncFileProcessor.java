package com.example;

import java.util.concurrent.Future;

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

	@Autowired
	private FileManager fileManager;
	
	@Async("threadPoolTaskExecutor")
	public Future<String> processFilesAsync(StorageService storageService) {

		fileManager.processFiles(storageService);

		return new AsyncResult<String>("Success");
	}


}
