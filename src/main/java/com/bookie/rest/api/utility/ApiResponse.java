package com.bookie.rest.api.utility;

import java.util.Date;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	private Date timestamp;
	private String message;
	private String details;
	
	public static HashMap<String, Object> getMessage(Object data, String message, int statusCode) {

		HashMap<String, Object> map = new HashMap<>();

		map.put("Id", data);
		map.put("message", message);
		map.put("statusCode", statusCode);

		return map;
	}
}
