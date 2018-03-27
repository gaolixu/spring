package com.intervalintl.cache;

import java.io.Serializable;

 
  public class Account implements Serializable{ 
	  
	  private String name;

	public Account(String acctName) {
		this.name=acctName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
  }
  
  