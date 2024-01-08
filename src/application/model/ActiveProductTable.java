package application.model;

public class ActiveProductTable {
		double price;
		String name;
		double quantitiy;
		
		public ActiveProductTable(double price, String name, double quantitiy) {
			super();
			this.price = price;
			this.name = name;
			this.quantitiy = quantitiy;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getQuantitiy() {
			return quantitiy;
		}

		public void setQuantitiy(double quantitiy) {
			this.quantitiy = quantitiy;
		}
}
