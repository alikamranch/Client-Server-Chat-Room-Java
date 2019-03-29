package assignment_4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatRoom extends JFrame {
	private JPanel contentPane;
	String s;
	private MyClient obj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
					ChatRoom frame = new ChatRoom();
					frame.setVisible(true);
				
			}	
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ChatRoom(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 71, 581, 316);
		contentPane.add(scrollPane);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setEditable(false);
		
		JLabel lblChatRoom = new JLabel("CHAT ROOM");
		lblChatRoom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChatRoom.setBounds(240, 11, 129, 22);
		contentPane.add(lblChatRoom);
		
		JLabel lblLoggedOnAs = new JLabel("Logged on as:");
		lblLoggedOnAs.setBounds(30, 46, 103, 14);
		contentPane.add(lblLoggedOnAs);
		
		JLabel lblNewLabel = new JLabel(Login.usernameText);
		lblNewLabel.setBounds(123, 46, 89, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 398, 451, 110);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Chat will update on key press");
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					obj=new MyClient();
					obj.sendMessage("*Chat Updating*");
					s=obj.receiveMessage();
				} catch (Exception e) {
					// TODO: handle exception
				}
				textArea_1.setText(s);
			}
		});
		scrollPane_1.setViewportView(textArea);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					obj=new MyClient();
					obj.sendMessage(Login.usernameText+": "+textArea.getText());
					s=obj.receiveMessage();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textArea_1.setText(s);
				textArea.setText("");
			}
		});
		btnNewButton.setBounds(491, 398, 121, 110);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Select File");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileBrowser field=new FileBrowser();
				field.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(491, 519, 121, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login field=new Login();
				field.setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(31, 585, 89, 23);
		contentPane.add(btnExit);
	}
}
