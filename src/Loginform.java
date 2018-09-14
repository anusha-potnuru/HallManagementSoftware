import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Loginform {

	public JFrame frmHallManagementPortal;
	private JTextField usernametext;
	private JTextField passwordtext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginform window = new Loginform();
					window.frmHallManagementPortal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Loginform() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHallManagementPortal = new JFrame();
		frmHallManagementPortal.setBackground(Color.LIGHT_GRAY);
		frmHallManagementPortal.setTitle("HALL MANAGEMENT PORTAL");
		frmHallManagementPortal.getContentPane().setBackground(new Color(192, 192, 192));
		frmHallManagementPortal.setBounds(0, 0, 600, 600);
		frmHallManagementPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHallManagementPortal.getContentPane().setLayout(null);
		frmHallManagementPortal.setLocationRelativeTo(null);
		
		usernametext = new JTextField();
		usernametext.setBounds(326, 181, 130, 26);
		frmHallManagementPortal.getContentPane().add(usernametext);
		usernametext.setColumns(10);
		
		passwordtext = new JTextField();
		passwordtext.setBounds(326, 238, 130, 26);
		frmHallManagementPortal.getContentPane().add(passwordtext);
		passwordtext.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setEnabled(false);
		lblUsername.setBounds(175, 186, 101, 16);
		frmHallManagementPortal.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setEnabled(false);
		lblPassword.setBounds(175, 243, 101, 16);
		frmHallManagementPortal.getContentPane().add(lblPassword);
		
		JComboBox comboBox_1 = new JComboBox();
		//comboBox_1.setBackground(UIManager.getColor("Button.highlight"));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"student", "admin", "warden ", "messmanager", "hmcchairman", "hallclerk"}));
		//comboBox_1.setSelectedIndex(0);
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(256, 107, 130, 26);
		frmHallManagementPortal.getContentPane().add(comboBox_1);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String x = String.valueOf(comboBox_1.getSelectedItem());
				// open different login screens for different logins
				//String query = "Select username, password from "
				
					try
					{	
						
						
						if(x.equals("student"))
						{

							String username= usernametext.getText();
							String pass= passwordtext.getText();
							//System.out.println(name+" "+pass);
							Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
							Statement st= conn.createStatement();
							String SQL = "select username, password, name, messdue from student where username = '"+username+"'and password='"+pass+"'";
							ResultSet rs=st.executeQuery(SQL);
							//System.out.println( rs.getString(0));					
							if(rs.next())  
							{
								System.out.println("Successful Student Login!\n----"+ rs.getString("name"));
								Student stu= new Student(rs.getString("name") , rs.getInt("messdue"));
						            
						        stu.setVisible(true);
						        frmHallManagementPortal.dispose();
								
						        
							}
							else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");						
								
							}
						}
						
						if(x.equals("warden "))
						{
							
							
							String username = usernametext.getText();
						    String pass = passwordtext.getText();
						    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
						    Statement st= conn.createStatement();
							String SQL = "select username, password, hall from nonadmindb where username = '"+username+"'and password='"+pass+"'";
							ResultSet rs=st.executeQuery(SQL);
						    
						    if (rs.next()) 
						    {
						    	
					            System.out.println("Successful Warden Login!\n----");
					            Warden wr= new Warden(rs.getString("hall"));
					            wr.setVisible(true);
					            frmHallManagementPortal.dispose();
						    }
						    else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");												
							}
						}
						
						
						
						if(x.equals("hallclerk"))
						{
							String name = usernametext.getText();
						    String pass =passwordtext.getText();
						    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
						    Statement st= conn.createStatement();
							String SQL = "select username, password, hall from nonadmindb where username = '"+name+"'and password='"+pass+"'";
							ResultSet rs=st.executeQuery(SQL);
						    
						    if (rs.next()) 
						    {
						    		HallClerk hk= new HallClerk(rs.getString("hall"));
						    		hk.setVisible(true);
						    		frmHallManagementPortal.dispose();
					            System.out.println("Successful hall clerk Login!\n----");
						    }
						    else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");												
							}			           				    
							
						}
						if(x.equals("messmanager"))
						{
							String name = usernametext.getText();
						    String pass =passwordtext.getText();
						    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/flats?useSSL=false","root","sujana");
						    Statement st= conn.createStatement();
							String SQL = "select username, password, hall from nonadmindb where username = '"+name+"'and password='"+pass+"'";
							ResultSet rs=st.executeQuery(SQL);
							
						    if (rs.next()) 
						    {
						    		MessManager mm= new MessManager(rs.getString("hall"));
						    		mm.setVisible(true);
						    		frmHallManagementPortal.dispose();
					            System.out.println("Successful Messmanager Login!\n----");
						    }
						    else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");												
							}			           				    
							
						}
						
						//UNIQUE
						if(x.equals("admin"))
						{
							String name = usernametext.getText();
						    String pass =passwordtext.getText();
						    if (name.equals("admin") && pass.equals("admin")) 
						    {
					            System.out.println("Successful Admin Login!\n----");
					            Admin adm= new Admin();
					            adm.setVisible(true);
					            frmHallManagementPortal.dispose();
						    }
						    else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");												
							}			           				    
							
						}
						if(x.equals("hmcchairman"))
						{
							String name = usernametext.getText();
						    String pass =passwordtext.getText();
						    if (name.equals("hmcchairman") && pass.equals("hmcchairman")) 
						    {
						    		HMCChairman hmcc= new HMCChairman();
						    		hmcc.setVisible(true);
						    		frmHallManagementPortal.dispose();
					            System.out.println("Successful hmcchairman Login!\n----");
						    }
						    else
							{
								JPanel panel = new JPanel();
							    JOptionPane.showMessageDialog(panel, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
								System.out.println("Not Successful Login!\n----");												
							}			           				    
							
						}						
					}
					catch(Exception ex)
					{
						System.out.println("Error");
					}				
			}
		});
		
		btnLogin.setBounds(238, 322, 117, 29);
		frmHallManagementPortal.getContentPane().add(btnLogin);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/Anusha/eclipse-workspace/HallManagementSoftware/pics/template.jpg"));
		lblNewLabel.setBounds(0, 0, 600, 600);
		frmHallManagementPortal.getContentPane().add(lblNewLabel);
		
		
	}
}
