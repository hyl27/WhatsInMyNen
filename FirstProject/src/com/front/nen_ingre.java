package com.front;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class nen_ingre extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPanel textPanel;

    public nen_ingre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(840, 840 / 12 * 9);// 창사이즈
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("n번 냉장고");
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel.setBounds(12, 8, 166, 37);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("냉장고 변경");
        btnNewButton.setBounds(679, 77, 135, 23);
        contentPane.add(btnNewButton);// 버튼사이즈

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(12, 102, 802, 419);
        contentPane.add(panel);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textField = new JTextField(20);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addWord(textField.getText());
                    textField.setText("");
                }
            }
        });
        inputPanel.add(textField);

        JButton addButton = new JButton("추가");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWord(textField.getText());
                textField.setText("");
            }
        });
        inputPanel.add(addButton);
        panel.add(inputPanel, BorderLayout.NORTH);
        
        JButton addButton_1 = new JButton("메뉴 검색");
        inputPanel.add(addButton_1);
        addButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });

               
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPane = new JScrollPane(textPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnNewButton_1 = new JButton("메뉴 후기");
        btnNewButton_1.setBounds(623, 531, 189, 37);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("메뉴 찾기");
        btnNewButton_2.setBounds(420, 531, 189, 37);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("내 정보");
        btnNewButton_3.setBounds(14, 531, 189, 37);
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("식재료");
        btnNewButton_4.setBounds(217, 531, 189, 37);
        contentPane.add(btnNewButton_4);
        
        JLabel lblNewLabel_1 = new JLabel("재료를 추가하세요");
        lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(12, 71, 299, 30);
        contentPane.add(lblNewLabel_1);
    }

    private void addWord(String word) {
        if (!word.isEmpty()) {
            JPanel wordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            JLabel wordLabel = new JLabel(word + " ");
            JButton deleteButton = new JButton("x");
            deleteButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textPanel.remove(wordPanel);
                    textPanel.revalidate();
                    textPanel.repaint();
                }
            });

            wordLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        String newWord = JOptionPane.showInputDialog("단어 수정", wordLabel.getText().trim());
                        if (newWord != null && !newWord.isEmpty()) {
                            wordLabel.setText(newWord + " ");
                        }
                    }
                }
            });

            wordPanel.add(wordLabel);
            wordPanel.add(deleteButton);
            textPanel.add(wordPanel);
            textPanel.revalidate();
            textPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                nen_ingre nen_ingre = new nen_ingre();
                nen_ingre.setVisible(true);
            }
        });
    }
}
