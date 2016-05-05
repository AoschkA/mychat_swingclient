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
/**
 * 
 * @author Jonas Praem
 *
 */
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

	//  Executes when user requests to send a message
	public void writeMessage() {
		String message = guiController.getWrittenMessage();
		if (!message.equals("")){
			firebaseHandler.createMessage(message);
		}
	}

	// Executes when user requests to remove a chatroom from their personal chatroom list
	public void removeChatroom() {
		String chatroomName = guiController.getChosenChatroom();
		firebaseHandler.deleteChatroom(chatroomName);
	}

	// Executes when user requests to be validated / when user tries to login
	// Returns if successful or not
	public boolean validateUser() {
		// Gets entered username and password in a String[]
		String[] loginDetails = guiController.getLoginDetails();
		// Sets the UserPreferences
		UserPreferences.USERNAME = loginDetails[0];
		UserPreferences.PASSWORD = loginDetails[1];
		// Tries to login and authenticate via the relay server
		boolean validated = RelayserverHandler.validateUser();
		// Informs the user if successful or not
		guiController.eventLogin(validated);
		if (validated) {
			// Connects to firebase, and gets the chatrooms and friends for the user logged in
			firebaseHandler.authenticateToFirebase();
			firebaseHandler.initiateChatrooms();
			firebaseHandler.initiateFriendList();
			// Initially updates the users filelist, otherwise empty before the user presses 'Update'
			updateFilelist();
			// Shows the users chatrooms and friends on the GUI
			guiController.eventListChatrooms();
		}
		// returns if successfully logged in 
		return validated;
	}

	// Updates filelist
	public void updateFilelist() {
		List<String> fileList = new ArrayList<String>();
		try {
			fileList = fileserverHandler.listFiles(UserPreferences.USERNAME, UserPreferences.USERNAME, UserPreferences.PASSWORD);
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiController.eventUpdateFilelist(fileList);
	}

	// Adds a chatroom the the users chatrooms
	public void addChatroom() {
		String[] chatroomDetails = guiController.getChatroomDetails();
		if (!chatroomDetails.equals(""))
			firebaseHandler.createChatroom(chatroomDetails);
	}

	// Executes when user requests to open a chatroom
	public void openChatroom() {
		UserPreferences.JOINED_CHATROOM = guiController.getChosenChatroom();
		// Clears stored chat details
		DialogHandler.clearChat();
		// Requests to pull information in the chat via firebase
		firebaseHandler.initiateChat();
		// Updates the GUI
		guiController.eventUpdateChat();
	}

	// Add friend from the chatuserlist
	public void addFriendFromList() {
		String username = guiController.getSelectedUser();
		firebaseHandler.addFriend(username);
	}

	// Adds a friend by name
	public void addFriend() {
		String username = guiController.getAddedUsername();
		if (!username.equals("")) 
			firebaseHandler.addFriend(username);
	}

	// Removes a friend
	public void removeFriend() {
		String username = guiController.getSelectedFriend();
		if (!username.equals("")) 
			firebaseHandler.deleteFriend(username);
	}

	// Changes the password via the relay server
	public void changePassword(String[] userDetails) {
		boolean result = RelayserverHandler.changePassword(userDetails[0], userDetails[1], userDetails[2]);
		PopChangePasswordGUIController.closeGUI(result);
	}

	// Requests an email with forgotten password via the relay server
	public void forgotPassword(String username) {
		boolean result = RelayserverHandler.forgotPassword(username);
		PopForgotPasswordGUIController.closeGUI(result);
	}

	// Opens the file explore GUI
	public void openFileExplore() {
		exploreGUI = new FileExploreGUI();
	}

	// Uploads file via the file server java client
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

	// Downloads file via the file server java client
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

	// Removes file via the file server java client
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
