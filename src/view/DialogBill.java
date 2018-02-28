package view;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SaleType;

import org.freixas.jcalendar.JCalendarCombo;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class DialogBill extends JDialog {

	private static final long serialVersionUID = 1L;

	private JCalendarCombo jCalendarCombo;
	private JTextField txtPrice;
	private JComboBox<String> comboSaleType;

	public DialogBill(Controller controller) {
		setTitle(ConstantList.TITLE);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource(
				ConstantList.ICON_PATH)).getImage());
		setSize(ConstantList.WIDTH_FORM, ConstantList.HEIGTH_FORM);
		JPanel panel = new JPanel(new GridLayout(7, 1));
		panel.add(UtilityList.createJLabel(ConstantList.DATE,
				ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		jCalendarCombo = new JCalendarCombo();
		jCalendarCombo.setFont(ConstantList.WORD_FONT);
		panel.add(jCalendarCombo);
		panel.add(UtilityList.createJLabel(ConstantList.SALE_TYPE,
				ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		loadTypes(panel);
		panel.add(UtilityList.createJLabel(ConstantList.PRICE,
				ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		txtPrice = new JTextField();
		txtPrice.setFont(ConstantList.WORD_FONT);
		txtPrice.setHorizontalAlignment(JTextField.CENTER);
		panel.add(txtPrice);
		panel.add(UtilityList.createJButtonText(
				CommandApp.COMMAND_ADD_BILL.getCommand(),
				CommandApp.COMMAND_ADD_BILL.getTitle(), ConstantList.APP_COLOR,
				ConstantList.LABEL_FONT, controller));
		add(panel);
		setResizable(false);
		setVisible(true);
	}

	private void loadTypes(JPanel panel) {
		comboSaleType = new JComboBox<>();
		comboSaleType.addItem(SaleType.Comida.getTitle());
		comboSaleType.addItem(SaleType.Material_educativo.getTitle());
		comboSaleType.addItem(SaleType.Artesanias.getTitle());
		comboSaleType.addItem(SaleType.Otros.getTitle());
		comboSaleType.setFont(ConstantList.WORD_FONT);
		panel.add(comboSaleType);
	}

	public String[] bilInfo() {
		Date date = jCalendarCombo.getDate();
		SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] info = { sFormat.format(date),
				comboSaleType.getSelectedItem().toString(), txtPrice.getText()};
		return info;
	}
}