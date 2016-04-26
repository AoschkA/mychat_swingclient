package microchat.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class MicrochatGUI extends JFrame {

	private static final long serialVersionUID = -8561732815765696953L;
	
	public JPanel contentPane;
	public JTextField textField_username;
	public JTextField textField_writingblock;
	public JButton btnSend;
	public JPanel panel_chat;
	public JButton btnLogin;
	public JTextArea textArea_chat;
	public JPasswordField textField_password;
	public JPanel panel_login;
	public JTextPane txtpnLoggedInAs;
	public JTextPane txtpnCurrentToken;
	public JPanel panel_chatrooms;
	public JTextField textField_chatroomwriter;
	public JButton btnCreateChatroom;
	public JList<String> listChatrooms;
	public JTextPane txtpnYourChatrooms;

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
		setBounds(100, 100, 861, 675);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_chat = new JPanel();
		panel_chat.setBackground(UIManager.getColor("Button.shadow"));
		panel_chat.setBounds(0, 0, 524, 598);
		contentPane.add(panel_chat);
		panel_chat.setLayout(null);
		
		btnSend = new JButton("SEND");
		btnSend.setBounds(425, 546, 89, 23);
		panel_chat.add(btnSend);
		
		textField_writingblock = new JTextField();
		textField_writingblock.setBounds(37, 547, 362, 20);
		panel_chat.add(textField_writingblock);
		textField_writingblock.setColumns(10);
		
		textArea_chat = new JTextArea();
		textArea_chat.setBounds(28, 66, 466, 460);
		panel_chat.add(textArea_chat);
		textArea_chat.setEditable(false);
		
		panel_login = new JPanel();
		panel_login.setBackground(UIManager.getColor("Button.shadow"));
		panel_login.setBounds(531, 0, 314, 171);
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
		txtpnLoggedInAs.setBackground(UIManager.getColor("Button.shadow"));
		txtpnLoggedInAs.setEditable(false);
		txtpnLoggedInAs.setBounds(10, 61, 165, 20);
		panel_login.add(txtpnLoggedInAs);
		
		txtpnCurrentToken = new JTextPane();
		txtpnCurrentToken.setText("Current token:  ");
		txtpnCurrentToken.setEditable(false);
		txtpnCurrentToken.setBackground(SystemColor.controlShadow);
		txtpnCurrentToken.setBounds(10, 91, 294, 60);
		panel_login.add(txtpnCurrentToken);
		
		panel_chatrooms = new JPanel();
		panel_chatrooms.setBackground(UIManager.getColor("Button.shadow"));
		panel_chatrooms.setBounds(563, 192, 282, 338);
		contentPane.add(panel_chatrooms);
		panel_chatrooms.setLayout(null);
		
		listChatrooms = new JList<String>();
		listChatrooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listChatrooms.setBounds(30, 49, 143, 231);
		panel_chatrooms.add(listChatrooms);
		
		btnCreateChatroom = new JButton("CREATE");
		btnCreateChatroom.setBounds(192, 292, 81, 23);
		panel_chatrooms.add(btnCreateChatroom);
		
		textField_chatroomwriter = new JTextField();
		textField_chatroomwriter.setBounds(30, 293, 143, 20);
		panel_chatrooms.add(textField_chatroomwriter);
		textField_chatroomwriter.setColumns(10);
		
		txtpnYourChatrooms = new JTextPane();
		txtpnYourChatrooms.setEditable(false);
		txtpnYourChatrooms.setText("Your Chatrooms");
		txtpnYourChatrooms.setBounds(30, 18, 143, 20);
		panel_chatrooms.add(txtpnYourChatrooms);
	}
}
