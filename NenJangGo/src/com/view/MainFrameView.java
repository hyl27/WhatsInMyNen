package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrameView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel cardPanel;
    private JPanel buttonPanel;
    private JButton buttonHome;
    private JButton buttonFindIngredient;
    private JButton buttonFindMenu;
    private JButton buttonWriteReview;
    private JButton buttonMyInfo;

    public MainFrameView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 840 / 12 * 9);
        setLocationRelativeTo(null);

        cardPanel = new JPanel(new CardLayout());
        buttonPanel = new JPanel();

        buttonHome = new JButton("홈");
        buttonFindIngredient = new JButton("재료 찾기");
        buttonFindMenu = new JButton("메뉴 찾기");
        buttonWriteReview = new JButton("후기작성");
        buttonMyInfo = new JButton("내 정보");
     

        buttonPanel.add(buttonHome);
        buttonPanel.add(buttonFindIngredient);
        buttonPanel.add(buttonFindMenu);
        buttonPanel.add(buttonWriteReview);
        buttonPanel.add(buttonMyInfo);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
      
        setVisible(true);
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }
//    public void showNenView() {
//        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
//        cardLayout.show(cardPanel, "NenView");
//    }
//    
//    public void showIngredientView() {
//        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
//        cardLayout.show(cardPanel, "IngredientView");
//    }

    public JButton getButtonHome() {
        return buttonHome;
    }

    public JButton getButtonFindIngredient() {
        return buttonFindIngredient;
    }

    public JButton getButtonFindMenu() {
        return buttonFindMenu;
    }

    public JButton getButtonWriteReview() {
        return buttonWriteReview;
    }

    public JButton getButtonMyInfo() {
        return buttonMyInfo;
    }
    
    public void showPanel(String panelName) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, panelName);
    }
}
