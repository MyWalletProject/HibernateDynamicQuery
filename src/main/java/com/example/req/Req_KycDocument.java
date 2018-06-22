package com.example.req;

import javax.validation.constraints.Pattern;

public class Req_KycDocument {

//	@Pattern(regexp = "[A-Za-z0-9_]*", message = "sort is not valid")
//		public String  sort;
	
	public String documentName;

	@Pattern(regexp = "[0-9]+", message = "documentId should be integer")
	public String documentId;
	
	public String description;
	
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
//	public String getSort() {
//		return sort;
//	}
//
//	public void setSort(String sort) {
//		this.sort = sort;
//	}

	
}
