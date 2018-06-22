package com.example.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class starinutility {

	public static Map<String, Object> successResponse(Map<String, Object> response, String successMessage ) {
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("success",true);
	    map.put("status",HttpStatus.OK);
	    map.put("error",false);
		map.put("data", response);
		map.put("message", successMessage);
		return map;
	}

	
	

}
