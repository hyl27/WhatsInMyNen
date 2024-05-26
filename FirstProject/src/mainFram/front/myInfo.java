package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	private Map<String, String> loginInfo;

	public myInfo() {
	        JLabel lblNewLabel = new JLabel("마이페이지!");
	        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
	        add(lblNewLabel, BorderLayout.CENTER); // add() 메서드 사용하여 JLabel을 패널에 추가
	    }

	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}
}
