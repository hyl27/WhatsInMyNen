package com.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

import com.controller.review.ReviewViewController;
import com.main.LoginInfoManager;
import com.model.dao.IngredientDAO;
import com.model.dao.MynenDAO;
import com.model.dao.ReviewDAO;
import com.model.dao.menuDAO;
import com.view.IngredientView;
import com.view.MenuView;
import com.view.MyInfoView;
import com.view.NenView;
import com.view.review.ReviewView;
import com.view.MainFrameView;

public class MainFrameController {
	private MainFrameView view;
	private String memberId;
	public int nenId;

	public MainFrameController(MainFrameView view) {
		start();

		this.view = view;


		// 버튼에 액션 리스너 추가
		view.getButtonHome().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NenView nenview = new NenView();
				MynenDAO nendao = new MynenDAO();
				NenController nenc = new NenController(nenview, nendao);
				view.getCardPanel().add(nenview, "NenView"); // cardPanel.add(card1, "Card1");
				view.showPanel("NenView"); // CardLayout에 NenView cardPanel show

			}
		});


		// 하단 냉장고 재료 버튼
		view.getButtonFindIngredient().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IngredientView ingview = new IngredientView();
					IngredientDAO ingdao = new IngredientDAO();
					menuDAO menudao = new menuDAO();
					IngredientController ingc = new IngredientController(ingview, ingdao, menudao);
					view.getCardPanel().add(ingview, "IngredientView"); // cardPanel.add(card2, "Card2");
					view.showPanel("IngredientView"); // CardLayout에 IngredientView cardPanel show
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "냉장고를 먼저 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 하단 메뉴 찾기 버튼
		view.getButtonFindMenu().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MenuView menuview = new MenuView();
					menuDAO menudao = new menuDAO();
					MenuController menuc = new MenuController(menuview, menudao);
					view.getCardPanel().add(menuview, "MenuView");
					view.showPanel("MenuView");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "먼저, 재료를 선택해주세요.", "메뉴를 찾을 수 없습니다.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 하단 리뷰 버튼 누르면 이동
		view.getButtonWriteReview().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 리뷰 작성 화면 추가
				ReviewView review = new ReviewView();
				ReviewDAO reviewdao = new ReviewDAO();
				ReviewViewController reviewc = new ReviewViewController(review);
				view.getCardPanel().add(review, "review");
				view.showPanel("review");
			}
		});

		// 하단 내 정보
		view.getButtonMyInfo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 내 정보 화면 추가
				MyInfoView myInfoView = new MyInfoView();
				MyInfoController myInfoc = new MyInfoController(myInfoView);
				view.getCardPanel().add(myInfoView, "myInfo");
				view.showPanel("myInfo");
			}
		});

	}

	private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		nenId = LoginInfoManager.getInstance().getNenId();
	}

}
