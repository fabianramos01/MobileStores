package view;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.freixas.jcalendar.JCalendarCombo;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class DialogBill extends JDialog {

	private static final long serialVersionUID = 1L;

	private JCalendarCombo jCalendarCombo;
	private JTextField txtPrice;
	
	public DialogBill(Controller controller) {
		setTitle(ConstantList.TITLE);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_PATH)).getImage());
		setSize(ConstantList.WIDTH_FORM, ConstantList.HEIGTH_FORM);
		JPanel panel = new JPanel(new GridLayout(5, 1));
		panel.add(UtilityList.createJLabel(ConstantList.DATE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		jCalendarCombo = new JCalendarCombo();
		jCalendarCombo.setFont(ConstantList.WORD_FONT);
		panel.add(jCalendarCombo);
		panel.add(UtilityList.createJLabel(ConstantList.PRICE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		txtPrice = new JTextField();
		txtPrice.setFont(ConstantList.WORD_FONT);
		txtPrice.setHorizontalAlignment(JTextField.CENTER);
		panel.add(txtPrice);
		panel.add(UtilityList.createJButtonText(CommandApp.COMMAND_ADD_BILL.getCommand(),
				CommandApp.COMMAND_ADD_BILL.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT, controller));
		add(panel);
		setResizable(false);
		setVisible(true);
	}
	
	public String[] bilInfo() {
		Date date = jCalendarCombo.getDate();
		SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] info = {sFormat.format(date), txtPrice.getText()};
		return info;
	}
}