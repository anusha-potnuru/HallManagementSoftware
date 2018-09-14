import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class complregwarden extends JFrame {

	private JPanel contentPane;
	private JTextField textstatus;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					complregwarden frame = new complregwarden();
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
	
	
	public complregwarden(String hall) {
		setTitle("COMPLAINT REGISTER for "+hall);
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
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int i= table.getSelectedRow();
				textstatus.setText(model.getValueAt(i, 3).toString());
				
			}
		});
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(26, 98, 541, 411);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Warden wr= new Warden(hall);
				dispose();
				wr.setVisible(true);	
			}
		});
		
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		
		btnNewButton.setBounds(16, 6, 50, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblStatusUpdate = new JLabel("Select Status:");
		lblStatusUpdate.setBounds(109, 37, 100, 16);
		contentPane.add(lblStatusUpdate);
		
		textstatus = new JTextField();
		textstatus.setBounds(223, 32, 155, 26);
		contentPane.add(textstatus);
		textstatus.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				String tt= textstatus.getText();
				String name= model.getValueAt(i, 1).toString();
				int no= Integer.parseInt(model.getValueAt(i, 0).toString());
				if(i>=0)
				{
					model.setValueAt(textstatus.getText(), i, 3);
					try
					{
						Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					    String sql1 = "update complaintreg set status = ? where name = ?, complaintno = ? and hall = ? ";
					 
						PreparedStatement st= conn.prepareStatement(sql1);
						st.setString(1, tt);	
						st.setString(2, name);	
						st.setInt(3, no);	
						st.setString(4 , hall);	
						st.executeUpdate();
						System.out.println("Status updated");
						
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					System.out.println("Update error");
				}
			}
		});
		btnNewButton_1.setBounds(457, 32, 89, 29);
		contentPane.add(btnNewButton_1);
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from complaintreg where hall='"+hall+"'";
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
