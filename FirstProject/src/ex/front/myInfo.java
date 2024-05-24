package ex.front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class myInfo extends JPanel {
	
    private static final long serialVersionUID = 1L;
    private Map<String, String> loginInfo; 

    public myInfo() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("냉장고 선택");
        lblNewLabel.setBounds(12, 8, 166, 37);
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(12, 102, 802, 419);
        add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton = new JButton("my 냉장고 생성");
        btnNewButton.setBounds(567, 10, 223, 23);
        panel.add(btnNewButton);
        
        JLabel lblNewLabel1 = new JLabel("냉장고를 선택하세요");
        lblNewLabel1.setBounds(12, 7, 269, 29);
        panel.add(lblNewLabel1);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 수행할 작업
            }
        });
    }

    public void setLoginInfo(Map<String, String> loginInfo) {
        this.loginInfo = loginInfo;
    }
}
