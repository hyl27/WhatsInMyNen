package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginInfoManager {

    private static LoginInfoManager instance;
    private Map<String, String> loginInfo;
    private int nen_id; // nen_id를 저장할 필드 추가
    private List<String> menus ;

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
        System.out.println("InfoManager: 로그인 정보가 설정되었습니다: " + loginInfo);
    }

    // 로그인 정보 가져오는 메서드
    public Map<String, String> getLoginInfo() {
        System.out.println("InfoManager: 로그인 정보를 가져옵니다: " + loginInfo);
        return loginInfo;
    }
    
    // nen_id 설정 메서드
    public void setNenId(int nen_id) {
        this.nen_id = nen_id;
        System.out.println("InfoManager: nen_id가 설정되었습니다: " + nen_id);
    }

    // nen_id 가져오는 메서드
    public int getNenId() {
        System.out.println("InfoManager: nen_id를 가져옵니다: " + nen_id);
        return nen_id;
    }
    
 // 메뉴들 설정 메서드
    public void setMenu(List<String> menus) {
        this.menus = menus;
        System.out.println("InfoManager: 메뉴가 설정되었습니다" );
    }

    // 메뉴들 가져오는 메서드
    public List<String> getMenu() {
        System.out.println("InfoManager: 메뉴를 가져옵니다");
        return menus;
    }
}
