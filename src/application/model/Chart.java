package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.util.Pair;



public class Chart {
		int userId;
		ArrayList<Pair<Product,Double>> items;
		double totalPrice;
		String state; //onChart // purchased //active //delivered
		LocalDateTime date;
		
		public Chart() {
			this.state="onChart";
			this.date=null;
		}
		
		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public ArrayList<Pair<Product, Double>> getItems() {
			return items;
		}

		public void setItems(ArrayList<Pair<Product, Double>> items) {
			this.items = items;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public Chart(int userId, double totalPrice, String state, LocalDateTime date) {
			super();
			this.userId = userId;
			
			this.totalPrice = totalPrice;
			this.state = state;
			this.date = date;
		}
		
		
}
