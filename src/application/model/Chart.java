package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.util.Pair;


public class Chart {
		int userId;
		int chartId;
		ArrayList<Pair<Product,Double>> items;
		double totalPrice;
		String state; //onChart // purchased //active //delivered
		LocalDateTime date;
		
		public void print() {
			System.out.println();
			System.out.println("Chart: " + this);
			System.out.println("UserID: " + userId);
			System.out.println("chartId: " + chartId);
			System.out.println("Total Price: " + totalPrice);
			System.out.println("State: " + state);
			System.out.println("Date: " + date);	
			System.out.println();
		}
		
		public Chart() {
			this.state="onChart";
			this.date=null;
			this.items = new ArrayList<Pair<Product,Double>>();
		}
		
		public int getChartId() {
			return chartId;
		}

		public void setChartId(int chartId) {
			this.chartId = chartId;
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
			this.items = new ArrayList<Pair<Product,Double>>();
			this.totalPrice = totalPrice;
			this.state = state;
			this.date = date;
		}
		
		public Chart(int chartId, int userId, double totalPrice, String state, LocalDateTime date) {
			super();
			
			this.chartId = chartId;
			this.userId = userId;
			this.items = new ArrayList<Pair<Product,Double>>();
			this.totalPrice = totalPrice;
			this.state = state;
			this.date = date;
		}
		
		public void pushToArray(Pair<Product,Double> pair) {
			items.add(pair);
		}
		
		
		public void calculatePrice() {
			totalPrice = 0;
			
			for(Pair<Product, Double> pair : items) {
				totalPrice += pair.getKey().getPrice() * pair.getValue();
			}
			
		}
		
		
		
}
