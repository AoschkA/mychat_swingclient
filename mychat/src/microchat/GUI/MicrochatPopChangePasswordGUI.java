package microchat.GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
/**
 * 
 * @author Jonas Praem
 * Change password GUI dialog
 */
public class MicrochatPopChangePasswordGUI extends JDialog {
	private static final long serialVersionUID = -8368711295267645746L;

	private final JPanel contentPanel = new JPanel();
	public JTextField textField_username;
	public JTextField textField_oldPassword;
	public JTextField textField_newPassword;
	public JButton okButton;
	public JButton cancelButton;
	private JTextPane txtpnUsername;
	private JTextPane txtpnOldPassword;
	private JTextPane txtpnNewPassword;
	private JTextPane txtpnToChangeYour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MicrochatPopChangePasswordGUI dialog = new MicrochatPopChangePasswordGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MicrochatPopChangePasswordGUI() {
		setTitle("CHANGE PASSWORD");
		setBounds(100, 100, 450, 205);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			okButton = new JButton("OK");
			okButton.setBounds(357, 119, 65, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("CANCEL");
			cancelButton.setBounds(261, 119, 86, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}

		textField_username = new JTextField();
		textField_username.setBounds(56, 88, 107, 20);
		contentPanel.add(textField_username);
		textField_username.setColumns(10);

		textField_oldPassword = new JTextField();
		textField_oldPassword.setColumns(10);
		textField_oldPassword.setBounds(173, 88, 107, 20);
		contentPanel.add(textField_oldPassword);

		textField_newPassword = new JTextField();
		textField_newPassword.setColumns(10);
		textField_newPassword.setBounds(290, 88, 107, 20);
		contentPanel.add(textField_newPassword);

		txtpnUsername = new JTextPane();
		txtpnUsername.setBackground(new Color(250, 250, 210));
		txtpnUsername.setFont(new Font("Sitka Text", Font.ITALIC, 9));
		txtpnUsername.setText("USERNAME");
		txtpnUsername.setEditable(false);
		txtpnUsername.setBounds(56, 57, 107, 20);
		contentPanel.add(txtpnUsername);

		txtpnOldPassword = new JTextPane();
		txtpnOldPassword.setText("OLD PASSWORD");
		txtpnOldPassword.setFont(new Font("Sitka Text", Font.ITALIC, 9));
		txtpnOldPassword.setEditable(false);
		txtpnOldPassword.setBackground(new Color(250, 250, 210));
		txtpnOldPassword.setBounds(173, 57, 107, 20);
		contentPanel.add(txtpnOldPassword);

		txtpnNewPassword = new JTextPane();
		txtpnNewPassword.setText("NEW PASSWORD");
		txtpnNewPassword.setFont(new Font("Sitka Text", Font.ITALIC, 9));
		txtpnNewPassword.setEditable(false);
		txtpnNewPassword.setBackground(new Color(250, 250, 210));
		txtpnNewPassword.setBounds(290, 57, 107, 20);
		contentPanel.add(txtpnNewPassword);

		txtpnToChangeYour = new JTextPane();
		txtpnToChangeYour.setFont(new Font("Sitka Heading", Font.ITALIC, 11));
		txtpnToChangeYour.setBackground(new Color(250, 250, 210));
		txtpnToChangeYour.setText("To change your password, enter your account details and provice a new password");
		txtpnToChangeYour.setEditable(false);
		txtpnToChangeYour.setBounds(56, 11, 291, 48);
		contentPanel.add(txtpnToChangeYour);
	}
}
