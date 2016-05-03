package microchat.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import microchat.GUI.MicrochatPopChangePasswordGUI;

/**
 * 
 * @author Jonas Praem
 * This class controls both inputs from the pop GUI and interface functions, since the GUI is not that big, its packed in one class
 */
public class PopChangePasswordGUIController {
	private static MicrochatPopChangePasswordGUI gui;
	private EventController eventManager;

	public PopChangePasswordGUIController(EventController eventManager) {
		this.eventManager = eventManager;
		gui = new MicrochatPopChangePasswordGUI();
		addListeners();
		addKeyListeners();

	}

	public void openGUI() {
		gui.setVisible(true);
	}

	public static void closeGUI(boolean result) {
		if (result) {
			JOptionPane.showMessageDialog(gui.getContentPane(), "Your password has been changed!");
		}
		gui.setVisible(false);
	}

	private String[] getUserDetails() {
		String[] userDetails = new String[3];
		userDetails[0] = gui.textField_username.getText();
		userDetails[1] = gui.textField_oldPassword.getText();
		userDetails[2] = gui.textField_newPassword.getText();
		return userDetails;
	}

	private void addListeners() {

		gui.cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeGUI(false);

			}

		});

		gui.okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.changePassword(getUserDetails());

			}

		});
	}

	private void addKeyListeners() {

		gui.textField_username.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					eventManager.changePassword(getUserDetails());

				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		gui.textField_oldPassword.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					eventManager.changePassword(getUserDetails());

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		gui.textField_newPassword.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					eventManager.changePassword(getUserDetails());

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

}
