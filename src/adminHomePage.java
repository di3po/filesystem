import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import project.*;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import java.awt.Component;

public class adminHomePage extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminHomePage frame = new adminHomePage();
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
	public adminHomePage() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select *from my_students");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
					}
					rs.close();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close app?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					System.exit(0);
				}
			}
		});
		btnX.setBackground(Color.RED);
		btnX.setBounds(822, 0, 56, 25);
		contentPane.add(btnX);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					setVisible(false);
					new LoginPage().setVisible(true);
				}
			}
		});
		btnLogOut.setBackground(Color.ORANGE);
		btnLogOut.setBounds(716, 0, 94, 25);
		contentPane.add(btnLogOut);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome, admin!");
		lblWelcomeAdmin.setForeground(Color.BLUE);
		lblWelcomeAdmin.setBackground(Color.WHITE);
		lblWelcomeAdmin.setFont(new Font("Nimbus Roman No9 L", Font.BOLD, 30));
		lblWelcomeAdmin.setBounds(310, 0, 258, 30);
		contentPane.add(lblWelcomeAdmin);
		
		JLabel lblSearchByName = new JLabel("Search by name or group:");
		lblSearchByName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSearchByName.setBounds(12, 47, 231, 17);
		contentPane.add(lblSearchByName);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(261, 46, 359, 19);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameOrGroup = searchTextField.getText(); 
				ResultSet rs = Select.getData("select *from my_students where student_name like '%"+nameOrGroup+"%' or student_group like '%"+nameOrGroup+"%' ");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
					}
					rs.close();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnSearch.setBounds(632, 43, 117, 25);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new adminHomePage().setVisible(true);
			}
		});
		btnClear.setBounds(761, 43, 105, 25);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setVerifyInputWhenFocusTarget(false);
		scrollPane.setEnabled(false);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setBounds(12, 76, 737, 353);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.WHITE);
		table.setName("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student_name", "Student_group", "Price", "Receipt", "Status"
			}
		) 
			{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		JButton btnChange = new JButton("Change");
		btnChange.setActionCommand("change                                                                                                                                            ");
		btnChange.setAlignmentY(Component.TOP_ALIGNMENT);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String student_name = model.getValueAt(index, 0).toString();
				String student_group = model.getValueAt(index, 1).toString();
				String status = model.getValueAt(index, 4).toString();
				if(status.equals("true")) {
					status = "false";
				}else { status="true"; }
				try {
					int ans = JOptionPane.showConfirmDialog(null, "Do you want to change status of '"+student_name+"'?", "Select", JOptionPane.YES_NO_OPTION);
					if(ans == 0) {
			/*new*/			InsertUpdateDelete.setData("update my_students set status = '"+status+"' where student_name = '"+student_name+"' and student_group = '"+student_group+"' ", "Status changed successfully.");
						setVisible(false);
						new adminHomePage().setVisible(true);
					}
				} catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnChange.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChange.setBounds(763, 245, 105, 25);
		contentPane.add(btnChange);
		
		JLabel lblChangeStatus = new JLabel("Change status");
		lblChangeStatus.setBounds(761, 210, 107, 40);
		contentPane.add(lblChangeStatus);
		
		JButton btnNewButton = new JButton("Add new");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegisterPage().setVisible(true);
			}
		});
		btnNewButton.setBounds(761, 103, 105, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new adminHomePage().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(761, 160, 105, 25);
		contentPane.add(btnNewButton_1);
	}
}
