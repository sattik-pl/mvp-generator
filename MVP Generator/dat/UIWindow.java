public class UIWindow extends JFrame {
	int width = 600;
	int height = 400;
	int locationX = 40;
	int locationY = 40;
	
	private JMenuBar menuBar;
	private JMenu menuHelp;
	JMenuItem itemAbout;
	
	public UIWindow() {
		createWindow();
		createMenu();
		createComponents();
		setVisible(true);
	}
	
	private void createWindow() {
		setTitle(title);
		setSize(width, height);
		setLocation(locationX, locationY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
	}
	
	private void createMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		itemAbout = new JMenuItem(menuAbout, 'A');
		menuHelp.add(itemAbout);
	}
	
	private void createComponents() {
		
	}
}
