package mychat.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class GUI {
	
	public static int dialogsize = 20;
	public JFrame frame;
	public JTextField txtNkn;
	private ArrayList<ArrayList<JLabel>> labels;
	private JPanel dialog_panel;
	private JScrollPane scrollpane;
	private ArrayList<JPanel> dialoglist;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(77,189,144));
		
		dialog_panel = new JPanel();
		dialog_panel.setBounds(117, 33, 635, 365);
		frame.getContentPane().add(dialog_panel);
		dialog_panel.setLayout(new GridLayout(dialogsize, 0, 0, 0));
		labels = new ArrayList<ArrayList<JLabel>>();
		dialoglist = new ArrayList<JPanel>();
		dialog_panel.setOpaque(true);
		dialog_panel.setBackground(new Color(77,189,144));
		
		for (int i = 0; i<dialogsize; i++) {
			JPanel temppanel = new JPanel();
			temppanel.setLayout(new FlowLayout());
			dialoglist.add(temppanel);
			dialog_panel.add(temppanel);
		}
		
		txtNkn = new JTextField();
		txtNkn.setText("nkn");
		txtNkn.setBounds(127, 410, 459, 19);
		frame.getContentPane().add(txtNkn);
		txtNkn.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(598, 407, 117, 25);
		frame.getContentPane().add(btnSend);
	}
	
	public void updateDialog(ArrayList<ArrayList<JLabel>> dialog) {
		labels=dialog;
		int messagecounter = dialog.size()-1;
		for (int i=0; i<dialog.size(); i++){
			ArrayList<JLabel> messagelist = labels.get(i);
			for (JLabel lab : messagelist) {
				dialoglist.get(messagecounter).add(lab);
			}
			messagecounter--;
		}
		dialog_panel.removeAll();
		for (int i = 0; i<dialog.size(); i++) {
			JPanel panel = dialoglist.get(i);
			panel.setBackground(Color.WHITE);
			panel.setOpaque(true);
			dialog_panel.add(panel);
		}
		
	}
}
