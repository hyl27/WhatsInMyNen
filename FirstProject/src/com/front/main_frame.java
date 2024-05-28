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

    private JPanel cardPanel;
    private myInfo myinfo;
    private seach_menu sm;
    private seach_ingri si;
    private review rv;

    public main_frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 840 / 12 * 9);
        setLocationRelativeTo(null);

        cardPanel = new JPanel(new CardLayout());

        // nen_select 패널만 미리 생성
        nen_select nenSelectPanel = new nen_select();
        cardPanel.add(nenSelectPanel, "nen_select"); 
        nenSelectPanel.setLayout(null);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();

        // 버튼 생성 및 액션 리스너 추가
        JButton buttonhome= new JButton("홈");
        buttonhome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showPanel("nen_select");
        	}
        });
        
        JButton button1 = new JButton("내 정보");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("myInfo"); 
            }
        });
        JButton button2 = new JButton("재료 찾기");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("seach_ingri"); 
            }
        });
        JButton button3 = new JButton("메뉴 찾기");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("search_menu");
            }
        });
        JButton button4 = new JButton("후기작성");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setLoginInfo(Map<String, String> loginInfo) {
        // LoginManager를 통해 로그인 정보 설정
        // 로그인 성공 후 패널들을 생성하고 로그인 정보 설정
        myinfo = new myInfo();
        myinfo.setLoginInfo(loginInfo);
        cardPanel.add(myinfo, "myInfo"); 

        sm = new seach_menu();
        sm.setLoginInfo(loginInfo);
        cardPanel.add(sm, "search_menu");

        si = new seach_ingri();
        si.setLoginInfo(loginInfo);
        cardPanel.add(si, "seach_ingri");

        rv = new review();
        rv.setLoginInfo(loginInfo);
        cardPanel.add(rv, "review"); 

        System.out.println("패널 생성 및 로그인 정보 설정 완료!");
    }

    // 각 패널을 보여주는 메서드 (중복 코드 제거)
    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, panelName);
    }
}
