package com.graduation.entity;

public class EntityProducts {
	private int PID;
    private int CategoryID;
    private String Name;
    private String Description;
	
    
    public int getPID() {
		return PID;
	}
	public void setPID(String pID) {
		try{
			PID =Integer.parseInt(pID); 
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(String categoryID) {
		try{ 
			CategoryID =Integer.parseInt(categoryID);	
		}catch (Exception e) {
			CategoryID=-1;
		}
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
    
    
}
