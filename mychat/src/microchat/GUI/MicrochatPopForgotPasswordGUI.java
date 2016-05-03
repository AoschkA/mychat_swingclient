package microchat.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
 * The forgot password GUI dialog
 */
public class MicrochatPopForgotPasswordGUI extends JDialog {
	private static final long serialVersionUID = 7985208805975050904L;

	private final JPanel contentPanel = new JPanel();
	public JTextField textField_username;
	public JButton okButton;
	public JButton cancelButton;
	private JTextPane txtpnUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MicrochatPopForgotPasswordGUI dialog = new MicrochatPopForgotPasswordGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MicrochatPopForgotPasswordGUI() {
		setTitle("FORGOT PASSWORD");
		setBounds(100, 100, 255, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_username = new JTextField();
			textField_username.setBounds(50, 63, 157, 20);
			contentPanel.add(textField_username);
			textField_username.setColumns(10);
		}
		{
			okButton = new JButton("OK");
			okButton.setBounds(150, 94, 63, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("CANCEL");
			cancelButton.setBounds(50, 94, 90, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			txtpnUsername = new JTextPane();
			txtpnUsername.setBackground(new Color(250, 250, 210));
			txtpnUsername.setFont(new Font("Sitka Heading", Font.ITALIC, 11));
			txtpnUsername.setText("Recieve an email with your password, by entering your username");
			txtpnUsername.setEditable(false);
			txtpnUsername.setBounds(50, 13, 157, 48);
			contentPanel.add(txtpnUsername);
		}
	}

}
