package view;

import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class PanelSignIn extends JPanel {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private JTextField storeName;
	private JPasswordField password;
	private JTextField cellPhone;
	private JFileChooser chooserImg;

	public PanelSignIn(Controller controller) {
		this.controller = controller;
		chooserImg = new JFileChooser();
		setLayout(new GridLayout(8, 1));
		informationPanel();
	}

	public void informationPanel() {
		add(UtilityList.createJButtonText(CommandApp.COMMAND_SELECT_FILE.getCommand(),
				CommandApp.COMMAND_SELECT_FILE.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT,
				controller));
		add(UtilityList.createJLabel(ConstantList.NAME, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		storeName = new JTextField();
		storeName.setHorizontalAlignment(JTextField.CENTER);
		storeName.setFont(ConstantList.WORD_FONT);
		add(storeName);
		add(UtilityList.createJLabel(ConstantList.PASSWORD, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		password = new JPasswordField();
		password.setFont(ConstantList.WORD_FONT);
		password.setHorizontalAlignment(JPasswordField.CENTER);
		add(password);
		add(UtilityList.createJLabel(ConstantList.CELLPHONE, ConstantList.LABEL_FONT, ConstantList.APP_COLOR));
		cellPhone = new JTextField();
		cellPhone.setFont(ConstantList.WORD_FONT);
		cellPhone.setHorizontalAlignment(JTextField.CENTER);
		add(cellPhone);
		add(UtilityList.createJButtonText(CommandApp.COMMAND_ADD_STORE.getCommand(),
				CommandApp.COMMAND_ADD_STORE.getTitle(), ConstantList.APP_COLOR, ConstantList.LABEL_FONT, controller));
	}

	public void loadJFileChooser() {
		chooserImg.setFileFilter(new FileNameExtensionFilter(".jpg", "JPG"));
		chooserImg.setDialogTitle(ConstantList.PHOTO);
		chooserImg.showOpenDialog(this);
	}

	public String[] getInfo() {
		String pass = "";
		for (int i = 0; i < password.getPassword().length; i++) {
			pass += password.getPassword()[i];
		}
		String photo = "";
		if (chooserImg.getSelectedFile() != null) {
			photo = chooserImg.getSelectedFile().getPath();
		}
		String[] info = { storeName.getText(), pass, cellPhone.getText(), photo };
		return info;
	}

	public void resetForm() {
		storeName.setText("");
		password.setText("");
		cellPhone.setText("");
	}
}