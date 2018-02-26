package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.freixas.jcalendar.JCalendarCombo;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class PanelHome extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel panelSeacrh;
	private JTable recordTable;
	private JCalendarCombo initDate;
	private JCalendarCombo endDate;

	public PanelHome(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		loadSearchPanel();
	}

	private void loadSearchPanel() {
		panelSeacrh = new JPanel();
		panelSeacrh.add(
				UtilityList.createJLabel(ConstantList.SET_RECORD, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		initDate = new JCalendarCombo();
		initDate.setFont(ConstantList.WORD_FONT);
		initDate.setToolTipText(ConstantList.INIT_DATE);
		panelSeacrh.add(initDate);
		endDate = new JCalendarCombo();
		endDate.setFont(ConstantList.WORD_FONT);
		endDate.setToolTipText(ConstantList.END_DATE);
		panelSeacrh.add(endDate);
		panelSeacrh.add(UtilityList.createJButtonText(CommandApp.COMMAND_LOAD_RECORD.getCommand(),
				CommandApp.COMMAND_LOAD_RECORD.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT,
				controller));
		add(panelSeacrh, BorderLayout.NORTH);
		add(UtilityList.createJButton(CommandApp.COMMAND_DOWNLOAD_RECORD.getCommand(),
				CommandApp.COMMAND_DOWNLOAD_RECORD.getTitle(), CommandApp.COMMAND_DOWNLOAD_RECORD.getIcon(),
				controller), BorderLayout.SOUTH);
	}

	public void loadTable(ArrayList<String[]> list) {
		String[] columnInfo = { ConstantList.NAME, ConstantList.ID, ConstantList.DATE, ConstantList.PRICE };
		Object[][] data;
		if (!list.isEmpty()) {
			data = new Object[list.size()][columnInfo.length];
			String[] info;
			for (int i = 0; i < list.size(); i++) {
				info = list.get(i);
				for (int j = 0; j < columnInfo.length; j++) {
					data[i][j] = info[j];
				}
			}
		} else {
			data = new Object[1][columnInfo.length];
			for (int j = 0; j < columnInfo.length; j++) {
				data[0][j] = "---";
			}
		}
		recordTable = new JTable(data, columnInfo);
		recordTable.setFont(ConstantList.WORD_FONT);
		JTableHeader header = recordTable.getTableHeader();
		header.setFont(ConstantList.LABEL_FONT);
		header.setBackground(ConstantList.APP_COLOR);
		header.setForeground(Color.WHITE);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(header, BorderLayout.NORTH);
		jPanel.add(recordTable, BorderLayout.CENTER);
		jPanel.setBorder(BorderFactory.createLineBorder(ConstantList.APP_COLOR));
		add(jPanel, BorderLayout.CENTER);
	}

	public LocalDate[] getDates() {
		LocalDate dateOne = initDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dateTwo = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate[] localDates = { dateOne, dateTwo };
		return localDates;
	}
}