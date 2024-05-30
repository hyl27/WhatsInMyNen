package com.front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.MemberDAO;
import com.vo.MemberVO;

public class member_join extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfBdate;
	private JCheckBox maleCheckBox; // 남자 체크박스 추가
	private JCheckBox femaleCheckBox; // 여자 체크박스 추가
	private MemberDAO memberDAO; // MemberDAO 인스턴스 생성
	private MemberVO memberVO; // MemberDAO 인스턴스 생성
	

	public member_join() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840 / 12 * 9);// 창사이즈
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20); // 궁서 바탕 돋움
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

		tfBdate = new JTextField();
		tfBdate.setColumns(10);
		tfBdate.setBounds(348, 379, 186, 35);
		contentPane.add(tfBdate);

		// 남자 체크박스 생성 및 설정
		maleCheckBox = new JCheckBox("남자");
		maleCheckBox.setBounds(348, 426, 80, 20);
		contentPane.add(maleCheckBox);

		// 여자 체크박스 생성 및 설정
		femaleCheckBox = new JCheckBox("여자");
		femaleCheckBox.setBounds(430, 426, 80, 20);
		contentPane.add(femaleCheckBox);

		joinCompleteBtn = new JButton("회원가입완료");
		joinCompleteBtn.setBounds(395, 472, 139, 29);
		contentPane.add(joinCompleteBtn);

		setVisible(true);

		// MemberDAO 인스턴스 생성
		memberDAO = new MemberDAO();
		memberVO = new MemberVO();

		joinCompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 각 입력 필드의 값을 가져옴
				String userid = tfUsername.getText();
				String password = tfPassword.getText();
				String name = tfName.getText();
				String email = tfEmail.getText();
				String bdate = tfBdate.getText();
				char gender; 
				
				// 성별 체크박스 선택 여부에 따라 gender 변수 설정
		        if (maleCheckBox.isSelected()) {
		            gender = 'm'; // 남자일 경우 m로 설정
		        } else if (femaleCheckBox.isSelected()) {
		            gender = 'f'; // 여자일 경우 f으로 설정
		        } else {
		            gender = 0; // 선택되지 않았을 경우 0으로 설정 (기본값)
		        }
		        
				memberVO.setId(userid);
				memberVO.setPw(password);
				memberVO.setName(name);
				memberVO.setemail(email);
				memberVO.setbdate(bdate);
				memberVO.setgender(gender);

				// 모든 필드가 채워져 있는지 확인
				if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || bdate.isEmpty()) {
					JOptionPane.showMessageDialog(null, "모든 항목을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
					return; // 필수 입력 항목이 비어있으면 메소드 종료
				}

				// MemberDAO를 사용하여 회원을 추가
				
				try {
				    // memberDAO의 insert_m 메소드를 호출하여 프로시저를 실행하고, 회원가입 상태를 반환받음
					String joinStatus = memberDAO.insert_m(memberVO);
				    
				    // 회원가입 상태에 따라 처리
				    if (joinStatus.equals("회원가입 성공!")) {
				        JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				        member_sign firstScreen = new member_sign();
				        firstScreen.setVisible(true); 
				        // 회원가입 성공 시 추가 동작 수행
				    } else {
				        JOptionPane.showMessageDialog(null, joinStatus, "에러", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}


				
			}
		});

	}
}
