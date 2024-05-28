package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
	private JTextField txtMenuurl;
	private JTextArea txtContent;

	java.util.Date today = new java.util.Date(); // 현재 날짜를 java.util.Date 객체로 가져옵니다.
	java.sql.Date sqlDate = new java.sql.Date(today.getTime()); // java.util.Date 객체를 java.sql.Date 객체로 변환합니다.

	public void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo; // loginInfo 맵 저장
		this.memberId = loginInfo.get("userid");
		System.out.println("리뷰 로그인정보: " + loginInfo);
	}

	public review() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("후기쓰기!");
		lblNewLabel.setBounds(0, 0, 450, 51);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 44));
		add(lblNewLabel); // JLabel을 상단에 배치

		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(0, 51, 450, 226);
		add(inputPanel); // 입력 필드를 중앙에 배치
		inputPanel.setLayout(null);

		JLabel label = new JLabel("링크:");
		label.setBounds(23, 49, 41, 15);
		inputPanel.add(label);
		txtMenuurl = new JTextField(30);
		txtMenuurl.setBounds(86, 46, 336, 21);
		inputPanel.add(txtMenuurl);

		JLabel label_1 = new JLabel("메뉴 ID:");
		label_1.setBounds(23, 21, 55, 15);
		inputPanel.add(label_1);
		txtMenuId = new JTextField(10);
		txtMenuId.setBounds(86, 18, 116, 21);
		inputPanel.add(txtMenuId);

		JLabel label_2 = new JLabel("내용:");
		label_2.setBounds(23, 74, 41, 15);
		inputPanel.add(label_2);
		txtContent = new JTextArea(5, 20);
		txtContent.setBounds(86, 74, 336, 63);
		inputPanel.add(txtContent);

		JLabel label_3 = new JLabel("별점:");
		label_3.setBounds(23, 151, 46, 15);
		inputPanel.add(label_3);

		JButton btnInsert = new JButton("리뷰 등록");
		btnInsert.setBounds(116, 193, 116, 23);
		inputPanel.add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertReview();
			}
		});

	}

	private void insertReview() {
		MemberVO mvo = new MemberVO();
		mvo.setId(loginInfo.get("userid")); // loginInfo 맵에서 memberId 가져오기
		try {
			// 1. 입력 필드에서 데이터 가져오기
			int menuId = Integer.parseInt(txtMenuId.getText());
			String content = txtContent.getText();
			String url = txtMenuurl.getText();

			// 2. ReviewVO 객체 생성 및 데이터 설정
			ReviewVO rvo = new ReviewVO();
			rvo.setContent(content);
			rvo.setDate("2024-05-28");
			rvo.setRating(5); // rating 설정 방법 개선 필요
			rvo.setUrl(url); // url 설정
			// 3. MemberVO 객체 생성 및 데이터 설정 (로그인 정보 활용)
			mvo.setId(loginInfo.get("userid")); // loginInfo에서 memberId 가져오도록 수정

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
			txtMenuurl.setText("");
		} catch (NumberFormatException ex) {
			// 잘못된 메뉴 ID 형식 처리
			System.err.println("잘못된 메뉴 ID 형식입니다.");
			// 오류 메시지 표시 등
		} catch (Exception e) {
			// 기타 예외 처리
			e.printStackTrace();
			// 오류 메시지 표시 등
		}
	}// insertReview

}// class