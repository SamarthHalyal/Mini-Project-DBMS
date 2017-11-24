package Login_Sys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Forgotpass extends JFrame {

	private JPanel contentPane;
	private JTextField forgotpassemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forgotpass frame = new Forgotpass();
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
	public Forgotpass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblEnterEmail = new JLabel("Enter Email :");
		lblEnterEmail.setForeground(Color.WHITE);
		lblEnterEmail.setBounds(54, 115, 87, 16);
		contentPane.add(lblEnterEmail);
		
		forgotpassemail = new JTextField();
		forgotpassemail.setBounds(153, 110, 231, 26);
		contentPane.add(forgotpassemail);
		forgotpassemail.setColumns(10);
		
		JButton btnSendPassword = new JButton("send password");
		btnSendPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String em = forgotpassemail.getText().toString();
				String forgotpas = null;
				try{
					// 1. Create a connection to database
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginApp","root","toor");
					// 2. Create  a statement
					Statement statement = conn.createStatement();
					// 3. Execute SQL query
					String sql = "select username from register where email = \"" + em + "\";" ;
					ResultSet set = statement.executeQuery(sql);
					String n = null;
					while(set.next()){
						n = set.getString("username");
					}
					
					sql = "select pass from login where firstname = \"" + n + "\";" ;
					set = statement.executeQuery(sql);
					String p = null;
					while(set.next()){
						p = set.getString("pass");
					}
					forgotpas = p;
					conn.close();
				}catch(Exception e3){
					System.out.println("Error in mail database");
				}
				
				String server = "smtp.gmail.com";
				int port = 587;
				String user = "samarthhalyal@gmail.com";
				String pass = "1ROBOTICshooter!";
				
				Properties prop = new Properties();
				prop.put("mail.smtp.host", server);
				prop.put("mail.smtp.user", user);
				prop.put("mail.smtp.password", pass);
				prop.put("mail.smtp.port", port);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				
				try{
					/// Create a session
					Session session = Session.getDefaultInstance(prop);
					
					/// Configure the message
					MimeMessage message1 = new MimeMessage(session);
					message1.addRecipient(RecipientType.TO, new InternetAddress(em));
					message1.setFrom(new InternetAddress(user));
					message1.setSubject("YOUR PASSWORD");
					message1.setText(forgotpas);
					
					// File sending part
//					if(s != ""){
//						MimeBodyPart messageBodyPart = new MimeBodyPart();
//				        Multipart multipart = new MimeMultipart();    
//				        messageBodyPart = new MimeBodyPart();
//				        String file = fpath;
//				        String fileName = s;
//				        DataSource source = new FileDataSource(file);
//				        messageBodyPart.setDataHandler(new DataHandler(source));
//				        messageBodyPart.setFileName(fileName);
//				        multipart.addBodyPart(messageBodyPart);
//				        message1.setContent(multipart);
//					}
					/// Create a Transport
					Transport transport = session.getTransport("smtp");
					transport.connect(server,user,pass);
					transport.sendMessage(message1, message1.getAllRecipients());
					transport.close();
					JOptionPane.showMessageDialog(null, "Password send to this email", "", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Mail not sent", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSendPassword.setBounds(150, 201, 158, 29);
		contentPane.add(btnSendPassword);
	}
}
