package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

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
				CommandApp.COMMAND_DIALOG_BILL.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT,
				controller));
		JLabel jLabel = new JLabel(UtilityList.scaledImage(store.getPhoto(), 420));
		JPanel backgroundPanel = new JPanel(new BorderLayout());
		backgroundPanel.add(jLabel, BorderLayout.WEST);
		backgroundPanel.add(panelInfo, BorderLayout.EAST);
		add(backgroundPanel);
		loadTable(store.getBills());
	}

	private JLabel labelInfo(String name) {
		JLabel jLabel = new JLabel(name);
		jLabel.setFont(ConstantList.LABEL_FONT);
		return jLabel;
	}

	public void loadTable(ArrayList<Bill> bills) {
		String[] columnInfo = { ConstantList.ID, ConstantList.SALE_TYPE, ConstantList.DATE, ConstantList.PRICE };
		Object[][] data;
		if (!bills.isEmpty()) {
			data = new Object[bills.size()][columnInfo.length];
			for (int i = 0; i < bills.size(); i++) {
				data[i][0] = bills.get(i).getId();
				data[i][1] = bills.get(i).getSaleType();
				data[i][2] = bills.get(i).getStDate();
				data[i][3] = bills.get(i).getPrice();
			}
		} else {
			data = new Object[1][columnInfo.length];
			for (int j = 0; j < columnInfo.length; j++) {
				data[0][j] = "---";
			}
		}
		JTable recordTable = new JTable(data, columnInfo);
		recordTable.setFont(ConstantList.WORD_FONT);
		JTableHeader header = recordTable.getTableHeader();
		header.setFont(ConstantList.LABEL_FONT);
		header.setBackground(ConstantList.APP_COLOR);
		header.setForeground(Color.WHITE);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(header, BorderLayout.NORTH);
		jPanel.add(recordTable, BorderLayout.CENTER);
		add(new JScrollPane(jPanel));
	}
}