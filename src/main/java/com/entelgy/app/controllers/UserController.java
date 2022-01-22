package com.entelgy.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entelgy.app.models.service.UserService;

@RestController
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,List<String>>> listUsers(){
		
		Map<String,List<String>> response = new HashMap<String,List<String>>();
		response.put("data", userService.listaUsuarios().stream().map(data ->{
			return "<"+data.getId()+">"+"|"+"<"+data.getLast_name()+">"+"|"+"<"+data.getEmail()+">";
		}).collect(Collectors.toList()));
		
		return new ResponseEntity<Map<String,List<String>>>(response,HttpStatus.OK);
	}
	
	
}
