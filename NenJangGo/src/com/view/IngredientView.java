package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.main.LoginInfoManager;

public class IngredientView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPanel textPanel;
	private JButton addButton, searchButton, selectAllButton, deselectAllButton;
	private String memberId;
	public int nenId;

	public IngredientView() {
		initialize();
	}

	private void initialize() {
		start();
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNewLabel = new JLabel(memberId + "의 " + nenId + " 냉장고"); 
		lblNewLabel.setFont(new Font("LG Smart UI Regular", Font.PLAIN, 20));
		topPanel.add(lblNewLabel);
		add(topPanel, BorderLayout.NORTH);

		JPanel sayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNewLabel2 = new JLabel("재료를 추가하세요");
		lblNewLabel2.setFont(new Font("LG Smart UI Regular", Font.PLAIN, 40));
		sayPanel.add(lblNewLabel2);
		add(sayPanel, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(251, 248, 221));
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField(20);
		inputPanel.add(textField);
		addButton = new JButton("추가");
		inputPanel.add(addButton);
		searchButton = new JButton("메뉴 검색");
		inputPanel.add(searchButton);
		selectAllButton = new JButton("전체 선택");
		inputPanel.add(selectAllButton);
		deselectAllButton = new JButton("전체 해제");
		inputPanel.add(deselectAllButton);

		centerPanel.add(inputPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 400)); 
		textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		scrollPane.setViewportView(textPanel);
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		add(centerPanel, BorderLayout.SOUTH);
	}

	// Getters for components
	public JTextField getTextField() {
		return textField;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getSelectAllButton() {
		return selectAllButton;
	}

	public JButton getDeselectAllButton() {
		return deselectAllButton;
	}

	private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		nenId = LoginInfoManager.getInstance().getNenId();
	}
}
