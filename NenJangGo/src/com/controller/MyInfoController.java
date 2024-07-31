package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JOptionPane;

import com.main.LoginInfoManager;
import com.model.dao.MemberDAO;
import com.model.vo.MemberVO;
import com.view.MainFrameView;
import com.view.MyInfoView;

public class MyInfoController {
    private MyInfoView view;
    private MemberDAO memberDAO;
    private MemberVO memberVO;
    private String memberId;

    public MyInfoController(MyInfoView view) {
        this.view = view;
        this.memberDAO = new MemberDAO();
        initialize();
    }

    private void initialize() {
        Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
        memberId = loginInfo.get("userid");

        // 회원 정보를 불러와서 뷰에 설정
        memberVO = memberDAO.getInfo(memberId);
        view.getTfId().setText(memberVO.getId());
        view.getTfId().setEditable(false);
        view.getTfPassword().setText(memberVO.getPw());
        view.getTfName().setText(memberVO.getName());
        view.getTfName().setEditable(false);
        view.getTfEmail().setText(memberVO.getemail());
        view.getTfBdate().setText(memberVO.getbdate());
        view.getTfBdate().setEditable(false);

        int gender = memberVO.getgender();
        if (gender == 'm') {
            view.getMaleCheckBox().setSelected(true);
            view.getMaleCheckBox().setEnabled(false);
        } else if (gender == 'f') {
            view.getFemaleCheckBox().setSelected(true);
            view.getFemaleCheckBox().setEnabled(false);
        }

        view.getModifyCompleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMemberInfo();
            }
        });

        view.getDeleteMemberBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });
    }

    private void updateMemberInfo() {
        String userid = view.getTfId().getText();
        String password = view.getTfPassword().getText();
        String name = view.getTfName().getText();
        String email = view.getTfEmail().getText();
        String bdate = view.getTfBdate().getText();
        char gender = view.getMaleCheckBox().isSelected() ? 'm' : (view.getFemaleCheckBox().isSelected() ? 'f' : 0);

        memberVO.setId(userid);
        memberVO.setPw(password);
        memberVO.setName(name);
        memberVO.setemail(email);
        memberVO.setbdate(bdate);
        memberVO.setgender(gender);

        if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || bdate.isEmpty()) {
            view.showMessage("모든 항목을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String updateStatus = memberDAO.update_m(memberVO);
            if (updateStatus.equals("정보가 성공적으로 업데이트되었습니다.")) {
                view.showMessage("회원 정보를 수정했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage(updateStatus, "에러", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMember() {
        try {
            String deleteStatus = memberDAO.delete_m(memberId);
            if (deleteStatus.equals("탈퇴 처리 되었습니다.")) {
                view.showMessage("탈퇴 완료 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                view.showMessage(deleteStatus, "에러", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
