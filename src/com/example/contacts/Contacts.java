package com.example.contacts;

import android.net.Uri;

public class Contacts {

	private Uri uriImage;
	private String name;
	private String conNumber;
	 
	public Contacts(Uri uriImage, String name, String conNumber) {
		super();
		this.uriImage = uriImage;
		this.name = name;
		this.conNumber = conNumber;
	}

	public Uri getUriImage() {
		return uriImage;
	}

	public void setUriImage(Uri uriImage) {
		this.uriImage = uriImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConNumber() {
		return conNumber;
	}

	public void setConNumber(String conNumber) {
		this.conNumber = conNumber;
	}
	
	
	
}
