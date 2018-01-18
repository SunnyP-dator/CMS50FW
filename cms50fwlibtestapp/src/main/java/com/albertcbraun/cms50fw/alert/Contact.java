package com.albertcbraun.cms50fw.alert;

public class Contact {
	
	//private variables
	int _id;
	int _name;

	// Empty constructor
	public Contact(){
		
	}
	// constructor
	public Contact(int id, int name ){
		this._id = id;
		this._name = name;
	}
	
	// constructor
	public Contact(int name){
		this._name = name;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public int getName(){
		return this._name;
	}
	
	// setting name
	public void setName(int name){
		this._name = name;
	}
	
	// getting phone number

}
