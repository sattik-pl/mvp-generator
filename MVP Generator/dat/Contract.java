public interface Contract {
	// Model API
	interface BaseModel {
		<T> T getData();
		<T> void setData(T data);
	}
	
	// Presenter API --> Model
	interface Responder {
		<T> void onDataChanged(T data);
	}
	
	// Presenter API --> View
	interface UIHandler {
		void handle();
		void handleAbout();
	}
	
	// View API
	interface UIUpdater {
		<T> void update(T data);
	}
}
