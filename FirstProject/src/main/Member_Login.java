package main;

import java.sql.SQLException;

import com.dao.MemberDAO;
import com.dao.ReviewDAO;
import com.vo.MemberVO;
import com.vo.ReviewVO;

public class Member_Login {

	public static void main(String[] args) throws SQLException {
		MemberDAO memberDao = new MemberDAO();
		

		try {
			// ReviewVO 객체 생성 및 데이터 설정
			ReviewVO rvo = new ReviewVO();
			MemberVO mvo = new MemberVO();

			rvo.setR_id(1);
			rvo.setContent("Great food!");
			rvo.setDate("2024-05-27");
			rvo.setRating(5);
			// MemberVO 객체 생성 및 데이터 설정
			mvo.setId("d"); // 회원 ID 설정
			// 리뷰 삽입 메뉴 아이디 문자열 설정
			int menuId = 128671;
			// menuId를 문자열로 설정
			rvo.setUrl("https://example.com");

			// ReviewVO 객체에 memberId 설정
			rvo.setMemberId(mvo.getId()); // memberId 값 설정
			

			// ReviewDAO 객체 생성
			ReviewDAO reviewDAO = new ReviewDAO();

			// 리뷰 삽입 메서드 호출 - memberId와 menuId 값을 전달
			reviewDAO.insert_review(mvo.getId(), menuId, rvo);

			// 성공적으로 삽입되었음을 출력
			System.out.println("리뷰가 성공적으로 삽입되었습니다.");

		} catch (Exception e) {
			// 오류 발생 시 오류 메시지 출력
			e.printStackTrace();
		}
	}
}// class
