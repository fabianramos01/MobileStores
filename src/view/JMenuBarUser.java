package view;

import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.CommandApp;
import controller.ConstantList;
import controller.Controller;

public class JMenuBarUser extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	private JMenu userOption;
	
	public JMenuBarUser(Controller controller) {
		userOption = new JMenu(ConstantList.STORE_LIST);;
		userOption.add(addJMenuItem(CommandApp.COMMAND_PRINCIPAL_PANEL, 'A', controller));
		userOption.add(addJMenuItem(CommandApp.COMMAND_PANEL_LOGN_IN, 'X', controller));
		userOption.add(addJMenuItem(CommandApp.COMMAND_SIGN_IN, 'D', controller));
		add(userOption);
	}
	
	private JMenuItem addJMenuItem(CommandApp commandApp, Character keyStroke, Controller controller) {
		JMenuItem menuItem = new JMenuItem(commandApp.getTitle());
		menuItem.addActionListener(controller);
		menuItem.setActionCommand(commandApp.getCommand());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(keyStroke, Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		return menuItem;
	}
}