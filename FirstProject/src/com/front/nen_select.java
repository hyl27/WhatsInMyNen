package com.front;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dao.MynenDAO;
import com.vo.MemberVO;
import com.vo.MynenVO;

import mainFram.front.LoginInfoManager;

public class nen_select extends JPanel {
	private String memberId;
	private Map<String, String> loginInfo;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo; // loginInfo 맵 저장
		this.memberId = loginInfo.get("userid");
		System.out.println("냉장고 로그인정보: " + loginInfo);
	}

	// 여기는 로그인하자마자 보이는 화면
	public nen_select() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("냉장고 선택");
		lblNewLabel.setBounds(12, 8, 166, 37);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(12, 102, 802, 419);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
		lblNewLabel1.setBounds(12, 7, 269, 29);
		panel.add(lblNewLabel1);

		JPanel addNen = new JPanel();
		addNen.setBounds(24, 57, 263, 352);
		addNen.setLayout(new FlowLayout()); // Ensure the panel has a layout
		add(addNen);

		JButton btnNewButton = new JButton("my 냉장고 생성");
		btnNewButton.setBounds(567, 10, 223, 23);
		panel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertNen();
				JLabel newLabel = new JLabel("새로운 냉장고가 생성되었습니다: ");
				addNen.add(newLabel);
				addNen.revalidate(); // Revalidate the new panel to ensure layout changes are applied
				addNen.repaint(); // Repaint the new panel to refresh the UI
				
			}
		});
	}
		private void insertNen() {
			Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo(); // 로그인 정보 가져오기
			
			MemberVO mvo = new MemberVO();
			System.out.println(loginInfo);
			mvo.setId(loginInfo.get("userid")); // loginInfo가 안 받아짐.

			try {
				MynenVO nvo = new MynenVO();
				MynenDAO dao = new MynenDAO();
		
				nvo.setM_id(mvo.getId());
				dao.insert_n(nvo);
				System.out.println(nvo.getM_id());
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

}
