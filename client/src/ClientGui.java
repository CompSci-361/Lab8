import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.google.gson.Gson;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
					ClientGui window = new ClientGui();
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
	public ClientGui() {
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
		
		JLabel lblFirstName = new JLabel("First Name");
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
		rdbtnMale.setBounds(206, 107, 61, 23);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(270, 107, 76, 23);
		panel.add(rdbtnFemale);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Other");
		rdbtnNewRadioButton.setBounds(348, 107, 73, 23);
		panel.add(rdbtnNewRadioButton);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnFemale);
		btnGroup.add(rdbtnMale);
		btnGroup.add(rdbtnNewRadioButton);
		btnGroup.clearSelection();
		
		
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
				
				fName = textField.getText();
				lName = textField_1.getText();
				department = textField_2.getText();
				phone = textField_3.getText();
				//JRadioButton test = (JRadioButton) btnGroup.getSelection();
				//gender = test.getText();
				if(rdbtnFemale.isSelected())
					gender = rdbtnFemale.getText();
				if(rdbtnMale.isSelected())
					gender = rdbtnMale.getText();
				if(rdbtnNewRadioButton.isSelected())
					gender = rdbtnNewRadioButton.getText();
				System.out.println(fName);
				System.out.println(lName);
				System.out.println(department);
				System.out.println(phone);
				System.out.println(gender);
				
				title = (String) comboBox.getSelectedItem();
				System.out.println(title);
				if(fName==""||lName==""||department==""||phone==""||gender==null||title=="") {
					System.out.println("Fill in all data points");
					return;
				}
				Employee employee = new Employee(fName,lName,department,phone,gender,title);
				
				try{
				// Client will connect to this location
				URL site = new URL("http://localhost:8000/sendresults");
				HttpURLConnection conn = (HttpURLConnection) site.openConnection();

				// now create a POST request
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());

				Gson g = new Gson();
				// build a string that contains JSON from console
				String content = g.toJson(employee);

				// write out string to output buffer for message
				out.writeBytes(content);
				out.flush();
				out.close();
				System.out.println("Done sent to server");

				System.out.println("Response Code: " + conn.getResponseCode());
				}
				catch(Exception e){
					e.getStackTrace();
				}
			}
		});
		btnSubmit.setBounds(107, 168, 89, 23);
		panel.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exiting");
				System.exit(0);
			}
		});
		btnExit.setBounds(203, 168, 89, 23);
		panel.add(btnExit);
	}
}

