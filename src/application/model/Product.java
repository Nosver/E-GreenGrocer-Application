package application.model;

public class Product {
	
	int id;
	String name;
	double stock;
	double price;
	double threshold;
	String imagePath;
	
	public double getThreshold() {
		return threshold;
	}


	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public Product(String name, double stock, double price, double threshold, String imagePath) {
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.threshold=threshold;
		this.imagePath=imagePath;
	}
	
	public Product(int id, String name, double stock, double price, double threshold, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.threshold = threshold;
		this.imagePath = imagePath;
	}
	
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", stock=" + stock + ", price=" + price + ", threshold=" + threshold
				+ ", imagePath=" + imagePath + "]";
	}


	public int getId() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
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
