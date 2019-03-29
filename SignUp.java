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
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField cpassword;
	private Connection con = null;
	private PreparedStatement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSignUp.setBounds(217, 21, 95, 27);
		contentPane.add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(135, 104, 73, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(135, 163, 83, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(135, 222, 117, 14);
		contentPane.add(lblConfirmPassword);
		
		username = new JTextField();
		username.setBounds(285, 103, 137, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(284, 157, 138, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		cpassword = new JTextField();
		cpassword.setBounds(285, 221, 137, 20);
		contentPane.add(cpassword);
		cpassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="Insert into Login(userName, Password)values(?, ?)";
				con = ConnectionManager.getConnection();
				try {
					 stmt = con.prepareStatement(sql);
					 if(password.getText().equals(cpassword.getText()))
			            {
			            	stmt.setString(1, username.getText());
				            stmt.setString(2, password.getText());
				            stmt.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Signed up successfully!");
				            int choose=0;
				            if(choose==JOptionPane.OK_OPTION)
				            {
				            	Login field=new Login();
				            	field.setVisible(true);
				            	setVisible(false);
				            }
			            }
			            else
			            {
			            	JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
			            	password.setText("");
							cpassword.setText("");
			            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(119, 303, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
				cpassword.setText("");
			}
		});
		btnNewButton_1.setBounds(343, 303, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
