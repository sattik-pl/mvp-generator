public class Presenter implements Contract.UIHandler, Contract.Responder {
	private Contract.UIUpdater view;
	private Contract.BaseModel model;
	
	public Presenter(Contract.UIUpdater view) {
		this.view = view;
		model = new Model(this);
	}
	
	// Presenter API -------------------------------------------
	
	@Override
	public void handle() {
		
	}
	
	@Override
	public void handleAbout() {
		info(menuAbout, about);
	}
	
	@Override
	public <T> void onDataChanged(T data) {
		
	}
	// ---------------------------------------------------------
}
