package com.example.processor;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.processor.filetype.OrderHeaderFile;
import com.example.processor.filetype.OrderHeaderFileDefinition;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileConverterTests {

	@Autowired
	FileConverter fileConverter;

	@Test
	public void shouldReturnOneEntryMap() throws Exception {
		final OrderHeaderFile orderHeaderFile = new OrderHeaderFile();
		final OrderHeaderFileDefinition uploadFile = new OrderHeaderFileDefinition();
		
		final String[] values = {"Hello","Goodbye","Cancel Order Flag"};

		final Map<String,Integer> result = fileConverter.getColumnPositions(values, uploadFile.getColumnNames());

		assertEquals(result.size(), 1);
		assertEquals(result.get(values[2]).intValue(), 2);
	}
	
	@Test
	public void shouldReturnTwoEntryMap() throws Exception {
		final OrderHeaderFile orderHeaderFile = new OrderHeaderFile();
		final OrderHeaderFileDefinition uploadFile = new OrderHeaderFileDefinition();
		final String[] values = {"Warehouse","Goodbye","Cancel Order Flag"};

		final Map<String,Integer> result = fileConverter.getColumnPositions(values, uploadFile.getColumnNames());

		assertEquals(result.size(), 2);
		assertEquals(result.get(values[0]).intValue(), 0);
		assertEquals(result.get(values[2]).intValue(), 2);
	}
}
