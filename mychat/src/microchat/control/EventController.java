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
	@SuppressWarnings("unused")
	private static FileExploreGUI exploreGUI;
	
	private FirebaseHandler firebaseHandler;
	private GUIController guiController;
	private FileserverHandler fileserverHandler;

	public EventController(MicrochatGUI gui) {
		guiController = new GUIController(gui);
		firebaseHandler = new FirebaseHandler(guiController);
		fileserverHandler = new FileserverHandler();
	}

	public void writeMessage() {
		String message = guiController.getWrittenMessage();
		if (!message.equals("")){
			firebaseHandler.createMessage(message);
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
			fileList = fileserverHandler.listFiles(UserPreferences.USERNAME, UserPreferences.USERNAME, UserPreferences.PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiController.eventUpdateFilelist(fileList);
	}

	public void addChatroom() {
		String[] chatroomDetails = guiController.getChatroomDetails();
		if (!chatroomDetails.equals(""))
			firebaseHandler.createChatroom(chatroomDetails);
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
		String filePath = guiController.getFilePath();
		if (!filePath.equals("")) {
			System.out.println(filePath);
			try {
				fileserverHandler.uploadFile(
						filePath, 
						UserPreferences.USERNAME, 
						UserPreferences.PASSWORD);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void downloadFile() {
		String selectedFile = guiController.getSelectedFile();
		try {
			fileserverHandler.downloadFile(
					UserPreferences.USERNAME, 
					selectedFile, 
					System.getProperty("user.dir")+"\\", 
					UserPreferences.USERNAME, 
					UserPreferences.PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeFile() {
		String selectedFile = guiController.getSelectedFile();
		try {
			fileserverHandler.deleteFile(
					UserPreferences.USERNAME, 
					selectedFile, 
					UserPreferences.USERNAME, 
					UserPreferences.PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
