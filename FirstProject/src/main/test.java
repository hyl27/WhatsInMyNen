package main;

import java.sql.SQLException;

import com.dao.IngredientDAO;
import com.dao.menuDAO;
import com.vo.IngredientVO;

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
		
		menuDAO mdao = new menuDAO();
		
		try {
			String result = mdao.search("스팸");
      } catch (SQLException e) {
          e.printStackTrace();
      }


	}

}
