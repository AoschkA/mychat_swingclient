package mychat.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.firebase.client.*;

public class MychatController {
	private static String AUTH_TOKEN;
	
	public static void main(String[] args) {
		try {
		URL oracle = new URL("http://85.11.31.36:8080/RelayServer/auth?user=s144883&psw=33rd");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("token = "+inputLine);
            AUTH_TOKEN=inputLine;
        }
        
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void connectToFirebase() {
		
		
		
//		Firebase ref = new Firebase("https://micro-chat.firebaseio.com/");
//		ref.authWithCustomToken(AUTH_TOKEN, new Firebase.AuthResultHandler() {
//		@Override
//		public void onAuthenticationError(FirebaseError error) {
//		System.err.println("Login Failed! " + error.getMessage());
//		}
//		@Override
//		public void onAuthenticated(AuthData authData) {
//		System.out.println("Login Succeeded!");
//		}
	}

}
