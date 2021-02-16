public final class Utils {
	private Utils() { throw new AssertionError(); }
	
	public static void error(String msg) {
		JOptionPane.showMessageDialog(null, 
			msg, "Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void info(String title, String msg) {
		JOptionPane.showMessageDialog(null, 
			msg, title, JOptionPane.INFORMATION_MESSAGE);		
	}
}
