package com.graduation.entity;

public class EntitySubject {
	 //	"select  Products.PID,Products.CategoryID,Products.Name,Category.CID,Category.CName 
    //	from Products,Category 
    //	where Products.CategoryID=Category.CID"
    private int CategoryID;
    private String CategoryName;
    private int ProductID;
    private String ProductName;

    //	select Items.IID,Items.ProductID,Items.BrandID,Items.Name from Items,Products,Brand
    //	where Products.PID=Items.ProductID
    //	Items.BrandID=Brand.BID
    private int ItemID;
    private String ItemName;

    //	Brand.BID,Brand.BrandName
    private int BrandID;
    private String BrandName;
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public int getItemID() {
		return ItemID;
	}
	public void setItemID(int itemID) {
		ItemID = itemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getBrandID() {
		return BrandID;
	}
	public void setBrandID(int brandID) {
		BrandID = brandID;
	}
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
    
    
}
