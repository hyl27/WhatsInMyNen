package com.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.dao.MynenDAO;
import com.vo.MynenVO;

import mainFram.front.LoginInfoManager;
import mainFram.front.seach_ingri;

public class nen_select extends JPanel {
    private static final long serialVersionUID = 1L;
    private MynenVO nvo = new MynenVO();
    private MynenDAO dao = new MynenDAO();
    private String memberId;
    // nen_id 추가
    private int nenId;

    private JPanel contentPane;
    private List<JButton> nenButtons;
    private List<JCheckBox> nenCheckBoxes;
    private static final int MAX_NEN_BUTTONS = 3;

    public void start() {
        Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
        memberId = loginInfo.get("userid");
        System.out.println(memberId);
    }

    public nen_select() {
        start();

        setLayout(null);
        nenButtons = new ArrayList<>();
        nenCheckBoxes = new ArrayList<>();

        JLabel lblNewLabel = new JLabel("냉장고 선택");
        lblNewLabel.setBounds(12, 8, 166, 37);
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        add(lblNewLabel);

        contentPane = new JPanel();
        contentPane.setBounds(12, 102, 802, 419);
        contentPane.setLayout(null);
        add(contentPane);

        JButton btnNewButton = new JButton("my 냉장고 생성");
        btnNewButton.setBounds(567, 10, 223, 23);
        contentPane.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nenButtons.size() < MAX_NEN_BUTTONS) {
                    insertNen();
                } else {
                    JOptionPane.showMessageDialog(null, "최대 냉장고 개수에 도달했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton btnDeleteButton = new JButton("선택한 냉장고 삭제");
        btnDeleteButton.setBounds(300, 10, 223, 23);
        contentPane.add(btnDeleteButton);

        JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
        lblNewLabel1.setBounds(22, 55, 269, 29);
        add(lblNewLabel1);

        btnDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNen();
            }
        });

        addExistingNenButtons();
    }
    
    private void addExistingNenButtons() {
        try {
            List<Integer> existingNenIds = dao.view_nen(memberId);

            for (int nenId : existingNenIds) {
                addNenButton(nenId);
            }

            contentPane.revalidate();
            contentPane.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void addNenButton(int nenId) {
        JCheckBox nenCheckBox = new JCheckBox();
        nenCheckBox.setBounds(getNextButtonX(), 10, 200, 20);
        contentPane.add(nenCheckBox);
        nenCheckBoxes.add(nenCheckBox);

        JButton nenButton = new JButton("냉장고 " + nenId);
        nenButton.setBounds(getNextButtonX(), 50, 200, 380);
        nenButton.setBackground(Color.WHITE);
        nenButton.setFocusPainted(false);

        nenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LoginInfoManager.getInstance().setNenId(nenId); // nen_id 가져오기
                main_frame mainFrame = (main_frame) SwingUtilities.getWindowAncestor(contentPane);
                JPanel cardPanel = mainFrame.getCardPanel();
                cardPanel.add(new seach_ingri(), "seach_ingri");
                mainFrame.showPanel("seach_ingri");
                
            }
        });

        contentPane.add(nenButton);
        nenButtons.add(nenButton);
    }
    
    private void insertNen() {
        try {
            nvo.setM_id(memberId);
            dao.insert_n(nvo);
            System.out.println(nvo.getM_id());

            addNenButton(nvo.getId());

            contentPane.revalidate();
            contentPane.repaint();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
    private void deleteNen() {
        try {
            for (int i = 0; i < nenCheckBoxes.size(); i++) {
                if (nenCheckBoxes.get(i).isSelected()) {
                    int nenId = Integer.parseInt(nenButtons.get(i).getText().replace("냉장고 ", ""));

                    MynenVO nvoToDelete = new MynenVO();
                    nvoToDelete.setM_id(memberId);
                    nvoToDelete.setId(nenId);

                    dao.delete_n(nvoToDelete);

                    contentPane.remove(nenCheckBoxes.get(i));
                    contentPane.remove(nenButtons.get(i));

                    nenCheckBoxes.remove(i);
                    nenButtons.remove(i);

                    i--;
                }
            }

            contentPane.revalidate();
            contentPane.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextButtonX() {
        return 30 + nenButtons.size() * 250;
    }
}
