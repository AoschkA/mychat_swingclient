package mychat.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JLabel;

public class GUIController {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.updateDialog(generateJLabels());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static ArrayList<ArrayList<JLabel>> generateJLabels() {
		ArrayList<ArrayList<JLabel>> labels = new ArrayList<ArrayList<JLabel>>();
		for (int i=0; i<20; i++) {
			ArrayList<JLabel> message = new ArrayList<JLabel>(); 
			message.add(generateUserLabel());
			message.add(generateMessageLabel());
			labels.add(message);
		}
		return labels;
	}
	
	private static JLabel generateUserLabel() {
		JLabel temp = new JLabel("User: ");
		temp.setForeground(Color.BLACK);
		return temp;
	}
	private static JLabel generateMessageLabel() {
		JLabel temp = new JLabel("message");
		temp.setForeground(Color.BLUE);
		return temp;
	}
	

}
