package com.kei.cisco.model;

public enum State {

	ACTIVE("Active"),
	INACTIVE("Inactive"),
	DELETED("Deleted"),
	LOCKED("Locked"),
	BANNED("Banned");
	
	private String state;
	
	private State(final String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}

	@Override
	public String toString(){
		return this.state;
	}


	public String getName(){
		return this.name();
	}


}
