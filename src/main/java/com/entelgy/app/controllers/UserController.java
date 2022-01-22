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
import org.springframework.web.client.HttpClientErrorException;

import com.entelgy.app.models.service.UserService;

@RestController
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listUsers(){
		
		Map<String,Object> response = new HashMap<String,Object>();
		List<String> listNew = null;
		try {
			listNew = userService.listaUsuarios().stream().map(data ->{
				return "<"+data.getId()+">"+"|"+"<"+data.getLast_name()+">"+"|"+"<"+data.getEmail()+">";
			}).collect(Collectors.toList());
			
		}catch(HttpClientErrorException e) {
			response.put("Ocurrio un error :",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("data",listNew );
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
}
