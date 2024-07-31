package com.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NenView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private List<JButton> nenButtons;
    private List<JCheckBox> nenCheckBoxes ;
    public static final int MAX_NEN_BUTTONS = 3;

    private JButton createButton; // 생성 버튼
    private JButton deleteButton; // 삭제 버튼

    public NenView() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("What's In My NenJangGo");
        lblNewLabel.setBounds(192, 37, 569, 37);
        lblNewLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
        add(lblNewLabel);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(251, 248, 221));
        contentPane.setBounds(12, 139, 802, 323);
        contentPane.setLayout(null);
        add(contentPane);

        createButton = new JButton("my 냉장고 생성");
        createButton.setBounds(567, 10, 223, 23);
        contentPane.add(createButton);

        deleteButton = new JButton("선택한 냉장고 삭제");
        deleteButton.setBounds(300, 10, 223, 23);
        contentPane.add(deleteButton);

        JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
        lblNewLabel1.setFont(new Font("LG Smart UI Regular", Font.PLAIN, 20));
        lblNewLabel1.setBounds(328, 88, 160, 29);
        add(lblNewLabel1);
        
        nenButtons = new ArrayList<>();
        nenCheckBoxes = new ArrayList<>();
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setNenButtons(List<JButton> nenButtons) {
        this.nenButtons = nenButtons;
    }

    public void setNenCheckBoxes(List<JCheckBox> nenCheckBoxes) {
        this.nenCheckBoxes = nenCheckBoxes;
    }

    public List<JButton> getNenButtons() {
        return nenButtons;
    }

    public List<JCheckBox> getNenCheckBoxes() {
        return nenCheckBoxes;
    }

    public void createNenButton(JButton nenButton, JCheckBox nenCheckBox) {
        contentPane.add(nenCheckBox);
        contentPane.add(nenButton);
        nenButtons.add(nenButton);
        nenCheckBoxes.add(nenCheckBox);
    }

    public void deleteNenButton(int index) {
        contentPane.remove(nenCheckBoxes.get(index));
        contentPane.remove(nenButtons.get(index));
        nenCheckBoxes.remove(index);
        nenButtons.remove(index);
    }

    public void repaintContent() {
        contentPane.revalidate();
        contentPane.repaint();
    }

    public int getNextButtonX() {
        return 50 + (nenButtons != null ? nenButtons.size() * 250 : 0);
    }
}
