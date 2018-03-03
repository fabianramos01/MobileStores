package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Bill;
import model.Store;
import model.StoreManager;
import persistence.FileManager;
import view.PrincipalFrame;

public class Controller implements ActionListener {

	private StoreManager storeManager;
	private PrincipalFrame principlaFrame;

	public Controller() {
		storeManager = new StoreManager();
		principlaFrame = new PrincipalFrame(this);
		loadFile();
		principlaFrame.home();
	}

	private void loadFile() {
		File[] files = new File("data").listFiles();
		Store store = null;
		ArrayList<Bill> bills;
		for (File file : files) {
			store = FileManager.loadStore(file.getPath() + "/" + file.getName() + ConstantList.XML);
			try {
				bills = FileManager.loadBills(file.getPath() + "/" + store.getName() + ConstantList.BILL_XML);
				for (Bill bill : bills) {
					store.addBill(bill);
				}
				storeManager.addStore(store);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		switch (CommandApp.valueOf(e.getActionCommand())) {
		case COMMAND_ADD_STORE:
			addStore();
			break;
		case COMMAND_ADD_BILL:
			addNewBill();
			break;
		case COMMAND_SELECT_FILE:
			principlaFrame.loadJFileChooser();
			break;
		case COMMAND_SIGN_IN:
			principlaFrame.signIn();
			break;
		case COMMAND_LOAD_RECORD:
			loadRecord();
			break;
		case COMMAND_LOGN_IN:
			lognIng();
			break;
		case COMMAND_PANEL_LOGN_IN:
			principlaFrame.lognIn();
			break;
		case COMMAND_PRINCIPAL_PANEL:
			principlaFrame.home();
			break;
		case COMMAND_DIALOG_BILL:
			principlaFrame.loadDialogBill();
			break;
		case COMMAND_DOWNLOAD_RECORD:
			break;
		}
	}

	private void loadRecord() {
		principlaFrame.loadReport(storeManager.getPercent(principlaFrame.getYear()));
		principlaFrame.home();
		for (Store store : storeManager.getStores()) {
			principlaFrame.addTable(storeManager.getDateStores(principlaFrame.getYear(), store));
		}

	}

	private void addNewBill() {
		String[] info = principlaFrame.newBill();
		Store store = storeManager.getActualStore();
		try {
			store.addBill(Store.createBill(info[0], info[1], Integer.parseInt(info[2])));
			FileManager.saveBills(
					ConstantList.DATA_PATH + store.getName() + "/" + store.getName() + ConstantList.BILL_XML,
					store.getBills());
			principlaFrame.closeDialog();
			principlaFrame.store(storeManager.getActualStore());
		} catch (NumberFormatException | ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	private void lognIng() {
		String[] info = principlaFrame.lognInInfo();
		if (validateInfo(info)) {
			Store store = storeManager.searchStore(info[0], info[1]);
			if (store != null) {
				try {
					storeManager.setActualStore(store);
					principlaFrame.store(store);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, ConstantList.ERROR_INFO, ConstantList.ERROR,
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, ConstantList.MISSING_INFO, ConstantList.ERROR,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void addStore() {
		String[] info = principlaFrame.newStore();
		if (validateInfo(info)) {
			Store store = StoreManager.createStore(info[0], info[1], info[2], info[3]);
			saveStore(store);
			storeManager.setActualStore(store);
			try {
				principlaFrame.store(store);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, ConstantList.MISSING_INFO, ConstantList.ERROR,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void saveStore(Store store) {
		storeManager.addStore(store);
		principlaFrame.resetForm();
		File file = new File(ConstantList.DATA_PATH + store.getName());
		file.mkdirs();
		FileManager.saveStores(file.getPath() + "/" + store.getName() + ConstantList.XML, store);
		FileManager.saveBills(file.getPath() + "/" + store.getName() + ConstantList.BILL_XML, store.getBills());
		JOptionPane.showMessageDialog(null, ConstantList.STORE_CONFIRMATION, ConstantList.OK,
				JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean validateInfo(String[] info) {
		for (int i = 0; i < info.length; i++) {
			if (info[i].equals("")) {
				return false;
			}
		}
		return true;
	}
}