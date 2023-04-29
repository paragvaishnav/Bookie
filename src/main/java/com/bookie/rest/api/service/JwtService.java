package com.bookie.rest.api.service;

import java.security.Key;
import java.util.Map;

import org.springframework.security.core.Authentication;

public interface JwtService {

	public String generateToken(String username);
	public String createToken(Map<String,Object>claims,String username);
	//public Key getSignKey();
}
