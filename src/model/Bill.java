package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bill {

	private int id;
	private LocalDate date;
	private int price;
	
	public Bill(int id, String dateString, int price) throws ParseException {
		this.id = id;
		setBirthDate(dateString);
		this.price = price;
	}
	
	private void setBirthDate(String stDate) throws ParseException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = LocalDate.parse(stDate, fmt);
	}
	
	public String getStDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(dtf);
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
}