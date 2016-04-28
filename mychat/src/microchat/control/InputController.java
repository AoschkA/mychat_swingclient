package microchat.control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import microchat.GUI.MicrochatGUI;
import microchat.entity.UserPreferences;

public class InputController {
	private MicrochatGUI gui;
	private EventController eventManager;

	public InputController() {
		gui = new MicrochatGUI();
		eventManager = new EventController(gui);
		addListeners();
		addKeyListeners();
	}

	public void initializeMicrochat() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private void addListeners() {
		// Login button
		gui.btnLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.validateUser();
			}

		});

		// Send button
		gui.btnSend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.writeMessage();

			}

		});

		gui.btnCreateChatroom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.addChatroom();
			}

		});
		
		gui.btnJoinchatroom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.joinChatroom();
				
			}
			
		});

	}

	private void addKeyListeners() {
		// For pressing enter to send text in writing block
		gui.textField_writingblock.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.writeMessage();
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}

		});
		
		gui.textField_password.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.validateUser();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		});
	}

}
