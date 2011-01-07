package hd.hx;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	public Main() throws HeadlessException {
		super("HexoPrime");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	protected void init() {
		setContentPane(new HexGrid());
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		main.pack();
		main.setVisible(true);
	}
}
