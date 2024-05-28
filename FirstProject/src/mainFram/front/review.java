package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dao.ReviewDAO;
import com.vo.MemberVO;
import com.vo.ReviewVO;

public class review extends JPanel {

    private static final long serialVersionUID = 1L;
    private Map<String, String> loginInfo;
    private String memberId;
    private JTextField txtMenuId;
    private JTextArea txtContent;
    
    public void setLoginInfo(Map<String, String> loginInfo) {
        this.loginInfo = loginInfo; // loginInfo 맵 저장
        this.memberId = loginInfo.get("username"); 
        System.out.println("리뷰 로그인정보: " + loginInfo); 
    }
    
    public review() {
        setLayout(new BorderLayout()); // BorderLayout으로 설정

        JLabel lblNewLabel = new JLabel("후기쓰기!");
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
        add(lblNewLabel, BorderLayout.NORTH); // JLabel을 상단에 배치

        JPanel inputPanel = new JPanel();
        add(inputPanel, BorderLayout.CENTER); // 입력 필드를 중앙에 배치

        inputPanel.add(new JLabel("메뉴 ID:"));
        txtMenuId = new JTextField(10);
        inputPanel.add(txtMenuId);

        inputPanel.add(new JLabel("내용:"));
        txtContent = new JTextArea(5, 20);
        inputPanel.add(txtContent);

        JButton btnInsert = new JButton("리뷰 등록");
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertReview();
            }
        });
        add(btnInsert, BorderLayout.SOUTH); // 버튼을 하단에 배치
    }

    private void insertReview() {
        MemberVO mvo = new MemberVO();
        mvo.setId(loginInfo.get("memberId")); // loginInfo 맵에서 memberId 가져오기
        try {
        	
        	System.out.println("리뷰의 로그인정보"+memberId);
            // 1. 입력 필드에서 데이터 가져오기
            int menuId = Integer.parseInt(txtMenuId.getText());
            String content = txtContent.getText();

            // 2. ReviewVO 객체 생성 및 데이터 설정
            ReviewVO rvo = new ReviewVO();
            rvo.setR_id(1); // r_id 설정 방법 확인 필요
            rvo.setContent(content);
            rvo.setDate("2024-05-27"); // 날짜 설정 방법 개선 필요
            rvo.setRating(5); // rating 설정 방법 개선 필요
            rvo.setUrl("https://example.com"); // url 설정 방법 개선 필요

            // 3. MemberVO 객체 생성 및 데이터 설정 (로그인 정보 활용)
            mvo.setId(loginInfo.get("memberId")); // loginInfo에서 memberId 가져오도록 수정

            // 4. ReviewVO 객체에 memberId 설정
            rvo.setMemberId(mvo.getId());

            // 5. ReviewDAO 객체 생성
            ReviewDAO reviewDAO = new ReviewDAO();

            // 6. 리뷰 삽입 메서드 호출 - memberId와 menuId 값을 전달
            reviewDAO.insert_review(mvo.getId(), menuId, rvo);

            // 7. 성공 메시지 출력 또는 예외 처리
            System.out.println("리뷰가 성공적으로 삽입되었습니다.");
            // 입력 필드 초기화
            txtMenuId.setText("");
            txtContent.setText("");
        } catch (NumberFormatException ex) {
            // 잘못된 메뉴 ID 형식 처리
            System.err.println("잘못된 메뉴 ID 형식입니다.");
            // 오류 메시지 표시 등
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            // 오류 메시지 표시 등
        }
    }

}