package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KycDocument {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int documentId;
	private String documentName;
	private String description;
	public KycDocument(){
		
	}
	
	public KycDocument(String documentName, String description) {
		this.documentName = documentName;
		this.description = description;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "KycDocument [documentId=" + documentId + ", documentName=" + documentName + ", description="
				+ description + "]";
	}
	
}
