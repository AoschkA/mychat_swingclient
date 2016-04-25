package mychat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import entity.User;
import mychat.UI.LoginGUI;

public class LoginController {
	private static String relayserverURL = "http://85.11.31.36:8080/RelayServer/";
	
	public static void main(String[] args) {
		initiateLoginGUI();
    }
	
	public static void initiateLoginGUI() {
		LoginGUI loginGUI = new LoginGUI();
		loginGUI.setVisible(true);
		loginGUI.btnExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginGUI.closeGUI();
			}		
		});
		
		loginGUI.btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] userinfo = loginGUI.getUserInfo();
				User.username=userinfo[0];
				User.password=userinfo[1];
				boolean validated = validateUser();
				if (validated) {
					ChatController.initiateChatGUI();
					loginGUI.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(loginGUI, "Username and password does not match");
				}
			}
		});
		
	}
	
	private static boolean validateUser() {
		String url = relayserverURL + "auth?user=" + User.username + "&psw=" + User.password;
		URL oracle;
		try {
			oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("token = "+inputLine);
				User.AUTH_TOKEN=inputLine;
				}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("exception in validation");
			e.printStackTrace();
		}
		
		if (User.AUTH_TOKEN.equals("-1"))
			return false;
		else
			return true;
	}

}
