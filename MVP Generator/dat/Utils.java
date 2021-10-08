import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import javax.swing.JOptionPane;

public final class Utils {
	private Utils() { throw new AssertionError(); }
	
	public static String separator = FileSystems.getDefault().getSeparator();
	
	public static void error(String msg) {
		JOptionPane.showMessageDialog(null, 
			msg, "Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void info(String title, String msg) {
		JOptionPane.showMessageDialog(null, 
			msg, title, JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public static int getCores() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	public static String getOSName() { return System.getProperty("os.name"); }
	
	public static String getDate() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                   .format(Calendar.getInstance().getTime());
	}
	
	public static String getDuration(Duration duration) {
		return duration.toString()
		               .substring(2)
		               .replaceAll("(\\d[HMS])(?!$)", "$1 ")
		               .toLowerCase();
	}
	
	public static void copyToClipboard(String s) {
		Toolkit.getDefaultToolkit()
	           .getSystemClipboard()
	           .setContents(new StringSelection(s), null);
	}
	
	public static String pasteFromClipboard() {
		try { return ((String) Toolkit.getDefaultToolkit()
		                              .getSystemClipboard()
		                              .getData(DataFlavor.stringFlavor)); }
		catch (HeadlessException | UnsupportedFlavorException | IOException e) {
			return "";
		}
	}
}
