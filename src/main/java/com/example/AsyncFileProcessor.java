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

		System.out.println("Execute method asynchronously. "
				+ Thread.currentThread().getName());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Execute method asynchronously after sleep. "
				+ Thread.currentThread().getName());

		return new AsyncResult<String>("Success");
	}


}
