package com.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.main.LoginInfoManager;
import com.model.dao.IngredientDAO;
import com.model.dao.MynenDAO;
import com.model.dao.menuDAO;
import com.model.vo.MynenVO;
import com.view.IngredientView;
import com.view.NenView;
import com.view.MainFrameView;

public class NenController {
    private NenView nenView;
    private MynenDAO dao;
    private String memberId;
    private IngredientView inview;
    private JPanel cardPanel;

    public NenController(NenView nenView, MynenDAO dao) {
        this.nenView = nenView;
        this.dao = dao;
        start();
        initializeView();
        addListeners();
    }

    private void start() {
        memberId = LoginInfoManager.getInstance().getLoginInfo().get("userid");
        System.out.println(memberId);
    }

    private void initializeView() {
        try {
            List<Integer> existingNenIds = dao.view_nen(memberId);
            for (int nenId : existingNenIds) {
                createNenButton(nenId);
            }
            nenView.repaintContent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        nenView.getCreateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nenView.getNenButtons().size() < NenView.MAX_NEN_BUTTONS) {
                    insertNen();
                } else {
                    JOptionPane.showMessageDialog(null, "최대 냉장고 개수에 도달했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        nenView.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNen();
            }
        });

        for (int i = 0; i < nenView.getNenButtons().size(); i++) {
            JButton nenButton = nenView.getNenButtons().get(i);
            int index = i;
            nenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int nenId = Integer.parseInt(nenButton.getText().replace("냉장고 ", ""));
                    LoginInfoManager.getInstance().setNenId(nenId);
                    MainFrameView mainview = (MainFrameView) SwingUtilities.getWindowAncestor(nenView);
            		
                	IngredientView ingview = new IngredientView();
            		IngredientDAO ingdao = new IngredientDAO();
            		menuDAO menudao = new menuDAO();
            		IngredientController ingc = new IngredientController(ingview, ingdao, menudao);
            		mainview.getCardPanel().add(ingview, "IngredientView"); // cardPanel.add(card2, "Card2");
            		mainview.showPanel("IngredientView"); // CardLayout에 IngredientView cardPanel show
                	
                   
                }
            });
        }

    }

    private void insertNen() {
        MynenVO nvo = new MynenVO();
        nvo.setM_id(memberId);
        try {
            dao.insert_n(nvo);
            createNenButton(nvo.getId());
            nenView.repaintContent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteNen() {
        for (int i = 0; i < nenView.getNenCheckBoxes().size(); i++) {
            JCheckBox nenCheckBox = nenView.getNenCheckBoxes().get(i);
            if (nenCheckBox.isSelected()) {
                int nenId = Integer.parseInt(nenView.getNenButtons().get(i).getText().replace("냉장고 ", ""));
                MynenVO nvoToDelete = new MynenVO();
                nvoToDelete.setM_id(memberId);
                nvoToDelete.setId(nenId);
                try {
                    dao.delete_n(nvoToDelete);
                    deleteNenButton(i);
                    i--; // Adjust index after removing the button
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        nenView.repaintContent();
    }

    private void createNenButton(int nenId) {
        JButton nenButton = new JButton("냉장고 " + nenId);
        nenButton.setIcon(new ImageIcon("images/냉장고움직임.gif")); // 이미지 경로에 맞게 변경
        nenButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nenButton.setBounds(nenView.getNextButtonX(), 80, 200, 200);
        nenButton.setBackground(new Color(251, 248, 221));
        nenButton.setBorder(BorderFactory.createLineBorder(new Color(251, 248, 221)));
        nenButton.setFocusPainted(false);

        // 체크박스 생성
        JCheckBox nenCheckBox = new JCheckBox();
        nenCheckBox.setBounds(nenView.getNextButtonX(), 50, 200, 20);
        nenCheckBox.setBackground(new Color(251, 248, 221));

        // view에 버튼 체크박스 생성
        nenView.createNenButton(nenButton, nenCheckBox);

    }

    private void deleteNenButton(int index) {
        nenView.deleteNenButton(index);
    }
}
