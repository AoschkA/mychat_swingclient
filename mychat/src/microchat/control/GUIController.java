package microchat.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;
import microchat.handlers.DialogHandler;
/**
 * 
 * @author Jonas Praem
 *
 */
public class GUIController {
	private MicrochatGUI gui;
	private ArrayList<String> chatroomListData;
	private ArrayList<String> friendListData;

	public GUIController(MicrochatGUI gui) {
		this.gui=gui;
	}

	// Returns entered username and password
	public String[] getLoginDetails() {
		String[] details = new String[2];
		details[0] = gui.textField_username.getText();
		details[1] = new String(gui.textField_password.getPassword());
		return details;
	}

	// Returns provided chatroom name and password
	public String[] getChatroomDetails() {
		String[] chatroomDetails = new String[2];
		chatroomDetails[0] = gui.textField_chatroomWriter.getText();
		chatroomDetails[1] = gui.textField_chatroomPassword.getText();
		// Empties the textfields
		gui.textField_chatroomWriter.setText("");
		gui.textField_chatroomPassword.setText("");
		return chatroomDetails;
	}

	// Returns selected chatroom from list
	public String getChosenChatroom() {
		return gui.listChatrooms.getSelectedValue();
	}

	// Returns written message
	public String getWrittenMessage() {
		String writtenMessage = gui.textField_writingblock.getText();
		// Empties the textfield
		gui.textField_writingblock.setText("");
		return writtenMessage;
	}

	// Returns selected user from chatlist
	public String getSelectedUser() {
		return gui.list_chatuserlist.getSelectedValue();
	}

	// Returns name of friend user wish to add
	public String getAddedUsername() {
		String username = gui.textField_addFriendWriter.getText();
		// Empties the textfield
		gui.textField_addFriendWriter.setText("");
		return username;
	}

	// Returns selected user from friendlist
	public String getSelectedFriend() {
		return gui.list_friends.getSelectedValue();
	}

	// GUI update every time user logs in
	public void eventLogin(boolean succeded){
		if (succeded) {
			// Updates token and 'logged in as' in GUI
			gui.txtpnLoggedInAs.setText("Logged in as: "+UserPreferences.USERNAME);
			gui.txtpnCurrentToken.setText("Logged in with token: "+UserPreferences.AUTH_TOKEN);
		} else {
			// Message dialog when logged in failed
			JOptionPane.showMessageDialog(
					gui.getContentPane(), 
					"Wrong username and password combination", 
					"Failed to login",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Updates chatroom list 
	public void eventListChatrooms(ArrayList<String> listData) {
		// Converts ArrayList<String> to String[]
		chatroomListData = listData;
		String[] arrayData = new String[listData.size()];
		for (int i=0; i<listData.size(); i++) arrayData[i]=listData.get(i); 
		// Updates GUI
		gui.listChatrooms.setListData(arrayData);
	}

	// Updates friendlist 
	public void eventListFriends(ArrayList<String> listData) {
		// Converts ArrayList<String> to String[]
		friendListData = listData;
		String[] arrayData = new String[listData.size()];
		for (int i=0; i<listData.size(); i++) arrayData[i]=listData.get(i); 
		// Updates GUI
		gui.list_friends.setListData(arrayData);;
	}

	// Removes a single chatroom from GUI
	public void removeChatroom(String key) {
		// Removes chatroom from chatroom list
		chatroomListData.remove(key);
		// Updates GUI
		String[] arrayData = new String[chatroomListData.size()];
		for (int i=0; i<chatroomListData.size(); i++) arrayData[i]=chatroomListData.get(i); 
		gui.listChatrooms.setListData(arrayData);
	}

	// Removes a single friend from GUI
	public void removeFriend(String key) {
		// Removes friend from chatroom list
		friendListData.remove(key);
		// Updates GUI
		String[] arrayData = new String[friendListData.size()];
		for (int i=0; i<friendListData.size(); i++) arrayData[i]=friendListData.get(i); 
		gui.list_friends.setListData(arrayData);
	}

	// Updates file list
	public void eventUpdateFilelist(List<String> fileList) {
		String[] arrayData = new String[fileList.size()];
		for (int i=1; i<fileList.size(); i++) arrayData[i]=fileList.get(i)
				.substring(1, fileList.get(i).length()-1); 
		gui.list_files.setListData(arrayData);
	}

	// Updates chat
	public void eventUpdateChat() {
		// Updates 'users in this chat'
		String[] arrayData = new String[DialogHandler.CHAT_USERS.size()];
		for (int i=0; i<DialogHandler.CHAT_USERS.size(); i++) arrayData[i]=DialogHandler.CHAT_USERS.get(i); 
		gui.list_chatuserlist.setListData(arrayData);

		// Updates chat
		DialogHandler.generateChat(gui.textPane_chat);
	}

	// Returns entered filepath
	public String getFilePath() {
		String filepath = gui.textField_pathToFileWriter.getText();
		gui.textField_pathToFileWriter.setText("");
		return filepath;
	}

	// Returns selected file from list
	public String getSelectedFile() {
		return gui.list_files.getSelectedValue();
	}

}
