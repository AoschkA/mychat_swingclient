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
		firebaseHandler.createMessage(guiController.getWrittenMessage());
		guiController.writeMessage();
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
	

}
