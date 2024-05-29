package com.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.dao.MynenDAO;
import com.vo.MynenVO;

import mainFram.front.LoginInfoManager;

public class nen_select extends JPanel {
	MynenVO nvo = new MynenVO();
	MynenDAO dao = new MynenDAO();
	private String memberId;

	private JPanel contentPane;
	private List<JButton> nenButtons; // 새로운 냉장고 버튼을 저장할 리스트를 추가합니다.
	private Map<String, String> loginInfo;
	private static final int MAX_NEN_BUTTONS = 3; // 최대 냉장고 버튼 개수를 정의합니다.

	private static final long serialVersionUID = 1L;

//	public void setLoginInfo(Map<String, String> loginInfo) {
//		this.loginInfo = loginInfo; // loginInfo 맵 저장
//		this.memberId = loginInfo.get("userid");
//		System.out.println("리뷰 로그인정보: " + loginInfo);
//	}
	public void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		System.out.println(memberId);
	}
	
	public nen_select() {
		start();
		
		setLayout(null);
		nenButtons = new ArrayList<>();

		JLabel lblNewLabel = new JLabel("냉장고 선택");
		lblNewLabel.setBounds(12, 8, 166, 37);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		add(lblNewLabel);

		contentPane = new JPanel();
		contentPane.setBounds(12, 102, 802, 419);
		contentPane.setLayout(null);
		add(contentPane);

		JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
		lblNewLabel1.setBounds(12, 7, 269, 29);
		contentPane.add(lblNewLabel1);

		JButton btnNewButton = new JButton("my 냉장고 생성");
		btnNewButton.setBounds(567, 10, 223, 23);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nenButtons.size() < MAX_NEN_BUTTONS) {
					insertNen();
				} else {
					JOptionPane.showMessageDialog(null, "최대 냉장고 개수에 도달했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// 새로운 로그인 시, 기존 냉장고 버튼을 추가합니다.
		addExistingNenButtons();
	}

	private void addExistingNenButtons() {

	    try {
	        List<Integer> existingNenIds = dao.view_nen(memberId);

	        for (int nenId : existingNenIds) {
	            JButton nenButton = createNenButton(nenId);
	            contentPane.add(nenButton);
	            nenButtons.add(nenButton);
	        }

	        contentPane.revalidate();
	        contentPane.repaint();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private JButton createNenButton(int nenId) {
		JButton nenButton = new JButton("냉장고 " + nenId);
		nenButton.setBounds(getNextButtonX(), 50, 200, 380);
		nenButton.setBackground(Color.WHITE);
		nenButton.setFocusPainted(false);

		nenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼이 클릭되었을 때 실행되는 동작을 추가할 수 있습니다.
			}
		});

		return nenButton;
	}

	private void insertNen() {
		try {
			nvo.setM_id(memberId);
			dao.insert_n(nvo);
			System.out.println(nvo.getM_id());
			
			JButton newNenButton = createNenButton(nvo.getId());

			contentPane.add(newNenButton);
			nenButtons.add(newNenButton);

			contentPane.revalidate();
			contentPane.repaint();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private int getNextButtonX() {
		// 현재 생성된 버튼 개수를 사용하여 다음 버튼의 X 좌표를 계산합니다.
		return 30 + nenButtons.size() * 250; // 적절한 간격으로 버튼을 배치합니다.
	}
}
