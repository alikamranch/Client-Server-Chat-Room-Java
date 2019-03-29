package assignment_4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class FileBrowser extends JFrame {

	private JPanel contentPane;
	static File s=new File("E://abc.txt");
	MyClient obj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileBrowser frame = new FileBrowser();
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
	public FileBrowser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (JFileChooser.APPROVE_SELECTION.equals(evt.getActionCommand())) {
		        	s=fileChooser.getSelectedFile();
		        	try {
						obj=new MyClient();
						obj.sendFile(s);
						JOptionPane.showMessageDialog(null, obj.receiveMessage());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
		        } else if (JFileChooser.CANCEL_SELECTION.equals(evt.getActionCommand())) {
		            setVisible(false);
		        }
			}
		});
		contentPane.add(fileChooser, BorderLayout.CENTER);
	}

}
