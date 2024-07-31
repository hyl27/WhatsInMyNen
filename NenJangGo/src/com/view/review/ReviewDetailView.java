package com.view.review;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReviewDetailView extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField txtMenuId;
    private JTextField txtMemberId;
    private JTextArea txtContent;
    private JTextField txtRating;
    private JTextField txtDate;
    private JTextField txtUrl;
    private JTextField txtReviewId;
    private JButton btnModify;
    private JButton btnSave;
    private JButton btnDelete;

    public ReviewDetailView(DefaultTableModel model, int row) {
        initialize(model, row);
    }

    public void initialize(DefaultTableModel model, int row) {
        setLayout(null);

        // 폰트 설정
        Font f1 = new Font("돋움", Font.BOLD, 20);

        // 메뉴 ID
        JLabel labelMenuId = new JLabel("메뉴이름:");
        labelMenuId.setBounds(50, 50, 100, 20);
        add(labelMenuId);

        txtMenuId = new JTextField((String) model.getValueAt(row, 0));
        txtMenuId.setBounds(150, 50, 200, 30);
        txtMenuId.setEditable(false);
        add(txtMenuId);

        // 작성자
        JLabel labelMemberId = new JLabel("작성자:");
        labelMemberId.setBounds(50, 100, 100, 20);
        add(labelMemberId);

        txtMemberId = new JTextField((String) model.getValueAt(row, 1));
        txtMemberId.setBounds(150, 100, 200, 30);
        txtMemberId.setEditable(false);
        add(txtMemberId);

        // 내용
        JLabel labelContent = new JLabel("내용:");
        labelContent.setBounds(50, 150, 100, 20);
        add(labelContent);

        setTxtContent(new JTextArea((String) model.getValueAt(row, 2)));
        getTxtContent().setBackground(new Color(236, 236, 236));
        getTxtContent().setBounds(150, 150, 200, 100);
        getTxtContent().setLineWrap(true);
        getTxtContent().setWrapStyleWord(true);
        getTxtContent().setEditable(false);
        JScrollPane scrollPane = new JScrollPane(getTxtContent());
        scrollPane.setBounds(150, 150, 200, 100);
        add(scrollPane);

        // 별점
        JLabel labelRating = new JLabel("별점:");
        labelRating.setBounds(50, 270, 100, 20);
        add(labelRating);

        setTxtRating(new JTextField((String) model.getValueAt(row, 3))); // 수정: String 값 사용
        getTxtRating().setBounds(150, 270, 200, 30);
        getTxtRating().setEditable(false);
        add(getTxtRating());

        // 작성일
        JLabel labelDate = new JLabel("작성일:");
        labelDate.setBounds(50, 320, 100, 20);
        add(labelDate);

        setTxtDate(new JTextField(String.valueOf((java.sql.Date) model.getValueAt(row, 4))));
        getTxtDate().setBounds(150, 320, 200, 30);
        getTxtDate().setEditable(false);
        add(getTxtDate());

        // 링크
        JLabel labelUrl = new JLabel("링크:");
        labelUrl.setBounds(50, 370, 100, 20);
        add(labelUrl);

        setTxtUrl(new JTextField((String) model.getValueAt(row, 5)));
        getTxtUrl().setBounds(150, 370, 200, 30);
        getTxtUrl().setEditable(false);
        add(getTxtUrl());

        setTxtReviewId(new JTextField(String.valueOf((int) model.getValueAt(row, 6))));
        getTxtReviewId().setBounds(150, 370, 200, 30);
        getTxtReviewId().setEditable(false);
        add(getTxtReviewId());

        // 수정 버튼
        setBtnModify(new JButton("수정"));
        getBtnModify().setBounds(50, 470, 100, 30);
        add(getBtnModify());

        // 저장 버튼
        setBtnSave(new JButton("저장"));
        getBtnSave().setBounds(150, 470, 100, 30);
        getBtnSave().setEnabled(false); // 초기에는 비활성화
        add(getBtnSave());

        // 삭제 버튼
        setBtnDelete(new JButton("삭제"));
        getBtnDelete().setBounds(250, 470, 100, 30);
        add(getBtnDelete());
    }

    public void enableEditing() {
        getTxtContent().setBackground(new Color(255, 255, 255));
        getTxtContent().setEditable(true);
        getTxtRating().setEditable(true);
        getTxtUrl().setEditable(true);
        getBtnModify().setEnabled(false);
        getBtnSave().setEnabled(true);
    }

    public void disableEditing() {
        getTxtContent().setEditable(false);
        getTxtRating().setEditable(false);
        getTxtUrl().setEditable(false);
        getBtnModify().setEnabled(true);
        getBtnSave().setEnabled(false);
    }

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JTextField getTxtReviewId() {
		return txtReviewId;
	}

	public void setTxtReviewId(JTextField txtReviewId) {
		this.txtReviewId = txtReviewId;
	}

	public JTextArea getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(JTextArea txtContent) {
		this.txtContent = txtContent;
	}

	public JTextField getTxtRating() {
		return txtRating;
	}

	public void setTxtRating(JTextField txtRating) {
		this.txtRating = txtRating;
	}

	public JTextField getTxtDate() {
		return txtDate;
	}

	public void setTxtDate(JTextField txtDate) {
		this.txtDate = txtDate;
	}

	public JTextField getTxtUrl() {
		return txtUrl;
	}

	public void setTxtUrl(JTextField txtUrl) {
		this.txtUrl = txtUrl;
	}
}
