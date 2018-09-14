import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Admin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		
		setTitle("ADMINSTRATOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnOpenStudentDatabase = new JButton("ADD/ REMOVE A STUDENT");
		btnOpenStudentDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentsheet st= new studentsheet();
				dispose();
				st.setVisible(true);
				contentPane.setVisible(false);
			}
		});
		btnOpenStudentDatabase.setBounds(69, 259, 231, 29);
		contentPane.add(btnOpenStudentDatabase);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginform lg= new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBounds(495, 40, 78, 42);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/admin.jpg"));
		lblNewLabel.setBounds(76, 66, 100, 100);
		contentPane.add(lblNewLabel);
	}
}
