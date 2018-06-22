package com.example.req;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Req_PageRequest {

	@NotNull(message="PageNo can not be null")
	@NotEmpty(message="PageNo can not be empty")
	//@Pattern(regexp="[0-9]+",message="PageNo not int")
	public	String pageNo;

	@NotNull(message="PageSize can not be null")
	@NotEmpty(message="PageSize can not be empty")
//	@Pattern(regexp="[0-9]+",message="PageSize not int")
	public	String pageSize;
	
	public	String count;


	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String customValidate() {
		int pageNumber = Integer.parseInt(pageNo);
		int pageSizee = Integer.parseInt(pageSize);

		if ( pageNumber == 0 || pageSizee == 0) {
			if(pageNumber != 0 && pageSizee !=0){
				return "pageNo and pageSize sould be zero if any one zero";
			}
		}

		return null;
	}

	public static Map<String, Object> prepareResponse(int pageNo1, int pageSize1, long count1, String string,
			List<Object> objectMap) {

		Map<String,Object> re = new HashMap<String, Object>();
		Map<String,Object> finalres = new HashMap<String, Object>();
		Map<String, Object> pagination = new HashMap<String, Object>();
		pagination.put("pageNo", pageNo1);
		pagination.put("pageSize", pageSize1);
		pagination.put("count", count1);
		
		re.put("pagination", pagination);
		re.put( string,objectMap);
		
		finalres.put("data", re);
		return  finalres;
	}



}
