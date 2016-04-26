package microchat.handlers;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import microchat.entity.UserPreferences;

public class FirebaseHandler implements ChildEventListener{
	
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
			getListedChatrooms();
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

public void getListedChatrooms() {
	Firebase firebaseReference = new Firebase("https://micro-chat.firebaseio.com/users/"+
			UserPreferences.USERNAME+"/chatrooms/");
	firebaseReference.addChildEventListener(this);
}

@Override
public void onCancelled(FirebaseError arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onChildAdded(DataSnapshot arg0, String arg1) {
	System.out.println("MADE IT HERE");
	System.out.println(arg0.getValue().toString());
	System.out.println(arg1);
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

	

}
