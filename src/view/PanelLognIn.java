package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class PanelLognIn extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtName;
	private JPasswordField passWord;

	public PanelLognIn(Controller controller) {
		setLayout(new GridLayout(5, 1));
		add(UtilityList.createJLabel(ConstantList.NAME, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		txtName = new JTextField();
		txtName.setHorizontalAlignment(JTextField.CENTER);
		txtName.setFont(ConstantList.WORD_FONT);
		add(txtName);
		add(UtilityList.createJLabel(ConstantList.PASSWORD, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		passWord = new JPasswordField();
		passWord.setFont(ConstantList.WORD_FONT);
		passWord.setHorizontalAlignment(JPasswordField.CENTER);
		add(passWord);
		add(UtilityList.createJButtonText(CommandApp.COMMAND_LOGN_IN.getCommand(),
				CommandApp.COMMAND_LOGN_IN.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT, controller));
	}
	
	public String[] getInfo() {
		String pass = "";
		for (int i = 0; i < passWord.getPassword().length; i++) {
			pass += passWord.getPassword()[i];
		}
		String[] info = {txtName.getText(), pass};
		return info;
	}
}