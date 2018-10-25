import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class staffregister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffregister frame = new staffregister();
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
	public staffregister(String hall) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 156, 512, 270);
		contentPane.add(scrollPane);
		
		Object[][] data= {};
		String[] columnNames = {"staffname","leaveno","leavedates"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);		
		table.setBounds(30,40,389,215);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(258, 53, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(258, 92, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLeaveno = new JLabel("Leaveno:");
		lblLeaveno.setBounds(138, 58, 61, 16);
		contentPane.add(lblLeaveno);
		
		JLabel lblLeavedates = new JLabel("Leavedates:");
		lblLeavedates.setBounds(138, 97, 92, 16);
		contentPane.add(lblLeavedates);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t1= textField.getText();
				String t2= textField_1.getText();
				
				int i= table.getSelectedRow();
				String name= model.getValueAt(i, 0).toString();
				if(model.getValueAt(i,2)!=null)
					t2= t2+ model.getValueAt(i,2);
				if(i>=0)
				{
					model.setValueAt( t1, i, 1);
					model.setValueAt( t2, i, 2);
					
					try
					{
						Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					    String sql1 = "update staffreg set leaveno = ?,leavedates = ?  where staffname = ? and hall =?";
					 
						PreparedStatement st= conn.prepareStatement(sql1);
						st.setString(1, t1);	
						st.setString(2, t2);	
						st.setString(3, name);
						st.setString(4, hall);	
						
						st.executeUpdate();
						System.out.println("Leave updated");					
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						System.out.println("error in staff attedance update");
					}
				}
				
			}
		});
		btnUpdate.setBounds(442, 63, 117, 29);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HallClerk hc = new HallClerk(hall);
				dispose();
				hc.setVisible(true);
			}
		});
		btnBack.setBounds(20, 19, 50, 40);
		contentPane.add(btnBack);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from staffreg where hall ='"+hall+"'";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString("staffname"),rs.getString("leaveno"), rs.getString("leavedates")});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("error in staffregister");
			
		}		
	}
}
