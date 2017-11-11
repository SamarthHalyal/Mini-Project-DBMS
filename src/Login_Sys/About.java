package Login_Sys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMailx = new JLabel("MailX");
		lblMailx.setForeground(Color.WHITE);
		lblMailx.setFont(new Font("Al Bayan", Font.PLAIN, 61));
		lblMailx.setBounds(129, 29, 173, 81);
		contentPane.add(lblMailx);
		
		JLabel lblRegisteredTo = new JLabel("Registered to Nitin Pradhan & Samarth Halyal");
		lblRegisteredTo.setForeground(Color.WHITE);
		lblRegisteredTo.setBounds(78, 117, 297, 16);
		contentPane.add(lblRegisteredTo);
		
		JLabel lblSingleUserLi = new JLabel("Single User License");
		lblSingleUserLi.setForeground(Color.WHITE);
		lblSingleUserLi.setBounds(157, 144, 132, 16);
		contentPane.add(lblSingleUserLi);
		
		JLabel lblNewLabel = new JLabel("Copyright © 2016-2017 KLE MSSCET");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(115, 225, 260, 16);
		contentPane.add(lblNewLabel);
	}
}
