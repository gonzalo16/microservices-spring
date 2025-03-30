package com.ifragodevs.msvc_gateway_server.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/authorized")
	public Map<String,String> authorized(@RequestParam String authCode){
		 Map<String,String> map = new HashMap<>();
		 map.put("code", authCode);
		 return map;
	}
	
	@PostMapping("/logout")
	public Map<String,String> logout(){
		 return Collections.singletonMap("logout", "Ok");
	}
}
