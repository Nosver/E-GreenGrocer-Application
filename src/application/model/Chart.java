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
}
