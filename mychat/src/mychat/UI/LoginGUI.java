package mychat.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_password;
	private JTextField textField_username;
	public JButton btnLogin;
	public JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_password = new JTextField();
		textField_password.setBounds(160, 125, 199, 20);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(160, 82, 199, 20);
		contentPane.add(textField_username);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setForeground(SystemColor.text);
		txtpnUsername.setBackground(SystemColor.textHighlight);
		txtpnUsername.setFont(new Font("Sylfaen", Font.BOLD, 11));
		txtpnUsername.setEditable(false);
		txtpnUsername.setText("USERNAME");
		txtpnUsername.setBounds(77, 82, 73, 20);
		contentPane.add(txtpnUsername);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setForeground(SystemColor.text);
		txtpnPassword.setBackground(SystemColor.textHighlight);
		txtpnPassword.setFont(new Font("Sylfaen", Font.BOLD, 11));
		txtpnPassword.setText("PASSWORD");
		txtpnPassword.setEditable(false);
		txtpnPassword.setBounds(77, 125, 75, 20);
		contentPane.add(txtpnPassword);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(270, 165, 89, 23);
		contentPane.add(btnLogin);
		
		btnExit = new JButton("EXIT");
		btnExit.setBounds(160, 165, 89, 23);
		contentPane.add(btnExit);
	}
	
	public void closeGUI() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public String[] getUserInfo() {
		String[] info = new String[2];
		info[0]=textField_username.getText();
		info[1]=textField_password.getText();
		return info;
	}
}
