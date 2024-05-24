package com.front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class nen_select extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Map<String, String> loginInfo; 
//여기는 로그인하자마자 보이는 화면
	public nen_select() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840/12*9);//창사이즈
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("냉장고 선택");
		lblNewLabel.setBounds(12, 8, 166, 37);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 102, 802, 419);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("my 냉장고 생성");
		btnNewButton.setBounds(567, 10, 223, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
		lblNewLabel1.setBounds(12, 7, 269, 29);
		panel.add(lblNewLabel1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
	
	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}
	
}
