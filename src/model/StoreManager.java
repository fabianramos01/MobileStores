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
		return new Store(random.nextInt(1000)+1, name, password, cellphone, photo);
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
		for (Store store : stores) {
			for (Bill bill : store.getBills()) {
				info = new String[4];
				if (isInRank(initDate, endDate, bill.getDate())) {
					info[0] = store.getName();
					info[1] = String.valueOf(bill.getId());
					info[2] = bill.getStDate();
					info[3] = String.valueOf(bill.getPrice());
					incomes.add(info);
				}
			}
		}
		return incomes;
	}
	
	private boolean isInRank(LocalDate initDate, LocalDate endDate, LocalDate billDate) {
		if (billDate.isBefore(endDate) && billDate.isAfter(initDate)) {
			return true;
		}
		return false;
	}
	
	public Store getActualStore() {
		return actualStore;
	}

	public ArrayList<Store> getStores() {
		return stores;
	}
}