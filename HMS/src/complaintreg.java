import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class complaintreg extends JFrame {

	private JPanel contentPane;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	
	/*
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					complaintreg frame = new complaintreg(String name, int messdue);
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
	 * @param frame 
	 */
	
		
	public complaintreg(String name, int messdue) {
		
		setTitle("COMPLAINT REGISTER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Object[][] data= {};
		String[] columnNames = {"Comp NO","name",  "Complaint", "status"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
		//contentPane.add(table);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(47, 71, 518, 464);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student stu= new Student(name, messdue);
				dispose();
				stu.setVisible(true);
				
				
			}
		});
		ImageIcon imc= new ImageIcon("/Users/Anusha/Desktop/backicon");
		Image im= imc.getImage();
		Image resizedImage = im.getScaledInstance(38, 24,  java.awt.Image.SCALE_SMOOTH);
		
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		
		btnNewButton.setBounds(16, 6, 50, 40);
		contentPane.add(btnNewButton);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			Statement st1= conn.createStatement();

			String s1= "select hall from student where name= '"+name+"'";
			ResultSet rs1=st1.executeQuery(s1);
			rs1.next();
			String SQL = "select * from complaintreg where hall = '"+rs1.getString("hall")+"'";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getInt("complaintno"),rs.getString("name"), rs.getString("complaint"), rs.getString("Status")});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		
	}
}
