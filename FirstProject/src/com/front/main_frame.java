package com.front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import mainFram.front.myInfo;
import mainFram.front.review;
import mainFram.front.seach_ingri;
import mainFram.front.seach_menu;

public class main_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Map<String, String> loginInfo;

	public main_frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 840 / 12 * 9); // 창사이즈
		setLocationRelativeTo(null);//창위치

		// CardLayout을 사용할 패널 생성
		JPanel cardPanel = new JPanel(new CardLayout());

		// 메인 화면 패널 생성
		JPanel panel1 = new JPanel();
		
		// 각 페이지의 생성자 호출 
		nen_select nenSelectPanel = new nen_select(); // nen_select 패널 생성
		myInfo myinfo = new myInfo();
		seach_menu sm = new seach_menu();// 메뉴찾기
		seach_ingri si = new seach_ingri();// 재료찾기
		review rv = new review();// 리뷰작성

		// 패널들을 CardLayout 패널에 추가
		cardPanel.add(nenSelectPanel, "nen_select"); // "Panel 1"이라는 이름으로 cardPanel에 추가
		nenSelectPanel.setLayout(null);
		cardPanel.add(myinfo, "DynamicPanel"); // "DynamicPanel"이라는 이름으로 cardPanel에 추가
		cardPanel.add(sm, "search_menu"); // "search_menu"이라는 이름으로 cardPanel에 추가
		cardPanel.add(si, "seach_ingri"); // "search_menu"이라는 이름으로 cardPanel에 추가
		cardPanel.add(rv, "review"); // "search_menu"이라는 이름으로 cardPanel에 추가

		// 버튼 패널 생성
		JPanel buttonPanel = new JPanel();

		// 내 정보 버튼 생성 및 액션 리스너 추가
		JButton button1 = new JButton("내 정보");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "DynamicPanel");
			}
		});
		JButton button3 = new JButton("메뉴 찾기");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "search_menu");
			}
		});
		JButton button2 = new JButton("재료 찾기");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "seach_ingri");
			}
		});

		JButton button4 = new JButton("후기작성");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				cl.show(cardPanel, "review");
			}
		});

		// 버튼들을 버튼 패널에 추가
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);

		// 메인 프레임에 버튼 패널과 카드 패널 추가
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(cardPanel, BorderLayout.CENTER);

		// 메인 프레임을 화면에 표시
		setVisible(true);

	}

	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}

}
