package hd.hx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.BitSet;

import javax.swing.JPanel;

public class HexGrid extends JPanel {

	private static final long serialVersionUID = 1L;

	final int HEX_SIZE = 4;
	final int MAX = 360*300;
	final double PI2 = Math.PI * 2;
	
	static int x = 0;

	private BitSet primes;

	public HexGrid() {
		super();
		init();
	}

	protected void init() {
		setBackground(Color.WHITE);
		primes = calcPrimes();
	}

	private BitSet calcPrimes() {
		BitSet set = new BitSet(MAX);
		for (int i = 2; i < MAX; i++) {
			if (i % 2 != 0 || i == 2) {
				int j;
				for (j = 2; j <= i / 2; j++) {
					if (i % j == 0) {
						break;
					}
				}

				if (j > i / 2) {
					set.set(i);
				}
			}
		}
		return set;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Dimension size = getSize();
		int h2 = size.height / 2;
		int w2 = size.width / 2;

		g.setColor(Color.BLUE);
		g.drawLine(w2, 0, w2, size.height);
		g.drawLine(0, h2, size.width, h2);

		g.setColor(Color.BLACK);
		int n = MAX / 6;
		for (int i = 0; i < n; i++) {
			circle(g, i);
		}
	}

	private void circle(Graphics g, int i) {
		Dimension size = getSize();
		int h2 = size.height / 2;
		int w2 = size.width / 2;

		int rad = HEX_SIZE * i;
		int rad2 = rad >> 1;

		int s = 6 * i; // radiant slices
		for (int a = 0; a < s; a++) {
			if (primes.get(x++))
				plot(g, w2, h2, a * PI2 / s, rad2);
		}
	}

	private void plot(Graphics g, int w, int h, double deg, int rad) {
		int x = w + (int) (Math.cos(deg) * rad);
		int y = h - (int) (Math.sin(deg) * rad);
		g.drawRect(x, y, 1, 1);
	}

	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	public Dimension getMinimumSize() {
		return new Dimension(600, 600);
	}

}
