package ex.front;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutExample {
   public static void main(String[] args) {
      // 메인 프레임 생성
      JFrame mainFrame = new JFrame("CardLayout Example");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setSize(840, 840 / 12 * 9); // 창사이즈

      // CardLayout을 사용할 패널 생성
      JPanel cardPanel = new JPanel(new CardLayout());

      // 메인 화면 패널
      JPanel panel1 = new JPanel();
      panel1.setBackground(Color.WHITE);
      panel1.add(new JLabel("This is the main panel"));
      
      // DynamicPanel 생성
      myInfo myinfo = new myInfo();
      seach_menu sm = new seach_menu();

      // 패널들을 CardLayout 패널에 추가
      //내정보 화면
      cardPanel.add(panel1, "Panel 1");//"Panel 1"이라는 이름으로 cardPanel에 추가
      cardPanel.add(myinfo, "DynamicPanel");//"DynamicPanel"이라는 이름으로 cardPanel에 추가

//      cardPanel.add(panel1, "Panel 2");
//      cardPanel.add(sm, "seach_menu");
      // 버튼 패널 생성
      JPanel buttonPanel = new JPanel();

      // 내정보 버튼 생성 및 액션 리스너 추가
      JButton button1 = new JButton("내 정보");
      button1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "DynamicPanel");
         }
      });
//      JButton button2 = new JButton("메뉴찾기");
//      button2.addActionListener(new ActionListener() {
//    	  public void actionPerformed(ActionEvent e) {
//    		  CardLayout cl = (CardLayout) cardPanel.getLayout();
//    		  cl.show(cardPanel, "DynamicPanel");
//    	  }
//      });

      // 버튼들을 버튼 패널에 추가
      buttonPanel.add(button1);

      // 메인 프레임에 버튼 패널과 카드 패널 추가
      mainFrame.setLayout(new BorderLayout());
      mainFrame.add(buttonPanel, BorderLayout.SOUTH);
      mainFrame.add(cardPanel, BorderLayout.CENTER);

      // 메인 프레임을 화면에 표시
      mainFrame.setVisible(true);
   }
}
