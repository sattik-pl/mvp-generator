import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * ImageIcon mouseLeft = new ImageIcon(ImageIO.read(
 * 	MyApplication.class.getResource(mouseLeftPath)));
 * ImageIcon mouseRight = new ImageIcon(ImageIO.read(
 * 	MyApplication.class.getResource(mouseRightPath)));
 * 
 * Jpanel toast;
 * toast = new JPanel();
 * // toast panel properties
 * toast.setLayout(new FlowLayout());
 * add(toast);
 *  
 * myComponent.addMouseListener(new MouseAdapterForToastPanel(
 * 	myComponent, toast, mouseLeft, "copy", mouseRight, "paste"));
 */

public class MouseAdapterForToastPanel extends MouseAdapter {
	JComponent component;
	JPanel toast;
	ImageIcon mouseLeft, mouseRight;
	String textLeft, textRight;
	
	private static final Font toastFont = new Font("Monospaced", Font.BOLD, 18);
	
	public MouseAdapterForToastPanel(
			JComponent component, JPanel toast, String text) {
		this(component, toast, null, text, null, "");
	}
	
	public MouseAdapterForToastPanel(
			JComponent component, JPanel toast, ImageIcon image, String text) {
		this(component, toast, image, text, null, "");
	}
	
	public MouseAdapterForToastPanel(
			JComponent component, JPanel toast, ImageIcon mouseLeft, String textLeft,
			ImageIcon mouseRight, String textRight) {
		super();
		this.component = component;
		this.toast = toast;
		this.mouseLeft = mouseLeft;
		this.mouseRight = mouseRight;
		this.textLeft = textLeft;
		this.textRight = textRight;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) { // left
			
		} else if (e.getButton() == MouseEvent.BUTTON3) { //right
			
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (mouseLeft != null)
			toast.add(new JLabel(" ", mouseLeft, JLabel.CENTER));
		
		JLabel l = new JLabel();
		l.setForeground(Color.BLACK);
		l.setFont(toastFont);
		if (mouseRight == null)
			l.setText(textLeft);
		else
			l.setText(textLeft.concat(" "));
		toast.add(l);
		
		if (mouseRight != null) {
			toast.add(new JLabel(" ", mouseRight, JLabel.CENTER));
			JLabel r = new JLabel(textRight);
			r.setForeground(Color.BLACK);
			r.setFont(toastFont);
			toast.add(r);
		}
		
		toast.revalidate();
		toast.repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		toast.removeAll();
		toast.revalidate();
		toast.repaint();
	}
}
