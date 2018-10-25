import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;

public class Student extends JFrame {
	
	
	

	private JPanel contentPane;
	private JTextField txtWelcome;
	private JTextField txtDue;

	/*
	 * Launch the application.
	 
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student(name, messdue);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	

	public Student(String name, int messdue) {
		
		
		
		setTitle(name+"'s account");
		setBackground(new Color(238, 238, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setText(String.valueOf(messdue));
		txtDue.setBounds(316, 225, 130, 26);
		contentPane.add(txtDue);
		txtDue.setColumns(10);
			
		JButton btnViewComplaintRegister = new JButton("View complaint register");
		btnViewComplaintRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				complaintreg cm= new complaintreg(name, messdue);
				dispose();
				cm.setVisible(true);
				
				
			}
		});
		btnViewComplaintRegister.setBounds(63, 326, 201, 29);
		contentPane.add(btnViewComplaintRegister);
		
		JButton btnNewButton = new JButton("File new complaint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame("InputDialog Example #1");
			    // prompt the user to enter their name
			    String tt = JOptionPane.showInputDialog(frame, "What's your complaint?");
			    System.out.println(tt);
			    if(tt!=null && tt.length()>0)
			    {
				    try
				    {
				    
					    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					    
					    String sql1= "Select hall from student where name ='"+name+"'";
					    String sql2 = "INSERT INTO complaintreg(name,complaint, status,hall) VALUES(?,?,?,?)";
					    PreparedStatement st1= conn.prepareStatement(sql1);
						PreparedStatement st2= conn.prepareStatement(sql2);
						ResultSet rs=st1.executeQuery(sql1);
						rs.next();
						System.out.println(rs.getString("hall"));
						
						st2.setString(1 , name );
						st2.setString(2 , tt );
						
						st2.setString(3, "in process");
						st2.setString(4, rs.getString("hall"));
						
						
						st2.executeUpdate();
						System.out.println("Complaint filed");
						
				    }
				    catch(Exception ex)
				    {
				    		System.out.println("Error in complaint");
				    		ex.printStackTrace();
				    }
			    }
    			    else if(tt!=null && tt.length()==0)
			    {
			    		JPanel panel = new JPanel();
				    JOptionPane.showMessageDialog(panel, "Invalid complaint", "Error", JOptionPane.ERROR_MESSAGE);
					
			    }
			    
			    
			}
		});
		btnNewButton.setBounds(59, 367, 205, 29);
		contentPane.add(btnNewButton);
		
		txtWelcome = new JTextField();
		txtWelcome.setEditable(false);
		txtWelcome.setText("Welcome "+name);
		txtWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		txtWelcome.setFont(new Font("Euphemia UCAS", Font.ITALIC, 15));
		txtWelcome.setBounds(109, 60, 236, 26);
		contentPane.add(txtWelcome);
		txtWelcome.setColumns(10);
		
		JButton logoutbutton = new JButton("Logout");
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				Loginform lg = new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
				//.setVisible(false);
				
			}
		});
		logoutbutton.setBounds(518, 17, 62, 41);
		contentPane.add(logoutbutton);
		
		
		
		JLabel lblNewLabel = new JLabel("Mess dues:");
		lblNewLabel.setBounds(134, 224, 138, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/images.jpeg"));
		lblNewLabel_1.setBounds(394, 44, 100, 100);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
