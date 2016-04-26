package mychat.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import microchat.entity.UserPreferences;
import mychat.UI.ChatGUI;
import mychat.UI.ChatGUIController;

public class ChatController {
	
	public static void initiateChatGUI(){
		ChatGUIController.initializeChatGUI();
		authenticateToFirebase();
	}
	private static void authenticateToFirebase() {
		
		Firebase ref = new Firebase("https://micro-chat.firebaseio.com/");
		ref.authWithCustomToken(UserPreferences.AUTH_TOKEN, new Firebase.AuthResultHandler() {
			@Override
			public void onAuthenticationError(FirebaseError error) {
			System.err.println("Login Failed! " + error.getMessage());
			}
			@Override
			public void onAuthenticated(AuthData authData) {
			System.out.println("Login Succeeded!");
			printChat();
			}
		});
	}
	
	private static void printChat() {
		String url = "https://micro-chat.firebaseio.com/users/"+UserPreferences.USERNAME+".json?print=pretty&auth="
				+ UserPreferences.AUTH_TOKEN;
		URL oracle;
		try {
			oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		}
	}

}
