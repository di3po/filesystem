import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import project.InsertUpdateDelete;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class RegisterPage extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField groupTextField;
	private JFileChooser fileChooser;
	private JLabel label;
	private JTextField ImageTextField;
	private JTextField priceTextField;
	//private JTextField receiptTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("x");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close app?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					System.exit(0);
				}
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(616, 0, 41, 33);
		contentPane.add(btnExit);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setForeground(Color.CYAN);
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 30));
		lblRegister.setBounds(262, 111, 162, 82);
		contentPane.add(lblRegister);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStudent.setForeground(Color.CYAN);
		lblStudent.setBackground(Color.WHITE);
		lblStudent.setBounds(55, 207, 139, 15);
		contentPane.add(lblStudent);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGroup.setForeground(Color.CYAN);
		lblGroup.setBackground(Color.WHITE);
		lblGroup.setBounds(55, 234, 139, 15);
		contentPane.add(lblGroup);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice.setForeground(Color.CYAN);
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setBounds(55, 261, 139, 15);
		contentPane.add(lblPrice);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(209, 205, 277, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		groupTextField = new JTextField();
		groupTextField.setBounds(209, 232, 277, 19);
		contentPane.add(groupTextField);
		groupTextField.setColumns(10);
		
		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(209, 259, 277, 19);
		contentPane.add(priceTextField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTextField.getText();
				String group = groupTextField.getText();
				String price = priceTextField.getText();
				String image = ImageTextField.getText();
				image = image.replace("\\", "\\\\");
				
				
				if (name.equals("") || group.equals("") || price.equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are required.");
				}
				else {
					String Query;
					Query = "insert into my_students values('"+name+"', '"+group+"', '"+price+"', '"+image+"', 'false')";
					InsertUpdateDelete.setData(Query, "Registered successfully!");
					setVisible(false);
					new RegisterPage().setVisible(true);
				}
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(498, 474, 108, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblReceipt_1 = new JLabel("Receipt");
		lblReceipt_1.setForeground(Color.CYAN);
		lblReceipt_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReceipt_1.setBackground(Color.WHITE);
		lblReceipt_1.setBounds(55, 288, 139, 15);
		contentPane.add(lblReceipt_1);
		
		JLabel label = new JLabel("New label");
		label.setBounds(209, 313, 234, 186);
		contentPane.add(label);
		
		ImageTextField = new JTextField();
		ImageTextField.setBounds(209, 282, 277, 19);
		contentPane.add(ImageTextField);
		ImageTextField.setColumns(10);
		
		JButton btnOpen_1 = new JButton("Attach");
		btnOpen_1.setForeground(Color.WHITE);
		btnOpen_1.setBackground(Color.DARK_GRAY);
		btnOpen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();	
				ImageTextField.setText(path);
				Image getAbsolutePath = null;
				ImageIcon icon = new ImageIcon(path);
				Image image = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				label.setIcon(icon);	
				
	                /*final JFrame f = new JFrame("View Receipt");
	                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	 
	                /*final JFileChooser fileChooser = new JFileChooser();
	                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));              
	                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
	                fileChooser.addChoosableFileFilter(filter);*/
	 
	                //JPanel gui = new JPanel(new BorderLayout());
	 
	                /*final JEditorPane document = new JEditorPane();
	                gui.add(new JScrollPane(document), BorderLayout.CENTER);*/
	 
	                /*int result = fileChooser.showSaveDialog(null);
	                if (result==JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                String path = selectedFile.getAbsolutePath();	                         
								//label.setIcon(ResizeImage(path));
								
	                            /*try {
	                                document.setPage(file.toURI().toURL());
	                            } catch(Exception e) {
	                                e.printStackTrace();
	                            }*/
	                //}
	                        
	                /*else if(result == JFileChooser.CANCEL_OPTION) {
	                      	System.out.println("No Data");
	                }*/
	 
	                /*f.setContentPane(gui);
	                f.pack();
	                f.setSize(900,700);
	                f.setLocationByPlatform(true);
	 
	                f.setVisible(true);
	                f.setResizable(false);*/
	            }
			
		});
		btnOpen_1.setBounds(498, 284, 108, 23);
		contentPane.add(btnOpen_1);
		
		JLabel lblNewLabel = new JLabel("background");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("/home/user2/eclipse-workspace/Students/src/images/1.jpg"));
		lblNewLabel.setBounds(0, 0, 657, 511);
		contentPane.add(lblNewLabel);
		
	}
}
