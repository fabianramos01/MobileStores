package model;

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

	public ArrayList<String[]> getDateStores(int year, Store store) {
		ArrayList<String[]> incomes = new ArrayList<String[]>();
		for (int i = 0; i < 4; i++) {
			incomes.add(new String[5]);
		}
		for (String[] strings : incomes) {
			for (int i = 0; i < strings.length; i++) {
				strings[i] = "";
			}
		}
		for (Bill bill : store.getBills()) {
			if (bill.getDate().getYear() == year) {
				addTableSale(incomes, bill.getSaleType(), bill.getPrice());
			}
		}
		return incomes;
	}

	private void addTableSale(ArrayList<String[]> info, SaleType saleType, int value) {
		switch (SaleType.valueOf(saleType.toString())) {
		case Comida:
			addType(info.get(0), saleType, value);
			break;
		case Material_educativo:
			addType(info.get(1), saleType, value);
			break;
		case Artesanias:
			addType(info.get(2), saleType, value);
			break;
		case Otros:
			addType(info.get(3), saleType, value);
			break;
		}
	}

	private void addType(String[] type, SaleType saleType, int value) {
		type[0] = saleType.toString();
		if (!type[1].equals("")) {
			type[1] = Integer.parseInt(type[1]) + 1 + "";
			type[2] = Integer.parseInt(type[2]) + value + "";
		} else {
			type[1] = String.valueOf(1);
			type[2] = String.valueOf(value);
		}
		type[3] = String.valueOf(saleType.getIva());
		type[4] = calculateTotal(Integer.parseInt(type[2]), saleType.getIva()) + "";

	}

	private int calculateTotal(int subtotal, int tax) {
		return subtotal + (subtotal * tax / 100);
	}

	public ArrayList<int[]> getPercent(int year) {
		ArrayList<int[]> list = new ArrayList<>();
		int[] percent;
		for (Store store : stores) {
			percent = null;
			for (Bill bill : store.getBills()) {
				if (bill.getDate().getYear() == year) {
					if (percent == null) {
						percent = new int[4];
					}
					percent = addSale(bill, percent);
				}
			}
			if (percent != null) {
				list.add(calculatePercent(percent));
			}
		}
		return list;
	}

	private int[] calculatePercent(int[] sales) {
		int total = 0;
		for (int i = 0; i < sales.length; i++) {
			total += sales[i];
		}
		for (int i = 0; i < sales.length; i++) {
			sales[i] = sales[i] * 100 / total;
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