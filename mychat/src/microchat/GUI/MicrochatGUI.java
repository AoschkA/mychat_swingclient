package microchat.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MicrochatGUI extends JFrame {

	private static final long serialVersionUID = -8561732815765696953L;
	
	public JPanel contentPane;
	public JTextField textField_username;
	public JTextField textField_writingblock;
	public JButton btnSend;
	public JPanel panel_chat;
	public JButton btnLogin;
	public JPasswordField textField_password;
	public JPanel panel_login;
	public JTextPane txtpnLoggedInAs;
	public JTextPane txtpnCurrentToken;
	public JPanel panel_chatrooms;
	public JTextField textField_chatroomwriter;
	public JButton btnCreateChatroom;
	public JList<String> listChatrooms;
	public JTextPane txtpnYourChatrooms;
	public JButton btnJoinchatroom;
	public JPanel panel_Friends;
	public JTextPane txtpnInThisChat;
	public JList<String> list_friends;
	public JTextField textField_addFriend;
	public JList<String> list_chatuserlist;
	public JButton btnAddSelected;
	public JButton btnAddFriend;
	public JTextPane textPane_chat;
	public JButton btnRemoveSelected;
	public JTextPane txtpnYourFriends;
	public JScrollPane scrollPane_chat;
	public JButton btnRemovechatroom;
	public JButton btnForgotPassword;
	public JButton btnChangePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MicrochatGUI frame = new MicrochatGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MicrochatGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 781);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_chat = new JPanel();
		panel_chat.setBackground(new Color(143, 188, 143));
		panel_chat.setBounds(10, 10, 524, 725);
		contentPane.add(panel_chat);
		panel_chat.setLayout(null);
		
		btnSend = new JButton("SEND");
		btnSend.setToolTipText("send message");
		btnSend.setBounds(425, 656, 89, 23);
		panel_chat.add(btnSend);
		
		textField_writingblock = new JTextField();
		textField_writingblock.setBounds(37, 657, 362, 20);
		panel_chat.add(textField_writingblock);
		textField_writingblock.setColumns(10);
		
		list_chatuserlist = new JList<String>();
		list_chatuserlist.setBounds(368, 58, 118, 307);
		panel_chat.add(list_chatuserlist);
		
		btnAddSelected = new JButton("ADD SELECTED");
		btnAddSelected.setToolTipText("add selected user to friendlist");
		btnAddSelected.setBounds(368, 376, 120, 23);
		panel_chat.add(btnAddSelected);
		
		txtpnInThisChat = new JTextPane();
		txtpnInThisChat.setForeground(new Color(255, 255, 255));
		txtpnInThisChat.setBackground(new Color(143, 188, 143));
		txtpnInThisChat.setEditable(false);
		txtpnInThisChat.setText("In this chat:");
		txtpnInThisChat.setBounds(368, 27, 120, 20);
		panel_chat.add(txtpnInThisChat);
		
		scrollPane_chat = new JScrollPane();
		scrollPane_chat.setBounds(37, 58, 308, 588);
		panel_chat.add(scrollPane_chat);
		
		textPane_chat = new JTextPane();
		scrollPane_chat.setViewportView(textPane_chat);
		textPane_chat.setEditable(false);
		
		panel_login = new JPanel();
		panel_login.setBackground(new Color(250, 250, 210));
		panel_login.setBounds(544, 10, 512, 164);
		contentPane.add(panel_login);
		panel_login.setLayout(null);
		
		textField_password = new JPasswordField();
		textField_password.setBounds(113, 23, 99, 20);
		panel_login.add(textField_password);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(222, 22, 89, 23);
		panel_login.add(btnLogin);
		
		textField_username = new JTextField();
		textField_username.setBounds(10, 23, 99, 20);
		panel_login.add(textField_username);
		textField_username.setColumns(10);
		
		txtpnLoggedInAs = new JTextPane();
		txtpnLoggedInAs.setText("Logged in as: ");
		txtpnLoggedInAs.setBackground(new Color(250, 250, 210));
		txtpnLoggedInAs.setEditable(false);
		txtpnLoggedInAs.setBounds(10, 61, 301, 20);
		panel_login.add(txtpnLoggedInAs);
		
		txtpnCurrentToken = new JTextPane();
		txtpnCurrentToken.setText("Current token:  ");
		txtpnCurrentToken.setEditable(false);
		txtpnCurrentToken.setBackground(new Color(250, 250, 210));
		txtpnCurrentToken.setBounds(10, 92, 481, 60);
		panel_login.add(txtpnCurrentToken);
		
		btnChangePassword = new JButton("CHANGE PASSWORD");
		btnChangePassword.setBounds(321, 22, 181, 23);
		panel_login.add(btnChangePassword);
		
		btnForgotPassword = new JButton("FORGOT PASSWORD");
		btnForgotPassword.setBounds(321, 58, 181, 23);
		panel_login.add(btnForgotPassword);
		
		panel_chatrooms = new JPanel();
		panel_chatrooms.setBackground(new Color(250, 250, 210));
		panel_chatrooms.setBounds(544, 179, 267, 308);
		contentPane.add(panel_chatrooms);
		panel_chatrooms.setLayout(null);
		
		listChatrooms = new JList<String>();
		listChatrooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listChatrooms.setBounds(10, 38, 143, 231);
		panel_chatrooms.add(listChatrooms);
		
		btnCreateChatroom = new JButton("JOIN");
		btnCreateChatroom.setToolTipText("join chatroom");
		btnCreateChatroom.setBounds(162, 279, 90, 22);
		panel_chatrooms.add(btnCreateChatroom);
		
		textField_chatroomwriter = new JTextField();
		textField_chatroomwriter.setBounds(10, 280, 143, 20);
		panel_chatrooms.add(textField_chatroomwriter);
		textField_chatroomwriter.setColumns(10);
		
		txtpnYourChatrooms = new JTextPane();
		txtpnYourChatrooms.setBackground(new Color(250, 250, 210));
		txtpnYourChatrooms.setEditable(false);
		txtpnYourChatrooms.setText("Your Chatrooms");
		txtpnYourChatrooms.setBounds(10, 11, 143, 20);
		panel_chatrooms.add(txtpnYourChatrooms);
		
		btnJoinchatroom = new JButton("OPEN");
		btnJoinchatroom.setToolTipText("open selected chatroom");
		btnJoinchatroom.setBounds(163, 38, 89, 23);
		panel_chatrooms.add(btnJoinchatroom);
		
		btnRemovechatroom = new JButton("Remove");
		btnRemovechatroom.setBounds(163, 72, 89, 23);
		panel_chatrooms.add(btnRemovechatroom);
		
		panel_Friends = new JPanel();
		panel_Friends.setBackground(new Color(250, 250, 210));
		panel_Friends.setBounds(544, 491, 267, 244);
		contentPane.add(panel_Friends);
		panel_Friends.setLayout(null);
		
		list_friends = new JList<String>();
		list_friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_friends.setBounds(10, 42, 132, 138);
		panel_Friends.add(list_friends);
		
		textField_addFriend = new JTextField();
		textField_addFriend.setBounds(10, 191, 132, 20);
		panel_Friends.add(textField_addFriend);
		textField_addFriend.setColumns(10);
		
		btnAddFriend = new JButton("ADD");
		btnAddFriend.setToolTipText("add new friend");
		btnAddFriend.setBounds(152, 190, 89, 23);
		panel_Friends.add(btnAddFriend);
		
		btnRemoveSelected = new JButton("REMOVE");
		btnRemoveSelected.setToolTipText("remove selected friend");
		btnRemoveSelected.setBounds(152, 39, 89, 23);
		panel_Friends.add(btnRemoveSelected);
		
		txtpnYourFriends = new JTextPane();
		txtpnYourFriends.setBackground(new Color(250, 250, 210));
		txtpnYourFriends.setText("Your Friends");
		txtpnYourFriends.setEditable(false);
		txtpnYourFriends.setBounds(10, 11, 132, 20);
		panel_Friends.add(txtpnYourFriends);
	}
}
