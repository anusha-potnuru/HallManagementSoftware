import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HMCChairman extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HMCChairman frame = new HMCChairman();
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
	public HMCChairman() {
		setTitle("HMC CHAIRMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnAllotFunds = new JButton("Allot funds");
		btnAllotFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("InputDialog Example #1");
			    // prompt the user to enter their name
			    String tt = JOptionPane.showInputDialog(frame, "What's the fund amount");
			    System.out.println(tt);
				
			}
		});
		btnAllotFunds.setBounds(172, 221, 207, 29);
		contentPane.add(btnAllotFunds);
		
		JButton btnNewButton = new JButton("View funds management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame("InputDialog Example #1");
			    
			    String hall = JOptionPane.showInputDialog(frame, "Which hall?");
			    if(hall!=null && hall.length()>0)
			    {
					expense exp= new expense(hall);
					dispose();
					setVisible(false);
					exp.setVisible(true);
			    
				
					JButton btnNewButton = new JButton("Back");
					btnNewButton.setBounds(16, 6, 50, 40);
					btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
						
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							exp.dispose();
							setVisible(true);
							
						}
					});
					exp.getContentPane().add(btnNewButton);
					
					exp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					exp.addWindowListener(new java.awt.event.WindowAdapter() {
				        @Override
				        public void windowClosing(java.awt.event.WindowEvent event) {
				           setVisible(true);
				        }
				    });
			    }
			}
		});
		btnNewButton.setBounds(172, 270, 207, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add/ delete a staff member");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				hallstaff hs = new hallstaff();
				dispose();
				hs.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(166, 324, 213, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Loginform lg = new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
			}
		});
		btnLogout.setBounds(449, 39, 107, 39);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/chairman.png"));
		lblNewLabel.setBounds(48, 40, 100, 100);
		contentPane.add(lblNewLabel);
	}

}
