package com.controller.review;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.model.dao.ReviewDAO;
import com.model.vo.ReviewVO;
import com.view.review.ReviewDetailView;


public class ReviewDetailController {
    private ReviewDetailView dview;
    private DefaultTableModel model;
    private int row;

    public ReviewDetailController(ReviewDetailView dview, DefaultTableModel model, int row) {
        this.dview = dview;
        this.model = model;
        this.row = row;

        init();
    }

    private void init() {
        dview.getBtnModify().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dview.enableEditing();
            }
        });

        dview.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 수정된 리뷰 데이터 가져오기
                    String content = dview.getTxtContent().getText();
                    int rating = dview.getTxtRating().getText().replace("★", "").length();
                    java.sql.Date date = java.sql.Date.valueOf(dview.getTxtDate().getText());
                    String url = dview.getTxtUrl().getText();
                    int reviewId = Integer.parseInt(dview.getTxtReviewId().getText());

                    // 리뷰 데이터 업데이트
                    ReviewVO rvo = new ReviewVO();
                    rvo.setContent(content);
                    rvo.setRating(rating);
                    rvo.setDate(date);
                    rvo.setUrl(url);

                    ReviewDAO reviewDAO = new ReviewDAO();
                    reviewDAO.update_review(reviewId, rvo);

                    // 업데이트된 내용을 모델에 반영
                    model.setValueAt(content, row, 2);
                    model.setValueAt(rating, row, 3);
                    model.setValueAt(date, row, 4);
                    model.setValueAt(url, row, 5);

                    dview.disableEditing();
                    JOptionPane.showMessageDialog(null, "수정된 내용이 저장되었습니다.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "리뷰 수정 중 오류가 발생했습니다.");
                }
            }
        });

        dview.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "정말로 이 리뷰를 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int reviewId = Integer.parseInt(dview.getTxtReviewId().getText());
                        ReviewDAO reviewDAO = new ReviewDAO();
                        reviewDAO.delete_Review(reviewId);

                        model.removeRow(row);
                        JOptionPane.showMessageDialog(null, "리뷰가 삭제되었습니다.");
                        dview.getParent().getParent().getParent().getParent().setVisible(false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "리뷰 삭제 중 오류가 발생했습니다.");
                    }
                }
            }
        });
    }
}
