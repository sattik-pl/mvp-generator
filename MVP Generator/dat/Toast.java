import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * new Toast(someJComponent, "Short message here.", 5);
 */

public class Toast extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Boolean spamProtect = false;
	private static final Color toastColor = new Color(210, 210, 210);
	private static final Font toastFont = new Font("Dialog", Font.BOLD, 22);

	public Toast(JComponent caller, String message, int duration) {
		if(spamProtect) { return; }
		
		setUndecorated(true);
		setAlwaysOnTop(true);
		setFocusableWindowState(false);
		setLayout(new GridBagLayout());

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panel.setBackground(toastColor);
		JLabel toastLabel = new JLabel(message);
		toastLabel.setForeground(Color.BLACK);
		toastLabel.setFont(toastFont);
		panel.add(toastLabel);
		add(panel);
		pack();

		Window window = SwingUtilities.getWindowAncestor(caller);
		int xcoord = window.getLocationOnScreen().x + window.getWidth() / 2 - getWidth() / 2;
		int ycoord = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - getHeight() / 2;
		setLocation(xcoord, ycoord);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		setVisible(true);

		new Thread() {
			@Override
			public void run() {
				try {
					spamProtect = true;
					Thread.sleep(duration * 1_000);
					dispose();
					spamProtect = false;
				} catch (InterruptedException e) { }
			}
		}.start();
	}
}