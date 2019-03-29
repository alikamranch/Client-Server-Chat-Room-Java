package assignment_4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs=null;
	static String usernameText="";
	private JPasswordField password;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginPage = new JLabel("LOGIN PAGE");
		lblLoginPage.setBounds(199, 22, 113, 14);
		lblLoginPage.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblLoginPage);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(132, 87, 91, 14);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(132, 140, 66, 14);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(264, 86, 133, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(68, 228, 89, 23);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username.setText("");
				password.setText("");
			}
		});
		contentPane.add(btnReset);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(223, 228, 89, 23);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from Login where userName=? and Password=?";
				con = ConnectionManager.getConnection();
				try {
					 stmt = con.prepareStatement(sql);
					 stmt.setString(1, username.getText());
					 stmt.setString(2, password.getText());
					 usernameText=username.getText();
					 rs=stmt.executeQuery();
					  if(rs.next())
			            {
			                JOptionPane.showMessageDialog(null, "Login Successfull!");
			                ChatRoom field=new ChatRoom();
			                field.setVisible(true);
			                setVisible(false);
			            }
			            else {
				            JOptionPane.showMessageDialog(null, "Username or Password not correct!", "ERROR", JOptionPane.ERROR_MESSAGE);
				            username.setText("");
				            password.setText("");
			            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(386, 228, 89, 23);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp field=new SignUp();
				field.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(btnSignUp);
		
		password = new JPasswordField();
		password.setBounds(264, 139, 133, 20);
		contentPane.add(password);
	}
}
