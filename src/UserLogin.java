import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private final JTextField namefield = new JTextField();
	private JPasswordField passfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		namefield.setBounds(198, 49, 116, 22);
		contentPane.add(namefield);
		namefield.setColumns(10);
		
		JLabel lbname = new JLabel("UserId");
		lbname.setBounds(76, 55, 68, 16);
		contentPane.add(lbname);
		
		JLabel lbpass = new JLabel("Password");
		lbpass.setBounds(61, 144, 68, 16);
		contentPane.add(lbpass);
		
		passfield = new JPasswordField();
		passfield.setBounds(198, 141, 116, 22);
		contentPane.add(passfield);
		
		JButton btnok = new JButton("Verify");
		btnok.setBounds(166, 202, 97, 25);
		contentPane.add(btnok);
		
		btnok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DB_conn db=new DB_conn();
            	try {
            	Connection con=db.getConnection();
        	
        		
        		
        		String username=null;
        		
        		
        		
         username=namefield.getText();
       
        	  String queryString =" select count(*) as \"exists\" from user_entry where user_name=? and user_pass=?";
              //set this values using PreparedStatement
           PreparedStatement   ps1 = con.prepareStatement(queryString);
              ps1.setString(1,username);
              ps1.setString(2,String.valueOf((passfield.getPassword())));
             ResultSet results = ps1.executeQuery(); 

              if (results.next()) {
                     int i = results.getInt("exists");
                if(i==1)

             {
                	Main.main(new String[] {});
                	//JOptionPane.showMessageDialog(null,"pass");
                	dispose();
             }
                else
                	JOptionPane.showMessageDialog(null,"Fail");
        	
        	
              }

            }catch(Exception e1) {
            	System.out.println(e1);
            }
            }
       
		 });
	}
}