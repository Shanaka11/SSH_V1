import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class runner {

	private JFrame frame;
	private static int i = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		i = run_script_start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					runner window = new runner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public runner() {
		initialize();
	}
	
	/* Method to run the script*/
	
	public static int run_script_start(){
		Process p;
		try {
			p = Runtime.getRuntime().exec("bash mytest start");
			BufferedReader stdInp = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			//BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String s;
			
			s = stdInp.readLine();
			return Integer.parseInt(s);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return 12;
		}
		
	}
	
	public static int run_script_stop(){
		Process p;
		try {
			p = Runtime.getRuntime().exec("bash mytest stop");
			BufferedReader stdInp = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			//BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String s;
			
			s = stdInp.readLine();
			return Integer.parseInt(s);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return 12;
		}
		
	}
	//get the map
	public void map(){
		map_all.main();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 178, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel Head = new JLabel("STATUS: ACTIVE");
		Head.setHorizontalAlignment(SwingConstants.CENTER);
		Head.setBounds(29, 11, 111, 14);
		frame.getContentPane().add(Head);
		
		
		JButton btnActivate = new JButton("Activate");
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( i == 0 ) {
					i = run_script_start();
					Head.setText("STATUS: ACTIVE");
					
				}
			}
		});
		btnActivate.setBounds(29, 39, 111, 35);
		frame.getContentPane().add(btnActivate);
		
		JButton btnDeactivate = new JButton("Deactivate");
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( i == 1){
					i = run_script_stop();
					Head.setText("STATUS: INACTIVE");
				}
			}
		});
		btnDeactivate.setBounds(29, 85, 111, 35);
		frame.getContentPane().add(btnDeactivate);
		
		JButton btnMap = new JButton("Map Blocked Ips");
		btnMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				map();
			}
		});
		btnMap.setBounds(29, 131, 111, 35);
		frame.getContentPane().add(btnMap);
		
		JButton btnNewButton = new JButton("Settings");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			
				settings.main();
			}
		});
		btnNewButton.setBounds(29, 177, 111, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(29, 223, 111, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
	}
}
