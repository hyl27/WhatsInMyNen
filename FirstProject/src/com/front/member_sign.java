package com.front;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.MemberDAO;

import mainFram.front.LoginInfoManager;

public class member_sign extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUserid, tfUserpw;
	private JButton loginBtn, joinBtn;
	private MemberDAO memberDAO; // MemberDAO 인스턴스 생성



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					member_sign frame = new member_sign();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public member_sign() {
		
		// MemberDAO 인스턴스 생성
        memberDAO = new MemberDAO();
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840/12*9);//창사이즈
		setLocationRelativeTo(null);//배치위치
		contentPane = new JPanel();//패널설정
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("아이디");
		lblLogin.setBounds(511, 265, 69, 35);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(510, 314, 69, 35);
		contentPane.add(lblPassword);
		
		tfUserid = new JTextField();
		tfUserid.setBounds(579, 265, 176, 35);
		contentPane.add(tfUserid);
		tfUserid.setColumns(10);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setBounds(514, 383, 104, 29);
		contentPane.add(joinBtn);
		
		loginBtn = new JButton("로그인");
		loginBtn.setBounds(630, 383, 106, 29);
		contentPane.add(loginBtn);
		
		tfUserpw = new JTextField();
		tfUserpw.setColumns(10);
		tfUserpw.setBounds(579, 316, 176, 35);
		contentPane.add(tfUserpw);
		
		JLabel lblNewLabel = new JLabel("What's in my NenJangGo");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setBounds(514, 193, 241, 46);
		contentPane.add(lblNewLabel);
		

   
		//회원가입 액션
		joinBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				member_join frame = new member_join();
			}
		});
		

		//로그인 액션
		loginBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (e.getSource() == loginBtn) {
		            String memberid = tfUserid.getText();
		            String userpass = tfUserpw.getText();
		            
		            try {
		                Map<String, String> loginInfo = new HashMap<>();
		                loginInfo.put("userid", memberid);
		                loginInfo.put("userpw", userpass);
		                System.out.println("입력한 id : "+loginInfo);
		                
		                String loginStatus = memberDAO.login_m(memberid, userpass);
		                
		                System.out.println(loginStatus);
		                if (loginStatus.equals("로그인 성공")) {
		                    JOptionPane.showMessageDialog(null, "안녕하세요 반갑습니다 :)");
		                    //main_frameFrame.setLoginInfo(loginInfo); // 로그인 정보 전달
		                    System.out.println("\"너뭐야?\"");
		                    LoginInfoManager.getInstance().setLoginInfo(loginInfo);
		                    main_frame main_frameFrame = new main_frame();
		                    main_frameFrame.setVisible(true); // nen_select 화면 열기
		                    setVisible(false); // 로그인 창 닫기
		                } else{
		                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "로그인 중 오류가 발생했습니다.");
		            }
		        }
		    }
		});


	}
}




