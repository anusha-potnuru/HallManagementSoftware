import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Warden extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warden frame = new Warden();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public Warden(String hall) {
		setTitle("Warden");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnComplaintRegister = new JButton("Complaint register");
		btnComplaintRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				complregwarden cm = new complregwarden(hall);
				cm.setVisible(true);
				
			}
		});
		btnComplaintRegister.setBounds(149, 213, 213, 29);
		contentPane.add(btnComplaintRegister);
		
		JButton btnNewButton = new JButton("Hall expenditure details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expense ex= new expense(hall);
				ex.setVisible(true);
				JButton btnNewButton = new JButton("Back");
				ImageIcon imc= new ImageIcon("/Users/Anusha/Desktop/backicon");
				Image im= imc.getImage();
				Image resizedImage = im.getScaledInstance(38, 24,  java.awt.Image.SCALE_SMOOTH);
				
				btnNewButton.setIcon(new ImageIcon(resizedImage));
				btnNewButton.setBounds(16, 6, 42, 29);
				
				
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ex.dispose();
						setVisible(true);
						
					}
				});
				ex.getContentPane().add(btnNewButton);
							
			}
			
		});
		btnNewButton.setBounds(149, 262, 223, 29);
		contentPane.add(btnNewButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Loginform lg = new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
			}
		});
		btnLogout.setBounds(331, 30, 81, 44);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/warden.png"));
		lblNewLabel.setBounds(46, 30, 100, 100);
		contentPane.add(lblNewLabel);
	}

}
