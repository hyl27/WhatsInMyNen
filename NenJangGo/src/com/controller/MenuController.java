package com.controller;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.main.LoginInfoManager;
import com.model.dao.menuDAO;
import com.view.MenuView;

public class MenuController {
    private MenuView view;
    private menuDAO menudao;
    private String memberId;
	public int nenId;
	public List<String> menus;

    public MenuController(MenuView view, menuDAO model) {
        this.view = view;
        this.menudao = model;
        initialize();
    }

    private void initialize() {
    	start();
        if (menus.isEmpty()) {
            view.getMenuList().setListData(new String[]{"메뉴가 없습니다."});
        } else {
            view.getMenuList().setListData(menus.toArray(new String[0]));
        }

        view.getMenuList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedMenu = view.getMenuList().getSelectedValue();
                    if (selectedMenu != null) {
                        try {
                            openWebpage("https://www.youtube.com/results?search_query=" + selectedMenu);
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(view, "유효하지 않은 URL입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "선택한 메뉴가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void openWebpage(String urlString) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(urlString));
    }
    
    private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		nenId = LoginInfoManager.getInstance().getNenId();
		menus = LoginInfoManager.getInstance().getMenu();
	}
}
