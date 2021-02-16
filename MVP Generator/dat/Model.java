public class Model implements Contract.BaseModel {
	private Contract.Responder presenter;
	
	public Model(Contract.Responder presenter) {
		this.presenter = presenter;
	}
	
	// Model API -------------------------------------------
	
	@Override
	public <T> T getData() {
		return null;
	}
	
	@Override
	public <T> void setData(T data) {
		
	}
	// -----------------------------------------------------
}
