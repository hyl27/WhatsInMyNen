package main;

import java.sql.SQLException;

import com.dao.MemberDAO;
import com.vo.MemberVO;

public class main_test {

		public static void main(String[] args) throws SQLException {
	        MemberDAO memberDao = new MemberDAO();

//	        // 회원가입 테스트
//	        MemberVO newMember = new MemberVO("hhh","1111","박일일", "f","2020-04-04","parkh1@naver.com");
//	        int isRegistered = memberDao.insert_m(newMember);
//	        if (isRegistered==-1) {
//	            System.out.println("회원가입 성공!");
//	        } else {
//	            System.out.println("회원가입 실패!");
//	        }

	        // 로그인 테스트
//	        String loginUsername = "hyl";
//	        String loginPassword = "222";
//	        boolean isLoggedIn = memberDao.login_m(loginUsername, loginPassword);
//	        if (isLoggedIn) {
//	            System.out.println("로그인 성공!");
//	        } else {
//	            System.out.println("로그인 실패!");
//	        }
	        
	        
	    }

	}


