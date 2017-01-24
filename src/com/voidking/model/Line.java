package com.voidking.model;

public class Line implements Cloneable{
	private int id;
	private String busName;
	private String fullName;
	private String firstStop;
	private String lastStop;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstStop() {
		return firstStop;
	}
	public void setFirstStop(String firstStop) {
		this.firstStop = firstStop;
	}
	public String getLastStop() {
		return lastStop;
	}
	public void setLastStop(String lastStop) {
		this.lastStop = lastStop;
	}
	
	public Line(int id, String busName, String fullName, String firstStop, String lastStop) {
		super();
		this.id = id;
		this.busName = busName;
		this.fullName = fullName;
		this.firstStop = firstStop;
		this.lastStop = lastStop;
	}
	
	@Override  
    public Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }  
	
}
