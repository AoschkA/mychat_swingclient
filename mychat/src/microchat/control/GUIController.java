package microchat.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	public String[] getChatroomDetails() {
		String[] chatroomDetails = new String[2];
		chatroomDetails[0] = gui.textField_chatroomWriter.getText();
		chatroomDetails[1] = gui.textField_chatroomPassword.getText();
		gui.textField_chatroomWriter.setText("");
		gui.textField_chatroomPassword.setText("");
		return chatroomDetails;
	}
	
	public String getChosenChatroom() {
		return gui.listChatrooms.getSelectedValue();
	}
	
	public String getWrittenMessage() {
		String writtenMessage = gui.textField_writingblock.getText();
		gui.textField_writingblock.setText("");
		return writtenMessage;
	}
	
	public String getSelectedUser() {
		return gui.list_chatuserlist.getSelectedValue();
	}
	
	public String getAddedUsername() {
		String username = gui.textField_addFriendWriter.getText();
		gui.textField_addFriendWriter.setText("");
		return username;
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
	
	public void loginFailure() {
		JOptionPane.showMessageDialog(gui.getContentPane(), "Wrong username and password combination", "Failed to login",JOptionPane.ERROR_MESSAGE);
	}
	
	public void eventUpdateFilelist(List<String> fileList) {
		String[] arrayData = new String[fileList.size()];
		for (int i=1; i<fileList.size(); i++) arrayData[i]=fileList.get(i)
				.substring(1, fileList.get(i).length()-1); 
		gui.list_files.setListData(arrayData);
	}
	
	public void eventUpdateChat() {
		// Updates 'users in this chat'
		String[] arrayData = new String[DialogHandler.CHAT_USERS.size()];
		for (int i=0; i<DialogHandler.CHAT_USERS.size(); i++) arrayData[i]=DialogHandler.CHAT_USERS.get(i); 
		gui.list_chatuserlist.setListData(arrayData);
		
		// Updates chat
		DialogHandler.generateChat(gui.textPane_chat);
	}
	
	public String getFilePath() {
		String filepath = gui.textField_pathToFileWriter.getText();
		gui.textField_pathToFileWriter.setText("");
		return filepath;
	}
	
	public String getSelectedFile() {
		return gui.list_files.getSelectedValue();
	}

}
