package com.RevBookStore.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class orders {

		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)	
		private long order_Id;
		private long user_Id;
		private long product_Id;
		private double total_price;
		
		private Timestamp order_date;
		private String payment_mode;
		private String shippingAddress;
		private String phone_Number;
		private String status;
		private int pincode;
		public long getOrder_Id() {
			return order_Id;
		}
		public void setOrder_Id(long order_Id) {
			this.order_Id = order_Id;
		}
		public long getUser_Id() {
			return user_Id;
		}
		public void setUser_Id(long user_Id) {
			this.user_Id = user_Id;
		}
		public long getProduct_Id() {
			return product_Id;
		}
		public void setProduct_Id(long product_Id) {
			this.product_Id = product_Id;
		}
		public double getTotal_price() {
			return total_price;
		}
		public void setTotal_price(double total_price) {
			this.total_price = total_price;
		}
		public Timestamp getOrder_date() {
			return order_date;
		}
		public void setOrder_date(Timestamp order_date) {
			this.order_date = order_date;
		}
		public String getPayment_mode() {
			return payment_mode;
		}
		public void setPayment_mode(String payment_mode) {
			this.payment_mode = payment_mode;
		}
		public String getShippingAddress() {
			return shippingAddress;
		}
		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
		public String getPhone_Number() {
			return phone_Number;
		}
		public void setPhone_Number(String phone_Number) {
			this.phone_Number = phone_Number;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getPincode() {
			return pincode;
		}
		public void setPincode(int pincode) {
			this.pincode = pincode;
		}
		
		
		
		
	}
