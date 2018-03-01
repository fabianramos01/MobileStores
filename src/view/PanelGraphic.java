package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelGraphic extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color[] colors = {Color.GREEN,Color.BLUE,Color.RED, Color.BLACK};
	private ArrayList<int[]> storeList;

	public PanelGraphic(ArrayList<int[]> storeList) {
		super();
		this.storeList = storeList;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(100, 100, 100, 600);
		g.drawLine(100, 600, storeList.size()*110+100, 600);
		int x1 = 0;
		int y1;
		for (int[] stores : storeList) {
			x1 += 110;
			y1 = 100;
			for (int i = 0; i < stores.length; i++) {
				g.setColor(colors[i]);
				g.fillRect(x1, y1, 80, calculatePixel(stores[i], 500));
				y1 += calculatePixel(stores[i], 500);
			}
		}
	}
	
	private int calculatePixel(int percente, int total) {
		return percente*total/100;
	}
}