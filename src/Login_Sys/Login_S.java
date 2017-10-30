package Login_Sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class Login_S extends JPanel {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtFirstName;
	private JTextField textlastname;
	private JTextField textemail;
	private JTextField txtPhone;
	private JTextField dd;
	private JTextField mm;
	private JTextField yyyy;
	private Connection conn;
	private JPasswordField txtpassword;
	mailapp mailobj = new mailapp();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_S window = new Login_S();
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
	public Login_S() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPanes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanes.setBounds(6, 6, 388, 436);
		Login_Sys.Login login = new Login();
		tabbedPanes.add("Login", login);
		login.setLayout(null);
		
		JLabel username = new JLabel("Username :");
		username.setBounds(25, 27, 84, 16);
		login.add(username);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(109, 22, 239, 26);
		login.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel password = new JLabel("Password :");
		password.setBounds(25, 76, 84, 16);
		login.add(password);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(109, 71, 239, 26);
		login.add(txtPassword);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtUsername.getText();
				String pass = txtPassword.getText();
				boolean flag = false;
				try{
					// 1. Create a connection to database
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginApp","root","toor");
					// 2. Create  a statement
					Statement statement = conn.createStatement();
					// 3. Execute SQL query
					ResultSet set = statement.executeQuery("select * from login");
					// 4. Process the result set
					String thisname = null;
					while(set.next()) {
						String name = set.getString("firstname");
						String pas = set.getString("pass");
						if(id.contains(name) && pass.contains(pas)){
							flag = true;
							thisname = name;
							break;
						}
					}
					set = statement.executeQuery("select email from login l ,details d where l.firstname = d.firstname and l.firstname = \""+ thisname +"\";");
					if(flag){
						while(set.next()){
							mailobj.user = set.getString("email");
						}
						mailobj.setVisible(true);
					}
					else{
						System.out.println("User not registered");
					}
					conn.close();
				} catch(Exception e1) {
					System.out.println("No Connection created");
				}
			}
		});
		loginbt.setBounds(121, 315, 117, 29);
		login.add(loginbt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 127, 323, 12);
		login.add(separator);
		Login_Sys.Register register = new Register();
		tabbedPanes.add("Register", register);
		register.setLayout(null);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(107, 5, 219, 26);
		register.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel first = new JLabel("FirstName :");
		first.setBounds(23, 10, 89, 16);
		register.add(first);
		
		JLabel last = new JLabel("LastName :");
		last.setBounds(23, 38, 77, 16);
		register.add(last);
		
		textlastname = new JTextField();
		textlastname.setBounds(107, 33, 219, 26);
		register.add(textlastname);
		textlastname.setColumns(10);
		
		JLabel mail = new JLabel("E-Mail :");
		mail.setBounds(23, 66, 61, 16);
		register.add(mail);
		
		textemail = new JTextField();
		textemail.setBounds(107, 61, 219, 26);
		register.add(textemail);
		textemail.setColumns(10);
		
		JLabel passwordlab = new JLabel("Password :");
		passwordlab.setBounds(23, 94, 77, 16);
		register.add(passwordlab);
		
		JLabel address = new JLabel("Postal Address :");
		address.setBounds(23, 150, 106, 16);
		register.add(address);
		
		JTextArea txtaddress = new JTextArea();
		txtaddress.setBounds(141, 150, 185, 68);
		register.add(txtaddress);
		
		JLabel phone = new JLabel("Phone :");
		phone.setBounds(23, 243, 61, 16);
		register.add(phone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(117, 238, 209, 26);
		register.add(txtPhone);
		txtPhone.setColumns(10);
		
		dd = new JTextField();
		dd.setText("DD");
		dd.setBounds(63, 271, 77, 26);
		register.add(dd);
		dd.setColumns(10);
		
		mm = new JTextField();
		mm.setText("MM");
		mm.setColumns(10);
		mm.setBounds(141, 271, 77, 26);
		register.add(mm);
		
		yyyy = new JTextField();
		yyyy.setText("YYYY");
		yyyy.setColumns(10);
		yyyy.setBounds(224, 271, 77, 26);
		register.add(yyyy);
		
		JLabel date = new JLabel("D.O.B :");
		date.setBounds(18, 271, 45, 16);
		register.add(date);
		
		JRadioButton female = new JRadioButton("Female");
		female.setBounds(45, 309, 106, 23);
		register.add(female);
		
		JRadioButton male = new JRadioButton("Male");
		male.setBounds(163, 309, 106, 23);
		register.add(male);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gender;
				String firstname = txtFirstName.getText();
				String lastname = textlastname.getText();
				String pass = txtpassword.getText();
				String email = textemail.getText();
				String add = txtaddress.getText();
				String d = dd.getText();
				String m = mm.getText();
				String y = yyyy.getText();
				String date = "\'"+y+"-"+m+"-"+d+"\'";
				if(female.isSelected()){
					gender = "F";
				}
				if(female.isSelected() && male.isSelected()){
					gender = "T";
				}
				else{
					gender = "M";
				}
				try{
					// 1. Create a connection to database
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginApp","root","toor");
					// 2. Create  a statement
					Statement statement = conn.createStatement();
					// 3. Execute SQL query
					String sql = "insert into login (firstname,pass) values(\"" + firstname.toString()+"\",\""+ pass.toString() +"\");";
					statement.executeUpdate(sql);
					sql = "insert into details (firstname,lastname,email,gender) values(\"" + firstname.toString()+"\",\""+ lastname.toString() +"\",\""+ email.toString() +"\",\""+ gender.toString() +"\" );";
					statement.executeUpdate(sql);
					sql = "insert into misc (address,dob) values(\"" + add.toString()+"\","+ date.toString()+");";
					statement.executeUpdate(sql);
					// 4. Process the result set
					conn.close();
				}catch(Exception e2){
					System.out.println("Not executed");
				}
			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 338, 303, 12);
		register.add(separator_1);
		frame.getContentPane().add(tabbedPanes);
		
		registerbt.setBounds(124, 352, 117, 29);
		register.add(registerbt);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(107, 89, 219, 26);
		register.add(txtpassword);
		
	}
}

class Login extends JPanel {
	
}

class Register extends JPanel{
	
}
