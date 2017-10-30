package Login_Sys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
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
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class mailapp extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtpassword;
	private JTextField txtto;
	private JTextField txtsubject;
	JFileChooser f;
	String s = "";
	String fpath = "";
	String user = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mailapp frame = new mailapp();
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
	public mailapp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		f = new JFileChooser();
		
		JLabel password = new JLabel("PASSWORD :");
		password.setBounds(20, 23, 94, 16);
		contentPane.add(password);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(126, 18, 226, 26);
		contentPane.add(txtpassword);
		
		JLabel smallmessage = new JLabel("(YOUR EMAIL PASSWORD WILL NOT BE STORED)");
		smallmessage.setBounds(48, 62, 322, 16);
		contentPane.add(smallmessage);
		
		JLabel to = new JLabel("TO :");
		to.setBounds(20, 95, 37, 16);
		contentPane.add(to);
		
		txtto = new JTextField();
		txtto.setBounds(58, 90, 294, 26);
		contentPane.add(txtto);
		txtto.setColumns(10);
		
		JLabel sublab = new JLabel("SUBJECT :");
		sublab.setBounds(23, 128, 61, 16);
		contentPane.add(sublab);
		
		txtsubject = new JTextField();
		txtsubject.setBounds(98, 123, 254, 26);
		contentPane.add(txtsubject);
		txtsubject.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 79, 332, 12);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 51, 332, 12);
		contentPane.add(separator_1);
		
		JLabel messagelab = new JLabel("MESSAGE :");
		messagelab.setBounds(20, 172, 94, 16);
		contentPane.add(messagelab);
		
		JTextArea messageArea = new JTextArea();
		messageArea.setBounds(108, 172, 244, 112);
		contentPane.add(messageArea);
		
		JButton send = new JButton("SEND");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					// 1. Create a connection to database
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginApp","root","toor");
					// 2. Create  a statement
					Statement statement = conn.createStatement();
					// 3. Execute SQL query
					String sql = "insert into recipient (fromid,toid) values(\"" + user +"\",\""+ txtto.getText().toString() +"\");";
					statement.executeUpdate(sql);
					sql = "insert into messages (fromid,message,subj) values(\"" + user +"\",\""+ messageArea.getText().toString() +"\",\""+ txtsubject.getText().toString() +"\");";
					statement.executeUpdate(sql);
					sql = "insert into attachments (fromid,attachment,toid) values(\"" + user +"\",\""+ fpath +"\",\""+ txtto.getText().toString() +"\");";
					statement.executeUpdate(sql);
					conn.close();
				}catch(Exception e3){
					System.out.println("Error in mail database");
				}
				
				
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.user", user);
				prop.put("mail.smtp.password", txtpassword.getText().toString());
				prop.put("mail.smtp.port", 587);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				
				try{
					/// Create a session
					Session session = Session.getDefaultInstance(prop);
					
					/// Configure the message
					MimeMessage message1 = new MimeMessage(session);
					message1.addRecipient(RecipientType.TO, new InternetAddress(txtto.getText().toString()));
					message1.setFrom(new InternetAddress(user));
					message1.setSubject(txtsubject.getText().toString());
					message1.setText(messageArea.getText().toString());
					
					// File sending part
					if(s != ""){
						MimeBodyPart messageBodyPart = new MimeBodyPart();
				        Multipart multipart = new MimeMultipart();    
				        messageBodyPart = new MimeBodyPart();
				        String file = fpath;
				        String fileName = s;
				        DataSource source = new FileDataSource(file);
				        messageBodyPart.setDataHandler(new DataHandler(source));
				        messageBodyPart.setFileName(fileName);
				        multipart.addBodyPart(messageBodyPart);
				        message1.setContent(multipart);
					}
			        
					/// Create a Transport
					Transport transport = session.getTransport("smtp");
					transport.connect("smtp.gmail.com",user,txtpassword.getText().toString());
					transport.sendMessage(message1, message1.getAllRecipients());
					transport.close();
					JOptionPane.showMessageDialog(null, "Mail Sent", "", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Mail not sent", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		send.setBounds(6, 358, 94, 29);
		contentPane.add(send);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpassword.setText("");
				txtto.setText("");
				txtsubject.setText("");
				messageArea.setText("");
			}
		});
		clear.setBounds(100, 358, 94, 29);
		contentPane.add(clear);
		
		JButton quit = new JButton("QUIT");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quit.setBounds(206, 358, 94, 29);
		contentPane.add(quit);
		
		JButton attach = new JButton("ATTACH");
		attach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnval = f.showOpenDialog(contentPane);
				if(returnval == JFileChooser.APPROVE_OPTION){
					File file = f.getSelectedFile();
					s = f.getName(file);
					fpath = f.getSelectedFile().getAbsolutePath();
				}
			}
		});
		attach.setBounds(300, 358, 94, 29);
		contentPane.add(attach);
	}

}
