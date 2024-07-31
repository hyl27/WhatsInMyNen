package com.view.review;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.model.dao.ReviewDAO;

public class MenuSearchView extends JDialog {
    private JTextField searchField;
    private JButton searchButton;
    private JList<String> menuList;
    private DefaultListModel<String> listModel;
    private String selectedMenuId = null;
    private ReviewDAO reviewDAO;

    public MenuSearchView(JFrame parent) {
        super(parent, "메뉴 검색", true);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);

        reviewDAO = new ReviewDAO();

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchButton = new JButton("검색");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        listModel = new DefaultListModel<>();
        menuList = new JList<>(listModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 리스트 마우스 클릭 이벤트
        menuList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = menuList.locationToIndex(evt.getPoint());
                if (index != -1) {
                    selectedMenuId = listModel.getElementAt(index);
                    dispose();
                }
            }
        });

        add(searchPanel, BorderLayout.NORTH); // 검색 패널 위치 (위쪽)
        add(new JScrollPane(menuList), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchMenu(); // 검색 버튼 클릭 시 메서드 호출
            }
        });

        // 검색 필드에서 엔터 키 입력 처리
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchMenu(); // 엔터 키 입력 시 검색 메서드 호출
                }
            }
        });
    }

    // 메뉴 검색
    public void searchMenu() {
        String keyword = searchField.getText();
        try {
            List<String> menuList = reviewDAO.search_mm(keyword);
            listModel.clear();
            for (String menu : menuList) {
                listModel.addElement(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JList<String> getMenuList() {
        return menuList;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public String getSelectedMenuId() {
        return selectedMenuId;
    }

    public void setSelectedMenuId(String selectedMenuId) {
        this.selectedMenuId = selectedMenuId;
    }
}
