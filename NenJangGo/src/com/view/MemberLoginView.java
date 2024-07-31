package com.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MemberLoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUserid, tfUserpw;
    private JButton loginBtn, joinBtn;

    public MemberLoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 840 / 12 * 9); // 창사이즈
        setLocationRelativeTo(null); // 배치위치
        contentPane = new JPanel(); // 패널설정
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("images/회원가입.gif");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(100, 100, 300, 400);
        contentPane.add(imageLabel);

        JLabel lblLogin = new JLabel("아이디");
        lblLogin.setBounds(511, 265, 69, 35);
        contentPane.add(lblLogin);

        JLabel lblPassword = new JLabel("비밀번호");
        lblPassword.setBounds(510, 314, 69, 35);
        contentPane.add(lblPassword);

        tfUserid = new JTextField();
        tfUserid.setBounds(579, 265, 176, 35);
        contentPane.add(tfUserid);
        tfUserid.setColumns(10);

        joinBtn = new JButton("회원가입");
        joinBtn.setBounds(514, 383, 104, 29);
        contentPane.add(joinBtn);

        loginBtn = new JButton("로그인");
        loginBtn.setBounds(630, 383, 106, 29);
        contentPane.add(loginBtn);

        tfUserpw = new JPasswordField();
        tfUserpw.setColumns(10);
        tfUserpw.setBounds(579, 316, 176, 35);
        contentPane.add(tfUserpw);

        JLabel lblNewLabel = new JLabel("What's in my NenJangGo");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel.setBounds(514, 193, 241, 46);
        contentPane.add(lblNewLabel);

        setVisible(true);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getJoinBtn() {
        return joinBtn;
    }

    public JTextField getTfUserid() {
        return tfUserid;
    }

    public JTextField getTfUserpw() {
        return tfUserpw;
    }
    
//    public MemberLoginView getMemberLoginView() {
//        return this.memberLoginView;
//    }
//
//    public void setMemberLoginView(MemberLoginView memberLoginView) {
//        this.memberLoginView = memberLoginView;
//    }
}

