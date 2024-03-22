package com.quest.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	
	//Static filter example
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value1","value2","value3");
	}

	

	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value4","value5","value6"),
				new SomeBean("value7","value8","value9"));
	}
	
	
	//Dynamic Filtering Ex.
	@GetMapping("/dynfiltering")
	public MappingJacksonValue filteringDynamic() {
		
		SomeBean somebean=new SomeBean("value1","value2","value3");
		//constructor of MappingJacksonValue class  that has bean as constructor argument  
		MappingJacksonValue mapJacksonValue=new MappingJacksonValue(somebean);
		
		//invoking static method filterOutAllExcept()  
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		//creating filter using FilterProvider class  
		FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		//configuring filters  
		mapJacksonValue.setFilters(filters);
		
		return mapJacksonValue ;
	}
	
	
	
	
	@GetMapping("/dynfiltering-list")
	public MappingJacksonValue filteringListDyn() {
		 List<SomeBean> list= Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value4","value5","value6"),
				new SomeBean("value7","value8","value9"));
		 
		 MappingJacksonValue mapJacksonValue=new MappingJacksonValue(list);
	
		 
		 SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
			
		 FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
			mapJacksonValue.setFilters(filters);
		 
		 return mapJacksonValue;
	}
}
