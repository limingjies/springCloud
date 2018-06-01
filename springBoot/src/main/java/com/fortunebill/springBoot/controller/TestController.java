package com.fortunebill.springBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

		@RequestMapping("/hello")
		public String index(){
			return "hi";
		}
}
