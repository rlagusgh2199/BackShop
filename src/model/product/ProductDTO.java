package com.backshop.productDB;

import java.sql.Date;

public class ProductDTO {
    private int id;
    private String productCategory;   
    private String productGender;
	private String productName;          
    private double productPrice;        
    private int productStock;            
    private double productTemperature;    
    private double productHumidity;     //습도 
    private String productImage;         
    private String productDescription; //재고
    private double idealTemperature; //이상 온도
    private Date registerDate; // 추가된 필드

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for productCategory
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductGender() {
		return productGender;
	}

	public void setProductGender(String productGender) {
		this.productGender = productGender;
	}
	
    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for productPrice
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Getter and Setter for productStock
    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    // Getter and Setter for productTemperature
    public double getProductTemperature() {
        return productTemperature;
    }

    public void setProductTemperature(double productTemperature) {
        this.productTemperature = productTemperature;
    }

    // Getter and Setter for productHumidity
    public double getProductHumidity() {
        return productHumidity;
    }

    public void setProductHumidity(double productHumidity) {
        this.productHumidity = productHumidity;
    }

    // Getter and Setter for productImage
    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    // Getter and Setter for productDescription
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    // Getter and Setter for idealTemperature
    public double getIdealTemperature() {
        return idealTemperature;
    }
    
    public void setIdealTemperature(double idealTemperature) {
        this.idealTemperature = idealTemperature;
    }
    
    public Date getRegisterDate() { // 추가된 getter 메소드
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) { // 추가된 setter 메소드
        this.registerDate = registerDate;
    }
}
