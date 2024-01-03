package application.model;

public class Product {
	int ID;
	String name;
	double stock;
	double price;
	
	public Product(String name, double stock, double price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	// Getters and Setter
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
