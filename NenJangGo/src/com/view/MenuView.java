package com.view;

import java.awt.*;
import javax.swing.*;

public class MenuView extends JPanel {
    private JLabel titleLabel;
    private JList<String> menuList;
    private JScrollPane scrollPane;

    public MenuView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        titleLabel = new JLabel("메뉴 보기", SwingConstants.CENTER);
        titleLabel.setFont(new Font("LG Smart UI Regular", Font.BOLD, 50));
        add(titleLabel, BorderLayout.NORTH);

        menuList = new JList<>();
        menuList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setFont(new Font("LG Smart UI Regular", Font.PLAIN, 30));
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                return renderer;
            }
        });

        scrollPane = new JScrollPane(menuList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(50, 0));
        add(emptyPanel, BorderLayout.WEST);
    }

    public JList<String> getMenuList() {
        return menuList;
    }
}
