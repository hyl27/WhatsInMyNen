package mainFram.front;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class LoginInfoManager {

	    private static LoginInfoManager instance;
	    private Map<String, String> loginInfo;

	    // private 생성자로 외부에서 인스턴스를 직접 생성하는 것을 방지
	    private LoginInfoManager() {
	        loginInfo = new HashMap<>();
	    }

	    // 인스턴스를 가져오는 정적 메서드
	    public static synchronized LoginInfoManager getInstance() {
	        if (instance == null) {
	            instance = new LoginInfoManager();
	        }
	        return instance;
	    }

	    // 로그인 정보 설정 메서드
	    public void setLoginInfo(Map<String, String> loginInfo) {
	        this.loginInfo = loginInfo;
	        System.out.println("LoginInfoManager: 로그인 정보가 설정되었습니다: " + loginInfo);
	    }

	    // 로그인 정보 가져오는 메서드
	    public Map<String, String> getLoginInfo() {
	        System.out.println("LoginInfoManager: 로그인 정보를 가져옵니다: " + loginInfo);
	        return loginInfo;
	    }
	}

