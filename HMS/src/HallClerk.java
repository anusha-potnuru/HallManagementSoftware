import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HallClerk extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HallClerk frame = new HallClerk();
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
	public HallClerk(String hall) {
		setTitle("Hall Clerk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Attendance register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				staffregister str= new staffregister(hall);
				dispose();
				str.setVisible(true);
			}
		});
		
		btnNewButton.setBounds(171, 286, 220, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Print cheques");
		btnNewButton_1.setBounds(171, 340, 220, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnInputExpenses = new JButton("Input expenses");
		btnInputExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				expensehclrk eg= new expensehclrk(hall);
				dispose();
				eg.setVisible(true);
				
			}
		});
		btnInputExpenses.setBounds(171, 230, 220, 29);
		contentPane.add(btnInputExpenses);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginform lg = new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
			}
		});
		btnLogout.setBounds(470, 39, 101, 39);
		contentPane.add(btnLogout);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/hallclerk.jpg"));
		label.setBounds(53, 64, 100, 100);
		contentPane.add(label);
	}

}
