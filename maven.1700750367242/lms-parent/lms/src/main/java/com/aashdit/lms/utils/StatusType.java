package com.aashdit.lms.utils;

public enum StatusType {
	 PENDING("PENDING"),
	 APPROVED("APPROVED"),
	 REJECTED("REJECTED"),
	 REVERTED("REVERTED");

	private String status ;
	
	StatusType(String status)
	{
		this.status = status;
	}

	public String getStatusType() {
		return status;
	}
	
	 @Override 
	 public String toString() {
		 
		 return status;
		 }
	

	
}
