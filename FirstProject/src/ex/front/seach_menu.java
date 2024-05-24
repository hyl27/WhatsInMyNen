package ex.front;

import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class seach_menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> loginInfo;
	
	public seach_menu() {
		
		JLabel lblNewLabel = new JLabel("메뉴찾기!");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
	}

    public void setLoginInfo(Map<String, String> loginInfo) {
        this.loginInfo = loginInfo;
    }
}