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
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		if (UserPreferences.AUTH_TOKEN.equals("-1"))
			return false;
		else
			return true;
	}
	
	public static boolean changePassword(String username, String password, String newPassword) {
		String authenticateURL = "http://85.11.31.36:8080/RelayServer/account/changepsw?"
				+"user=" + username
				+"&oldPsw=" + password
				+"&newPsw=" + newPassword;
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
		
		if (result.equals("Password changed.")) return true;
		else return false;
	}
	
	public static boolean forgotPassword(String username) {
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
		
		if (result.equals("A new password has been sent to your email address.")) return true;
		else return false;
	}

}
