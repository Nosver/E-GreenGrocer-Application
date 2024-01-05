package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.util.Pair;



public class Chart {
		int userId;
		ArrayList<Pair<Product,Double>> items;
		double totalPrice;
		boolean isPurchased;
		LocalDateTime date;
		String situation;
		
		public Chart() {
			this.isPurchased=false;
			this.date=null;
		}
}
