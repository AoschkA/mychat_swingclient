package microchat.test;

import microchat.entity.UserPreferences;
import microchat.handlers.FirebaseHandler;
import microchat.handlers.RelayserverHandler;
/**
 * 
 * @author Jonas Praem
 * Old test, doesnt work anymore
 */
public class DistributedConnectionTest {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		FirebaseHandler firebaseHandler = new FirebaseHandler(null);
		RelayserverHandler relayHandler = new RelayserverHandler();
		UserPreferences.USERNAME = "s144883";
		UserPreferences.PASSWORD = "33rd";
		
		if (relayHandler.validateUser()){
			System.out.println("user validated!");
			firebaseHandler.authenticateToFirebase();
			try {
				Thread.sleep(100000);
			} catch (Exception e) {
				System.err.println("ERROR IN THREADSLEEP");
			}
			System.out.println("logged in with token: "+UserPreferences.AUTH_TOKEN);
		}
	}

}
