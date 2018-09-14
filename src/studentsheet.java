import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class studentsheet extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentsheet frame = new studentsheet();
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
	public studentsheet() 
	{
		setTitle("STUDENT DATABASE");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		Object[][] data= {};
		String[] columnNames = {"RollNO","name","username", "password","Father name","hall", "roomno"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames); 
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		//model.addRow(new Object[] {"1","ram","yes","yo"});
		table.setBounds(30,40,389,215);   
		//contentPane.add(table);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(40, 254, 503, 276);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Admin ad= new Admin();
				dispose();
				ad.setVisible(true);
			}
		});
		
		
		btnNewButton.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/back.jpg"));
		
		btnNewButton.setBounds(18, 10, 50, 40);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(180, 10, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 40, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 70, 130, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(180, 100, 130, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(180, 130, 130, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblFatherName = new JLabel("Father name:");
		lblFatherName.setBounds(68, 140, 87, 16);
		contentPane.add(lblFatherName);
		
		JLabel lblHall = new JLabel("Hall:");
		lblHall.setBounds(78, 170, 61, 16);
		contentPane.add(lblHall);
		
		textField_5 = new JTextField();
		textField_5.setBounds(180, 160, 130, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblRoomno = new JLabel("Roomno:");
		lblRoomno.setBounds(78, 195, 61, 16);
		contentPane.add(lblRoomno);
		
		textField_6 = new JTextField();
		textField_6.setBounds(180, 190, 130, 26);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[] {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(), textField_4.getText(),textField_5.getText(),textField_6.getText()});
				try
				{
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
					
					String SQL = "INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement st= conn.prepareStatement(SQL);
					st.setString(1,textField.getText());
					st.setString(2 ,textField_1.getText() );
					st.setInt(3, 0);
					
					st.setString(4 ,textField_2.getText() );
					st.setString(5 ,textField_3.getText() );
					st.setString(6 ,null );
					st.setString(7 ,textField_4.getText() );
					st.setString(8 ,textField_5.getText() );
					st.setString(9 ,textField_6.getText() );
					
					st.executeUpdate();
					
			
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(391, 75, 117, 29);
		contentPane.add(btnAdd);
		
		JLabel lblRollNo = new JLabel("Roll No:");
		lblRollNo.setBounds(107, 15, 61, 16);
		contentPane.add(lblRollNo);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(94, 45, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(91, 75, 77, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(67, 105, 76, 16);
		contentPane.add(lblPassword);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setToolTipText("Select a row");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= table.getSelectedRow();
				String name= model.getValueAt(i, 1).toString();
				try
				{
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
				    String sql1 = "DELETE FROM student where name = ?";
				 
					PreparedStatement st= conn.prepareStatement(sql1);
					
					st.setString(1 , name );
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
		btnDelete.setBounds(391, 130, 117, 29);
		contentPane.add(btnDelete);
		
		
		
		
		try
		{
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
			Statement st= conn.createStatement();
			String SQL = "select * from student";
			ResultSet rs=st.executeQuery(SQL);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString("rollno"),rs.getString("name"), rs.getString("username"), rs.getString("password"),rs.getString("father name"), rs.getString("hall"),rs.getString("roomno")});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
	}
}
