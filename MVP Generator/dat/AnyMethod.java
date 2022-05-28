/*
 * Designed to pass any method as parameter
 * 
 * void runWithDelay1s(AnyMethod method) {
 * 		new java.util.Timer().schedule(new java.util.TimerTask() {
 * 			@Override
 * 			public void run() { method.apply(); }
 * 		}, 1);
 * }
 * 
 * runWithDelay1s(() -> presenter.handle());
 * runWithDelay1s(() -> presenter.handle(arg));
*/

@FunctionalInterface
public interface AnyMethod { void apply(); }
