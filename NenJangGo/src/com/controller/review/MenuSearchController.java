package com.controller.review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.view.review.MenuSearchView;

public class MenuSearchController {
    private MenuSearchView view;

    public MenuSearchController(MenuSearchView view) {
        this.view = view;
        
        initController();
    }

    private void initController() {
        view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.searchMenu(); // 검색 버튼 클릭 시 메서드 호출
            }
        });

        view.getSearchField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.searchMenu(); // 엔터 키 입력 시 검색 메서드 호출
                }
            }
        });

        view.getMenuList().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = view.getMenuList().locationToIndex(evt.getPoint());
                if (index != -1) {
                    view.setSelectedMenuId(view.getListModel().getElementAt(index));
                    view.dispose();
                }
            }
        });
    }

    public String getSelectedMenuId() {
        return view.getSelectedMenuId();
    }
}
