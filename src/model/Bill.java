package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bill {

	private int id;
	private LocalDate date;
	private int price;
	private SaleType saleType;
	
	public Bill(int id, String dateString, String saleType, int price) throws ParseException {
		this.id = id;
		setBirthDate(dateString);
		setSaleType(saleType);
		this.price = price;
	}
	
	private void setSaleType(String saleType) {
		switch (SaleType.valueOf(saleType)) {
		case Comida:
			this.saleType = SaleType.Comida;
			break;
		case Material_educativo:
			this.saleType = SaleType.Material_educativo;
			break;
		case Artesanias:
			this.saleType = SaleType.Artesanias;
			break;
		case Otros:
			this.saleType = SaleType.Otros;
			break;
		}
		
	}

	private void setBirthDate(String stDate) throws ParseException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = LocalDate.parse(stDate, fmt);
	}
	
	public String getStDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(dtf);
	}
	
	public SaleType getSaleType() {
		return saleType;
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