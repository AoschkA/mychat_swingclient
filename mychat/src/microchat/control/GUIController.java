package microchat.control;

import java.util.ArrayList;

import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;
import microchat.handlers.DialogHandler;

public class GUIController {
	private MicrochatGUI gui;
	private ArrayList<String> chatroomListData;
	private ArrayList<String> friendListData;
	
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
	
	public String getWrittenMessage() {
		return gui.textField_writingblock.getText();
	}
	
	public String getSelectedUser() {
		return gui.list_chatuserlist.getSelectedValue();
	}
	
	public String getAddedUsername() {
		return gui.textField_addFriend.getText();
	}
	
	public String getSelectedFriend() {
		return gui.list_friends.getSelectedValue();
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
		chatroomListData = listData;
		String[] arrayData = new String[listData.size()];
		for (int i=0; i<listData.size(); i++) arrayData[i]=listData.get(i); 
		gui.listChatrooms.setListData(arrayData);
	}
	
	public void eventListFriends(ArrayList<String> listData) {
		friendListData = listData;
		String[] arrayData = new String[listData.size()];
		for (int i=0; i<listData.size(); i++) arrayData[i]=listData.get(i); 
		gui.list_friends.setListData(arrayData);;
	}
	
	public void removeChatroom(String key) {
		chatroomListData.remove(key);
		String[] arrayData = new String[chatroomListData.size()];
		for (int i=0; i<chatroomListData.size(); i++) arrayData[i]=chatroomListData.get(i); 
		gui.listChatrooms.setListData(arrayData);
	}
	
	public void removeFriend(String key) {
		friendListData.remove(key);
		String[] arrayData = new String[friendListData.size()];
		for (int i=0; i<friendListData.size(); i++) arrayData[i]=friendListData.get(i); 
		gui.list_friends.setListData(arrayData);
	}
	
	public void eventUpdateChat() {
		// Updates 'users in this chat'
		String[] arrayData = new String[DialogHandler.CHAT_USERS.size()];
		for (int i=0; i<DialogHandler.CHAT_USERS.size(); i++) arrayData[i]=DialogHandler.CHAT_USERS.get(i); 
		gui.list_chatuserlist.setListData(arrayData);
		
		// Updates chat
		DialogHandler.generateChat(gui.textPane_chat);
	}
	
	
	public void writeMessage() {
		gui.textField_writingblock.setText("");
	}

}
