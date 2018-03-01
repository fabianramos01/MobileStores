package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class StoreManager {

	private ArrayList<Store> stores;
	private Store actualStore;

	public StoreManager() {
		stores = new ArrayList<>();
	}

	public static Store createStore(String name, String password, String cellphone, String photo) {
		Random random = new Random();
		return new Store(random.nextInt(1000) + 1, name, password, cellphone, photo);
	}

	public void addStore(Store store) {
		stores.add(store);
	}

	public void setActualStore(Store actualStore) {
		this.actualStore = actualStore;
	}

	public Store searchStore(String name, String password) {
		for (Store store : stores) {
			if (store.getName().equals(name) && store.getPassword().equals(password)) {
				return store;
			}
		}
		return null;
	}

	public ArrayList<String[]> getDateStores(LocalDate initDate, LocalDate endDate) {
		ArrayList<String[]> incomes = new ArrayList<String[]>();
		String[] info;
		int total;
		for (Store store : stores) {
			total = 0;
			for (Bill bill : store.getBills()) {
				if (isInRank(initDate, endDate, bill.getDate())) {
					total += bill.getPrice();
				}
			}
			info = new String[3];
			info[0] = String.valueOf(store.getId());
			info[1] = store.getName();
			info[2] = "$" + String.valueOf(total);
			incomes.add(info);
		}
		return incomes;
	}

	private boolean isInRank(LocalDate initDate, LocalDate endDate, LocalDate billDate) {
		if (billDate.isBefore(endDate) && billDate.isAfter(initDate)) {
			return true;
		}
		return false;
	}

	public ArrayList<int[]> getPercent(int year) {
		ArrayList<int[]> list = new ArrayList<>();
		int[] percent;
		for (Store store : stores) {
			percent = new int[4];
			for (Bill bill : store.getBills()) {
				if (bill.getDate().getYear() == year) {
					percent = addSale(bill, percent);
				}
			}
			list.add(calculatePercent(percent));
		}
		return list;
	}
	
	private int[] calculatePercent(int[] sales) {
		int total = 0;
		for (int i = 0; i < sales.length; i++) {
			total += sales[i];
		}
		for (int i = 0; i < sales.length; i++) {
			sales[i] = sales[i]*100/total;
		}
		return sales;

	}

	private int[] addSale(Bill bill, int[] types) {
		switch (SaleType.valueOf(bill.getSaleType().toString())) {
		case Comida:
			types[0] += bill.getPrice();
			break;
		case Material_educativo:
			types[1] += bill.getPrice();
			break;
		case Artesanias:
			types[2] += bill.getPrice();
			break;
		case Otros:
			types[3] += bill.getPrice();
			break;
		}
		return types;
	}

	public Store getActualStore() {
		return actualStore;
	}

	public ArrayList<Store> getStores() {
		return stores;
	}
}