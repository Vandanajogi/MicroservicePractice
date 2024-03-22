package com.quest.controller;



import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootController {
	
	
	private MessageSource mesgSource;
	
	public SpringBootController(MessageSource mesgSource) {
	this.mesgSource=mesgSource;	
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/welcome")
	public String welcome() {
		return "Welcome";
		
		
	}

	
	@GetMapping(path="/helloworld")
	public String helloworld() {
		return "Hello World";		
	}
	
//hello world bean
	@GetMapping(path="/helloworldbean")
	public HelloWorldBean helloworldBean() {
		return new HelloWorldBean("Hello World");		
	}
	
	//hello world bean
		@GetMapping(path="/helloworldbean1/path-variable/{name}")
		public HelloWorldBean helloworldBeanpathvariable(@PathVariable String name) {
			return new HelloWorldBean(String.format("Hello World, %s",name));		
		}
	
		
		//for internationalization ex for language change
		@GetMapping(path="/helloworld-internationalized")
		public String helloworldInternationalization() {
			Locale locale=LocaleContextHolder.getLocale();
			return mesgSource.getMessage("good.morning.message", null, "Default Message", locale);
					
		}
}
