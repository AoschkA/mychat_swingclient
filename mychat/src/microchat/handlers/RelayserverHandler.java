package microchat.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import microchat.entity.UserPreferences;

public class RelayserverHandler {
	
	public static boolean validateUser() {
		String authenticateURL = "http://85.11.31.36:8080/RelayServer/" +
				"account/auth?user=" + UserPreferences.USERNAME + "&psw=" + UserPreferences.PASSWORD;
		URL urlInstance;
		try {
			urlInstance = new URL(authenticateURL);
			URLConnection yc = urlInstance.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("token = "+inputLine);
				UserPreferences.AUTH_TOKEN=inputLine;
				}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		}
		
		if (UserPreferences.AUTH_TOKEN.equals("-1"))
			return false;
		else
			return true;
	}

}
