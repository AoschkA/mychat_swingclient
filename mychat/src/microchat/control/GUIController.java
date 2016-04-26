package microchat.control;

import java.util.ArrayList;

import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;

public class GUIController {
	private MicrochatGUI gui;
	
	public GUIController(MicrochatGUI gui) {
		this.gui=gui;
	}
	
	public String[] getLoginDetails() {
		String[] details = new String[2];
		details[0] = gui.textField_username.getText();
		details[1] = new String(gui.textField_password.getPassword());
		return details;
	}
	
	public String getChatroomDetails() {
		return gui.textField_chatroomwriter.getText();
	}
	
	public String getChosenChatroom() {
		return gui.listChatrooms.getSelectedValue();
	}
	
	public void eventLogin(boolean succeded){
		System.out.println(UserPreferences.USERNAME);
		System.out.println(UserPreferences.PASSWORD);
		if (succeded) {
			gui.txtpnLoggedInAs.setText("Logged in as: "+UserPreferences.USERNAME);
			gui.txtpnCurrentToken.setText("Logged in with token: "+UserPreferences.AUTH_TOKEN);
		} else {
			
		}
	}
	
	public void eventListChatrooms(ArrayList<String> listData) {
		String[] arrayData = new String[listData.size()];
		for (int i=0; i<listData.size(); i++) arrayData[i]=listData.get(i); 
		gui.listChatrooms.setListData(arrayData);
	}

}
