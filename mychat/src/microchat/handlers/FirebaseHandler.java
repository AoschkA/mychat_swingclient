package microchat.handlers;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import microchat.control.GUIController;
import microchat.entity.UserPreferences;

public class FirebaseHandler {
	private GUIController guiController;

	public FirebaseHandler(GUIController guiController){
		this.guiController=guiController;
	}
	
public void authenticateToFirebase() {
		
		Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/");
		firebaseReference.authWithCustomToken(UserPreferences.AUTH_TOKEN, new Firebase.AuthResultHandler() {
			@Override
			public void onAuthenticationError(FirebaseError error) {
			System.err.println("Login Failed! " + error.getMessage());
			}
			@Override
			public void onAuthenticated(AuthData authData) {
			System.out.println("Login Succeeded!");
			initiateChatrooms();
			}
		});
	}

public void addFriend(String username) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/friends/");
	Map<String, Object> firebaseMap = new HashMap<String, Object>();
	firebaseMap.put(username, 0);
	firebaseReference.updateChildren(firebaseMap);
}

public void createChatroom(String[] chatroomDetails) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/chatrooms/");
	Map<String, Object> firebaseMap = new HashMap<String, Object>();
	if (chatroomDetails[1].equals(""))
		firebaseMap.put(chatroomDetails[0], 0);
	else
		firebaseMap.put(chatroomDetails[0], chatroomDetails[1]);
	firebaseReference.updateChildren(firebaseMap);
}

public void deleteChatroom(String chatroomName) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/chatrooms/"+chatroomName+"/");
	System.out.println("Deleting chatroom: "+chatroomName);
	firebaseReference.removeValue();
	firebaseReference.push();
}

public void deleteFriend(String username) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/friends/"+username+"/");
	System.out.println("Deleting friend: "+username);
	firebaseReference.removeValue();
	firebaseReference.push();
}

public void createMessage(String message) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/chat-rooms/"
			+ UserPreferences.JOINED_CHATROOM + "/");
	Map<String, Object> firebaseMap = new HashMap<String, Object>();
	firebaseMap.put("name", UserPreferences.USERNAME);
	firebaseMap.put("message", message);
	Firebase postref = firebaseReference.push();
	postref.setValue(firebaseMap);
}

public void initiateFriendList() {
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
}

public void initiateChatrooms() {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/chatrooms/");
	firebaseReference.addChildEventListener(new ChildEventListener(){

		@Override
		public void onCancelled(FirebaseError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
			if (!UserPreferences.CHATROOMS.contains(dataSnapshot.getKey()))
				UserPreferences.CHATROOMS.add(dataSnapshot.getKey());
			guiController.eventListChatrooms(UserPreferences.CHATROOMS);
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
}

public void initiateChat() {
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
}



	

}
