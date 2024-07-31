package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.main.LoginInfoManager;
import com.model.dao.IngredientDAO;
import com.model.dao.MemberDAO;
import com.model.dao.MynenDAO;
import com.model.dao.menuDAO;
import com.view.IngredientView;
import com.view.MemberJoinView;
import com.view.MemberLoginView;
import com.view.NenView;
import com.view.MainFrameView;

public class MemberLoginController {
	private MemberLoginView view;
	private MemberDAO memberDAO;

	public MemberLoginController(MemberLoginView view, MemberDAO memberDAO) {
		this.view = view;
		this.memberDAO = memberDAO;

		// 회원가입 버튼에 대한 액션 리스너 추가
		this.view.getJoinBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openMemberJoinView();
			}
		});

		// 로그인 버튼에 대한 액션 리스너 추가
		this.view.getLoginBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}

	// 회원가입 창을 열고 로그인 창을 숨기는 메서드
	private void openMemberJoinView() {
		MemberJoinView joinFrame = new MemberJoinView();
		joinFrame.setVisible(true);
		MemberJoinController join = new MemberJoinController(joinFrame, memberDAO);
		view.setVisible(false);
	}

	// 로그인 처리를 수행하는 메서드
	private void login() {
		String memberid = view.getTfUserid().getText();
		String userpass = view.getTfUserpw().getText();

		try {
			Map<String, String> loginInfo = new HashMap<>();
			loginInfo.put("userid", memberid);
			loginInfo.put("userpw", userpass);
			System.out.println("입력한 id : " + loginInfo);

			String loginStatus = memberDAO.login_m(memberid, userpass);

			// 로그인 성공 시 처리
			if (loginStatus.equals("로그인 성공")) {
				handleSuccessfulLogin(loginInfo);
			} else {
				handleFailedLogin();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			handleLoginError();
		}
	}

	// 로그인 성공 처리를 수행하는 메서드
	private void handleSuccessfulLogin(Map<String, String> loginInfo) {
		JOptionPane.showMessageDialog(null, "안녕하세요 반갑습니다 :)");
		LoginInfoManager.getInstance().setLoginInfo(loginInfo);

		// main_frame_view 초기화 및 보이기
		MainFrameView mainview = new MainFrameView();
		NenView nenview = new NenView();
		MynenDAO nendao = new MynenDAO();

		// main_controller 및 NenController 초기화
		MainFrameController mainc = new MainFrameController(mainview);
		NenController nenc = new NenController(nenview, nendao);

        mainview.setVisible(true);
		nenview.setVisible(true);
		mainview.getCardPanel().add(nenview, "NenView");
		
		
		// 현재 로그인 창 숨기기
		view.setVisible(false);
	}

	// 로그인 실패 처리를 수행하는 메서드
	private void handleFailedLogin() {
		JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요");
	}

	// 로그인 중 오류 처리를 수행하는 메서드
	private void handleLoginError() {
		JOptionPane.showMessageDialog(null, "로그인 중 오류가 발생했습니다.");
	}
}
