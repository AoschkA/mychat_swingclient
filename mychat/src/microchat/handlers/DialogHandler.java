package microchat.handlers;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import microchat.entity.UserPreferences;
/**
 * 
 * @author Jonas Praem
 * Handles the dialogs / chat layout
 */
public class DialogHandler {
	public static ArrayList<String> CHAT_USERS = new ArrayList<String>();
	public static ArrayList<String[]> MESSAGES = new ArrayList<String[]>();

	// Formats the message and adds it to the messagelist
	public static void createMessage(String message) {
		if (message.startsWith("{name=") && message.endsWith("}")){
			String[] valueSplit = message.split(", ");
			String[] nameValue = valueSplit[0].split("=");
			String[] messageValue = valueSplit[1].split("=");
			String name = nameValue[1];
			String chatMessage = messageValue[1].substring(0, messageValue[1].length()-1);
			if (!CHAT_USERS.contains(name) && !name.equals(UserPreferences.USERNAME)) CHAT_USERS.add(name);
			MESSAGES.add(new String[] {name, chatMessage});
		}
	}

	// Generates the JTextPane chat, by using a StyledDocument
	public static void generateChat(JTextPane textPane){
		// Clears textPane
		textPane.setText("");

		StyledDocument doc = textPane.getStyledDocument();
		Style style = textPane.addStyle("Style1", null);
		for (String[] message : MESSAGES) {
			if (message[0].equals(UserPreferences.USERNAME))
				StyleConstants.setForeground(style, new Color(0, 102, 0));
			else
				StyleConstants.setForeground(style, new Color(0, 128, 255));

			try { doc.insertString(doc.getLength(), message[0]+ ": \t",style); }
			catch (BadLocationException e){}

			StyleConstants.setForeground(style, Color.black);

			try { doc.insertString(doc.getLength(), message[1]+ "\n" ,style); }
			catch (BadLocationException e){};
		}
	}

	// Clears chat
	public static void clearChat() {
		CHAT_USERS = new ArrayList<String>();
		MESSAGES = new ArrayList<String[]>();
	}

}
