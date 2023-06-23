package com.example.demo;

public class Address {

	private String label;
	private String address;
	
	public Address(String label, String address) throws Exception {
		// TODO
		super();
		if(address.isBlank() || label.isBlank()){
			throw new Exception();
		}
		this.label=label;
		this.address=address;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String getAddress() {
		return this.address;
	}
}
