package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.model.dao.MemberDAO;
import com.model.vo.MemberVO;
import com.view.MemberJoinView;
import com.view.MemberLoginView;

public class MemberJoinController {
    private MemberJoinView view;
    private MemberDAO memberDAO;

    public MemberJoinController(MemberJoinView view, MemberDAO memberDAO) {
        this.view = view;
        this.memberDAO = memberDAO;

        // 회원가입 완료 버튼 클릭 시
        this.view.getJoinCompleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 각 입력 필드의 값을 가져옴
                String userid = view.getTfUsername().getText();
                String password = view.getTfPassword().getText();
                String name = view.getTfName().getText();
                String email = view.getTfEmail().getText();
                String bdate = view.getTfBdate().getText();
                char gender = 0;

                // 성별 체크박스 선택 여부에 따라 gender 변수 설정
                if (view.getMaleCheckBox().isSelected()) {
                    view.getFemaleCheckBox().setSelected(false);
                    gender = 'm'; // 남자일 경우 m로 설정
                } else if (view.getFemaleCheckBox().isSelected()) {
                    view.getMaleCheckBox().setSelected(false);
                    gender = 'f'; // 여자일 경우 f로 설정
                }

                MemberVO memberVO = new MemberVO();
                memberVO.setId(userid);
                memberVO.setPw(password);
                memberVO.setName(name);
                memberVO.setemail(email);
                memberVO.setbdate(bdate);
                memberVO.setgender(gender);

                // 모든 필드가 채워져 있는지 확인
                if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || bdate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 항목을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                    return; // 필수 입력 항목이 비어있으면 메소드 종료
                }

                // MemberDAO를 사용하여 회원을 추가
                try {
                    String joinStatus = memberDAO.insert_m(memberVO);

                    // 회원가입 상태에 따라 처리
                    if (joinStatus.equals("회원가입 성공!")) {
                        JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                        view.setVisible(false); // 회원가입 뷰 숨기기
                        MemberLoginView login = new MemberLoginView();
                        login.setVisible(true);
                        MemberLoginController loginc = new MemberLoginController(login, memberDAO);
                    } else {
                        JOptionPane.showMessageDialog(null, joinStatus, "에러", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
