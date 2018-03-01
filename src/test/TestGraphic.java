package test;

import java.util.ArrayList;

import javax.swing.JDialog;

import view.PanelGraphic;

public class TestGraphic {

	public static void main(String[] args) {
		JDialog jDialog = new JDialog();
		int[] store = {20, 20, 20, 40};
		int[] store1 = {10, 30, 30, 30};
		int[] store2 = {50, 20, 10, 20};
		int[] store3 = {90, 7, 2, 1};
		ArrayList<int[]> list = new ArrayList<>();
		list.add(store);
		list.add(store1);
		list.add(store2);
		list.add(store3);
		PanelGraphic panelGraphic =  new PanelGraphic(list);
		panelGraphic.repaint();
		jDialog.add(panelGraphic);
		jDialog.setSize(800,600);
		jDialog.setVisible(true);
	}
}
