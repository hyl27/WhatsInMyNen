package com.main;

import com.controller.MemberLoginController;
import com.model.dao.MemberDAO;
import com.model.dao.MynenDAO;
import com.view.MemberLoginView;
import com.view.NenView;

public class Main {
    public static void main(String[] args) {
    	
        // 로그인 뷰 초기화 및 컨트롤러 연결
        MemberLoginView loginView = new MemberLoginView();
        MemberLoginController loginController = new MemberLoginController(loginView, new MemberDAO());
       
    }
}
