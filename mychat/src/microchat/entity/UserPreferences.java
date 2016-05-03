package microchat.entity;

import java.util.ArrayList;
/**
 * 
 * @author Jonas Praem
 * Stores the user preferences
 */
public class UserPreferences {
	public static ArrayList<String> CHATROOMS = new ArrayList<String>();
	public static ArrayList<String> FRIENDS = new ArrayList<String>();
	public static String AUTH_TOKEN = "-1";
	public static String USERNAME = "-1";
	public static String PASSWORD = "-1";
	public static String JOINED_CHATROOM;

}
