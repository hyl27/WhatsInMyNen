package com.front;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.MemberDAO;

public class member_join extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private MemberDAO memberDAO; // MemberDAO 인스턴스 생성
	
	public member_join() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840/12*9);//창사이즈
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); //궁서 바탕 돋움
		lblJoin.setFont(f1); 
		lblJoin.setBounds(364, 126, 101, 20);
		contentPane.add(lblJoin);

		JLabel label = new JLabel("아이디");
		label.setBounds(258, 195, 69, 20);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("비밀번호");
		lblUsername.setBounds(258, 245, 69, 20);
		contentPane.add(lblUsername);
		
		
		JLabel lblName = new JLabel("이름");
		lblName.setBounds(258, 292, 69, 20);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(258, 339, 69, 20);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("생년월일");
		lblPhone.setBounds(258, 386, 69, 20);
		contentPane.add(lblPhone);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(348, 188, 186, 35);
		contentPane.add(tfUsername);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(348, 238, 186, 35);
		contentPane.add(tfPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(348, 285, 186, 35);
		contentPane.add(tfName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(348, 332, 186, 35);
		contentPane.add(tfEmail);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(348, 379, 186, 35);
		contentPane.add(tfPhone);
		
		joinCompleteBtn = new JButton("회원가입완료");
		joinCompleteBtn.setBounds(395, 445, 139, 29);
		contentPane.add(joinCompleteBtn);
		
		setVisible(true);
		
	    // MemberDAO 인스턴스 생성
        memberDAO = new MemberDAO();
        
		

	}
}


