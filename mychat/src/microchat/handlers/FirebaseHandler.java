package microchat.handlers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import microchat.control.GUIController;
import microchat.entity.UserPreferences;
/**
 * 
 * @author Jonas Praem
 * Handles the firebase communication
 */
public class FirebaseHandler {
	private GUIController guiController;

	public FirebaseHandler(GUIController guiController){
		this.guiController=guiController;
	}

	// Authenticates to firebase
	public void authenticateToFirebase() {
		System.out.println("logging in...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/");
		firebaseReference.authWithCustomToken(UserPreferences.AUTH_TOKEN, new Firebase.AuthResultHandler() {
			@Override
			public void onAuthenticationError(FirebaseError error) {
				System.err.println("Login Failed! " + error.getMessage());
			}
			@Override
			public void onAuthenticated(AuthData authData) {
				System.out.println("Login Succeeded!");
				// initiate chatrooms
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							initiateChatrooms();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				// initiate friend list
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							initiateFriendList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

	// Adds friend
	public void addFriend(String username) {
		System.out.println("Adding friend "+username+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/friends/");
		Map<String, Object> firebaseMap = new HashMap<String, Object>();
		firebaseMap.put(username, 0);
		firebaseReference.updateChildren(firebaseMap);
		System.out.println(username+" added to friendlist");
	}

	// Creates chatroom
	public void createChatroom(String[] chatroomDetails) {
		System.out.println("Joining chatroom "+chatroomDetails[0]+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/chatrooms/");
		Map<String, Object> firebaseMap = new HashMap<String, Object>();
		if (chatroomDetails[1].equals(""))
			firebaseMap.put(chatroomDetails[0], 0);
		else
			firebaseMap.put(chatroomDetails[0], chatroomDetails[1]);
		firebaseReference.updateChildren(firebaseMap);
		System.out.println("Chatroom "+chatroomDetails[0]+" joined");
	}

	// Deletes chatroom
	public void deleteChatroom(String chatroomName) {
		System.out.println("Deleting chatroom "+chatroomName+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/chatrooms/"+chatroomName+"/");
		System.out.println("Deleting chatroom: "+chatroomName);
		firebaseReference.removeValue();
		firebaseReference.push();
		System.out.println("Chatroom "+chatroomName+" deleted");
	}

	// Deletes friend
	public void deleteFriend(String username) {
		System.out.println("Deleting friend "+username+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/friends/"+username+"/");
		System.out.println("Deleting friend: "+username);
		firebaseReference.removeValue();
		firebaseReference.push();
		System.out.println(username+" removed from friendlist");
	}

	// Sends a message
	public void createMessage(String message) {
		System.out.println("Sending message "+message+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/chat-rooms/"
				+ UserPreferences.JOINED_CHATROOM + "/");
		Map<String, Object> firebaseMap = new HashMap<String, Object>();
		firebaseMap.put("name", UserPreferences.USERNAME);
		firebaseMap.put("message", message);
		Firebase postref = firebaseReference.push();
		postref.setValue(firebaseMap);
		System.out.println("Message "+message+" sent");
	}

	// Gets the users friendlist
	public void initiateFriendList() {
		System.out.println("Synchronizing with friendlist...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/friends/");
		firebaseReference.addChildEventListener(new ChildEventListener() {

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				if (!UserPreferences.FRIENDS.contains(dataSnapshot.getKey()))
					UserPreferences.FRIENDS.add(dataSnapshot.getKey());
				guiController.eventListFriends(UserPreferences.FRIENDS);

			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				guiController.removeFriend(dataSnapshot.getKey());

			}

		});
		guiController.eventListFriends(UserPreferences.FRIENDS);
		System.out.println("Synchronized with friendlist");
	}

	// Gets the users chatrooms
	public void initiateChatrooms() {
		System.out.println("Synchronizing with chatroomlist...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
				UserPreferences.USERNAME+"/chatrooms/");
		firebaseReference.addChildEventListener(new ChildEventListener(){

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				// Creates a list of stored chatrooms
				ArrayList<String> chatroomNameList = new ArrayList<String>();
				for (String[] chatroomDetails : UserPreferences.CHATROOMS) 
					chatroomNameList.add(chatroomDetails[0]);
				// get chatroom name
				String chatroomName = dataSnapshot.getKey();
				// if not already registered, add chatroom and password to preferences
				if (!chatroomNameList.contains(chatroomName)){
					String password = dataSnapshot.getValue().toString();
					String[] result = {chatroomName, password};
					UserPreferences.CHATROOMS.add(result);
				}
				// Adds names to GUI
				guiController.eventListChatrooms();
			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				guiController.removeChatroom(dataSnapshot.getKey());

			}

		});
		guiController.eventListChatrooms();
		System.out.println("Synchronized with chatroomlist");
	}

	// Gets the chatlog for a specific chatroom
	public void initiateChat() {
		System.out.println("Getting chatlog from chatroom "+UserPreferences.JOINED_CHATROOM+"...");
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/chat-rooms/"
				+UserPreferences.JOINED_CHATROOM+"/");
		firebaseReference.addChildEventListener(new ChildEventListener(){

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
				DialogHandler.createMessage(dataSnapshot.getValue().toString());
				guiController.eventUpdateChat();
			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub

			}

		});
		System.out.println("Chatlog recieved for chatroom "+UserPreferences.JOINED_CHATROOM);
	}





}
