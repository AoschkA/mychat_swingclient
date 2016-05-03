package microchat.control;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import microchat.GUI.MicrochatPopForgotPasswordGUI;
/**
 * 
 * @author Jonas Praem
 * This class controls both inputs from the pop GUI and interface functions, since the GUI is not that big, its packed in one class
 */
public class PopForgotPasswordGUIController {
	private static MicrochatPopForgotPasswordGUI gui;
	private EventController eventManager;

	public PopForgotPasswordGUIController(EventController eventManager) {
		this.eventManager = eventManager;
		gui = new MicrochatPopForgotPasswordGUI();
		addListeners();
		addKeyListeners();

	}

	public void openGUI() {
		gui.setVisible(true);
	}

	public static void closeGUI(boolean result) {
		if (result) {
			JOptionPane.showMessageDialog(gui.getContentPane(), "Your password has been send to your email");
		}
		gui.setVisible(false);
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
				eventManager.forgotPassword(gui.textField_username.getText());

			}

		});
	}

	private void addKeyListeners() {

		gui.textField_username.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					eventManager.forgotPassword(gui.textField_username.getText());

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
