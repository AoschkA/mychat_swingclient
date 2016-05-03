package microchat.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import microchat.GUI.FileExploreGUI;
import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;
import microchat.handlers.DialogHandler;
import microchat.handlers.FileserverHandler;
import microchat.handlers.FirebaseHandler;
import microchat.handlers.RelayserverHandler;

public class EventController {
	private FirebaseHandler firebaseHandler;
	private GUIController guiController;
	private FileserverHandler fileserverHandler;
	private FileExploreGUI exploreGUI;

	public EventController(MicrochatGUI gui) {
		guiController = new GUIController(gui);
		firebaseHandler = new FirebaseHandler(guiController);
		fileserverHandler = new FileserverHandler();
	}

	public void writeMessage() {
		if (!guiController.getWrittenMessage().equals("")){
			firebaseHandler.createMessage(guiController.getWrittenMessage());
		}
	}

	public void removeChatroom() {
		String chatroomName = guiController.getChosenChatroom();
		firebaseHandler.deleteChatroom(chatroomName);
	}

	public boolean validateUser() {
		String[] loginDetails = guiController.getLoginDetails();
		UserPreferences.USERNAME = loginDetails[0];
		UserPreferences.PASSWORD = loginDetails[1];
		boolean validated = RelayserverHandler.validateUser();
		guiController.eventLogin(validated);
		if (validated) {
			firebaseHandler.authenticateToFirebase();
			firebaseHandler.initiateChatrooms();
			firebaseHandler.initiateFriendList();
			updateFilelist();
			guiController.eventListChatrooms(UserPreferences.CHATROOMS);
		} else {
			guiController.loginFailure();
		}
		return validated;
	}

	public void updateFilelist() {
		List<String> fileList = new ArrayList<String>();
		try {
			fileList = fileserverHandler.listFiles("s974489", UserPreferences.USERNAME, UserPreferences.PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiController.eventUpdateFilelist(fileList);
	}

	public void addChatroom() {
		String chatroomName = guiController.getChatroomDetails();
		if (!chatroomName.equals(""))
			firebaseHandler.createChatroom(chatroomName);
	}

	public void joinChatroom() {
		UserPreferences.JOINED_CHATROOM = guiController.getChosenChatroom();
		System.out.println(UserPreferences.JOINED_CHATROOM);
		DialogHandler.clearChat();
		firebaseHandler.initiateChat();
		guiController.eventUpdateChat();
	}

	public void addFriendFromList() {
		String username = guiController.getSelectedUser();
		firebaseHandler.addFriend(username);
	}

	public void addFriend() {
		String username = guiController.getAddedUsername();
		if (!username.equals("")) 
			firebaseHandler.addFriend(username);
	}

	public void removeFriend() {
		String username = guiController.getSelectedFriend();
		if (!username.equals("")) 
			firebaseHandler.deleteFriend(username);
	}

	public void changePassword(String[] userDetails) {
		boolean result = RelayserverHandler.changePassword(userDetails[0], userDetails[1], userDetails[2]);
		PopChangePasswordGUIController.closeGUI(result);
	}

	public void forgotPassword(String username) {
		boolean result = RelayserverHandler.forgotPassword(username);
		PopForgotPasswordGUIController.closeGUI(result);
	}

	public void openFileExplore() {
		exploreGUI = new FileExploreGUI();
	}

	public void uploadFile() {
		if (!guiController.getFilePath().equals("")) {
			try {
				fileserverHandler.uploadFile(
						guiController.getFilePath(), 
						UserPreferences.USERNAME, 
						UserPreferences.PASSWORD);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}



}
