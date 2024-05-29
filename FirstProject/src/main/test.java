package main;

import java.sql.SQLException;
import java.util.List;

import com.dao.MynenDAO;

public class test {

	public static void main(String[] args) {
		// IngredientVO ivo = new IngredientVO("양배추");
//		 IngredientDAO idao = new IngredientDAO();
//		try {
//            String result = idao.insert_i("hyl",1,"스팸");
//            System.out.println("결과: " + result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//		
		
//		menuDAO mdao = new menuDAO();
//		
//		try {
//			String result = mdao.search("스팸");
//      } catch (SQLException e) {
//          e.printStackTrace();
//      }
		
		String memberId = "hgd"; // 여기에 회원의 ID를 넣어주세요

        MynenDAO dao = new MynenDAO();
//        try {
//            List<String> nenIds = dao.view_nen(memberId);
//            System.out.println("회원의 냉장고 ID 목록:");
//            for (String nenId : nenIds) {
//                System.out.println(nenId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


	}

}
