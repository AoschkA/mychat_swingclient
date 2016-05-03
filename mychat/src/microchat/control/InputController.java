package microchat.control;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import microchat.GUI.MicrochatGUI;
/**
 * 
 * @author Jonas Praem
 *
 */
public class InputController {
	private MicrochatGUI gui;
	private EventController eventManager;
	private PopChangePasswordGUIController popChangePasswordGUIController;
	private PopForgotPasswordGUIController popForgotPasswordGUIController;

	public InputController() {
		gui = new MicrochatGUI();
		eventManager = new EventController(gui);
		popChangePasswordGUIController = new PopChangePasswordGUIController(eventManager);
		popForgotPasswordGUIController = new PopForgotPasswordGUIController(eventManager);
		addListeners();
		addKeyListeners();
	}

	// initializing microchat 
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

	// Adds all click listeners
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

		// Create chatroom button
		gui.btnCreateChatroom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.addChatroom();
			}

		});

		// Join chatroom button
		gui.btnJoinchatroom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.openChatroom();

			}

		});

		// Remove chatroom button
		gui.btnRemovechatroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				eventManager.removeChatroom();

			}

		});

		// Add friend button
		gui.btnAddFriend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				eventManager.addFriend();

			}

		});

		// Adds selected user to friendlist button
		gui.btnAddSelected.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.addFriendFromList();

			}

		});

		// Removes selected user from friendlist button
		gui.btnRemoveSelected.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.removeFriend();

			}

		});

		// Change password button
		gui.btnChangePassword.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				popChangePasswordGUIController.openGUI();

			}

		});

		// Forgot password button
		gui.btnForgotPassword.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				popForgotPasswordGUIController.openGUI();

			}

		});

		// Update filelist button
		gui.btnUpdateFilelist.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.updateFilelist();

			}

		});

		// Open system explore button
		gui.btnBrowse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.openFileExplore();

			}

		});

		// Upload file button
		gui.btnUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.uploadFile();

			}

		});

		// Download file button
		gui.btnDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.downloadFile();

			}

		});

		// Remove file button
		gui.btnRemoveFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eventManager.removeFile();

			}

		});

	}

	/**
	 * Adds key listeners, adds a enter check for every writing block, so its not necessary to always press buttons
	 */
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

		gui.textField_username.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.validateUser();

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

		gui.textField_chatroomWriter.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.addChatroom();

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

		gui.textField_addFriendWriter.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.addFriend();

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

		gui.textField_pathToFileWriter.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.uploadFile();

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

		gui.textField_chatroomPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) eventManager.addChatroom();

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
	}

}
