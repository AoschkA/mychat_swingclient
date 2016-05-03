package microchat.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import microchat.entity.UserPreferences;
/**
 * 
 * @author Jonas Praem
 * Handles all connection with relay server
 */
public class RelayserverHandler {

	// Connects to relayserver validating service, returns if successful or not
	public static boolean validateUser() {
		System.out.println("Connecting to relayserver validation service...");
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
				UserPreferences.AUTH_TOKEN=inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (UserPreferences.AUTH_TOKEN.equals("-1"))
			return false;
		else {
			System.out.println("Validated with token "+ UserPreferences.AUTH_TOKEN);
			return true;
		}
	}

	// Connects to relayserver passwordchanging service, returns if successful or not
	public static boolean changePassword(String username, String password, String newPassword) {
		System.out.println("Connecting to relayserver passwordchanging service...");
		String authenticateURL = "http://85.11.31.36:8080/RelayServer/account/changepsw?"
				+"user=" + username
				+"&oldPsw=" + password
				+"&newPsw=" + newPassword;
		System.out.println(username + " " + password + " "+ newPassword);
		URL urlInstance;
		String result = "";
		try {
			urlInstance = new URL(authenticateURL);
			URLConnection yc = urlInstance.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result=inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (result.equals("Password changed.")) {
			System.out.println("Password succesfully changed");
			return true;
		}
		else return false;
	}

	// Connects to relayserver recover password service
	public static boolean forgotPassword(String username) {
		System.out.println("Connecting to relayserver password recovery service...");
		String authenticateURL = "http://85.11.31.36:8080/RelayServer/account/forgotpsw?user="
				+ username;
		URL urlInstance;
		String result = "";
		try {
			urlInstance = new URL(authenticateURL);
			URLConnection yc = urlInstance.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result=inputLine;
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (result.equals("A new password has been sent to your email address.")){
			System.out.println("Relayserver password recovery service successfully connected to, password will be sent to users email");
			return true;
		}
		else return false;
	}

}
