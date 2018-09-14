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

public class expensepay extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	
	public JTable getTable()
	{
		return table;
	}

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
	public expensepay(String hall) {
		setTitle("HALL EXPENSES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		Object[][] data= {};
		String[] columnNames = {"SNO","reason",  "amount","status"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
		
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(26, 81, 545, 407);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		
					
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessManager mm= new MessManager(hall);
				dispose();
				mm.setVisible(true);
			}
		});
				
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		
		btnNewButton.setBounds(16, 6, 50, 40);
		contentPane.add(btnNewButton);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				//System.out.println(model.getValueAt(i, 0).toString()+" "+ model.getValueAt(i, 1).toString()+" "+model.getValueAt(i, 2).toString()+" "+model.getValueAt(i, 3).toString());
				
				int sno= Integer.parseInt(model.getValueAt(i, 0).toString());
				System.out.println(i+" "+sno);
				
				
					model.setValueAt("Paid", i, 3);
					try
					{
						Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					    String sql1 = "update expenses set status = ? where sno = '"+sno+"' and hall ='"+hall+"'";
					 
						PreparedStatement st= conn.prepareStatement(sql1);
						st.setString(1, "Paid");						
						st.executeUpdate();
						System.out.println("Status updated");
						
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}				
			
		});
		btnPay.setBounds(219, 22, 117, 29);
		contentPane.add(btnPay);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from expenses";
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