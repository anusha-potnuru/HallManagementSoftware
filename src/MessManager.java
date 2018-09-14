
import java.sql.*;
import javax.swing.JTextField;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class MessManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					MessManager frame = new MessManager();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public MessManager(String hall) {
		setTitle("Mess Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnInputMessDues = new JButton("Input mess dues");
		btnInputMessDues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextField dueField = new JTextField(10);
			     JTextField stField = new JTextField(20);

			      JPanel myPanel = new JPanel();
			      myPanel.add(new JLabel("Mess due of : "));
			      myPanel.add(dueField);
			      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			      myPanel.add(new JLabel("For student: "));
			      myPanel.add(stField);

			      int result = JOptionPane.showConfirmDialog(null, myPanel, 
			               "Update the due for student", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) 
			      {
			    	  	try {
			    	  
						Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
						String SQL = "update student set messdue = ? where name = '"+ stField.getText() +"'and hall='"+hall+"'";
						
						//System.out.println( rs.getString(0));					
						PreparedStatement st= conn.prepareStatement(SQL);
						st.setString(1, dueField.getText());
						
						
						int x= st.executeUpdate();
						if(x==1)
							System.out.println("Mess due updated");
						else
						{
							JPanel panel = new JPanel();
						    JOptionPane.showMessageDialog(panel, "No such student", "Error", JOptionPane.ERROR_MESSAGE);
							System.out.println("No such student");
						}

						
			    	  	}
			    	  	catch(Exception ex)
			    	  	{
			    	  		ex.printStackTrace();
			    	  	}
			      }
		
			}
		});
		
		
		btnInputMessDues.setBounds(117, 248, 185, 29);
		contentPane.add(btnInputMessDues);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginform lg = new Loginform();
				dispose();
				lg.frmHallManagementPortal.setVisible(true);
			}
		});
		btnLogout.setBounds(305, 38, 105, 37);
		contentPane.add(btnLogout);
		
		JButton btnPayExpenses = new JButton("Pay expenses");
		btnPayExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expensepay exp= new expensepay(hall);
				dispose();
				exp.setVisible(true);
												
				
			}
		});
		btnPayExpenses.setBounds(117, 289, 185, 29);
		contentPane.add(btnPayExpenses);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/manager.jpeg"));
		lblNewLabel.setBounds(31, 22, 100, 100);
		contentPane.add(lblNewLabel);
	}
}
