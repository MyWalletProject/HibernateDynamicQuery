package com.example.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class StarinsUtility {

	public static Map<String,Object> getFieldErrorResponse(BindingResult result){	
		Map<String,Object> fielderror=new HashMap<String,Object>();	
		List<FieldError> errors =result.getFieldErrors();		
		for(FieldError error : errors){		 
			fielderror.put(error.getField(), error.getDefaultMessage());		
		}		return fielderror;
	}

}
