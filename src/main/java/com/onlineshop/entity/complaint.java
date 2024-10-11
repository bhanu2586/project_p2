package com.onlineshop.entity;

public class complaint {
	 private int complaintId;
	    private int userId;
	    private int retailerId;
	    private String complaintText;
	    private String complaintDate;
		public int getComplaintId() {
			return complaintId;
		}
		public void setComplaintId(int complaintId) {
			this.complaintId = complaintId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getRetailerId() {
			return retailerId;
		}
		public void setRetailerId(int retailerId) {
			this.retailerId = retailerId;
		}
		public String getComplaintText() {
			return complaintText;
		}
		public void setComplaintText(String complaintText) {
			this.complaintText = complaintText;
		}
		public String getComplaintDate() {
			return complaintDate;
		}
		public void setComplaintDate(String complaintDate) {
			this.complaintDate = complaintDate;
		}
}

	   
