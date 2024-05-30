package mainFram.front;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.ReviewDAO;
import com.vo.MemberVO;
import com.vo.ReviewVO;

import mainFram.front.LoginInfoManager;

public class review extends JPanel {

    private static final long serialVersionUID = 1L;
    private Map<String, String> loginInfo;
    private String memberId;
    private JTextField txtMenuId;
    private JTextField txtMenuurl;
    private JTextArea txtContent;
    private CardLayout innerCardLayout;
    private JPanel innerCardPanel;

    java.util.Date today = new java.util.Date(); // 현재 날짜를 java.util.Date 객체로 가져옵니다.
    java.sql.Date sqlDate = new java.sql.Date(today.getTime()); // java.util.Date 객체를 java.sql.Date 객체로 변환합니다.
    private JTable reviewTable;

    public void start() {// 로그인정보
        Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
        memberId = loginInfo.get("userid");
        System.out.println(memberId);
    }

    public review() {
        start();// 로그인정보 리뷰에 가져옴
        setLayout(null);

        // 내부 카드 패널 생성
        innerCardLayout = new CardLayout();
        innerCardPanel = new JPanel(innerCardLayout);
        add(innerCardPanel); // innerCardPanel을 review JPanel에 추가
        innerCardPanel.setBounds(0, 0, 450, 300); // innerCardPanel의 위치 및 크기 설정

        // 내부 첫 번째 카드 (리뷰 작성)
        JPanel innerFirstCard = createReviewInputPanel();
        innerCardPanel.add(innerFirstCard, "Write Review"); // "Write Review"라는 이름으로 카드 추가
        innerCardLayout.show(innerCardPanel, "Write Review"); // 처음에 "Write Review" 카드 표시

        // 내부 두 번째 카드 (리뷰 목록)
        JPanel innerSecondCard = createReviewListPanel();
        innerCardPanel.add(innerSecondCard, "Review List"); // "Review List"라는 이름으로 카드 추가

        // 리뷰 목록 버튼 클릭 이벤트
        JButton innerButton1 = new JButton("리뷰 목록");
        innerButton1.setBounds(244, 193, 116, 23);
        innerFirstCard.add(innerButton1);
        innerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("리뷰리스트버튼 클릭");
                innerCardLayout.show(innerCardPanel, "Review List"); // "Review List" 카드 표시
            }
        });

        // 리뷰 작성 버튼 클릭 이벤트
        JButton innerButton2 = new JButton("리뷰 작성");
        innerButton2.setBounds(244, 193, 116, 23);
        innerSecondCard.add(innerButton2);
        innerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("리뷰 작성 버튼 클릭");
                innerCardLayout.show(innerCardPanel, "Write Review"); // "Write Review" 카드 표시
            }
        });

    }// review

    private JPanel createReviewInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.PINK);
        inputPanel.setBounds(0, 51, 450, 226);
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
        });// btninsert

        return inputPanel;
    }

    private JPanel createReviewListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.LIGHT_GRAY);
        listPanel.setLayout(null);

        reviewTable = new JTable();
        reviewTable.setModel(
                new DefaultTableModel(new Object[][] {}, new String[] { "메뉴 ID", "작성자", "내용", "별점", "작성일", "링크" }));
        JScrollPane scrollPane = new JScrollPane(reviewTable);
        scrollPane.setBounds(10, 10, 470, 240);
        listPanel.add(scrollPane); // JPanel에 추가

        // 리뷰 목록 가져오기
        try {
            ReviewDAO reviewDAO = new ReviewDAO(); // ReviewDAO 객체 생성
            List<ReviewVO> reviewList = reviewDAO.selectList(memberId); // selectList 메서드 호출

            // 테이블 모델 업데이트
            DefaultTableModel model = (DefaultTableModel) reviewTable.getModel();
            model.setRowCount(0);
            for (ReviewVO review : reviewList) {
                model.addRow(new Object[] { review.getMenuId(), review.getMemberId(), review.getContent(),
                        review.getRating(), review.getDate(), review.getUrl() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listPanel;
    }

    private void insertReview() {
        MemberVO mvo = new MemberVO();
        try {
            System.out.println("리뷰의 로그인정보" + memberId);
            // 1. 입력 필드에서 데이터 가져오기
            int menuId = Integer.parseInt(txtMenuId.getText());
            String content = txtContent.getText();
            String url = txtMenuurl.getText();

            // 2. ReviewVO 객체 생성 및 데이터 설정
            ReviewVO rvo = new ReviewVO();
            rvo.setContent(content);
            rvo.setDate(sqlDate);
            rvo.setRating(5); // rating 설정 방법 개선 필요
            rvo.setUrl(url); // url 설정
            // 4. ReviewVO 객체에 memberId 설정
            rvo.setMemberId(memberId);

            // 5. ReviewDAO 객체 생성
            ReviewDAO reviewDAO = new ReviewDAO();

            // 6. 리뷰 삽입 메서드 호출 - memberId와 menuId 값을 전달
            reviewDAO.insert_review(memberId, menuId, rvo);

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