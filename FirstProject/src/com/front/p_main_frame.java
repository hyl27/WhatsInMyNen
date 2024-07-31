package com.front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mainFram.front.myInfo;
import mainFram.front.review;
import mainFram.front.seach_ingri;
import mainFram.front.seach_menu;

public class main_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private myInfo myinfo;
	private seach_menu sm;
	private seach_ingri si;
	private review rv;
	private nen_select jg;

	public main_frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840 / 12 * 9);
		setLocationRelativeTo(null);

		cardPanel = new JPanel(new CardLayout());

		// 초기 패널 추가
		nen_select nenSelectPanel = new nen_select();
		cardPanel.add(nenSelectPanel, "nen_select");

		// 버튼 패널 생성
		JPanel buttonPanel = new JPanel();

		// 버튼 생성 및 액션 리스너 추가
		JButton buttonhome = new JButton("홈");
		buttonhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel("nen_select");
			}
		});

		JButton button1 = new JButton("내 정보");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myinfo = new myInfo();
				cardPanel.add(myinfo, "myInfo");
				showPanel("myInfo");

			}
		});

		JButton button2 = new JButton("재료 찾기");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				si = new seach_ingri();
				if (si.nenId == 0 ) {
					System.out.println("재료 찾기 불가: 선택된 냉장고 X");
					JOptionPane.showMessageDialog(null, "냉장고를 먼저 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					cardPanel.add(si, "seach_ingri");
					showPanel("seach_ingri");
				}
			}

		});

		JButton button3 = new JButton("메뉴 찾기");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seach_menu sm = new seach_menu();
					if (sm.menus == null || sm.menus.isEmpty()) {
						JOptionPane.showMessageDialog(null, "메뉴가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
					} else {
						cardPanel.add(sm, "seach_menu");
						showPanel("seach_menu");
					}
				} catch (Exception ex) {
					System.out.println("메뉴 찾기 불가: 선택된 냉장고 X");
					JOptionPane.showMessageDialog(null, "냉장고를 먼저 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton button4 = new JButton("후기작성");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rv = new review();
				cardPanel.add(rv, "review");
				showPanel("review");
			}

		});

		// 버튼들을 버튼 패널에 추가
		buttonPanel.add(buttonhome);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);

		// 메인 프레임에 버튼 패널과 카드 패널 추가
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(buttonPanel, BorderLayout.SOUTH); // 버튼 패널을 상단에 고정
		getContentPane().add(cardPanel, BorderLayout.CENTER); // 카드 패널을 중앙에 배치

		setVisible(true);
	}

	// 각 패널을 보여주는 메서드
	public void showPanel(String panelName) {
		CardLayout cl = (CardLayout) cardPanel.getLayout();
		cl.show(cardPanel, panelName);
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}
}
