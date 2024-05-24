package com.front;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class main_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Map<String, String> loginInfo;


	public main_frame() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(840, 840/12*9);//창사이즈
			setLocationRelativeTo(null);//창위치
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JButton btnNewButton_1 = new JButton("메뉴 후기");
			btnNewButton_1.setBounds(623, 531, 189, 37);
			contentPane.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("메뉴 찾기");
			btnNewButton_2.setBounds(420, 531, 189, 37);
			contentPane.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("내 정보");
			btnNewButton_3.setBounds(14, 531, 189, 37);
			contentPane.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("식재료");
			btnNewButton_4.setBounds(217, 531, 189, 37);
			contentPane.add(btnNewButton_4);
	}
	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}



}
