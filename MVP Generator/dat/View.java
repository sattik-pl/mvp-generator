public class View extends UIWindow implements Contract.UIUpdater, 
		ActionListener {
	
	private Contract.UIHandler presenter;
	
	public View() {
		super();
		presenter = new Presenter(this);
		
		itemAbout.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		if (action == itemAbout) { presenter.handleAbout(); }
	}
	
	// View API -------------------------------------------
	
	@Override
	public <T> void update(T data) {
		
	}
	// ----------------------------------------------------
}
