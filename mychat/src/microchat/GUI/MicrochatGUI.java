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
import java.awt.Font;
/**
 * 
 * @author Jonas Praem
 * Stores the user preferences
 * The Main GUI
 */
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
	public JTextField textField_chatroomWriter;
	public JButton btnCreateChatroom;
	public JList<String> listChatrooms;
	public JTextPane txtpnYourChatrooms;
	public JButton btnJoinchatroom;
	public JPanel panel_Friends;
	public JTextPane txtpnInThisChat;
	public JList<String> list_friends;
	public JTextField textField_addFriendWriter;
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
	public JList<String> list_files;
	public JTextField textField_pathToFileWriter;
	public JTextPane txtpnFileserver;
	public JPanel panel_fileserver;
	public JButton btnBrowse;
	public JButton btnUpload;
	public JTextPane txtpnFileList;
	public JButton btnDownload;
	public JButton btnUpdateFilelist;
	private JScrollPane scrollPane_friends;
	private JScrollPane scrollPane_filelist;
	public JButton btnRemoveFile;
	public JTextField textField_chatroomPassword;
	private JTextPane txtpnThisIsYour;
	private JTextPane txtpnAddFriendsFrom;
	private JTextPane txtpnchat;
	private JTextPane txtpnChatroomId;
	private JTextPane txtpnProvidePasswordIf;

	/**
	 * Test the GUI layout
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
		setBounds(100, 100, 1134, 774);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_chat = new JPanel();
		panel_chat.setBackground(new Color(143, 188, 143));
		panel_chat.setBounds(10, 10, 588, 725);
		contentPane.add(panel_chat);
		panel_chat.setLayout(null);

		btnSend = new JButton("Send");
		btnSend.setToolTipText("send message");
		btnSend.setBounds(425, 656, 89, 23);
		panel_chat.add(btnSend);

		textField_writingblock = new JTextField();
		textField_writingblock.setBounds(37, 657, 362, 34);
		panel_chat.add(textField_writingblock);
		textField_writingblock.setColumns(10);

		list_chatuserlist = new JList<String>();
		list_chatuserlist.setBounds(458, 57, 118, 307);
		panel_chat.add(list_chatuserlist);

		btnAddSelected = new JButton("Add friend");
		btnAddSelected.setToolTipText("add selected user to friendlist");
		btnAddSelected.setBounds(458, 411, 118, 23);
		panel_chat.add(btnAddSelected);

		txtpnInThisChat = new JTextPane();
		txtpnInThisChat.setForeground(new Color(0, 0, 0));
		txtpnInThisChat.setBackground(new Color(143, 188, 143));
		txtpnInThisChat.setEditable(false);
		txtpnInThisChat.setText("In this chat:");
		txtpnInThisChat.setBounds(456, 25, 120, 20);
		panel_chat.add(txtpnInThisChat);

		scrollPane_chat = new JScrollPane();
		scrollPane_chat.setBounds(37, 58, 362, 588);
		panel_chat.add(scrollPane_chat);

		textPane_chat = new JTextPane();
		scrollPane_chat.setViewportView(textPane_chat);
		textPane_chat.setEditable(false);

		txtpnAddFriendsFrom = new JTextPane();
		txtpnAddFriendsFrom.setText("Add friends from the chatlist directly here");
		txtpnAddFriendsFrom.setFont(new Font("Sitka Subheading", Font.PLAIN, 11));
		txtpnAddFriendsFrom.setEditable(false);
		txtpnAddFriendsFrom.setBackground(new Color(143, 188, 143));
		txtpnAddFriendsFrom.setBounds(458, 363, 118, 34);
		panel_chat.add(txtpnAddFriendsFrom);

		txtpnchat = new JTextPane();
		txtpnchat.setBackground(new Color(143, 188, 143));
		txtpnchat.setFont(new Font("Placard MT Condensed", Font.BOLD, 40));
		txtpnchat.setText("\u00B5-Chat");
		txtpnchat.setEditable(false);
		txtpnchat.setBounds(10, 0, 241, 61);
		panel_chat.add(txtpnchat);

		panel_login = new JPanel();
		panel_login.setBackground(new Color(250, 250, 210));
		panel_login.setBounds(610, 10, 512, 164);
		contentPane.add(panel_login);
		panel_login.setLayout(null);

		textField_password = new JPasswordField();
		textField_password.setBounds(113, 23, 99, 20);
		panel_login.add(textField_password);

		btnLogin = new JButton("Login");
		btnLogin.setToolTipText("login with entered user details");
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

		btnChangePassword = new JButton("Change password");
		btnChangePassword.setToolTipText("change your password");
		btnChangePassword.setBounds(321, 22, 181, 23);
		panel_login.add(btnChangePassword);

		btnForgotPassword = new JButton("Forgot password");
		btnForgotPassword.setToolTipText("forgot your password?");
		btnForgotPassword.setBounds(321, 58, 181, 23);
		panel_login.add(btnForgotPassword);

		panel_chatrooms = new JPanel();
		panel_chatrooms.setBackground(new Color(250, 250, 210));
		panel_chatrooms.setBounds(610, 180, 267, 327);
		contentPane.add(panel_chatrooms);
		panel_chatrooms.setLayout(null);

		JScrollPane scrollPane_chatrooms = new JScrollPane();
		scrollPane_chatrooms.setBounds(10, 29, 143, 211);
		panel_chatrooms.add(scrollPane_chatrooms);

		listChatrooms = new JList<String>();
		scrollPane_chatrooms.setViewportView(listChatrooms);
		listChatrooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnCreateChatroom = new JButton("Join");
		btnCreateChatroom.setToolTipText("join chatroom, provide password if required");
		btnCreateChatroom.setBounds(165, 262, 90, 22);
		panel_chatrooms.add(btnCreateChatroom);

		textField_chatroomWriter = new JTextField();
		textField_chatroomWriter.setBounds(10, 264, 143, 20);
		panel_chatrooms.add(textField_chatroomWriter);
		textField_chatroomWriter.setColumns(10);

		txtpnYourChatrooms = new JTextPane();
		txtpnYourChatrooms.setBackground(new Color(250, 250, 210));
		txtpnYourChatrooms.setEditable(false);
		txtpnYourChatrooms.setText("Your Chatrooms");
		txtpnYourChatrooms.setBounds(10, 11, 143, 22);
		panel_chatrooms.add(txtpnYourChatrooms);

		btnJoinchatroom = new JButton("Open");
		btnJoinchatroom.setToolTipText("open selected chatroom");
		btnJoinchatroom.setBounds(163, 38, 89, 23);
		panel_chatrooms.add(btnJoinchatroom);

		btnRemovechatroom = new JButton("Remove");
		btnRemovechatroom.setToolTipText("remove selected chatroom");
		btnRemovechatroom.setBounds(163, 72, 89, 23);
		panel_chatrooms.add(btnRemovechatroom);

		textField_chatroomPassword = new JTextField();
		textField_chatroomPassword.setBounds(10, 296, 143, 20);
		panel_chatrooms.add(textField_chatroomPassword);
		textField_chatroomPassword.setColumns(10);

		txtpnChatroomId = new JTextPane();
		txtpnChatroomId.setBackground(new Color(250, 250, 210));
		txtpnChatroomId.setFont(new Font("Sitka Subheading", Font.PLAIN, 11));
		txtpnChatroomId.setText("Chatroom id:");
		txtpnChatroomId.setEditable(false);
		txtpnChatroomId.setBounds(10, 242, 90, 20);
		panel_chatrooms.add(txtpnChatroomId);

		txtpnProvidePasswordIf = new JTextPane();
		txtpnProvidePasswordIf.setText("provide password if required");
		txtpnProvidePasswordIf.setFont(new Font("Sitka Subheading", Font.PLAIN, 10));
		txtpnProvidePasswordIf.setEditable(false);
		txtpnProvidePasswordIf.setBackground(new Color(250, 250, 210));
		txtpnProvidePasswordIf.setBounds(162, 288, 105, 52);
		panel_chatrooms.add(txtpnProvidePasswordIf);

		panel_Friends = new JPanel();
		panel_Friends.setBackground(new Color(250, 250, 210));
		panel_Friends.setBounds(610, 511, 267, 224);
		contentPane.add(panel_Friends);
		panel_Friends.setLayout(null);

		scrollPane_friends = new JScrollPane();
		scrollPane_friends.setBounds(10, 29, 148, 151);
		panel_Friends.add(scrollPane_friends);

		list_friends = new JList<String>();
		scrollPane_friends.setViewportView(list_friends);
		list_friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		textField_addFriendWriter = new JTextField();
		textField_addFriendWriter.setBounds(10, 191, 132, 20);
		panel_Friends.add(textField_addFriendWriter);
		textField_addFriendWriter.setColumns(10);

		btnAddFriend = new JButton("Add");
		btnAddFriend.setToolTipText("add new friend");
		btnAddFriend.setBounds(152, 190, 89, 23);
		panel_Friends.add(btnAddFriend);

		btnRemoveSelected = new JButton("Remove");
		btnRemoveSelected.setToolTipText("remove selected friend");
		btnRemoveSelected.setBounds(168, 39, 89, 23);
		panel_Friends.add(btnRemoveSelected);

		txtpnYourFriends = new JTextPane();
		txtpnYourFriends.setBackground(new Color(250, 250, 210));
		txtpnYourFriends.setText("Your Friends");
		txtpnYourFriends.setEditable(false);
		txtpnYourFriends.setBounds(10, 11, 132, 20);
		panel_Friends.add(txtpnYourFriends);

		panel_fileserver = new JPanel();
		panel_fileserver.setBackground(new Color(250, 250, 210));
		panel_fileserver.setBounds(887, 180, 235, 555);
		contentPane.add(panel_fileserver);
		panel_fileserver.setLayout(null);

		scrollPane_filelist = new JScrollPane();
		scrollPane_filelist.setBounds(10, 113, 200, 370);
		panel_fileserver.add(scrollPane_filelist);

		list_files = new JList<String>();
		scrollPane_filelist.setViewportView(list_files);

		txtpnFileserver = new JTextPane();
		txtpnFileserver.setBackground(new Color(250, 250, 210));
		txtpnFileserver.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		txtpnFileserver.setText("Fileserver");
		txtpnFileserver.setEditable(false);
		txtpnFileserver.setBounds(10, 11, 92, 20);
		panel_fileserver.add(txtpnFileserver);

		textField_pathToFileWriter = new JTextField();
		textField_pathToFileWriter.setBounds(30, 494, 125, 20);
		panel_fileserver.add(textField_pathToFileWriter);
		textField_pathToFileWriter.setColumns(10);

		btnBrowse = new JButton("...");
		btnBrowse.setToolTipText("browse your system");
		btnBrowse.setBounds(165, 493, 45, 23);
		panel_fileserver.add(btnBrowse);

		btnUpload = new JButton("Upload");
		btnUpload.setToolTipText("upload file");
		btnUpload.setBounds(66, 521, 89, 23);
		panel_fileserver.add(btnUpload);

		txtpnFileList = new JTextPane();
		txtpnFileList.setFont(new Font("Sitka Heading", Font.BOLD, 11));
		txtpnFileList.setText("File list");
		txtpnFileList.setBackground(new Color(250, 250, 210));
		txtpnFileList.setBounds(10, 89, 92, 20);
		panel_fileserver.add(txtpnFileList);

		btnDownload = new JButton("Download");
		btnDownload.setBounds(112, 11, 113, 23);
		panel_fileserver.add(btnDownload);

		btnUpdateFilelist = new JButton("Update");
		btnUpdateFilelist.setToolTipText("update filelist");
		btnUpdateFilelist.setBounds(112, 86, 113, 23);
		panel_fileserver.add(btnUpdateFilelist);

		btnRemoveFile = new JButton("Remove");
		btnRemoveFile.setBounds(112, 38, 113, 23);
		panel_fileserver.add(btnRemoveFile);

		txtpnThisIsYour = new JTextPane();
		txtpnThisIsYour.setText("This is your personal file folder");
		txtpnThisIsYour.setFont(new Font("Sitka Subheading", Font.PLAIN, 11));
		txtpnThisIsYour.setEditable(false);
		txtpnThisIsYour.setBackground(new Color(250, 250, 210));
		txtpnThisIsYour.setBounds(12, 38, 90, 52);
		panel_fileserver.add(txtpnThisIsYour);
	}
}
