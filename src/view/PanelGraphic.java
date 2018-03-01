package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.ConstantList;

public class PanelGraphic extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color[] colors = {Color.GREEN,Color.BLUE,Color.RED, Color.GRAY};
	private ArrayList<int[]> storeList;

	public PanelGraphic(ArrayList<int[]> storeList) {
		super();
		this.storeList = storeList;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(ConstantList.WORD_FONT);
		g.drawLine(100, 100, 100, 600);
		g.drawLine(100, 600, storeList.size()*110+100, 600);
		int percent = 100;
		for (int i = 0; i < 100; i += 20) {
			g.drawString(String.valueOf(percent), 60, 100+calculatePixel(i, 500)+10);
			percent -=20;
		}
		int x1 = 0;
		int y1;
		int j = 1;
		for (int[] stores : storeList) {
			x1 += 110;
			y1 = 100;
			for (int i = 0; i < stores.length; i++) {
				g.setColor(colors[i]);
				g.fillRect(x1, y1, 80, calculatePixel(stores[i], 500));
				g.setColor(Color.BLACK);
				g.drawRect(x1, y1, 80, calculatePixel(stores[i], 500));
				y1 += calculatePixel(stores[i], 500);
			}
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(j++), x1+35, y1+20);
		}
	}
	
	private int calculatePixel(int percente, int total) {
		return percente*total/100;
	}
}