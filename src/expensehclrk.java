import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class expensehclrk extends JFrame {

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
					expense frame = new expense();
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
	public expensehclrk(String hall) {
		setTitle("HALL EXPENSES for "+hall);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		
		Object[][] data= {};
		String[] columnNames = {"SNO","reason",  "amount","status"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
		
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 162, 536, 359);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
					
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(16, 6, 50, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HallClerk wr= new HallClerk(hall);
				dispose();
				wr.setVisible(true);	
			}
		});
		
		
		
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		contentPane.add(btnNewButton);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rsn= textField.getText();
				int amount= Integer.parseInt(textField_1.getText());
				int id = Integer.parseInt(textField_2.getText());
				
				
				try
				{
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
				    String sql1 = "INSERT INTO expenses VALUES(?,?,?,?,?)";
				 
					PreparedStatement st= conn.prepareStatement(sql1);
					st.setInt(3, id);
					st.setString(1, rsn);
					st.setInt(2, amount);
					st.setString(4, "not paid");
					st.setString(5, hall);
					st.executeUpdate();
					model.addRow(new Object[] {id, rsn, amount, "not paid" });
					System.out.println("Status updated");
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(424, 66, 117, 29);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("Reason:");
		lblNewLabel.setBounds(81, 34, 61, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(200, 29, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(81, 71, 61, 16);
		contentPane.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 66, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(81, 114, 61, 16);
		contentPane.add(lblId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 109, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from expenses where hall = '"+hall+"'";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getInt("sno"),rs.getString("reason"), rs.getString("amount"),rs.getString("status")});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
	}
}
