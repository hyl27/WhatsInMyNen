package com.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class MemberJoinView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblJoin;
    private JButton joinCompleteBtn;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfBdate;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;

    public MemberJoinView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 840 / 12 * 9); // 창사이즈
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblJoin = new JLabel("회원가입");
        Font f1 = new Font("돋움", Font.BOLD, 20); // 궁서 바탕 돋움
        lblJoin.setFont(f1);
        lblJoin.setBounds(364, 126, 101, 20);
        contentPane.add(lblJoin);

        JLabel label = new JLabel("아이디");
        label.setBounds(258, 195, 69, 20);
        contentPane.add(label);

        JLabel lblUsername = new JLabel("비밀번호");
        lblUsername.setBounds(258, 245, 69, 20);
        contentPane.add(lblUsername);

        JLabel lblName = new JLabel("이름");
        lblName.setBounds(258, 292, 69, 20);
        contentPane.add(lblName);

        JLabel lblEmail = new JLabel("이메일");
        lblEmail.setBounds(258, 339, 69, 20);
        contentPane.add(lblEmail);

        JLabel lblPhone = new JLabel("생년월일");
        lblPhone.setBounds(258, 386, 69, 20);
        contentPane.add(lblPhone);

        tfUsername = new JTextField();
        tfUsername.setColumns(10);
        tfUsername.setBounds(348, 188, 186, 35);
        contentPane.add(tfUsername);

        tfPassword = new JTextField();
        tfPassword.setColumns(10);
        tfPassword.setBounds(348, 238, 186, 35);
        contentPane.add(tfPassword);

        tfName = new JTextField();
        tfName.setColumns(10);
        tfName.setBounds(348, 285, 186, 35);
        contentPane.add(tfName);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(348, 332, 186, 35);
        contentPane.add(tfEmail);

        tfBdate = new JTextField();
        tfBdate.setColumns(10);
        tfBdate.setBounds(348, 379, 186, 35);
        contentPane.add(tfBdate);

        maleCheckBox = new JCheckBox("남자");
        maleCheckBox.setBounds(348, 426, 80, 20);
        contentPane.add(maleCheckBox);

        femaleCheckBox = new JCheckBox("여자");
        femaleCheckBox.setBounds(430, 426, 80, 20);
        contentPane.add(femaleCheckBox);

        joinCompleteBtn = new JButton("회원가입완료");
        joinCompleteBtn.setBounds(395, 472, 139, 29);
        contentPane.add(joinCompleteBtn);

        setVisible(true);
    }

    public JButton getJoinCompleteBtn() {
        return joinCompleteBtn;
    }

    public JTextField getTfUsername() {
        return tfUsername;
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
}

