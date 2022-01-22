package com.entelgy.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entelgy.app.models.entity.User;
import com.entelgy.app.models.entity.UserModalData;

@Service
public class UserSerciceImpl implements UserService{
    
	@Autowired
	private RestTemplate userRest;
	
	@Override
	public List<User> listaUsuarios() {
		UserModalData userModalData = userRest.getForObject("https://reqres.in/api/users", UserModalData.class);
		return userModalData.getData();
	}

}
