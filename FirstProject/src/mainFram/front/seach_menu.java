package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class seach_menu extends JPanel {

	private String memberId;
	public int nenId;
	public List<String> menus;

	public seach_menu() {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "데이터를 가져오는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}

		// 패널 설정
		setLayout(new BorderLayout());
		setBackground(Color.WHITE); // 배경색 설정

		// 화면 제목 레이블 생성 및 설정
		JLabel titleLabel = new JLabel("메뉴 보기", SwingConstants.CENTER);
		titleLabel.setFont(new Font("굴림", Font.BOLD, 50));
		add(titleLabel, BorderLayout.NORTH);

		// JList 생성
		JList<String> menuList;
		if (menus.isEmpty()) { // 만약 메뉴 리스트가 비어있으면
			menuList = new JList<>(new String[] { "메뉴가 없습니다." });
		} else { // 메뉴 리스트가 비어있지 않으면
			menuList = new JList<>(menus.toArray(new String[0]));
		}

		// 셀 렌더러를 사용하여 글꼴 크기 및 정렬 변경
		menuList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				renderer.setFont(new Font("굴림", Font.PLAIN, 30)); // 글꼴 크기 설정
				renderer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
				return renderer;
			}
		});
		menuList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedMenu = menuList.getSelectedValue();
                    if (selectedMenu != null) {
                        try {
                            openWebpage("https://www.youtube.com/results?search_query=" + selectedMenu);
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(seach_menu.this, "유효하지 않은 URL입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(seach_menu.this, "선택한 메뉴가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

		// 패널을 스크롤 가능하도록 스크롤 패널에 추가
		JScrollPane scrollPane = new JScrollPane(menuList);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		// 빈 공간을 위한 빈 JPanel 생성 및 추가
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(50, 0)); // 옆면 비움
		add(emptyPanel, BorderLayout.WEST);
		
	
	}
	private void openWebpage(String urlString) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(urlString));
    }
	

	private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		nenId = LoginInfoManager.getInstance().getNenId();
		menus = LoginInfoManager.getInstance().getMenu();
	}
}
