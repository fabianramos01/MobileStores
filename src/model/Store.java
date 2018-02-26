package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class Store {
	
	private int id;
	private String name;
	private String password;
	private String cellphone;
	private String photo;
	private ArrayList<Bill> bills;
	
	public Store(int id, String name, String password, String cellphone, String photo) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.cellphone = cellphone;
		this.photo = photo;
		bills = new ArrayList<>();
	}
	
	public static Bill createBill(String date, int price) throws ParseException {
		Random random = new Random();
		return new Bill(random.nextInt(1000)+1, date, price);
	}
	
	public static Bill createBill(int id, String date, int price) throws ParseException {
		return new Bill(id, date, price);
	}
	
	public void addBill(Bill bill) {
		bills.add(bill);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getcellphone() {
		return cellphone;
	}
	
	public String getPhoto() {
		return photo;
	}

	public ArrayList<Bill> getBills() {
		return bills;
	}
}