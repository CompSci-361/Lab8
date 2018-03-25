package editor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGui {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String fName;
	private String lName;
	private String phone;
	private String department;
	private String gender;
	private String title;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEnterEmployeeInformation = new JLabel("Enter Employee Information");
		lblEnterEmployeeInformation.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblEnterEmployeeInformation, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("FIrst Name");
		lblFirstName.setBounds(123, 11, 73, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(123, 36, 73, 14);
		panel.add(lblLastName);
		
		textField = new JTextField();
		textField.setBounds(206, 8, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 33, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(123, 61, 66, 14);
		panel.add(lblDepartment);
		
		textField_2 = new JTextField();
		textField_2.setBounds(206, 58, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(123, 86, 73, 14);
		panel.add(lblPhone);
		
		textField_3 = new JTextField();
		textField_3.setBounds(206, 83, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(123, 111, 46, 14);
		panel.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(206, 107, 52, 23);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(260, 107, 73, 23);
		panel.add(rdbtnFemale);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Other");
		rdbtnNewRadioButton.setBounds(335, 107, 66, 23);
		panel.add(rdbtnNewRadioButton);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(123, 136, 46, 14);
		panel.add(lblTitle);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Mr.", "Ms.", "Mrs.", "Dr.", "Col.", "Prof."}));
		comboBox.setBounds(206, 137, 86, 20);
		panel.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Submitting Data");
				String fName = textField.getText();
				System.out.println(fName);
				String title = (String) comboBox.getSelectedItem();
				System.out.println(title);
			}
		});
		btnSubmit.setBounds(107, 168, 89, 23);
		panel.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exitting");
				System.exit(0);
			}
		});
		btnExit.setBounds(203, 168, 89, 23);
		panel.add(btnExit);
	}
}
