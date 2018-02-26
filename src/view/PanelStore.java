package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;
import model.Bill;
import model.Store;

public class PanelStore extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelStore(Store store, Controller controller) throws ParseException {
		setLayout(new GridLayout(2, 1));
		JPanel panelInfo = new JPanel(new GridLayout(6, 1));
		panelInfo.add(UtilityList.createJLabel(ConstantList.NAME, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		panelInfo.add(labelInfo(store.getName()));
		panelInfo.add(UtilityList.createJLabel(ConstantList.CELLPHONE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		panelInfo.add(labelInfo(store.getcellphone()));
		panelInfo.add(UtilityList.createJButtonText(CommandApp.COMMAND_DIALOG_BILL.getCommand(),
				CommandApp.COMMAND_DIALOG_BILL.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT, controller));
		JLabel jLabel = new JLabel(UtilityList.scaledImage(store.getPhoto(), 420));
		JPanel backgroundPanel = new JPanel(new BorderLayout());
		backgroundPanel.add(jLabel, BorderLayout.WEST);
		backgroundPanel.add(panelInfo, BorderLayout.EAST);
		add(backgroundPanel, BorderLayout.CENTER);
		loadBills(store.getBills());
	}

	private void loadBills(ArrayList<Bill> bills) throws ParseException {
		JPanel panelBills = new JPanel();
		if (!bills.isEmpty()) {
			for (Bill bill : bills) {
				panelBills.add(billCard(bill));
			}
		} else {
			panelBills.add(billCard(new Bill(0, "01/01/0001", 0)));
		}
		add(new JScrollPane(panelBills), BorderLayout.EAST);
	}

	private JPanel billCard(Bill bill) {
		JPanel jPanel = new JPanel(new GridLayout(6, 1));
		jPanel.add(UtilityList.createJLabel(ConstantList.ID, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		jPanel.add(labelInfo(String.valueOf(bill.getId())));
		jPanel.add(UtilityList.createJLabel(ConstantList.DATE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		jPanel.add(labelInfo(bill.getStDate()));
		jPanel.add(UtilityList.createJLabel(ConstantList.PRICE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		jPanel.add(labelInfo(String.valueOf("$ " + bill.getPrice())));
		jPanel.setBorder(BorderFactory.createLineBorder(ConstantList.APP_COLOR));
		jPanel.setBorder(BorderFactory.createTitledBorder(ConstantList.BILL));
		return jPanel;
	}
	
	private JLabel labelInfo(String text) {
		JLabel jLabel = new JLabel(text);
		jLabel.setFont(ConstantList.WORD_FONT);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		return jLabel;
	}
}