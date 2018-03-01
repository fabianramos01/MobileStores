package view;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

import controller.ConstantList;

public class DialogReport extends JDialog {

	private static final long serialVersionUID = 1L;

	public DialogReport(ArrayList<int[]> list) {
		setTitle(ConstantList.TITLE);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_PATH)).getImage());
		setSize(ConstantList.WIDTH_REPORT, ConstantList.HEIGTH_REPORT);
		add(new PanelGraphic(list));
		setResizable(false);
		setVisible(true);
	}
}