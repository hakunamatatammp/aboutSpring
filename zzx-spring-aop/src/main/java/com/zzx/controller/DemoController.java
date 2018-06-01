package com.zzx.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		Date date = new Date();
		
		System.out.println("test()执行");
		return "123";
	}
	
	@GetMapping("/test/e")
	@ResponseBody
	public String testEx() throws Exception {
		throw new Exception("");
	}
}
