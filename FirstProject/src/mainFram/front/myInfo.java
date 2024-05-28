package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class myInfo extends JPanel {
    private static final long serialVersionUID = 1L;
    private String memberId;
    private JLabel lblNewLabel; // JLabel 변수 추가

    public void setLoginInfo(Map<String, String> loginInfo) {
        this.memberId = loginInfo.get("username");
        System.out.println("마이페이지 로그인정보: " + loginInfo); // 출력문 추가
        if (memberId != null) {
            lblNewLabel.setText("어서오세요, " + memberId + "님!");
        } else {
            lblNewLabel.setText("회원 정보를 불러올 수 없습니다.");
        }
    }
    
    public myInfo() {
        // JPanel의 레이아웃을 BorderLayout으로 설정
        setLayout(new BorderLayout());

        // JLabel 초기화 및 설정
        lblNewLabel = new JLabel("마이페이지!");
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
        add(lblNewLabel, BorderLayout.CENTER);
    }

}
