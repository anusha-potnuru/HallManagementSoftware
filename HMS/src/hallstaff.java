import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class hallstaff extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hallstaff frame = new hallstaff();
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
	public hallstaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		Object[][] data= {};
		String[] columnNames = {"Staffname","pay","hall"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
				
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(34, 169, 507, 354);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(253, 39, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(253, 77, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(253, 111, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblStaffname = new JLabel("Staffname:");
		lblStaffname.setBounds(144, 49, 77, 16);
		contentPane.add(lblStaffname);
		
		JLabel lblPay = new JLabel("Pay:");
		lblPay.setBounds(160, 82, 61, 16);
		contentPane.add(lblPay);
		
		JLabel lblHall = new JLabel("Hall:");
		lblHall.setBounds(160, 116, 61, 16);
		contentPane.add(lblHall);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				HMCChairman ad= new HMCChairman();
				dispose();
				ad.setVisible(true);
			}
		});
		ImageIcon imc= new ImageIcon("/Users/Anusha/Desktop/backicon");
		Image im= imc.getImage();
		Image resizedImage = im.getScaledInstance(38, 24,  java.awt.Image.SCALE_SMOOTH);
		
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		
		btnNewButton.setBounds(16, 20, 50, 40);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[] {textField.getText(),textField_1.getText(),textField_2.getText()});
				try
				{
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					
					String SQL = "INSERT INTO hallstaff VALUES(?,?,?)";
					PreparedStatement st= conn.prepareStatement(SQL);
					st.setString(1,textField.getText());
					st.setInt(2 ,Integer.parseInt(textField_1.getText()) );
					st.setString(3 ,textField_2.getText() );
					st.executeUpdate();			
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(435, 60, 117, 29);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				String name= model.getValueAt(i, 0).toString();
				try
				{
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
				    String sql1 = "DELETE FROM hallstaff where staffname = '"+name+"'";				 
					PreparedStatement st= conn.prepareStatement(sql1);
					
					
					st.executeUpdate();
					model.removeRow(i);
					System.out.println("deleted");
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(435, 98, 117, 29);
		contentPane.add(btnDelete);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from hallstaff";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString("staffname"),rs.getInt("pay"), rs.getString("hall")});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
			
		
		
		
	}

}
