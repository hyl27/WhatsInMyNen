package com.view;

import javax.swing.*;
import java.awt.*;

public class MyInfoView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel lblinfo;
    private JTextField tfId;
    private JTextField tfPassword;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfBdate;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;
    private JButton modifyCompleteBtn;
    private JButton deleteMemberBtn;

    public MyInfoView() {
        initialize();
    }

    private void initialize() {
        setLayout(null);

        lblinfo = new JLabel("회원 정보 수정");
        Font f1 = new Font("돋움", Font.BOLD, 20);
        lblinfo.setFont(f1);
        lblinfo.setBounds(364, 126, 207, 20);
        add(lblinfo);

        JLabel label = new JLabel("아이디");
        label.setBounds(258, 195, 69, 20);
        add(label);

        JLabel lblUsername = new JLabel("비밀번호");
        lblUsername.setBounds(258, 245, 69, 20);
        add(lblUsername);

        JLabel lblName = new JLabel("이름");
        lblName.setBounds(258, 292, 69, 20);
        add(lblName);

        JLabel lblEmail = new JLabel("이메일");
        lblEmail.setBounds(258, 339, 69, 20);
        add(lblEmail);

        JLabel lblPhone = new JLabel("생년월일");
        lblPhone.setBounds(258, 386, 69, 20);
        add(lblPhone);

        tfId = new JTextField();
        tfId.setColumns(10);
        tfId.setBounds(348, 188, 186, 35);
        add(tfId);

        tfPassword = new JTextField();
        tfPassword.setColumns(10);
        tfPassword.setBounds(348, 238, 186, 35);
        add(tfPassword);

        tfName = new JTextField();
        tfName.setColumns(10);
        tfName.setBounds(348, 285, 186, 35);
        add(tfName);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(348, 332, 186, 35);
        add(tfEmail);

        tfBdate = new JTextField();
        tfBdate.setColumns(10);
        tfBdate.setBounds(348, 379, 186, 35);
        add(tfBdate);

        maleCheckBox = new JCheckBox("남자");
        maleCheckBox.setBounds(348, 426, 80, 20);
        add(maleCheckBox);

        femaleCheckBox = new JCheckBox("여자");
        femaleCheckBox.setBounds(430, 426, 80, 20);
        add(femaleCheckBox);

        modifyCompleteBtn = new JButton("회원정보수정");
        modifyCompleteBtn.setBounds(371, 452, 139, 35);
        add(modifyCompleteBtn);

        deleteMemberBtn = new JButton("회원탈퇴");
        deleteMemberBtn.setBounds(27, 10, 139, 35);
        add(deleteMemberBtn);
    }

    // Getter methods for controller to access view components
    public JTextField getTfId() {
        return tfId;
    }

    public JTextField getTfPassword() {
        return tfPassword;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JTextField getTfBdate() {
        return tfBdate;
    }

    public JCheckBox getMaleCheckBox() {
        return maleCheckBox;
    }

    public JCheckBox getFemaleCheckBox() {
        return femaleCheckBox;
    }

    public JButton getModifyCompleteBtn() {
        return modifyCompleteBtn;
    }

    public JButton getDeleteMemberBtn() {
        return deleteMemberBtn;
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
