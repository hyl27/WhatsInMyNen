package com.controller.review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.main.LoginInfoManager;
import com.model.dao.ReviewDAO;
import com.model.vo.ReviewVO;
import com.view.review.MenuSearchView;
import com.view.review.ReviewDetailView;
import com.view.review.ReviewView;

public class ReviewViewController {
    private ReviewView view;
    private String memberId;
    private int rating = 0;
    private ReviewDetailView dview;
    private DefaultTableModel model;
    private int row;

    public ReviewViewController(ReviewView view) {
        this.view = view;

        init();
    }

    private void init() {
        // 로그인한 사용자의 아이디를 LoginInfoManager에서 가져옴
        memberId = LoginInfoManager.getInstance().getLoginInfo().get("userid");

        // 리뷰 등록 버튼에 액션리스너 추가
        view.getBtnInsert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 선택된 메뉴 아이디를 가져옴
                String selectedMenuId = view.getTxtMenuId().getText();
                System.out.println("선택 한 메뉴: " + selectedMenuId);
                insertReview(selectedMenuId); // 리뷰를 등록 메서드 호출
            }
        });

        // 리뷰 목록 보기 버튼에 액션 리스너 추가
        view.getInnerButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("리뷰리스트버튼 클릭");
                loadReviews(); // 리뷰 목록을 로드하여 테이블에 표시
                view.getInnerCardLayout().show(view.getInnerCardPanel(), "Review List");
            }
        });

        // 닫기 버튼에 액션 리스너 추가
        view.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReviews(); // 리뷰 목록을 로드하여 테이블에 표시
                view.getInnerCardLayout().show(view.getInnerCardPanel(), "Write Review");
            }
        });

        // 메뉴 ID 필드 클릭 시 메뉴 검색 다이얼로그 표시
        view.getTxtMenuId().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // 텍스트입력창 한번클릭시 팝업
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
                    MenuSearchView dialog = new MenuSearchView(parentFrame);
                    dialog.setVisible(true);
                    String selectedMenuId = dialog.getSelectedMenuId();
                    if (selectedMenuId != null) {
                        view.getTxtMenuId().setText(selectedMenuId);
                    }
                }
            }
        });

        // 리뷰 테이블에서 더블 클릭 시 리뷰 상세 정보 표시
        view.getReviewTable().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = view.getReviewTable().getSelectedRow();
                    System.out.println("로우아이" + row);
                    if (row != -1) {
                        showReviewDetails(row);
                    }
                }
            }
        });

        // 초기 로드 시 리뷰 목록을 표시하도록 설정 (필요시 주석 해제)
        // loadReviews();

        // 별점 버튼들에 액션 리스너 추가
        JButton[] starButtons = view.getStarButtons();
        for (int i = 0; i < starButtons.length; i++) {
            final int index = i;
            starButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rating = index + 1;
                    updateStarDisplay(); // 별점표시업데이트
                }
            });
        }
    }

    // 리뷰작성시 별점 표시 업데이트
    private void updateStarDisplay() {
        JButton[] starButtons = view.getStarButtons();
        for (int i = 0; i < starButtons.length; i++) {
            if (i < rating) {
                starButtons[i].setText("★");
            } else {
                starButtons[i].setText("☆");
            }
        }
    }

    // 리뷰등록
    private void insertReview(String selectedMenuId) {
        try {
            System.out.println("리뷰의 로그인정보" + memberId);

            String content = view.getTxtContent().getText();
            String url = view.getTxtMenuurl().getText();

            // 날짜
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());

            ReviewVO rvo = new ReviewVO();
            rvo.setContent(content);
            rvo.setDate(sqlDate);
            rvo.setRating(rating); // 선택된 별점 값을 설정
            rvo.setUrl(url);
            rvo.setMemberId(memberId);

            ReviewDAO reviewDAO = new ReviewDAO();
            reviewDAO.insert_review(memberId, selectedMenuId, rvo);

            view.getTxtContent().setText("");
            view.getTxtMenuurl().setText("");
            rating = 0;
            updateStarDisplay();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 리뷰 상세 정보 표시 메서드
    private void showReviewDetails(int row) {
        JFrame detailFrame = new JFrame("리뷰 상세 정보");
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailFrame.setSize(400, 600);
        detailFrame.setLocationRelativeTo(null);

        ReviewDetailView detailDialog = new ReviewDetailView((DefaultTableModel) view.getReviewTable().getModel(),
                row);
        ReviewDetailController detailController = new ReviewDetailController(detailDialog,
                (DefaultTableModel) view.getReviewTable().getModel(), row);

        detailFrame.getContentPane().add(detailDialog);
        detailFrame.setVisible(true);
    }

    // 리뷰 목록 로드 메서드
    private void loadReviews() {
        ReviewDAO reviewDAO = new ReviewDAO();
        try {
            System.out.println("loadReivews호출됨");
            List<ReviewVO> reviewList = reviewDAO.selectList(memberId);
            DefaultTableModel tableModel = (DefaultTableModel) view.getReviewTable().getModel();
            tableModel.setRowCount(0);

            for (ReviewVO review : reviewList) {
                // 별점을 별 모양(★)으로 변환하여 표시
                String starRating = getStarRatingString(review.getRating());
                // 테이블에 데이터 추가
                tableModel.addRow(
                        new Object[] { review.getMenuName(), review.getMemberId(), review.getContent(), starRating, // 별점
                                                                                                                // ★
                                                                                                                // 표시로
                                                                                                                // 변경
                                review.getDate(), review.getUrl(), review.getR_id() });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 별점을 별 모양(★)으로 변환하는 유틸리티 메서드 추가
    private String getStarRatingString(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }
}
