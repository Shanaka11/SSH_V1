import java.awt.EventQueue;

import javax.swing.JFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class settings {

	private JFrame frmSettings;
	private JTextField txtpath;
	private JTextField textField_1;
	private JTextField textField_2;

	static String [] settings = new String[3];
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadset();
					settings window = new settings();
					window.frmSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	//To show errors
	public static void infoBox(String infoMessage, String titleBar){
		
			JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
   
	}	
	// Saving Settings
	public static void saveset(){
		
	}
	// Loading settings
	public static void loadset(){
		
		File file = new File("Config.txt");
		try {
			Scanner x = new Scanner(file);
			
			settings[0] = x.nextLine();	// LogPath
			settings[1] = x.nextLine(); // Unblock Interval
			settings[2] = x.nextLine(); // Refresh Interval
			
			x.close();
			
		} catch (FileNotFoundException e) {
			
			infoBox("Make sure Config.txt is in the same folder","Cannot find Config.txt");
		
		}
		
		
	}
	public settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSettings = new JFrame();
		frmSettings.setTitle("Settings");
		frmSettings.setBounds(100, 100, 320, 214);
		frmSettings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSettings.getContentPane().setLayout(null);
		
		JLabel lblPathOfLog = new JLabel("Path of Log file");
		lblPathOfLog.setBounds(21, 23, 159, 20);
		frmSettings.getContentPane().add(lblPathOfLog);
		
		txtpath = new JTextField();
		txtpath.setBounds(21, 44, 181, 20);
		frmSettings.getContentPane().add(txtpath);
		txtpath.setColumns(10);
		txtpath.setText(settings[0]);
		
		JButton btnOk = new JButton("Check");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File(txtpath.toString());
				try {
					Scanner x = new Scanner(file);
					infoBox("Config.txt is available","Check");
					x.close();
				} catch (FileNotFoundException e) {
					infoBox("Config.txt is not found","Check");
				}
				
			}
		});
		btnOk.setBounds(218, 44, 72, 20);
		frmSettings.getContentPane().add(btnOk);
		
		JLabel lblUnblockintervel = new JLabel("Unblock Interval");
		lblUnblockintervel.setBounds(21, 78, 134, 14);
		frmSettings.getContentPane().add(lblUnblockintervel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 75, 45, 20);
		frmSettings.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(settings[1]);
		
		JLabel lblRefreshInterval = new JLabel("Refresh Interval");
		lblRefreshInterval.setBounds(21, 107, 104, 14);
		frmSettings.getContentPane().add(lblRefreshInterval);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 104, 45, 20);
		frmSettings.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(settings[2]);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				write_ip wr = new write_ip();
				
				wr.fileClean("Config.txt");
				wr.write_i(txtpath.getText(), "Config.txt");
				wr.write_i(textField_1.getText(), "Config.txt");
				wr.write_i(textField_2.getText(), "Config.txt");
				frmSettings.dispose();
			}
		});
		btnSave.setBounds(55, 145, 72, 20);
		frmSettings.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmSettings.dispose();
				
			}
		});
		btnCancel.setBounds(172, 145, 79, 20);
		frmSettings.getContentPane().add(btnCancel);
		
		
		
		
	}
}
