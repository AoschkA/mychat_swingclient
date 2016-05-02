package microchat.control;

import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;
import microchat.handlers.DialogHandler;
import microchat.handlers.FirebaseHandler;
import microchat.handlers.RelayserverHandler;

public class EventController {
	private FirebaseHandler firebaseHandler;
	private GUIController guiController;

	public EventController(MicrochatGUI gui) {
		guiController = new GUIController(gui);
		firebaseHandler = new FirebaseHandler(guiController);
	}

	public void writeMessage() {
		if (!guiController.getWrittenMessage().equals("")){
			firebaseHandler.createMessage(guiController.getWrittenMessage());
			guiController.writeMessage();
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
			System.out.println(UserPreferences.CHATROOMS);
			guiController.eventListChatrooms(UserPreferences.CHATROOMS);
		}
		return validated;
	}

	public void addChatroom() {
		String chatroomName = guiController.getChatroomDetails();
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
	


}
