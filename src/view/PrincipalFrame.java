package view;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ConstantList;
import controller.Controller;
import model.Store;

public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private PanelSignIn jPanelSignIn;
	private PanelHome panelHome;
	private PanelLognIn panelLognIn;
	private PanelStore panelStore;
	private DialogBill dialogBill;
	private JPanel backPanel;

	public PrincipalFrame(Controller controller) {
		this.controller = controller;
		setTitle(ConstantList.TITLE);
		setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource(ConstantList.ICON_PATH)).getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jPanelSignIn = new PanelSignIn(this.controller);
		backPanel = new JPanel();
		setJMenuBar(new JMenuBarUser(controller));
	}
	
	public void signIn() {
		backPanel.removeAll();
		backPanel.updateUI();
		setResizable(true);
		setSize(ConstantList.WIDTH_FORM, ConstantList.HEIGTH_FORM);
		setResizable(false);
		backPanel.add(jPanelSignIn);
		add(backPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void home(ArrayList<String[]> list) {
		backPanel.removeAll();
		backPanel.updateUI();
		setResizable(true);
		setSize(ConstantList.WIDTH_HOME, ConstantList.HEIGTH_HOME);
		setResizable(false);
		panelHome = new PanelHome(controller);
		panelHome.loadTable(list);
		backPanel.add(panelHome);
		add(backPanel);
		setVisible(true);
	}

	public void lognIn() {
		backPanel.removeAll();
		backPanel.updateUI();
		setResizable(true);
		setSize(ConstantList.WIDTH_LOG, ConstantList.HEIGTH_LOG);
		setResizable(false);
		panelLognIn = new PanelLognIn(controller);
		backPanel.add(panelLognIn);
		add(backPanel);
		setVisible(true);
	}
	
	public void store(Store store) throws ParseException {
		backPanel.removeAll();
		backPanel.updateUI();
		setResizable(true);
		setSize(ConstantList.WIDTH_STORE, ConstantList.HEIGTH_HOME);
		setResizable(false);
		panelStore = new PanelStore(store, controller);
		backPanel.add(panelStore);
		add(backPanel);
		setVisible(true);
	}
	
	public void loadDialogBill() {
		dialogBill = new DialogBill(controller);
	}
	
	public String[] newStore() {
		return jPanelSignIn.getInfo();
	}
	
	public String[] newBill() {
		return dialogBill.bilInfo();
	}
	
	public void loadJFileChooser() {
		jPanelSignIn.loadJFileChooser();
	}
	
	public void closeDialog() {
		dialogBill.dispose();
	}
	
	public LocalDate[] getDates() {
		return panelHome.getDates();
	}
	
	public void resetForm() {
		jPanelSignIn.resetForm();
	}

	public String[] lognInInfo() {
		return panelLognIn.getInfo();
	}
}