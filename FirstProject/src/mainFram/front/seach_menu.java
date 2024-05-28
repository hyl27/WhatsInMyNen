package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class seach_menu extends JPanel { // seach_menu 클래스가 JPanel을 상속하도록 변경
    private static final long serialVersionUID = 1L;
    private String memberId;
    
    public void setLoginInfo(Map<String, String> loginInfo) {
        this.memberId = loginInfo.get("username");
        System.out.println("메뉴파트 로그인정보: " + loginInfo); // 출력문 추가
    } 

    public seach_menu() {
        JLabel lblNewLabel = new JLabel("메뉴찾기!");
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
        add(lblNewLabel, BorderLayout.CENTER); // add() 메서드 사용하여 JLabel을 패널에 추가
    }

}
	

