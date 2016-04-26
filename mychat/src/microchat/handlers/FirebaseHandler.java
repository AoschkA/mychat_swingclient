package microchat.handlers;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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

public void createChatroom(String chatroomName) {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/chatrooms/");
	Map<String, Object> firebaseMap = new HashMap<String, Object>();
	firebaseMap.put(chatroomName, 0);
	firebaseReference.updateChildren(firebaseMap);
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
			System.out.println("key- "+dataSnapshot.getKey());
			System.out.println(UserPreferences.CHATROOMS);
			
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
			System.out.println(dataSnapshot);
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
