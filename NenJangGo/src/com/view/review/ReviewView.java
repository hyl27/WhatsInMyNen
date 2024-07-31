package com.view.review;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReviewView extends JPanel {
    private JTextField txtMenuId; // 메뉴 아이디 입력 필드
    private JTextField txtMenuurl; // 메뉴 URL 입력 필드
    private JTextField txtRating; // 별점 입력 필드
    private JTextArea txtContent; // 내용 입력 필드
    private CardLayout innerCardLayout; // 카드 레이아웃
    private JPanel innerCardPanel; // 카드 패널
    private JTable reviewTable; // 리뷰 목록 테이블
    private JButton innerButton1; // 리뷰 목록 보기 버튼
    private JButton btnClose; // 닫기 버튼
    private JButton btnInsert; // 리뷰 등록 버튼
    private JButton[] starButtons = new JButton[5]; //별점 관련 필드 

    public ReviewView() {
        setLayout(new BorderLayout());

        innerCardLayout = new CardLayout();
        innerCardPanel = new JPanel(innerCardLayout);
        add(innerCardPanel, BorderLayout.CENTER);

        JPanel innerFirstCard = createReviewInputPanel();
        innerCardPanel.add(innerFirstCard, "Write Review");

        JPanel innerSecondCard = createReviewListPanel();
        innerCardPanel.add(innerSecondCard, "Review List");

        innerCardLayout.show(innerCardPanel, "Write Review");
    }

    private JPanel createReviewInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.PINK);
        inputPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelMenuId = new JLabel("메뉴 이름");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inputPanel.add(labelMenuId, gbc);

        txtMenuId = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        inputPanel.add(txtMenuId, gbc);

        JLabel labelMenuUrl = new JLabel("링크:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(labelMenuUrl, gbc);

        txtMenuurl = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        inputPanel.add(txtMenuurl, gbc);

        JLabel labelContent = new JLabel("내용:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(labelContent, gbc);

        txtContent = new JTextArea(5, 30);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtContent);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        inputPanel.add(scrollPane, gbc);
//
//        JLabel labelRating = new JLabel("별점:");
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.gridwidth = 1;
//        inputPanel.add(labelRating, gbc);
//
//        txtRating = new JTextField(2);
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.gridwidth = 3;
//        inputPanel.add(txtRating, gbc);

        JLabel labelRating = new JLabel("별점:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        inputPanel.add(labelRating, gbc);

        JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < 5; i++) {
            starButtons[i] = new JButton("★");
            starButtons[i].setFont(new Font("돋움", Font.PLAIN, 14));
            starPanel.add(starButtons[i]);
        }
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        inputPanel.add(starPanel, gbc);


        btnInsert = new JButton("리뷰 등록");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(btnInsert, gbc);

        innerButton1 = new JButton("리뷰 목록");
        gbc.gridy = 5;
        inputPanel.add(innerButton1, gbc);
        
        return inputPanel;
    }

    
    
    
    private JPanel createReviewListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.LIGHT_GRAY);
        listPanel.setLayout(new GridBagLayout());

        btnClose = new JButton("x");
        GridBagConstraints gbc_btnClose = new GridBagConstraints();
        gbc_btnClose.insets = new Insets(0, 0, 5, 0);
        gbc_btnClose.gridx = 7;
        gbc_btnClose.gridy = 0;
        listPanel.add(btnClose, gbc_btnClose);

     // 리뷰 목록을 보여주는 테이블을 생성
        reviewTable = new JTable(new DefaultTableModel(
            new Object[][] {}, // 초기 데이터는 빈 배열로 설정됩니다. 데이터베이스에서 가져온 데이터를 여기에 추가합니다.
            new String[] { "메뉴이름", "작성자", "내용", "별점", "작성일", "링크", "리뷰아이디" } // 테이블의 열(컬럼) 이름을 설정합니다.
        		) {
            // 테이블의 셀을 수정할 수 없게 설정하기 위해 DefaultTableModel의 isCellEditable 메서드를 오버라이드합니다.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 수정 불가능하도록 false를 반환합니다.
            }
        });

        // 테이블의 행 높이를 설정합니다.
        reviewTable.setRowHeight(20); // 각 행의 높이를 45로 설정합니다.


        JScrollPane scrollPane = new JScrollPane(reviewTable);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        gbc_scrollPane.gridwidth = 8;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        listPanel.add(scrollPane, gbc_scrollPane);

        return listPanel;
    }

    // Getters for various components
    public JTextField getTxtMenuId() { return txtMenuId; }
    public JTextField getTxtMenuurl() { return txtMenuurl; }
    public JTextField getTxtRating() { return txtRating; }
    public JTextArea getTxtContent() { return txtContent; }
    public CardLayout getInnerCardLayout() { return innerCardLayout; }
    public JPanel getInnerCardPanel() { return innerCardPanel; }
    public JTable getReviewTable() { return reviewTable; }
    public JButton getInnerButton1() { return innerButton1; }
    public JButton getBtnClose() { return btnClose; }
    public JButton getBtnInsert() { return btnInsert; }
    public JButton[] getStarButtons() { return starButtons; }//별점 추가
}
